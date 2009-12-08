package org.knipsX.model.projectmanagement;

import java.util.ArrayList;

import org.knipsX.model.AbstractModel;


public class ProjectList extends AbstractModel {
	
	private ArrayList<String> projectlist;
	
	public ProjectList() {		
		projectlist = new ArrayList<String>();		
	}
	
	public void addToList(String id) {
		projectlist.add(id);		
	}
	
	public void removeFromList (int position) {
		projectlist.remove(position);
	}

	public ArrayList<String> getProjectlist() {
		return projectlist;
	}

	public void setProjectlist(ArrayList<String> projectlist) {
		this.projectlist = projectlist;
	}
}

