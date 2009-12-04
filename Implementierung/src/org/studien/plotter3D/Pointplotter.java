package org.studien.plotter3D;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.pickfast.PickCanvas;
import com.sun.j3d.utils.universe.*;
import java.util.Random;
import javax.media.j3d.*;
import javax.vecmath.*;
 

public class Pointplotter extends MouseAdapter {	

	private static final long serialVersionUID = 1L;
	private static final double AXISSIZE = 10.0;
	private static final double BOXDENSITYFACTOR = 0.25;
	private static final double GRIDDENSITYFACTOR = 1;
	private static final int DATAAMOUNT = 100;
	private static final int GEODETAIL = 10;
	private static final BranchGroup OBJROOT = new BranchGroup();
	private static Canvas3D canvas3D = null;
	private static SimpleUniverse simpleU = null;
	private PickCanvas pickCanvas;
	private PointPlotterWindow frame;
	
	Appearance basicMaterial(float x, float y, float z)
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

	
	private void createAxis() {	
		
		for(int i = 0; i <= 2; i++) {
			
			//The transformation information of the axis			
			Transform3D axistrans = new Transform3D();
			
			if(i == 0) {
				axistrans.rotX(90 * Math.PI / 180.0d);
				axistrans.setTranslation(new Vector3d(0,0,Pointplotter.AXISSIZE/2.0));
			} else if(i == 1) {
				axistrans.rotY(90 * Math.PI / 180.0d);
				axistrans.setTranslation(new Vector3d(0,Pointplotter.AXISSIZE/2.0,0));
			} else {
				axistrans.rotZ(90 * Math.PI / 180.0d);
				axistrans.setTranslation(new Vector3d(Pointplotter.AXISSIZE/2.0,0,0));
			}			
			
			axistrans.setScale(new Vector3d(0.15d,Pointplotter.AXISSIZE,0.15d));
			
			// Create transformation group
			TransformGroup objAxis = new TransformGroup(axistrans);		
			
			Appearance myAppearance = basicMaterial(0.0f,0.0f,0.0f);
		    		          
			Cylinder myAxisGeo = new Cylinder(0.3f,1f, myAppearance);
			
			objAxis.addChild(myAxisGeo);
			
			// Add both groups to the root
			Pointplotter.OBJROOT.addChild(objAxis);
			
		}
		
	// Abschnitte
	for(int q = 0; q<=4; q++) {
		for(int i=0; i<=10; i++) {
			Transform3D segment = new Transform3D();
			if(q==0) {
				segment.rotZ(90 * Math.PI / 180.0d);
				segment.setTranslation(new Vector3d(0,i,0));
			} else if (q==1) {
				segment.rotX(90 * Math.PI / 180.0d);
				segment.setTranslation(new Vector3d(i,0,0));
			} else if (q==2){
				segment.rotZ(90 * Math.PI / 180.0d);
				segment.setTranslation(new Vector3d(0,0,i));
			} else if (q==3){
				segment.rotX(90 * Math.PI / 180.0d);
				segment.setTranslation(new Vector3d(i,0,Pointplotter.AXISSIZE));
			} else if (q==4){
				segment.rotZ(90 * Math.PI / 180.0d);
				segment.setTranslation(new Vector3d(Pointplotter.AXISSIZE,0,i));
			}

			TransformGroup objSeg = new TransformGroup(segment);			
			Box myAxisGeo = new Box(0.0125f,0.5f,0.0125f,1, basicMaterial(1f,1f,1f));
			objSeg.addChild(myAxisGeo);
			Pointplotter.OBJROOT.addChild(objSeg);		
			
		}
	}
	
	// Einheiten
	for(int q = 0; q<=10; q++) {
		for(int i=0; i<=2; i++) {
		if(i==0)
			createSingleText(new Vector3d(-1,q,0), String.valueOf(q+1) + ".11.09",0.5);
		if(i==1)
			if(q>0)
				createSingleText(new Vector3d(q,0,-1), String.valueOf((q+17)*2),0.5);
		if(i==2)
			if(q>0)
				createSingleText(new Vector3d(-1,0,q), String.valueOf(q*100),0.3);
		}
	}

		
		
	
	}
	
