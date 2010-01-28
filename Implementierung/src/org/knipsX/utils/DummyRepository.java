package org.knipsX.utils;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;

public class DummyRepository implements Repository {

    /* the list */
    private final List<ProjectModel> projects = new LinkedList<ProjectModel>();

    public DummyRepository() {

        /* create a list of picture sets */
        List<PictureSet> pictureSetList = new LinkedList<PictureSet>();

        /* create a list of reports */
        List<AbstractReportModel> reportList = new LinkedList<AbstractReportModel>();

        /* create the first picture set an some picture containers */
        PictureSet dummyPictureSet = new PictureSet("Goldfische", 1);

        /* create some dummy picture containers and add to the picture set */
        Picture dummyPicture = new Picture(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "DSC00964.JPG", true);
        dummyPictureSet.addToChilds(dummyPicture);

        Directory dummyDirectory = new Directory(System.getProperty("user.dir") + File.separator + "testbilder"
                + File.separator + "testordner");
        
        dummyPictureSet.addToChilds(dummyDirectory);

        dummyPictureSet.addToChilds(new PictureSet("Grillfest", 10));
        dummyPictureSet.addToChilds(new PictureSet("SuperGoldfische", 11));

        /* add to list */
        pictureSetList.add(dummyPictureSet);

        /* create some dummy picture sets and add */
        PictureSet test = new PictureSet("Urlaub", 3);
        test.addToChilds(dummyDirectory);
        pictureSetList.add(test);
        pictureSetList.add(new PictureSet("Golfen", 4));
        pictureSetList.add(new PictureSet("Weihnachten 2008", 5));

        /* create some dummy reports */
        AbstractReportModel dummyReportOne = new BoxplotModel();
        dummyReportOne.setReportName("Blendenanalyse");
        dummyReportOne.setReportDescription("Analyse über Blenden");

        AbstractReportModel dummyReportTwo = new BoxplotModel();
        dummyReportTwo.setReportName("neue Blendenanalyse");
        dummyReportTwo.setReportDescription("Analyse über Blenden - neu");
        
        Cluster3DModel dummyReportThree = new Cluster3DModel();
        dummyReportThree.setReportName("Cluster ftw");
        dummyReportThree.setReportDescription("Analyse über Blenden - neu");    
        dummyReportThree.addPictureContainer(test);
        dummyReportThree.setxAxis(new Axis(ExifParameter.DATE));
        dummyReportThree.setyAxis(new Axis(ExifParameter.ISO));
        dummyReportThree.setzAxis(new Axis(ExifParameter.FOCALLENGTH));       
        

        int numberOfElements = 0;    
        
        for (Picture picture : test) {                    
            numberOfElements++;
        }
        
        System.out.println(numberOfElements);
        
        reportList.add(dummyReportThree);
        reportList.add(dummyReportOne);
        reportList.add(dummyReportTwo);

        /* add some dummy projects */
        this.projects.add(new ProjectModel(UUID.randomUUID().hashCode(), "Testprojekt mit Realbildern",
                "Keine Beschreibung.", new GregorianCalendar(2009, 11, 12, 7, 9, 3), pictureSetList, reportList));
        this.projects.add(new ProjectModel(UUID.randomUUID().hashCode(), "Der Ehhhhhhhmer", "", new GregorianCalendar(
                2009, 11, 12, 12, 42, 43), pictureSetList, reportList));
    }

    public List<ProjectModel> getProjects() {
        return new LinkedList<ProjectModel>(this.projects);
    }

    public ProjectModel getProject(int projectId) {
        assert this.projects.size() > 0;

        boolean goOn = true;
        ProjectModel project = null;

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
        this.projects.add(new ProjectModel(id, "", "", new GregorianCalendar()));
        return id;
    }

    public int createProject(ProjectModel toCopy) {
        int id = UUID.randomUUID().hashCode();
        this.projects.add(new ProjectModel(toCopy, id));
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