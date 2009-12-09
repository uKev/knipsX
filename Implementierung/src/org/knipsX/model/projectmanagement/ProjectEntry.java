package org.knipsX.model.projectmanagement;

public class ProjectEntry {
		
	private String id;
	private String name;
	private String date;
	private String path;
	
	public ProjectEntry(String id, String name, String date, String path) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.path = path;		
	}
	
	public ProjectEntry(ProjectEntry projectEntry) {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getPath() {
		return path;
	}
}
