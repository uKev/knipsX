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
import org.knipsX.model.reportmanagement.WilcoxonTestType;
import org.knipsX.utils.ExifParameter;

/**
 * This class reads a project file and returns the information.
 * 
 */
public class XMLInput {

    private Element project;

    /**
     * Constructs a MyXmlHandler.
     */
    public XMLInput(final File xml) {
        final SAXBuilder saxBuilder = new SAXBuilder();

        Document docXml;
        try {
            docXml = saxBuilder.build(xml);
            this.project = docXml.getRootElement();
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ProjectModel getProject(int projectId) {
        ProjectModel project = null;

        /* check if we have the right project */
        if (projectId == this.getId()) {
            String name = this.getName();
            String description = this.getDescription();
            GregorianCalendar creationDate = this.getCreationDate();
            List<PictureSet> pictureSets = this.getPictureSets();
            List<AbstractReportModel> reports = this.getReports(pictureSets);

            project = new ProjectModel(projectId, name, description, creationDate, pictureSets, reports);
        }
        return project;
    }

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
            return parseTimestamp(this.project.getChildText("creationDate"));
        } catch (ParseException e) {
            e.printStackTrace();
            return new GregorianCalendar();
        }
    }

    private static GregorianCalendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date d = sdf.parse(timestamp);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal;
    }

    private List<PictureSet> getPictureSets() {

        /*
         * store all picture sets in a hashmap like:
         * 1 -> picset1
         * 1234 -> picset2
         */
        Map<Integer, PictureSet> pictureSets = new HashMap<Integer, PictureSet>();
        for (Object item : this.project.getChild("pictureSets").getChildren("pictureSet")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String name = set.getChildText("name");

                pictureSets.put(id, new PictureSet(name, id));
            }
        }

        /*
         * store all directories in a hashmap like:
         * 1 -> dir1
         * 1234 -> dir2
         */
        Map<Integer, Directory> directories = new HashMap<Integer, Directory>();
        for (Object item : this.project.getChild("directories").getChildren("directory")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String path = set.getChildText("path");

                directories.put(id, new Directory(path));
            }
        }

        /*
         * store all pictures in a hashmap like:
         * 1 -> pic1
         * 1234 -> pic2
         */
        Map<Integer, Picture> pictures = new HashMap<Integer, Picture>();
        for (Object item : this.project.getChild("pictures").getChildren("picture")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String path = set.getChildText("path");

                try {
                    pictures.put(id, new Picture(path, true));
                } catch (PictureNotFoundException e) {
                    System.err.println("[XMLInput::getPictureSets()] - Picture not found -> " + path);
                }
            }
        }

        for (Object item : this.project.getChild("pictureSets").getChildren("pictureSet")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int rootId = Integer.parseInt(set.getChildText("id"));

                /* walk through the picture sets an connect them to their children */
                for (Object child : set.getChild("children").getChildren()) {
                    if (child instanceof Element) {
                        Element container = (Element) child;

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
            }
        }

        /* create the list to return the values */
        List<PictureSet> returnList = new ArrayList<PictureSet>();
        for (PictureSet set : pictureSets.values()) {
            returnList.add(set);
        }
        return returnList;
    }

    private List<AbstractReportModel> getReports(List<PictureSet> pictureSets) {
        List<AbstractReportModel> reports = new LinkedList<AbstractReportModel>();

        for (Object item : this.project.getChild("reports").getChildren("report")) {
            if (item instanceof Element) {
                Element report = (Element) item;

                String type = report.getChildText("type");

                if (type.equals("boxplot")) {
                    reports.add(this.getBoxplotReport(pictureSets, report));
                }

            }
        }
        return reports;
    }

    private Axis getAxis(Element axis) {
        Axis newAxis = new Axis(ExifParameter.valueOf(axis.getChildText("parameter")));
        newAxis.setDescription(axis.getChildText("description"));
        return newAxis;
    }

    private List<String> getExifFilterKeywords(Element keywords) {
        List<String> allKeywords = new LinkedList<String>();

        for (Object item : keywords.getChildren("exifFilterKeyword")) {
            if (item instanceof Element) {
                allKeywords.add(((Element) item).getText());
            }
        }
        return allKeywords;
    }

    private BoxplotModel getBoxplotReport(List<PictureSet> pictureSets, Element report) {
        BoxplotModel model = new BoxplotModel();

        /* set the meta data */
        model.setReportName(report.getChildText("name"));
        model.setReportDescription(report.getChildText("description"));

        /* extract the axes */
        Object axisItem = report.getChild("axes").getChild("axis");
        if (axisItem instanceof Element) {
            Element axis = (Element) axisItem;

            if (axis.getChildText("type").equalsIgnoreCase("x")) {
                model.setxAxis(getAxis(axis));
            }
        }

        /* connect the model with picture sets */
        for (Object pictureSetItem : report.getChild("pictureSets").getChildren("pictureSet")) {
            if (pictureSetItem instanceof Element) {
                Element pictureSet = (Element) pictureSetItem;
                for (PictureSet set : pictureSets) {
                    if (Integer.parseInt(pictureSet.getText()) == set.getID()) {
                        model.addPictureContainer(set);
                    }
                }
            }
        }

        /* extract the keywords */
        Object exifFilterKeywordsItem = report.getChild("exifFilterKeywords");
        if (exifFilterKeywordsItem instanceof Element) {
            for (String keyword : this.getExifFilterKeywords((Element) exifFilterKeywordsItem)) {
                model.addExifFilterKeyword(keyword);
            }
        }
        // item = this.project.getChild("wilcoxonTest");
        // if (item instanceof Element) {
        // Element wilcoxonTest = (Element) item;
        // model.getWilcoxonTest().setActiveStatus(Boolean.parseBoolean(wilcoxonTest.getChildText("isActive")));
        // model.getWilcoxonTest().(WilcoxonTestType.valueOf(wilcoxonTest.getChildText("testType")));
        //            
        // }
        return model;
    }
}
