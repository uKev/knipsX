package org.knipsX.utils;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;


public class DummyRepository implements Repository {

    @Override
    public void deleteProject(int projectID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ProjectModel> getProjects() {
        
        /* the list */
        final List<ProjectModel> projectList = new LinkedList<ProjectModel>();
        
        /* create a list of picture sets */
        List<PictureSet> pictureSetList = new LinkedList<PictureSet>();

        /* create a list of reports */
        List<AbstractReportModel> reportList = new LinkedList<AbstractReportModel>();

        /* create the project */
        ProjectModel project = new ProjectModel(6, "XML", "Desc", new GregorianCalendar(2009, 11, 12, 7, 9, 3), "");

        /* create the first picture set an some picture containers */
        PictureSet dummyPictureSet = new PictureSet("Goldfische", 1);

        /* create some dummy picture containers and add to the picture set */
        Picture dummyPicture = new Picture("DSC00964.JPG", System.getProperty("user.dir") + File.separator + "DSC00964.JPG", false);
        dummyPicture.setName("Nemo");
        dummyPictureSet.add(dummyPicture);

        Directory dummyDirectory = new Directory();
        dummyDirectory.setName("Urlaubsbilder");
        dummyPictureSet.add(dummyDirectory);

        dummyPictureSet.add(new PictureSet("Grillfest", 10));
        dummyPictureSet.add(new PictureSet("SuperGoldfische", 11));

        /* add to list */
        pictureSetList.add(dummyPictureSet);

        /* create some dummy picture sets and add */
        pictureSetList.add(new PictureSet("Urlaub", 3));
        pictureSetList.add(new PictureSet("Golfen", 4));
        pictureSetList.add(new PictureSet("Weihnachten 2008", 5));

        /* create some dummy reports */
        AbstractReportModel dummyReportOne = new BoxplotModel();
        dummyReportOne.setReportName("Blendenanalyse");
        dummyReportOne.setReportDescription("Analyse über Blenden");

        AbstractReportModel dummyReportTwo = new BoxplotModel();
        dummyReportTwo.setReportName("neue Blendenanalyse");
        dummyReportTwo.setReportDescription("Analyse über Blenden - neu");

        reportList.add(dummyReportOne);
        reportList.add(dummyReportTwo);
        
        /* add some dummy projects */
        projectList.add(ProjectModel.getInstance(project, pictureSetList, reportList));
        
        projectList.add(new ProjectModel(2, "Der Ehhhhhhhmer", "", new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

        /* returns the list */
        return projectList;
        
    }

    @Override
    public void saveProject(int projectID, ProjectModel projectModel) {
        // TODO Auto-generated method stub
        
    }


}
