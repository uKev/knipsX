package org.knipsX.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureNotFoundException;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;

public class DummyRepository implements Repository {

    /* the list */
    private final List<ProjectModel> projects = new ArrayList<ProjectModel>();

    public DummyRepository() {

        /* create a list of picture sets */

        /* create a list of reports */

    }

    public List<ProjectModel> getProjects() {
        System.out.println("getProjects() initiated...");

        /* add some dummy projects */
        List<PictureSet> pictureSetList = new ArrayList<PictureSet>();
        List<AbstractReportModel> reportList = new ArrayList<AbstractReportModel>();

        /* create the first picture set an some picture containers */
        PictureSet dummyPictureSet = new PictureSet("Goldfische", 1);

        /* create some dummy picture containers and add to the picture set */
        String picturePath = System.getProperty("user.home") + File.separator + ".knipsX_test_bilder" + File.separator
                + "DSC00964.JPG";
        System.out.println("Picture Path: " + picturePath);
        Picture dummyPicture = null;
        try {
            dummyPicture = new Picture(picturePath, true);
        } catch (PictureNotFoundException e) {
            System.out.println("Can not create Picture from File: File not found");
            e.printStackTrace();
        }
        dummyPictureSet.add(dummyPicture);

        String pictureDir = System.getProperty("user.home") + File.separator + ".knipsX_test_bilder" + File.separator
                + "testordner";
        System.out.println("Picture Dir:" + pictureDir);
        Directory dummyDirectory = new Directory(pictureDir);

        dummyPictureSet.add(dummyDirectory);

        dummyPictureSet.add(new PictureSet("Fische", 12));
        dummyPictureSet.add(new PictureSet("Oldfische", 13));
        dummyPictureSet.add(new PictureSet("Abc", 14));
        dummyPictureSet.add(new PictureSet("Grillfest", 10));
        dummyPictureSet.add(new PictureSet("SuperGoldfische", 11));

        /* add to list */

        /*
         * create some dummy picture sets antem.getProperty("user.home") + File.separator + ".knipsX_test_bilder" +
         * File.separator + "testordner";
         * System.out.println(pictd add
         */

        PictureSet test = new PictureSet("Urlaub", 3);
        test.add(dummyDirectory);

        /* create some dummy reports */
        AbstractReportModel dummyReportOne = new BoxplotModel();
        dummyReportOne.setReportName("Blendenanalyse");
        dummyReportOne.setReportDescription("Analyse über Blenden");

        Histogram3DModel dummyReportTwo = new Histogram3DModel();
        dummyReportTwo.setReportName("Histogram 3D");
        dummyReportTwo.setReportDescription("Analyse über Blenden - neu");
        dummyReportTwo.addPictureContainer(test);
        dummyReportTwo.setxAxis(new Axis(ExifParameter.FNUMBER));
        dummyReportTwo.setzAxis(new Axis(ExifParameter.FOCALLENGTH));

        Cluster3DModel dummyReportThree = new Cluster3DModel();
        dummyReportThree.setReportName("Cluster ftw");
        dummyReportThree.setReportDescription("Analyse über Blenden - neu");
        dummyReportThree.addPictureContainer(test);
        dummyReportThree.setxAxis(new Axis(ExifParameter.FNUMBER));
        dummyReportThree.setyAxis(new Axis(ExifParameter.FOCALLENGTH));
        dummyReportThree.setzAxis(new Axis(ExifParameter.ISO));

        reportList.add(dummyReportThree);
        reportList.add(dummyReportOne);
        reportList.add(dummyReportTwo);

        this.projects.add(new ProjectModel(UUID.randomUUID().hashCode(), "Testprojekt mit Realbildern",
                "Keine Beschreibung.", new GregorianCalendar(2009, 11, 12, 7, 9, 3), pictureSetList, reportList));
        this.projects.add(new ProjectModel(UUID.randomUUID().hashCode(), "Der Ehhhhhhhmer", "", new GregorianCalendar(
                2009, 11, 12, 12, 42, 43), pictureSetList, reportList));

        pictureSetList.add(dummyPictureSet);
        pictureSetList.add(test);
        pictureSetList.add(new PictureSet("Golfen", 4));
        pictureSetList.add(new PictureSet("Weihnachten 2008", 5));

        return new ArrayList<ProjectModel>(this.projects);
    }

    public ProjectModel getProject(int projectId) {
        assert this.projects.size() > 0;

        boolean goOn = true;
        ProjectModel project = new ProjectModel(projectId, "Name_of_a_project", "Descr. of a project",
                new GregorianCalendar(), new ArrayList<PictureSet>(), new ArrayList<AbstractReportModel>());

        /* delete the project */
        for (int i = 0; goOn && i < this.projects.size(); ++i) {
            if (this.projects.get(i).equals(projectId)) {
                project = this.projects.get(i);
                goOn = false;
            }
        }
        return project;
    }

    public int createProject() {
        int id = UUID.randomUUID().hashCode();
        this.projects.add(new ProjectModel(id, "", "", new GregorianCalendar(), new ArrayList<PictureSet>(),
                new ArrayList<AbstractReportModel>()));
        return id;
    }

    public int createProject(ProjectModel toCopy) {
        int id = UUID.randomUUID().hashCode();
        this.projects.add(new ProjectModel(toCopy, id, ""));
        return id;
    }

    public void deleteProject(int projectId) {
        assert this.projects.size() > 0;

        boolean goOn = true;

        /* delete the project */
        for (int i = 0; goOn && i < this.projects.size(); ++i) {
            if (this.projects.get(i).equals(projectId)) {
                this.projects.remove(this.projects.get(i));
                goOn = false;
            }
        }
    }

    public void saveProject(ProjectModel toSave) {
        this.projects.add(toSave);
    }
}