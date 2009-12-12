package org.knipsX.model.projectmanagement;

import java.util.GregorianCalendar;
import java.util.List;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;

public class ProjectManagementModel extends AbstractModel {
	
	public static final int SELECT = 0;
	public static final int NEW = 1;
	public static final int COPY = 2;
	public static final int DELETE = 3;
	public static final int OPEN = 4;
				
	private int modelStatus = SELECT;
	
	private List<ProjectEntry> projectList;

	public ProjectManagementModel(List<ProjectEntry> linkedList) {
		this.projectList = linkedList;
	}

	public List<ProjectEntry> getProjectList() {
		return projectList;
	}

	public void setProjectlist(List<ProjectEntry> projectlist) {
		this.projectList = projectlist;
	}

	/**
	 * Erstelle komplett neues Projekt.
	 * 
	 * @param projectName Der Projektname.
	 */
	public void addNewProject(String projectName) {
		
		/* Generiere neue ID */
		int id = this.generateFreeProjectID();
		
		/* Generiere neuen Pfad  */
		String path = this.generatePathforID(id);			
		
		/* Füge hinzu */
		this.projectList.add(0, new ProjectEntry(id, projectName, "", new GregorianCalendar(), path));	
		
		/* TODO Hier fehlen die Routinen zum Schreiben! */
	}
	
	/**
	 * Erstelle neues Projekt, basierend auf einem alten Projekt. 
	 * 
	 * @param projectEntry Das alte Projekt.
	 * @param projectName Der Projektname.
	 */
	public void copyProject(ProjectEntry projectEntry, String projectName) {
		
		/* Generiere neue ID */
		int id = this.generateFreeProjectID();
		
		/* Generiere neuen Pfad  */
		String path = this.generatePathforID(id);
		
		/* Füge hinzu */
		this.projectList.add(0, new ProjectEntry(id, projectName, "", new GregorianCalendar(), path));
		
		/* TODO Hier fehlen die Routinen zum Kopieren! */
	}
	
	private int generateFreeProjectID() {
		return 0;
	}

	private String generatePathforID(int id) {
		return "path";
	}

	public void setModelStatus(int modelStatus) {
		this.modelStatus = modelStatus;
	}

	public int getModelStatus() {
		return modelStatus;
	}
	
	public void removeFromList(int position) {
		projectList.remove(position);
	}
	
	public void addToList(ProjectEntry projectEntry) {
		projectList.add(0, projectEntry);		
	}
	
}