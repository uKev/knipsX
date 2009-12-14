package org.knipsX.view.diagrams;

import java.awt.Font;
import java.awt.image.BufferedImage;

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
import org.knipsX.model.picturemanagement.Picture;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;


public abstract class JAbstract3DView extends JAbstractDiagram{

	private static final long serialVersionUID = 1L;
	
	/**
	 * The canvas in which the 3D elemtens are painted to
	 */
	protected Canvas3D canvas3D;
	
	/**
	 * This SimpleUniverse is a minimal user environment to quickly 
	 * and easily get a Java 3D program up and running. 
	 */
	protected SimpleUniverse simpleU;
	
	/**
	 * The BranchGroup serves as a pointer to the root of a scene graph branch. 
	 * This is where all of the 3D Components are stored
	 */
	protected BranchGroup objRoot;
	
	/**
	 * The size of the various axes
	 */
	protected final double AXISSIZE = 10.0;
	
	/**
	 * Specifies how dense the grid is to be drawn, 
	 * a high number indicates a high grid density
	 */
	protected final double GRIDDENSITYFACTOR = 1;
	
	
	/**
	 * Specifies the number of segments on one axis
	 */
	protected final int NUMBEROFSEGMENTS = 10;
	
	/**
	 * Specifies at which detail level the geometry is to be drawn
	 */
	protected final static int GEODETAIL = 10;
	
	/**
	 * Specifies if the text in the current view should point to the camera
	 */
	protected boolean textautorotate = true;
	
	
	/**
	 * Specified the number of axis which are added to the object root
	 */
	protected int numberofAxis = 3;
		
	/**
	 * Constructor initialized the canvas3D
	 * @param abstractModel the model from which the drawing information is taken
	 */
	public JAbstract3DView(AbstractModel abstractModel) {
		super(abstractModel);
		this.objRoot = new BranchGroup();
		this.canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        this.simpleU = new SimpleUniverse(this.canvas3D);
        
        preinitialize();
        
		generateContent();

		postinitialize();	
		
	}
	
	/**
	 * Specifies the preinitialization routine which is executed before every scence draw
	 */
	public abstract void preinitialize();
	
	/**
	 * Specifies the contents which are to be drawn in the canvas3D
	 */
	public abstract void generateContent();

	/**
	 * Creates a Cube
	 * @param position
	 * @param scale
	 * @param material
	 */
	public void createCube(Vector3d position, Vector3d scale, Appearance material){
		TransformGroup objMove = createTransformGroup(position, scale);
		Box myBox = new Box(1, 1, 1, material);
		objMove.addChild(myBox);
		this.objRoot.addChild(objMove);
	}
	
	/**
	 * Creates a Sphere
	 * @param position
	 * @param scale
	 * @param material
	 */
	public void createSphere(Vector3d position, Vector3d scale, Appearance material){
		TransformGroup objMove = createTransformGroup(position, scale);
		Sphere mySphere = new Sphere(1,Primitive.GENERATE_NORMALS,JAbstract3DView.GEODETAIL,material);
		objMove.addChild(mySphere);
		this.objRoot.addChild(objMove);
	}
	
	/**
	 * Creates Text
	 * @param position
	 * @param scale
	 * @param material
	 * @param text
	 */
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
	
	/**
	 * Creates the Axis with axis labels which are automatically placed into the root BranchGroup
	 */
	protected void createAxis() {	
			
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
				
	}
	