	private void createGrid() {
		for(int q=0;q<=0;q++) {
		for(int p=0;p<=1;p++){
			for(int i=0; i<=Pointplotter.GRIDDENSITYFACTOR * (int)Pointplotter.AXISSIZE; i++) {
				//The transformation of each grid element			
				Transform3D gridTrans = new Transform3D();
				if(p==0) {
					gridTrans.rotX(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d((double)i/(Pointplotter.GRIDDENSITYFACTOR),0,Pointplotter.AXISSIZE/2.0));
				}
				else if(p==1) {
					gridTrans.rotZ(90 * Math.PI / 180.0d);
					gridTrans.setTranslation(new Vector3d(Pointplotter.AXISSIZE/2.0,0,(double)i/(Pointplotter.GRIDDENSITYFACTOR)));
				}				
				
				gridTrans.setScale(new Vector3d(0.025,Pointplotter.AXISSIZE,0.025));
				TransformGroup objGrid = new TransformGroup(gridTrans);	
				Appearance myAppearance = basicMaterial(0.0f,0.0f,1.0f);
				Cylinder myGridGeo = new Cylinder(0.5f,1f, myAppearance);
				objGrid.addChild(myGridGeo);
				Pointplotter.OBJROOT.addChild(objGrid);
			}
		}
		}
		
	}
	
	private void createBackground(Color3f color) {
		BoundingSphere riesenkugel =
			   new BoundingSphere(
			      new Point3d(0.0d, 0.0d, 0.0d),
			      Double.MAX_VALUE);

		Background hg = new Background();
		hg.setColor(color);
		hg.setApplicationBounds(riesenkugel);
		Pointplotter.OBJROOT.addChild(hg);
	}
	
