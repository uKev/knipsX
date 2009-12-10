package org.knipsX.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.knipsX.model.AbstractModel;

public class JAbstractView extends JFrame implements Observer {

	/* Für Serialisierung */
	private static final long serialVersionUID = -5981384605515636896L;
	
	/* Definiere ein abstraktes Modell */
	protected AbstractModel model;
	
	/**
	 * Erstellt einen neuen View, der mit einem Modell verbunden ist.
	 * @param abstractModel das Modell.
	 */
	public JAbstractView(AbstractModel abstractModel) {
		
		/* Rufe JFrame Konstruktor auf */
		super();
		
		/* Setze internes Modell */
		this.model = abstractModel;
		
		/* Registriere diese View am Beobachter */
		this.model.addObserver(this);
	}
	
	public void update(Observable arg0, Object arg1) {
		/* Does nothing */
	}
}