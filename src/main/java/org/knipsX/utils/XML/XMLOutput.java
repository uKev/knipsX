package org.knipsX.utils.XML;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;

/**
 * This class reads a project file and returns the information.
 * 
 */
public class XMLOutput {

    private Document xmlDocument;

    private ProjectModel project;

    private final Logger logger = Logger.getLogger(this.getClass());

    public XMLOutput(final ProjectModel project) {
        this.project = project;

        Element root = new Element("project");

        root.addContent(this.resolveId());
        root.addContent(this.resolveName());
        root.addContent(this.resolveDescription());
        root.addContent(this.resolveCreationDate());
        root.addContent(this.resolvePictureSets());
        root.addContent(this.resolveDirectories());
        root.addContent(this.resolvePictures());
        root.addContent(this.resolveReports());

        this.xmlDocument = new Document(root);
    }

    public Document getDocument() {
        return this.xmlDocument;
    }

    /*
     * #########################################################
     * META DATA
     * #########################################################
     */
    private String convertToUTF8(String old) {

        try {
            return new String(old.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            this.logger.error(e.getMessage());
        }
        return "";
    }

    private Element resolveId() {
        return new Element("id").setText(convertToUTF8("" + this.project.getId()));
    }

    private Element resolveName() {
        return new Element("name").setText(convertToUTF8(this.project.getName()));
    }

    private Element resolveDescription() {
        return new Element("description").setText(convertToUTF8(this.project.getDescription()));
    }

    private Element resolveCreationDate() {
        return new Element("creationDate").setText(convertToUTF8(XMLOutput.parseDate(this.project.getCreationDate())));
    }

    private static String parseDate(GregorianCalendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    /*
     * #########################################################
     * PICTURE SETS
     * #########################################################
     */

    private Element resolvePictureSets() {
        Element pictureSets = new Element("pictureSets");

        for (PictureSet set : this.project.getPictureSets()) {
            pictureSets.addContent(this.resolvePictureSet(set));
        }
        return pictureSets;
    }

    private Element resolvePictureSet(PictureSet set) {
        Element pictureSet = new Element("pictureSet");

        pictureSet.addContent(new Element("id").setText("" + set.hashCode()));
        pictureSet.addContent(new Element("name").setText(set.getName()));
        pictureSet.addContent(this.getChildrenFromPictureSet(set));
        return pictureSet;
    }

    private Element getChildrenFromPictureSet(PictureSet set) {
        Element children = new Element("children");

        for (PictureContainer child : set.getItems()) {
            if (child instanceof PictureSet) {
                children.addContent(new Element("pictureSet").setText("" + child.hashCode()));
            } else if (child instanceof Directory) {
                children.addContent(new Element("directory").setText("" + child.hashCode()));
            } else if (child instanceof Picture) {
                children.addContent(new Element("picture").setText("" + child.hashCode()));
            }
        }
        return children;
    }

    /*
     * #########################################################
     * DIRECTORIES
     * #########################################################
     */

    private Element resolveDirectories() {
        Element directories = new Element("directories");

        for (PictureSet set : this.project.getPictureSets()) {
            for (Directory dir : this.project.getDirectoriesFromPictureSet(set)) {
                directories.addContent(this.resolveDirectory(dir));
            }
        }
        return directories;
    }

    private Element resolveDirectory(Directory dir) {
        Element directory = new Element("directory");

        directory.addContent(new Element("id").setText("" + dir.hashCode()));
        directory.addContent(new Element("path").setText(dir.getPath()));
        return directory;
    }

    /*
     * #########################################################
     * PICTURES
     * #########################################################
     */

    private Element resolvePictures() {
        Element pictures = new Element("pictures");

        for (PictureSet set : this.project.getPictureSets()) {
            for (PictureInterface pic : this.project.getPicturesFromPictureSet(set)) {
                pictures.addContent(this.resolvePicture(pic));
            }
        }
        return pictures;
    }

    private Element resolvePicture(PictureInterface pic) {
        Element picture = new Element("picture");

        picture.addContent(new Element("id").setText("" + pic.hashCode()));
        picture.addContent(new Element("path").setText(pic.getPath()));
        return picture;
    }

    /*
     * #########################################################
     * REPORTS
     * #########################################################
     */

    private Element resolveReports() {
        Element reports = new Element("reports");

        for (AbstractReportModel report : this.project.getReports()) {
            reports.addContent(this.resolveReport(report));
        }
        return reports;
    }

    private Element resolveReport(AbstractReportModel model) {
        Element report = null;

        if (model instanceof BoxplotModel) {
            report = resolveBoxplotReport((BoxplotModel) model);
        } else if (model instanceof Histogram2DModel) {
            report = resolveHistogram2DReport((Histogram2DModel) model);
        } else if (model instanceof Histogram3DModel) {
            report = resolveHistogram3DReport((Histogram3DModel) model);
        } else if (model instanceof Cluster3DModel) {
            report = resolveCluster3DReport((Cluster3DModel) model);
        } else if (model instanceof TableModel) {
            report = resolveTableReport((TableModel) model);
        }
        return report;
    }

    private Element resolveAxis(Axis axis, String axisType) {
        Element elementAxis = new Element("axis");

        elementAxis.addContent(new Element("type").setText(axisType));

        if (axis.getParameter() != null) {
            elementAxis.addContent(new Element("parameter").setText("" + axis.getParameter().ordinal()));
        } else {
            elementAxis.addContent(new Element("parameter"));
        }
        elementAxis.addContent(new Element("description").setText(axis.getDescription()));
        return elementAxis;
    }

    private Element resolveReportPictureSets(List<PictureContainer> container) {
        Element pictureSets = new Element("pictureSets");

        for (PictureContainer set : container) {
            pictureSets.addContent(new Element("pictureSet").setText("" + set.hashCode()));
        }
        return pictureSets;
    }

    private Element resolveReportExifFilterKeywords(String[] exifFilterKeywords) {
        Element pictureSets = new Element("exifFilterKeywords");

        for (String exifFilterKeyword : exifFilterKeywords) {
            pictureSets.addContent(new Element("exifFilterKeyword").setText(exifFilterKeyword));
        }
        return pictureSets;
    }

    private Element resolveWilcoxonTest(BoxplotModel model) {
        Element wilcoxonTest = new Element("wilcoxonTest");

        wilcoxonTest.addContent(new Element("isActive").setText("" + model.getWilcoxonTest().isActive()));
        wilcoxonTest.addContent(new Element("testType").setText(Integer.toString(model.getWilcoxonTest().getWilcoxonTestType().ordinal())));
        wilcoxonTest.addContent(new Element("significance").setText("" + model.getWilcoxonTest().getSignificance()));
        return wilcoxonTest;
    }

    private Element resolveBoxplotReport(BoxplotModel model) {
        Element report = new Element("report");

        report.addContent(new Element("type").setText("boxplot"));
        report.addContent(new Element("name").setText(model.getReportName()));
        report.addContent(new Element("description").setText(model.getReportName()));

        Element axes = new Element("axes");
        if (model.getXAxis() != null) {
            axes.addContent(resolveAxis(model.getXAxis(), "x"));
        }
        report.addContent(axes);

        report.addContent(this.resolveReportPictureSets(model.getPictureContainer()));
        report.addContent(this.resolveReportExifFilterKeywords(model.getExifFilterKeywords().toArray(new String[] {})));
        report.addContent(this.resolveWilcoxonTest(model));
        return report;
    }

    private Element resolveHistogram2DReport(Histogram2DModel model) {
        Element report = new Element("report");

        report.addContent(new Element("type").setText("histogram2D"));
        report.addContent(new Element("name").setText(model.getReportName()));
        report.addContent(new Element("description").setText(model.getReportName()));

        Element axes = new Element("axes");
        if (model.getXAxis() != null) {
            axes.addContent(resolveAxis(model.getXAxis(), "x"));
        }
        report.addContent(axes);

        report.addContent(this.resolveReportPictureSets(model.getPictureContainer()));
        report.addContent(this.resolveReportExifFilterKeywords(model.getExifFilterKeywords().toArray(new String[] {})));
        return report;
    }

    private Element resolveHistogram3DReport(Histogram3DModel model) {
        Element report = new Element("report");

        report.addContent(new Element("type").setText("histogram3D"));
        report.addContent(new Element("name").setText(model.getReportName()));
        report.addContent(new Element("description").setText(model.getReportName()));

        Element axes = new Element("axes");
        if (model.getXAxis() != null) {
            axes.addContent(resolveAxis(model.getXAxis(), "x"));
        }
        if (model.getZAxis() != null) {
            axes.addContent(resolveAxis(model.getZAxis(), "z"));
        }
        report.addContent(axes);

        report.addContent(this.resolveReportPictureSets(model.getPictureContainer()));
        report.addContent(this.resolveReportExifFilterKeywords(model.getExifFilterKeywords().toArray(new String[] {})));
        return report;
    }

    private Element resolveCluster3DReport(Cluster3DModel model) {
        Element report = new Element("report");

        report.addContent(new Element("type").setText("cluster3D"));
        report.addContent(new Element("name").setText(model.getReportName()));
        report.addContent(new Element("description").setText(model.getReportName()));

        Element axes = new Element("axes");
        if (model.getXAxis() != null) {
            axes.addContent(resolveAxis(model.getXAxis(), "x"));
        }
        if (model.getZAxis() != null) {
            axes.addContent(resolveAxis(model.getZAxis(), "z"));
        }
        if (model.getYAxis() != null) {
            axes.addContent(resolveAxis(model.getYAxis(), "y"));
        }
        report.addContent(axes);

        report.addContent(this.resolveReportPictureSets(model.getPictureContainer()));
        report.addContent(this.resolveReportExifFilterKeywords(model.getExifFilterKeywords().toArray(new String[] {})));
        return report;
    }

    private Element resolveTableReport(TableModel model) {
        Element report = new Element("report");

        report.addContent(new Element("type").setText("table"));
        report.addContent(new Element("name").setText(model.getReportName()));
        report.addContent(new Element("description").setText(model.getReportName()));
        report.addContent(this.resolveReportPictureSets(model.getPictureContainer()));
        report.addContent(this.resolveReportExifFilterKeywords(model.getExifFilterKeywords().toArray(new String[] {})));
        return report;
    }
}