	/**
	 * Creates a Grid which is automatically placed into the root BranchGroup
	 */
	protected void createGrid() {
		for(int q=0;q<=0;q++) {
		for(int p=0;p<=1;p++){
			for(int i=0; i<=this.GRIDDENSITYFACTOR * (int)this.AXISSIZE; i++) {
				//The transformation of each grid element			
				Transform3D gridTrans = new Transform3D();
				if(p==0) {
					gridTrans.rotX(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d(i/(this.GRIDDENSITYFACTOR),0,this.AXISSIZE/2.0));
				}
				else if(p==1) {
					gridTrans.rotZ(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d(this.AXISSIZE/2.0,0,i/(this.GRIDDENSITYFACTOR)));
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
	
	/**
	 * Creates and assigns the specified background color to the root BranchGroup 
	 * @param color
	 */
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
	
	/**
	 * Creates and assigns a default light setup to the root BranchGroup 
	 */
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
	
	/**
	 * Creates a basicMaterial with the specified color
	 * @param x amount of red in color
	 * @param y amount of green in color
	 * @param z amount of blue in color
	 * @return
	 */
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
	
	/**
	 * Changes the camera position to the specified location
	 * @param x
	 * @param y
	 * @param z
	 */
	protected void changeCameraPosition(float x, float y, float z) {
        TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        this.simpleU.getViewingPlatform().getViewPlatform();
        Vector3f translate = new Vector3f(x,y,z);
        Transform3D T3D = new Transform3D();
        T3D.setTranslation(translate);
        vpTrans.setTransform(T3D);
	}
	
	/**
	 * Changes the camera position and orientation so that is faced the xy plane
	 */
	protected void changeCamtoFaceYXPlane() {
        TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        this.simpleU.getViewingPlatform().getViewPlatform();
        Transform3D T3D = new Transform3D();
        T3D.rotY(-90 * Math.PI / 180.0);
        T3D.setTranslation(new Vector3d(0,this.AXISSIZE/2.0,this.AXISSIZE/2.0));
        vpTrans.setTransform(T3D);
	}
	
	/**
	 * Creates a TransformGroup with the specified positon and scale
	 * @param position
	 * @param scale
	 * @return
	 */
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
	
/**
 * Specifies the labels that are displayed next to each axis
 * @param xAxis The label of the xAxis
 * @param zAxis The label of the zAxis
 * @param yAxis The label of the yAxis
 */
	protected void createLabels(String xAxis, String zAxis, String yAxis) {
		double offset = 1.5;	
		Appearance myapp = basicMaterial(1,1,1);
		createText(new Vector3d(0.0d,0.0d,AXISSIZE+offset), new Vector3d(1,1,1), myapp, xAxis);
		createText(new Vector3d(AXISSIZE+offset,0.0d,0.0), new Vector3d(1,1,1), myapp, yAxis);
		createText(new Vector3d(0,AXISSIZE+offset,0), new Vector3d(1,1,1), myapp, zAxis);		
	}
	
	
	
	/**
	 * Generates a string array which contains the segment description of one axis.
	 * The array will have the length of NUMBEROFSEGMENTS.
	 * @param minValue the minimum value which will be placed at the origin of the axis
	 * @param maxValue the maxium value of the axis
	 */
	protected String[] generateSegmentDescription(Object minValue, Object maxValue) {
		String[] returnstring = {"", "asas"};
		return returnstring;
	}
	
	
	
	/**
	 * Defines the Description of each Segment of an axis. Note that the String Arrays
	 * you pass in here have length of NUMBEROFSEGMENTS
	 * @param xAxis the string array which represent the xAxis
	 * @param zAxis the string array which represent the zAxis
	 * @param yAxis the string array which represent the yAxis
	 */
	protected void setSegmentDescription(String[] xAxis, String[] zAxis, String[] yAxis ) {
		
		// Einheiten
		double size = 0.33d;
		for(int q = 0; q<= NUMBEROFSEGMENTS - 1; q++) {
			
			for(int i=0; i<= this.numberofAxis - 1 ; i++) {
			if(i==0)
				createText(new Vector3d(-1,q,-0.75d), new Vector3d(size,size,size), basicMaterial(1,1,1), xAxis[q]);
			if(i==1)
				if(q>0)
				createText(new Vector3d(-1,-0.5d,q), new Vector3d(size,size,size), basicMaterial(1,1,1), zAxis[q]);						
			if(i==2)
				if(q>0)
				createText(new Vector3d(q,0,-1), new Vector3d(size,size,size), basicMaterial(1,1,1), yAxis[q]);
			}
		}	
	}
	
	/**
	 * Sets the current picture which is displayed outside of the 3D View with the
	 * specified exif parameters
	 * @param pic
	 */
	public void setCurrentDescription(Picture pic) {
		//TODO
	}
	
	@Override
	BufferedImage getDiagramScreenshot() {
		// TODO Auto-generated method stub
		return null;
	}
}
