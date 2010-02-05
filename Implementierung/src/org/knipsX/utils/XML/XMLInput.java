package org.knipsX.utils.XML;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureNotFoundException;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.model.reportmanagement.WilcoxonTestType;
import org.knipsX.utils.ExifParameter;

/**
 * This class reads a project file and returns the information.
 */
public class XMLInput {

    private Element project;
    private final Logger log = Logger.getLogger(this.getClass());

    public XMLInput(final File xml) {
        final SAXBuilder saxBuilder = new SAXBuilder();

        try {
            final Document docXml = saxBuilder.build(xml);
            this.project = docXml.getRootElement();
        } catch (final JDOMException e) {
            this.log.error("[constructor()] - " + e.getStackTrace());
        } catch (final IOException e) {
            this.log.error("[constructor()] - " + e.getStackTrace());
        }
    }

    public ProjectModel getProject(final int projectId) {
        ProjectModel project = null;

        /* check if we have the right project */
        if (projectId == this.getId()) {
            final String name = this.getName();
            final String description = this.getDescription();
            final GregorianCalendar creationDate = this.getCreationDate();
            final List<PictureSet> pictureSets = this.getPictureSets();
            final List<AbstractReportModel> reports = this.getReports(pictureSets);

            project = new ProjectModel(projectId, name, description, creationDate, pictureSets, reports);
        }
        return project;
    }

    /*
     * #########################################################
     * META DATA
     * #########################################################
     */

    private int getId() {
        return Integer.parseInt(this.project.getChildText("id"));
    }

    private String getName() {
        return this.project.getChildText("name");
    }

    private String getDescription() {
        return this.project.getChildText("description");
    }

    private GregorianCalendar getCreationDate() {
        try {
            return XMLInput.parseTimestamp(this.project.getChildText("creationDate"));
        } catch (final ParseException e) {
            this.log.error("[XMLInput::getCreationDate()] - " + e.getStackTrace());
            return new GregorianCalendar();
        }
    }

