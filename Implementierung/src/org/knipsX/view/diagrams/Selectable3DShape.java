package org.knipsX.view.diagrams;

import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import org.knipsX.model.reportmanagement.Frequency3DPoint;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * This class represents a selectable shape in a 3D diagram. Additionally
 * it stores a reference to a Frequency3DPoint object which can be used for 
 * further evaluation purposes.
 * 
 * @author David Kaufman
 *
 */
public class Selectable3DShape extends Shape3D {

    private Frequency3DPoint frequencypoint;

    /**
     * Creates a Selectable3DShape object with the specified frequency3Dpoint
     * 
     * @param frequencypoint the frequency3dpoint you want to assign
     */
    public Selectable3DShape(Frequency3DPoint frequencypoint) {
	this.frequencypoint = frequencypoint;
	Sphere mySphere = new Sphere(0.075f * 2, Primitive.GENERATE_NORMALS, JAbstract3DView.GEODETAIL);
	addGeometry(mySphere.getShape().getGeometry());
    }
    
    /**
     * Creates a Selectable3DShape object with the specified frequency3Dpoint and 
     * geometry
     * 
     * @param frequencypoint the frequency3dpoint you want to assign
     * @param geo the geometry of the point
     */
    public Selectable3DShape(Frequency3DPoint frequencypoint, Geometry geo) {
        this.frequencypoint = frequencypoint;
        addGeometry(geo);
    }

    /**
     * Returns the Frequency3DPoint of the current object
     * @return the Frequncy3DPoint 
     */
    public Frequency3DPoint getFrequence3DPoint() {
    	return this.frequencypoint;
    }

}
