package org.knipsX.view.diagrams;
import javax.vecmath.Vector3d;

import org.knipsX.model.AbstractModel;


public class JBoxplot extends JAbstract2DDiagram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JBoxplot(AbstractModel abstractModel) {
		super(abstractModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateContent() {
		addLights();
		createText(new Vector3d(5,2,5), new Vector3d(1,1,1), basicMaterial(1, 0, 1), "Der Draxler");
		createSphere(new Vector3d(5,5,5), new Vector3d(1,1,1), basicMaterial(1, 0, 1));
		createCube(new Vector3d(5,5,10), new Vector3d(1,1,1), basicMaterial(0, 1, 0));
	}
}