package org.knipsX.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {

    /**
     * Aktualisiere alle View, die das Modell beobachten.
     */
    public void updateViews() {
	this.setChanged();
	this.notifyObservers();
    }
}