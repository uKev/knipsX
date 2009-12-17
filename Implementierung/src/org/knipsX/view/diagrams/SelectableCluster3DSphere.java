package org.knipsX.view.diagrams;

import javax.media.j3d.Shape3D;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.reportmanagement.Frequency3DPoint;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * This class represents a selectable point in a 3D diagram. Additionally
 * it stores a reference to a picture object which can be used for evaluation
 * purposes.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class SelectableCluster3DSphere extends Shape3D {

    private Frequency3DPoint frequencypoint;

    public SelectableCluster3DSphere(Frequency3DPoint frequencypoint) {
	this.frequencypoint = frequencypoint;
	Sphere mySphere = new Sphere(0.075f * 2, Primitive.GENERATE_NORMALS, JAbstract3DView.GEODETAIL);
	addGeometry(mySphere.getShape().getGeometry());
    }

    public Frequency3DPoint getFrequence3DPoint() {
    	return frequencypoint;
    }

}
