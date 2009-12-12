package org.knipsX.view.diagrams;

import javax.swing.JTable;

import org.knipsX.model.AbstractModel;


public class JTableDiagram extends JAbstractDiagram{

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	/**
	 * Constructor
	 * @param abstractModel the model from which the drawing information is taken
	 */
	public JTableDiagram(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}
}
