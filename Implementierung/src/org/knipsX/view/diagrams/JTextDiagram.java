package org.knipsX.view.diagrams;

import javax.swing.JTextPane;

import org.knipsX.model.AbstractModel;


public class JTextDiagram extends JAbstractDiagram{

	private static final long serialVersionUID = 1L;
	private JTextPane pane;

	/**
	 * Constructor
	 * @param abstractModel the model from which the drawing information is taken
	 */
	public JTextDiagram(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}
}
