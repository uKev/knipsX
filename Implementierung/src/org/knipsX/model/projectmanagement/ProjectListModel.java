package org.knipsX.model.projectmanagement;

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

	public int generateFreeProjectID() {
		return 0;
	}

	public String generatePathforID(int id) {
		return "path";
	}
}
