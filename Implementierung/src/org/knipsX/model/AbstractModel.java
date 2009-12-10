package org.knipsX.model;

import java.util.Observable;

public abstract class AbstractModel extends Observable {

    /**
     * Initialstatus
     */
    public static final int NO_STATE = -1;

    /* Modellstatus */
    private int modelStatus = AbstractModel.NO_STATE;

    /**
     * Bekomme Modellstatus geliefert.
     * 
     * @return der Modellstatus.
     */
    public int getModelStatus() {
	return this.modelStatus;
    }

    /**
     * Setze den Modellstatus.
     * 
     * @param modelStatus
     *            der Modellstatus.
     */
    public void setModelStatus(final int modelStatus) {
	
	/* Setze Status */
	this.modelStatus = modelStatus;
    }

    /**
     * Aktualisiere alle View, die das Modell beobachten.
     */
    public void updateViews() {
	this.setChanged();
	this.notifyObservers();
    }
}