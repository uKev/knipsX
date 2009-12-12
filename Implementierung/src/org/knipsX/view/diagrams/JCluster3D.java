package org.knipsX.view.diagrams;


import javax.vecmath.Vector3d;

import org.knipsX.model.AbstractModel;


public class JCluster3D extends JAbstract3DDiagram{

	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param abstractModel the model from which the drawing information is taken
	 */
	public JCluster3D(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateContent() {
		createCube(new Vector3d(5,5,10), new Vector3d(1,1,1), basicMaterial(0, 1, 0));		
	}
}