	private void createSingleText(Vector3d pos, String text, double size) {
		Transform3D t3D = new Transform3D();
        t3D.setTranslation(pos);
        t3D.setScale(new Vector3d(size, size, size));
        TransformGroup objMove = new TransformGroup(t3D);
        Pointplotter.OBJROOT.addChild(objMove);
        
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
		
        // Create a simple shape leaf node, add it to the scene graph.
        Font3D font3D = new Font3D(new Font("Arial", Font.PLAIN, 1),
                                   new FontExtrusion());
        Text3D textGeom = new Text3D(font3D, new String(text));
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D textShape = new Shape3D();
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppear);
        objSpin.addChild(textShape);
        Pointplotter.OBJROOT.addChild(mybillboard);
	}
	
	private void createLabels() {
		double offset = 1.5;		
		createSingleText(new Vector3d(0.0d,0.0d,Pointplotter.AXISSIZE+offset), "ISO",0.5);
		createSingleText(new Vector3d(Pointplotter.AXISSIZE+offset,0.0d,0.0), "Brennweite",0.5);
		createSingleText(new Vector3d(0,Pointplotter.AXISSIZE+offset,0), "Datum",0.5);		
	}
	
	private void makeInteractive() {
        ViewingPlatform viewingPlatform = simpleU.getViewingPlatform();        
    	
    	OrbitBehavior orbit = new OrbitBehavior(Pointplotter.canvas3D,
				OrbitBehavior.REVERSE_ALL);
    	BoundingSphere bounds =
    	    new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    	orbit.setSchedulingBounds(bounds);
    	orbit.setRotationCenter(new Point3d(Pointplotter.AXISSIZE/2.0,0,Pointplotter.AXISSIZE/2.0));
    	orbit.setZoomFactor(3);
    	viewingPlatform.setViewPlatformBehavior(orbit); 
	}


	private void changeCameraPosition(SimpleUniverse simpleU, float x, float y, float z) {
        TransformGroup vpTrans = simpleU.getViewingPlatform().getViewPlatformTransform();
        Vector3f translate = new Vector3f(x,y,z);
        Transform3D T3D = new Transform3D();
        T3D.setTranslation(translate);
        vpTrans.setTransform(T3D);
	}
	
	public class myShape3D extends Shape3D {
		public int id = new Random().nextInt();	
		
		public myShape3D(float x, float y, float z) {	        
	        Sphere mySphere = new Sphere(0.075f*2,Sphere.GENERATE_NORMALS,Pointplotter.GEODETAIL);
	        addGeometry(mySphere.getShape().getGeometry());
	        setAppearance(basicMaterial(x,y,z));
		}
		
	}
	
	private void insertData() {
		for(int i=0; i<=Pointplotter.DATAAMOUNT; i++) {
			
			Random random = new Random();			
			
			Transform3D dataTrans = new Transform3D();
			
			float myfloat = (float)random.nextDouble();
			
			
			dataTrans.setTranslation(new Vector3d(random.nextDouble()*Pointplotter.AXISSIZE,random.nextDouble()*Pointplotter.AXISSIZE,random.nextDouble()*Pointplotter.AXISSIZE));
			
			// Create transformation group
			TransformGroup objData = new TransformGroup(dataTrans);	
			objData.setCapability(PickInfo.PICK_GEOMETRY );		
			objData.addChild(new myShape3D(myfloat,myfloat+myfloat,myfloat));
			
			
			// Picking			
		     pickCanvas = new PickCanvas(Pointplotter.canvas3D, Pointplotter.OBJROOT);
		     pickCanvas.setMode(PickInfo.PICK_GEOMETRY); 
			
			// Add both groups to the root
			Pointplotter.OBJROOT.addChild(objData);
		}
		
	}
	
	private void addLights() {
	    
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
	    Pointplotter.OBJROOT.addChild(d_licht);
		}
	    
	    // ambientes Licht
	    AmbientLight a_licht = new AmbientLight();
	    a_licht.setInfluencingBounds(new
	       BoundingSphere(new
	          Point3d(0.0d, 0.0d, 0.0d),
	          Double.MAX_VALUE));
	    a_licht.setColor(new
	       Color3f(1.0f,1.0f, 1.0f));
	    Pointplotter.OBJROOT.addChild(a_licht);

	}
	
	private void addSingleBar(double z) {
		Random random = new Random();	
		for(int p=0; p<(int)Pointplotter.AXISSIZE*(1/Pointplotter.BOXDENSITYFACTOR);p++) {
		float heigth = random.nextInt(4);
		Transform3D dataTrans = new Transform3D();
		dataTrans.setTranslation(new Vector3d(Pointplotter.BOXDENSITYFACTOR*(p+0.5),heigth,z+0.5));
		TransformGroup objData = new TransformGroup(dataTrans);	
		Box myBox = new Box((float) (0.5 * Pointplotter.BOXDENSITYFACTOR),heigth,(float) (0.5 * Pointplotter.BOXDENSITYFACTOR),1,basicMaterial( (float)random.nextDouble(), (float)random.nextDouble(), (float)random.nextDouble()  ));
		objData.addChild(myBox);	
		Pointplotter.OBJROOT.addChild(objData);
			
		}
		
	}
	
	private void addAllBars() {
		for(int p = 0; p<(int)Pointplotter.AXISSIZE*(1/Pointplotter.BOXDENSITYFACTOR); p++) {
			addSingleBar(p*Pointplotter.BOXDENSITYFACTOR);
		}
	}
	
	public BranchGroup createSceneGraph(Canvas3D canvas3d) {
		// Create the root of the branch graph
		BranchGroup objRoot = Pointplotter.OBJROOT;
		Pointplotter.canvas3D = canvas3d; 
			
		createAxis();
		
		createGrid();
		
		createBackground(new Color3f(0.7f, 0.7f, 0.7f));
		
		createLabels();

		//insertData();
		
		addAllBars();
		
		addLights();		
		
		// Let Java 3D perform optimizations on this scene graph.
	        objRoot.compile();	       
	   
		return objRoot;		
		
    }
	
    public Pointplotter() {
    	
    	this.frame = new PointPlotterWindow();
    	
        Pointplotter.canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration()); 

        BranchGroup scene = createSceneGraph(canvas3D);

        // SimpleUniverse is a Convenience Utility class
        Pointplotter.simpleU = new SimpleUniverse(canvas3D);
        
        int goodcamerapos = (int)Pointplotter.AXISSIZE/2;
        changeCameraPosition(simpleU,goodcamerapos, goodcamerapos, goodcamerapos*6);
        
        // Add Antialiasing - Turn off if view is laggy
        simpleU.getViewer().getView().setSceneAntialiasingEnable(true);
        
        // Create Behavior for interacting with scene
        makeInteractive();

        // Add scene to branch graph
        simpleU.addBranchGraph(scene);
        
        // Assign listener to MouseAdapter
        canvas3D.addMouseListener(this);
        
        canvas3D.setSize(800, 600);
        frame.add(canvas3D);   
        frame.pack();
        frame.setVisible(true);
        

    } 

    
    public void mouseClicked(MouseEvent e)

    {

        pickCanvas.setShapeLocation(e);

        PickInfo result = pickCanvas.pickClosest();

        if (result == null) {

           System.out.println("Nothing picked");

        } else {

           myShape3D p = (myShape3D) result.getNode();           
           
           Transform3D mytrans = new Transform3D();
           result.getNode().getLocalToVworld(mytrans);
           Point3d mypoint = new Point3d();
           mytrans.transform(mypoint);
           
           frame.xLabel.setText(String.valueOf((int)mypoint.x));
           frame.yLabel.setText(String.valueOf((int)mypoint.y));
           frame.zLabel.setText(String.valueOf((int)mypoint.z));
           

           if (p != null) {
              System.out.println(p.id);    
           } else{
              System.out.println("null");
           }

        }

    }   
    
    public static void main(String[] args) {
        new Pointplotter();
    } 

}


