package org.knipsX.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {
	
	public void updateViews() {
	    setChanged(); 
	    notifyObservers();		
	}

}
