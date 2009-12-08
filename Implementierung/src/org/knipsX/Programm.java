package org.knipsX;

import org.knipsX.model.projectmanagement.ProjectList;
import org.knipsX.view.projectmanagement.Projectadministration;


public class Programm {
	
	public static void main(String[] args) {
		
		ProjectList project1list = new ProjectList();
		project1list.addToList("Schwarzwald");
		project1list.addToList("Der Ehhhhhhhmer");		
		Projectadministration project1 = new Projectadministration(project1list);
		project1list.addObserver(project1);
	}
}
