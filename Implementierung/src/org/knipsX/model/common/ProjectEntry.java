package org.knipsX.model.common;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.knipsX.model.AbstractModel;

public class ProjectEntry extends AbstractModel {
	
	/* Die Projektid */
	private int id;
	
	/* Der Projektname */
	private String name;
	
	/* Das Erstellugsdatum */
	private GregorianCalendar creationDate;
	
	/* Der Pfad */
	private String path;

	public ProjectEntry(int id, String name, GregorianCalendar creationDate, String path) {
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.path = path;
	}

	public ProjectEntry(ProjectEntry projectEntry) {
		this.id = projectEntry.getId();
		this.name = projectEntry.getName();
		this.creationDate = projectEntry.getCreationDate();
		this.path = projectEntry.getPath();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GregorianCalendar getCreationDate() {			
		return creationDate;
	}

	public void setCreationDate(GregorianCalendar creationDate) {
		this.creationDate = creationDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
	
	public String calendarToString () {
		int year	= creationDate.get(Calendar.YEAR);
		int month	= creationDate.get(Calendar.MONTH);
		int day		= creationDate.get(Calendar.DAY_OF_MONTH);
		int hour	= creationDate.get(Calendar.HOUR_OF_DAY);
		int minute	= creationDate.get(Calendar.MINUTE);
		int second	= creationDate.get(Calendar.SECOND);
		String dateAndTime = day+"."+month+"."+year+" - "+hour+":"+minute+":"+second;		
		return dateAndTime;		
	}
}