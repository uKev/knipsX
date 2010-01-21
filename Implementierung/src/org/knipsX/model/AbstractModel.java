/**
 * This package contains all files co knipsX 
 */
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
     * update all viewAktualisiere alle View, die das Modell beobachten.
     */
    // FIXME: NEEDS TO BE CHANGED TO PROTECTED, but is blocked, see issue #77
    public void updateViews() {
	this.setChanged();
	this.notifyObservers();
    }
}