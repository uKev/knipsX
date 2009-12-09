package org.knipsX;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class Programm {

	// Hier muss noch ueberlegt werden wie der Path gesetzt wird
	private static final String projectFilePath = System.getProperty("user.home")+File.separator+".knipsX";

	/**************************************************************************
	 * main() ist die Startmethode unseres Programms. Sie erstellt das Model, in
	 * welchem die Projekte aus der Projektkonfigurationsdatei gespeichert sind.
	 * Zusaetzlich wird von ihr das erste Fenster bzw. die erste View erstellt.
	 * 
	 * @param args
	 *************************************************************************/

	public static void main(String[] args) {
		ProjectListModel projectListModel = new ProjectListModel(scanProjectListFile());
		JAbstractView firstWindow = new JProjectAdministration(projectListModel);
	}

	/**************************************************************************
	 * Hier wird die Projektdatei mit der Liste der Projekte ausgelesen und eine
	 * Arraylist daraus erzeugt. TODO: Achtung!!! Der derzeitige Inhalt ist nur
	 * dummyhaft eingesetzt. Es muss auch ein Path festgelegt werden wo man
	 * diese Datei findet. Siehe hierzu Attribute "projectFilePath".
	 *************************************************************************/
	public static List<ProjectEntry> scanProjectListFile() {
		List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();
		projectList.add(new ProjectEntry(1, "Schwarzwald", new GregorianCalendar(2009, 11, 12, 1, 2, 3), ""));
		projectList.add(new ProjectEntry(2, "Der Ehhhhhhhmer", new GregorianCalendar(2009, 11, 12, 1, 2, 3), ""));
		return projectList;
	}
	
	/**************************************************************************
	 * Hier wird die Projektdatei neu geschrieben. TODO: Methode noch leer.
	 * Es muss auch ein Path festgelegt werden wo man
	 * diese Datei findet. Siehe hierzu Attribute "projectFilePath".
	 * @param list : Liste der aktuellen Projekte
	 *************************************************************************/
	public static void writeProjectListFile(LinkedList<ProjectEntry> list) {		
	}
}
