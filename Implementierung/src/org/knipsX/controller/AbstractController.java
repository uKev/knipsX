package org.knipsX.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractController<M, V> implements ActionListener {

    protected M model;
    protected V view;

    public AbstractController(final M model, final V view) {
	this.model = model;
	this.view = view;
    }

    public abstract void actionPerformed(ActionEvent e);
}