    private static GregorianCalendar parseTimestamp(final String timestamp) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        final Date d = sdf.parse(timestamp);
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal;
    }

    /*
     * #########################################################
     * PICTURE SETS
     * #########################################################
     */

    private List<PictureSet> getPictureSets() {

        /*
         * store all picture sets in a hashmap like:
         * 1 -> picset1
         * 1234 -> picset2
         */
        final Map<Integer, PictureSet> pictureSets = new HashMap<Integer, PictureSet>();
        for (final Element set : XMLHelper.convertList(this.project.getChild("pictureSets").getChildren("pictureSet"))) {

            final int id = Integer.parseInt(set.getChildText("id"));
            final String name = set.getChildText("name");

            pictureSets.put(id, new PictureSet(name, id));
        }

        /*
         * store all directories in a hashmap like:
         * 1 -> dir1
         * 1234 -> dir2
         */
        final Map<Integer, Directory> directories = new HashMap<Integer, Directory>();
        for (final Element set : XMLHelper.convertList(this.project.getChild("directories").getChildren("directory"))) {

            final int id = Integer.parseInt(set.getChildText("id"));
            final String path = set.getChildText("path");

            directories.put(id, new Directory(path));
        }

        /*
         * store all pictures in a hashmap like:
         * 1 -> pic1
         * 1234 -> pic2
         */
        final Map<Integer, Picture> pictures = new HashMap<Integer, Picture>();
        for (final Element picture : XMLHelper.convertList(this.project.getChild("pictures").getChildren("picture"))) {

            final int id = Integer.parseInt(picture.getChildText("id"));
            final String path = picture.getChildText("path");

            try {
                pictures.put(id, new Picture(path, true));
            } catch (final PictureNotFoundException e) {
                this.log.error("[getPictureSets()] - Picture not found -> " + path);
            }

        }

        for (final Element set : XMLHelper.convertList(this.project.getChild("pictureSets").getChildren("pictureSet"))) {
            final int rootId = Integer.parseInt(set.getChildText("id"));

            /* walk through the picture sets an connect them to their children */
            for (final Element container : XMLHelper.convertList(set.getChild("children").getChildren())) {

                if (container.getName() == "pictureSet") {

                    /* get a picture set from the hashmap and connect another to it */
                    pictureSets.get(rootId).add(pictureSets.get(Integer.parseInt(container.getText())));
                } else if (container.getName() == "directory") {

                    /* get a picture set from the hashmap and connect a directory to it */
                    pictureSets.get(rootId).add(directories.get(Integer.parseInt(container.getText())));
                } else if (container.getName() == "picture") {

                    /* get a picture set from the hashmap and connect a picture to it */
                    pictureSets.get(rootId).add(pictures.get(Integer.parseInt(container.getText())));
                }

            }
        }

        /* create the list to return the values */
        final List<PictureSet> returnList = new ArrayList<PictureSet>();
        for (final PictureSet set : pictureSets.values()) {
            returnList.add(set);
        }
        return returnList;
    }

    /*
     * #########################################################
     * REPORTS
     * #########################################################
     */

    private List<AbstractReportModel> getReports(final List<PictureSet> pictureSets) {
        final List<AbstractReportModel> reports = new LinkedList<AbstractReportModel>();

        for (final Element report : XMLHelper.convertList(this.project.getChild("reports").getChildren("report"))) {
            final String type = report.getChildText("type");

            if (type.equals("boxplot")) {
                reports.add(this.getBoxplotReport(pictureSets, report));
            } else if (type.equals("histogram2D")) {
                reports.add(this.getHistogram2DReport(pictureSets, report));
            } else if (type.equals("histogram3D")) {
                reports.add(this.getHistogram3DReport(pictureSets, report));
            } else if (type.equals("cluster3D")) {
                reports.add(this.getCluster3DReport(pictureSets, report));
            } else if (type.equals("table")) {
                reports.add(this.getTableReport(pictureSets, report));
            }
        }
        return reports;
    }

    private <T extends AbstractReportModel> T setupCommonData(final T model, final List<PictureSet> pictureSets,
            final Element report) {

        /* set the meta data */
        model.setReportName(report.getChildText("name"));
        model.setReportDescription(report.getChildText("description"));

        /* connect the model with picture sets */
        for (final Element pictureSet : XMLHelper.convertList(report.getChild("pictureSets").getChildren("pictureSet"))) {
            for (final PictureSet set : pictureSets) {
                if (Integer.parseInt(pictureSet.getText()) == set.getID()) {
                    model.addPictureContainer(set);
                }
            }
        }

        /* extract the keywords */
        final Object exifFilterKeywordsItem = report.getChild("exifFilterKeywords");
        if (exifFilterKeywordsItem instanceof Element) {
            for (final String keyword : this.getExifFilterKeywords((Element) exifFilterKeywordsItem)) {
                model.addExifFilterKeyword(keyword);
            }
        }
        return model;
    }

    private Axis getAxis(final Element axis) {
        final Axis newAxis = new Axis(ExifParameter.values()[Integer.parseInt(axis.getChildText("parameter"))]);
        newAxis.setDescription(axis.getChildText("description"));
        return newAxis;
    }

    private List<String> getExifFilterKeywords(final Element keywords) {
        final List<String> allKeywords = new LinkedList<String>();

        for (final Object item : keywords.getChildren("exifFilterKeyword")) {
            if (item instanceof Element) {
                allKeywords.add(((Element) item).getText());
            }
        }
        return allKeywords;
    }

    private BoxplotModel getBoxplotReport(final List<PictureSet> pictureSets, final Element report) {
        final BoxplotModel model = this.setupCommonData(new BoxplotModel(), pictureSets, report);

        /* extract the axes */
        final Object axisItem = report.getChild("axes").getChild("axis");
        if (axisItem instanceof Element) {
            final Element axis = (Element) axisItem;

            if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("x")) {
                model.setxAxis(this.getAxis(axis));
            }
        }

        /* FIXME set the willcoxon */
        final Object item = report.getChild("wilcoxonTest");
        if (item instanceof Element) {
            final Element wilcoxonTest = (Element) item;
            model.getWilcoxonTest().setActiveStatus(Boolean.parseBoolean(wilcoxonTest.getChildText("isActive")));
            model.getWilcoxonTest()
                    .setWilcoxonTestType(WilcoxonTestType.valueOf(wilcoxonTest.getChildText("testType")));
            model.getWilcoxonTest().setSignificance(Double.parseDouble(wilcoxonTest.getChildText("significance")));

        }
        return model;
    }

    private Histogram2DModel getHistogram2DReport(final List<PictureSet> pictureSets, final Element report) {
        final Histogram2DModel model = this.setupCommonData(new Histogram2DModel(), pictureSets, report);

        /* extract the axes */
        final Object axisItem = report.getChild("axes").getChild("axis");
        if (axisItem instanceof Element) {
            final Element axis = (Element) axisItem;

            if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("x")) {
                model.setxAxis(this.getAxis(axis));
            }
        }
        return model;
    }

    private Histogram3DModel getHistogram3DReport(final List<PictureSet> pictureSets, final Element report) {
        final Histogram3DModel model = this.setupCommonData(new Histogram3DModel(), pictureSets, report);

        /* extract the axes */
        for (final Element axis : XMLHelper.convertList(report.getChild("axes").getChildren("axis"))) {
            if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("x")) {
                model.setxAxis(this.getAxis(axis));
            } else if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("z")) {
                model.setzAxis(this.getAxis(axis));
            }
        }
        return model;
    }

    private Cluster3DModel getCluster3DReport(final List<PictureSet> pictureSets, final Element report) {
        final Cluster3DModel model = this.setupCommonData(new Cluster3DModel(), pictureSets, report);

        /* extract the axes */
        for (final Element axis : XMLHelper.convertList(report.getChild("axes").getChildren("axis"))) {
            if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("x")) {
                model.setxAxis(this.getAxis(axis));
            } else if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("z")) {
                model.setzAxis(this.getAxis(axis));
            } else if (!axis.getChildText("parameter").equals("") && axis.getChildText("type").equalsIgnoreCase("y")) {
                model.setyAxis(this.getAxis(axis));
            }
        }
        return model;
    }

    private TableModel getTableReport(final List<PictureSet> pictureSets, final Element report) {
        return this.setupCommonData(new TableModel(), pictureSets, report);
    }
}
