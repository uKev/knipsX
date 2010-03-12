package org.knipsX.model;

import java.util.Observable;
/**
 * This AbstractModel is the top of our model structure. It is a observable and notify all observers of the model.
 * 
 * @author Kevin Zuber
 *
 */
public abstract class AbstractModel extends Observable {

    /**
     * Notify all observers of the model.
     */
    protected void updateViews() {
        this.setChanged();
        this.notifyObservers();        
    }
}