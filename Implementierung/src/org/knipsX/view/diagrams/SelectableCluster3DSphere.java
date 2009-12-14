package org.knipsX.view.diagrams;

import javax.media.j3d.Shape3D;
import org.knipsX.model.picturemanagement.Picture;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class SelectableCluster3DSphere extends Shape3D {
	
	private Picture picture;
	
	public SelectableCluster3DSphere(Picture picture) {	
		this.picture = picture;
        Sphere mySphere = new Sphere(0.075f*2,Primitive.GENERATE_NORMALS,JAbstract3DView.GEODETAIL);
        addGeometry(mySphere.getShape().getGeometry());
	}

	public Picture getPicture() {
		return picture;
	}

}
