package org.knipsX.view.diagrams;

import java.awt.Font;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.Billboard;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import org.knipsX.model.AbstractModel;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.SimpleUniverse;


public abstract class JAbstract3DEngine extends JAbstractDiagram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Canvas3D canvas3D;
	protected SimpleUniverse simpleU;
	protected BranchGroup objRoot;
	protected final double AXISSIZE = 10.0;
	protected final double GRIDDENSITYFACTOR = 1;
	protected final int GEODETAIL = 10;
	protected boolean textautorotate = true;
	
	public JAbstract3DEngine(AbstractModel abstractModel) {
		super(abstractModel);
		this.objRoot = new BranchGroup();
		this.canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        this.simpleU = new SimpleUniverse(this.canvas3D);
        
        preinitialize();
        
		generateContent();

		postinitialize();	
		
	}
	
	public abstract void generateContent();
	public abstract void preinitialize();
	
	public void createCube(Vector3d position, Vector3d scale, Appearance material){
		// TODO add implementation
	}
	public void createSphere(Vector3d position, Vector3d scale, Appearance material){
		// TODO add implementation
	}
	
	protected void createText(Vector3d position, Vector3d scale, Appearance material, String text){	
			
        TransformGroup objMove = createTransformGroup(position, scale);  
        
        TransformGroup objSpin = new TransformGroup();        
        Billboard mybillboard = new Billboard(objSpin);
        BoundingSphere bSphere = new BoundingSphere();
        mybillboard.setSchedulingBounds(bSphere);        
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objMove.addChild(objSpin); 
        
        
        Appearance textAppear = new Appearance();
        ColoringAttributes textColor = new ColoringAttributes();
        textColor.setColor(1.0f, 1.0f, 1.0f);
        textAppear.setColoringAttributes(textColor);		
        Font3D font3D = new Font3D(new Font("Arial", Font.PLAIN, 1), new FontExtrusion());
        Text3D textGeom = new Text3D(font3D, new String(text));
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D textShape = new Shape3D();
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppear);
        
        if(this.textautorotate) {
        	objSpin.addChild(textShape);
        	this.objRoot.addChild(mybillboard);       	
        } else {
            objMove.addChild(textShape);            
        }
        this.objRoot.addChild(objMove);
	}
	
	
	protected void createAxis(int numberofAxis) {	
			
			for(int i = 0; i <= numberofAxis - 1; i++) {
				
				//The transformation information of the axis			
				Transform3D axistrans = new Transform3D();
				
				if(i == 0) {
					axistrans.rotX(90 * Math.PI / 180.0d);
					axistrans.setTranslation(new Vector3d(0,0,this.AXISSIZE/2.0));
				} else if(i == 1) {
					axistrans.rotY(90 * Math.PI / 180.0d);
					axistrans.setTranslation(new Vector3d(0,this.AXISSIZE/2.0,0));
				} else {
					axistrans.rotZ(90 * Math.PI / 180.0d);
					axistrans.setTranslation(new Vector3d(this.AXISSIZE/2.0,0,0));
				}			
				
				axistrans.setScale(new Vector3d(0.15d,this.AXISSIZE,0.15d));
				
				// Create transformation group
				TransformGroup objAxis = new TransformGroup(axistrans);		
				
				Appearance myAppearance = basicMaterial(0.0f,0.0f,0.0f);
			    		          
				Cylinder myAxisGeo = new Cylinder(0.3f,1f, myAppearance);
				
				objAxis.addChild(myAxisGeo);
				
				// Add both groups to the root
				this.objRoot.addChild(objAxis);
			}
			
			// Abschnitte
			for(int q = 0; q<=1; q++) {
				for(int i=0; i<=(int)this.AXISSIZE; i++) {
					Transform3D segment = new Transform3D();
					if(q==0) {
						segment.rotX(90 * Math.PI / 180.0d);
						segment.setTranslation(new Vector3d(0,i,0));
					} else if (q==1) {
						segment.rotY(90 * Math.PI / 180.0d);
						segment.setTranslation(new Vector3d(0,0,i));
					} else if (q==2){
						segment.rotX(90 * Math.PI / 180.0d);
						segment.setTranslation(new Vector3d(i,0,0));
					} else if (q==3){
						segment.rotX(90 * Math.PI / 180.0d);
						segment.setTranslation(new Vector3d(i,0,this.AXISSIZE));
					} else if (q==4){
						segment.rotZ(90 * Math.PI / 180.0d);
						segment.setTranslation(new Vector3d(this.AXISSIZE,0,i));
					}

					TransformGroup objSeg = new TransformGroup(segment);			
					Box myAxisGeo = new Box(0.0125f,0.25f,0.0125f,1, basicMaterial(1f,1f,1f));
					objSeg.addChild(myAxisGeo);
					this.objRoot.addChild(objSeg);					
				}
			}
			
			// Einheiten
			double size = 0.33d;
			for(int q = 0; q<=10; q++) {
				for(int i=0; i<=2; i++) {
				if(i==0)
						createText(new Vector3d(-1,q,-0.75d), new Vector3d(size,size,size), basicMaterial(1,1,1), String.valueOf(q+1) + ".11.09");
				if(i==1)
					if(q>0)
						createText(new Vector3d(-1,-0.5d,q), new Vector3d(size,size,size), basicMaterial(1,1,1), String.valueOf(q*100));						
				if(i==2)
					if(q>0)
						createText(new Vector3d(q,0,-1), new Vector3d(size,size,size), basicMaterial(1,1,1), String.valueOf((q+17)));
				}
			}
				
	}
	
	
	protected void createGrid() {
		for(int q=0;q<=0;q++) {
		for(int p=0;p<=1;p++){
			for(int i=0; i<=this.GRIDDENSITYFACTOR * (int)this.AXISSIZE; i++) {
				//The transformation of each grid element			
				Transform3D gridTrans = new Transform3D();
				if(p==0) {
					gridTrans.rotX(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d((double)i/(this.GRIDDENSITYFACTOR),0,this.AXISSIZE/2.0));
				}
				else if(p==1) {
					gridTrans.rotZ(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d(this.AXISSIZE/2.0,0,(double)i/(this.GRIDDENSITYFACTOR)));
				}				
				
				gridTrans.setScale(new Vector3d(0.025,this.AXISSIZE,0.025));
				TransformGroup objGrid = new TransformGroup(gridTrans);	
				Appearance myAppearance = basicMaterial(0.0f,0.0f,1.0f);
				Cylinder myGridGeo = new Cylinder(0.5f,1f, myAppearance);
				objGrid.addChild(myGridGeo);
				this.objRoot.addChild(objGrid);
			}
		}
		}
		
	}
	
	protected void createBackground(Color3f color) {
		BoundingSphere riesenkugel =
			   new BoundingSphere(
			      new Point3d(0.0d, 0.0d, 0.0d),
			      Double.MAX_VALUE);

		Background hg = new Background();
		hg.setColor(color);
		hg.setApplicationBounds(riesenkugel);
		this.objRoot.addChild(hg);
	}
	
	
	protected void addLights() {
	    
		 // gerichtetes Licht
			for (int p =0; p<=2; p++) {
		    DirectionalLight d_licht =
		       new DirectionalLight();
		    d_licht.setInfluencingBounds(new
		       BoundingSphere(new
		          Point3d(0.0d, 0.0d, 0.0d),
		          Double.MAX_VALUE));
		    d_licht.setColor(new
		       Color3f(0.88f, 0.88f, 0.88f));
		    
		    Vector3f dir = null;
		    if(p==0) {
		    	dir = new Vector3f(-1.0f, -2.0f, 1.0f);
		    } else if(p==1) {
		    	dir = new Vector3f(1.0f, 2.0f, -1.0f);
		    } else {
		    	dir = new Vector3f(1.0f, 1.0f, 1.0f);	    
		    }
		   
		    dir.normalize();
		    d_licht.setDirection(dir);
		    this.objRoot.addChild(d_licht);
			}
		    
		    // ambientes Licht
		    AmbientLight a_licht = new AmbientLight();
		    a_licht.setInfluencingBounds(new
		       BoundingSphere(new
		          Point3d(0.0d, 0.0d, 0.0d),
		          Double.MAX_VALUE));
		    a_licht.setColor(new
		       Color3f(1.0f,1.0f, 1.0f));
		    this.objRoot.addChild(a_licht);

		}
	
	
	protected Appearance basicMaterial(float x, float y, float z)
	   {
		   Appearance a = new Appearance();
		   Material mat = new Material();
		   mat.setShininess(100.0f);
		   mat.setDiffuseColor(new
		      Color3f(x, y, z));
		   mat.setSpecularColor(new
		      Color3f(x, y, z));
		   a.setMaterial(mat);
		   return a;
	   }
	
	protected void changeCameraPosition(float x, float y, float z) {
        TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        this.simpleU.getViewingPlatform().getViewPlatform();
        Vector3f translate = new Vector3f(x,y,z);
        Transform3D T3D = new Transform3D();
        T3D.setTranslation(translate);
        vpTrans.setTransform(T3D);
	}
	
	protected void changeCamtoFaceYXPlane() {
        TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        this.simpleU.getViewingPlatform().getViewPlatform();
        Transform3D T3D = new Transform3D();
        T3D.rotY(-90 * Math.PI / 180.0);
        T3D.setTranslation(new Vector3d(0,0,-2));
        vpTrans.setTransform(T3D);
	}
	
	protected TransformGroup createTransformGroup(Vector3d position, Vector3d scale) {
		Transform3D t3D = new Transform3D();
		t3D.rotY(-90 * Math.PI / 180.0d);
        t3D.setTranslation(position);
        t3D.setScale(new Vector3d(scale.x, scale.y, scale.z));
        TransformGroup objMove = new TransformGroup(t3D);
		return objMove;
	}
	


	
	private void postinitialize() {        
        // Add Antialiasing - Turn off if view is laggy
        this.simpleU.getViewer().getView().setSceneAntialiasingEnable(true);
        
        createBackground(new Color3f(0.7f, 0.7f, 0.7f));
        
        // Add scene to branch graph
        this.simpleU.addBranchGraph(this.objRoot);      
        
        this.canvas3D.setSize(800, 600);
        add(this.canvas3D);   
        pack();
        setVisible(true);
	}

	protected void createLabels() {
		double offset = 1.5;		
		createText(new Vector3d(0.0d,0.0d,this.AXISSIZE+offset), new Vector3d(0.5d,0.5d,0.5d), basicMaterial(1,1,1), "ISO");
		createText(new Vector3d(this.AXISSIZE+offset,0.0d, 0.0d), new Vector3d(0.5d,0.5d,0.5d), basicMaterial(1,1,1), "Datum");
		createText(new Vector3d(0.0d,this.AXISSIZE+offset,0.0d), new Vector3d(0.5d,0.5d,0.5d), basicMaterial(1,1,1), "Brennweite");		
	}
}
