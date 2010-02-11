package org.knipsX.utils;
 
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
 
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
 
public class XMLRepository implements Repository {
	private final List<ProjectModel> projects = new ArrayList<ProjectModel>();
	List<PictureSet> pictureSetList = new ArrayList<PictureSet>();
	List<AbstractReportModel> reportList = new ArrayList<AbstractReportModel>();
 
	public List<ProjectModel> getProjects() {
        // TODO Auto-generated method stub
		System.out.println("XML_repository: initiated!");
		System.out.println("Getting a list of projects...");
		
		pictureSetList.add(new PictureSet("Spacecrafts"));
		
	    this.projects.add(new ProjectModel(UUID.randomUUID().hashCode(), "Test Project 1",
                "No comments", new GregorianCalendar(2010, 02, 04, 7, 9, 3), pictureSetList, reportList));
	    
  
    
        return null;
        
    }
 
	
	public ProjectModel getProject(int projectId) {
    	
    	System.out.println("::getProject initiated");
        // TODO Auto-generated method stub
        return null;
    }    
	
    public int createProject() {
        /* create a list of picture sets */
//        List<PictureSet> pictureSetList = new ArrayList<PictureSet>();
//        
//        return pictureSetList;
    	return 0;
    }
 
    public int createProject(ProjectModel toCopy) {
        // TODO Auto-generated method stub
        return 0;
    }
 
    public void deleteProject(int projectId) {
        // TODO Auto-generated method stub
        
    }	
 
    
    public void saveProject(ProjectModel toSave) {
        // TODO Auto-generated method stub
        
    }
}