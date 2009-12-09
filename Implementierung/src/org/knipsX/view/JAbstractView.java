package org.knipsX.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class JAbstractView extends JFrame implements Observer {

	/**
	 * Für Serialisierung
	 */
	private static final long serialVersionUID = -5981384605515636896L;

	@Override
	public void update(Observable arg0, Object arg1) {
		/* Does nothing */
	}

}
