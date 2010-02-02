/******************************************************************************
 * This package is the root of all controller classes.
 *****************************************************************************/
package org.knipsX.controller;

/* import classes from the java sdk */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This AbstractController is the top of our controller structure. It represents ActionListener classes connected with
 * views. They are getting userinteractions and evaluate them so they act in content.
 * @param <M> The model
 * @param <V> The view
 */
public abstract class AbstractController<M, V> implements ActionListener {

    /* The related model */
    protected M model;
    
    /* The related view */
    protected V view;

    /**
     * Construct a Controller with a relation to the view
     * 
     * @param view
     *            The realted view
     */
    public AbstractController(final V view) {
        this.view = view;
    }

    /**
     * /**
     * Construct a Controller with a relation to the model and the view
     * 
     * @param model
     *            The related model
     * @param view
     *            The related view
     */
    public AbstractController(final M model, final V view) {
        this.model = model;
        this.view = view;
    }

    /**
     * If a specific action is done by the user the related action is executed.
     * 
     * @param e
     *            The Event happened before. For Example a button is pressed.
     */
    public abstract void actionPerformed(ActionEvent e);
}