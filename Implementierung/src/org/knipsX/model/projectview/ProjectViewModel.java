package org.knipsX.model.projectview;

import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;

public class ProjectViewModel extends AbstractModel {

	private ProjectEntry project;
	
	public ProjectViewModel(ProjectEntry project) {
		this.project = project;
	}
	
	public String getProjectName() {
		assert this.project != null;
		return this.project.getName();
	}
	
}
