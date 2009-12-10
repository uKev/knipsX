package org.knipsX.model.projectmanagement;

import java.util.GregorianCalendar;
import java.util.List;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;

public class ProjectListModel extends AbstractModel {

	private List<ProjectEntry> projectList;

	public ProjectListModel(List<ProjectEntry> linkedList) {
		this.projectList = linkedList;
	}

	public void removeFromList(int position) {
		projectList.remove(position);
	}
	
	public void addToList(ProjectEntry projectEntry) {
		projectList.add(0, projectEntry);		
	}

	public List<ProjectEntry> getProjectlist() {
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
		this.projectList.add(0, new ProjectEntry(id, projectName, new GregorianCalendar(), path));	
		
		/* TODO Hier fehlen die Routinen zum Schreiben! */
	}
	
	/**
	 * Erstelle neues Projekt, basierend auf einem alten Projekt. 
	 * 
	 * @param projectEntry Das alte Projekt.
	 * @param projectName Der Projektname.
	 */
	public void addNewProject(ProjectEntry projectEntry, String projectName) {
		
		/* Generiere neue ID */
		int id = this.generateFreeProjectID();
		
		/* Generiere neuen Pfad  */
		String path = this.generatePathforID(id);
		
		/* Füge hinzu */
		this.projectList.add(0, new ProjectEntry(id, projectName, new GregorianCalendar(), path));
		
		/* TODO Hier fehlen die Routinen zum Kopieren! */
	}
	
	private int generateFreeProjectID() {
		return 0;
	}

	private String generatePathforID(int id) {
		return "path";
	}
}
