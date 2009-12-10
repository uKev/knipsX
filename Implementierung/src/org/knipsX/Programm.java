package org.knipsX;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.projectmanagement.JProjectAdministration;

public class Programm {

    /* Der Pfas zum Projektverzeichnis */
    private static final String projectFilePath = System.getProperty("user.home") + File.separator + ".knipsX";

    /**
     * main() ist die Startmethode unseres Programms. Sie erstellt das Model, in
     * welchem die Projekte aus der Projektkonfigurationsdatei gespeichert sind.
     * Zusaetzlich wird von ihr das erste Fenster bzw. die erste View erstellt.
     * 
     * @param args
     *            Parameter.
     */
    public static void main(final String[] args) {
	final ProjectListModel projectListModel = new ProjectListModel(Programm.scanProjectListFile());
	new JProjectAdministration(projectListModel);
    }

    /**
     * Hier wird die Projektdatei mit der Liste der Projekte ausgelesen und eine
     * Arraylist daraus erzeugt.
     * 
     * TODO: Achtung!!! Der derzeitige Inhalt ist nur dummyhaft eingesetzt. Es muss auch ein Path festgelegt werden wo
     * man diese Datei findet. Siehe hierzu Attribute "projectFilePath".
     * 
     * @return gibt eine Liste mit Projekten zurück.
     */
    public static List<ProjectEntry> scanProjectListFile() {

	/* Die Liste */
	final List<ProjectEntry> projectList = new LinkedList<ProjectEntry>();

	/* Füge Projekte hinzu */
	projectList.add(new ProjectEntry(1, "Schwarzwald", new GregorianCalendar(2009, 11, 12, 13, 42, 43), ""));
	projectList.add(new ProjectEntry(2, "Der Ehhhhhhhmer", new GregorianCalendar(2009, 11, 12, 12, 42, 43), ""));

	/* Gib Liste zurück */
	return projectList;
    }

    /**
     * Hier wird die Projektdatei neu geschrieben.
     * 
     * TODO: Methode noch leer. Es muss auch ein Path festgelegt werden wo man diese Datei findet. Siehe hierzu
     * Attribute "projectFilePath".
     * 
     * @param list
     *            Liste der aktuellen Projekte
     */
    public static void writeProjectListFile(final List<ProjectEntry> list) {
    }
}
