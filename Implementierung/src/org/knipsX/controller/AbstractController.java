package org.knipsX.controller;

/**
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.knipsX.model.AbstractModel;

public abstract class AbstractController implements ActionListener {

    protected AbstractModel model;

    public AbstractController() {
    }

    public AbstractController(final AbstractModel abstractModel) {
	this.model = abstractModel;
    }

    public abstract void actionPerformed(ActionEvent e);
}