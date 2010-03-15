package org.knipsX.view.diagrams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.controller.diagrams.View3DClickController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Resource;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.pickfast.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * This class manages all java 3d interactions and offers methods than simplify the
 * utilization of the java 3d functions significantly.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 *            the model which is to be drawn
 */
public abstract class JAbstract3DView<M extends AbstractReportModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 1305626160044284511L;

    /** The canvas in which the 3D elemtens are painted to. */
    protected Canvas3D canvas3D;

    /** This SimpleUniverse is a minimal user environment to quickly and easily get a Java 3D program up and running. */
    protected SimpleUniverse simpleUniverse;

    /**
     * The BranchGroup serves as a pointer to the root of a scene graph branch. This is where all of the 3D Components
     * are stored.
     */
    protected BranchGroup objRoot;

    /** Specifies how dense the grid is to be drawn, a high number indicates a high grid density. */
    protected final static double GRIDDENSITYFACTOR = 10;

    /** Specifies at which level of detail the geometry is to be drawn. */
    protected final static int GEODETAIL = 10;

    /** Specifies the number of axes used. */
    protected int numberOfAxes = 3;

    /** Specifies if the text in the current view should point to the camera. */
    protected boolean textAutoRotate = true;

    /** Specifies the off screen scale of the off screen canvas3d. */
    private static final int OFF_SCREEN_SCALE = 2;

    /** Specifies the left Panel which is visible in diagram view. */
    protected JPanel leftPanel;

    /** Specifies the right Panel which is visible in diagram view. */
    protected JPanel rightPanel;

    /** Stores the reference to the OffScreenCanvas3D object. */
    private OffScreenCanvas3D offScreenCanvas3D;

    /**
     * Create three axis3D objects for each axis one axis3D object.
     * Note that all three axes have to be initialized even if only
     * two axis are displayed.
     */
    private final Axis3D[] axis3D = { new Axis3D(), new Axis3D(), new Axis3D() };

    /** Stores the current perspective. */
    private int currentPerspective = 0;

    /** The pick canvas which realizes object picking in the 3D view. */
    private PickCanvas pickCanvas;

    /** The root transform group. */
    protected TransformGroup rootTransform;

    /** Specifies if the grid should be drawn. */
    protected boolean showGrid = true;

    /** Specifies if the buffered range on each axis should be used. Look at the axis3d class for more information. */
    protected static boolean useBufferRange = true;

    /** The default perspective which executed set in the postinitialized() method. */
    protected Perspectives perspective = Perspectives.PERSPECTIVE;

    /** Initialize the logger **/
    private Logger log = Logger.getLogger(this.getClass());
    
    /**
     * Constructor initialized the canvas3D.
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportID
     *            the report id of the report.
     */
    public JAbstract3DView(final M model, final int reportID) {
        super(model, reportID);

        /* initialize the object root branch group. All elements are in the view are added to this group */
        this.objRoot = new BranchGroup();

        /* initialize the root transform group */
        this.rootTransform = new TransformGroup();
        this.rootTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        this.rootTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        /* initialize the canvas 3D */
        this.canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        /* initialize the universe of the 3D view */
        this.simpleUniverse = new SimpleUniverse(this.canvas3D);

       	try {        
	        /* call the preinitialization routine */
	        this.preInitialize();
	
	        /* call the generateContent routine which is implemented by every diagram type */
	        this.generateContent();
	
	        /* call the postinitialization routine */
	        this.postInitialize();
       	}
       	catch (IllegalArgumentException e) {
    		/* catch various java 3d exceptions. See issue #188 */
    		this.log.error(e.toString());    		
    	}
        
    }

    /** Creates and assigns a default light setup to the root BranchGroup. */
    protected void addLights() {

        /* directional light */
        for (int p = 0; p <= 2; ++p) {
            final DirectionalLight dLight = new DirectionalLight();
            dLight.setInfluencingBounds(new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE));
            dLight.setColor(new Color3f(0.88f, 0.88f, 0.88f));

            Vector3f dir = null;
            if (p == 0) {
                dir = new Vector3f(1.0f, 2.0f, -1.0f);
            } else if (p == 1) {
                dir = new Vector3f(-1.0f, -2.0f, 1.0f);
            } else {
                dir = new Vector3f(-1.0f, -1.0f, -1.0f);
            }
            dir.normalize();
            dLight.setDirection(dir);
            this.rootTransform.addChild(dLight);
        }

        /* ambient light */
        final AmbientLight aLight = new AmbientLight();
        aLight.setInfluencingBounds(new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE));
        aLight.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        this.rootTransform.addChild(aLight);
    }

    /**
     * Creates a basicMaterial with the specified color.
     * 
     * @param red
     *            the amount of red of the material.
     * @param green
     *            the amount of green of the material.
     * @param blue
     *            the amount of blue of the material.
     * 
     * @return the appearance object with the specified color.
     */
    protected Appearance basicMaterial(final float red, final float green, final float blue) {
        final Material material = new Material();
        material.setShininess(100.0f);
        material.setDiffuseColor(new Color3f(red, green, blue));
        material.setSpecularColor(new Color3f(red, green, blue));

        final Appearance appearance = new Appearance();
        appearance.setMaterial(material);
        return appearance;
    }

    /**
     * Creates a basicMaterial with the specified color.
     * 
     * @param color
     *            the color of the material.
     * 
     * @return the appearance object with the specified color.
     */
    protected Appearance basicMaterial(final Color color) {
        final Material material = new Material();
        material.setShininess(100.0f);
        material.setDiffuseColor(new Color3f(color));
        material.setSpecularColor(new Color3f(color));

        final Appearance appearance = new Appearance();
        appearance.setMaterial(material);
        return appearance;
    }

    /**
     * Changes the camera position to the specified location.
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     * @param z
     *            the z coordinate.
     */
    protected void changeCameraPosition(final float x, final float y, final float z) {
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3f(x, y, z));

        final TransformGroup vpTrans = this.simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vpTrans.setTransform(transform3D);
    }

    /* change the camera to a position where it faces the x z plane */
    private void changeCamtoFaceXZPlane() {
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(-90 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(-2 * this.getYAxis().getAxisSize(),
                this.getXAxis().getAxisSize() / 2.0, this.getZAxis().getAxisSize() / 2));

        final TransformGroup vpTrans = this.simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vpTrans.setTransform(transform3D);
    }

    /* change the camera to a position where it faces the y x plane */
    private void changeCamtoFaceYXPlane() {
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(this.getXAxis().getAxisSize() / 2.0,
                this.getYAxis().getAxisSize() / 2.0, 3 * this.getYAxis().getAxisSize()));

        final TransformGroup vpTrans = this.simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vpTrans.setTransform(transform3D);
    }

    /* change the camera to a position where it faces the y z plane */
    private void changeCamtoFaceYZPlane() {
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(180 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(this.getYAxis().getAxisSize() / 2.0,
                this.getXAxis().getAxisSize() / 2.0, -2 * this.getXAxis().getAxisSize()));

        final TransformGroup vpTrans = this.simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vpTrans.setTransform(transform3D);
    }

    /* change the camera to a position where it shows all of the important scene elements */
    private void changeCamtoPerspective() {
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(60 * Math.PI / 180.0);
        transform3D.rotX(-12.5 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(this.getYAxis().getAxisSize() / 2.0,
                2 * this.getXAxis().getAxisSize() / 2.0, 3 * this.getZAxis().getAxisSize()));

        final TransformGroup vpTrans = this.simpleUniverse.getViewingPlatform().getViewPlatformTransform();
        vpTrans.setTransform(transform3D);
    }

    /**
     * Creates the axes which are automatically placed into the root BranchGroup.
     * To change the number of axis you must change the numberOfAxes variable
     * before calling this function.
     * 
     */
    protected void createAxis() {

        for (int i = 0; i < this.numberOfAxes; ++i) {

            /* the transformation information of the axis */
            final Transform3D axisTrans = new Transform3D();

            if (i == 2) {

                /* The z axis */
                axisTrans.rotX(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(0, 0, this.getZAxis().getAxisSize() / 2.0));
                axisTrans.setScale(new Vector3d(0.15d, this.getZAxis().getAxisSize(), 0.15d));
            } else if (i == 0) {

                /* The y axis */
                axisTrans.rotY(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(0, this.getYAxis().getAxisSize() / 2.0, 0));
                axisTrans.setScale(new Vector3d(0.15d, this.getYAxis().getAxisSize(), 0.15d));
            } else if (i == 1) {

                /* The x axis */
                axisTrans.rotZ(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(this.getXAxis().getAxisSize() / 2.0, 0, 0));
                axisTrans.setScale(new Vector3d(0.15d, this.getXAxis().getAxisSize(), 0.15d));
            }
            final Appearance appearance = this.basicMaterial(0.0f, 0.0f, 0.0f);

            final Cylinder axisGeo = new Cylinder(0.3f, 1f, appearance);

            /* create transformation group */
            final TransformGroup objAxis = new TransformGroup(axisTrans);
            objAxis.addChild(axisGeo);

            /* add both groups to the root */
            this.rootTransform.addChild(objAxis);
        }

        /* create ticks for each axis */
        for (int i = 0; i < this.numberOfAxes; ++i) {

            for (int j = 1; j < this.axis3D[i].getNumberOfSegments(); ++j) {
                final Transform3D segment = new Transform3D();
                final double normDistance = j * this.axis3D[i].getSegmentSize();

                if ((i == 0) && this.getYAxis().isShowSegments()) {

                    /* y-axis */
                    segment.rotX(90 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(0, normDistance, 0));
                } else if ((i == 2) && this.getZAxis().isShowSegments()) {

                    /* z-axis */
                    segment.rotY(90 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(0, 0, normDistance));
                } else if ((i == 1) && this.getXAxis().isShowSegments()) {

                    /* x-axis */
                    segment.rotX(0 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(normDistance, 0, 0));
                }

                if (this.axis3D[i].isShowSegments()) {
                    final Box axisGeo = new Box(0.0125f, 0.25f, 0.0125f, 1, this.basicMaterial(1f, 1f, 1f));

                    final TransformGroup objSeg = new TransformGroup(segment);
                    objSeg.addChild(axisGeo);

                    this.rootTransform.addChild(objSeg);
                }
            }
        }

        /* create axis arrows */
        for (int i = 0; i < this.numberOfAxes; ++i) {

            final Transform3D coneTransformation = new Transform3D();
            Cone axisArrow = null;

            /* The ratio between cone length and axis size */
            final float percentage = 1.0f / 20.0f;

            double normDistance = 0;

            if (i == 1) {

                /* x axis */
                coneTransformation.rotZ(270 * Math.PI / 180.0d);
                normDistance = 0.5 * this.getXAxis().getAxisSize() * percentage + this.getXAxis().getAxisSize();
                coneTransformation.setTranslation(new Vector3d(normDistance, 0, 0));
                axisArrow = new Cone(0.125f, (float) this.getXAxis().getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            } else if (i == 0) {

                /* y axis */
                normDistance = 0.5 * this.getYAxis().getAxisSize() * percentage + this.getYAxis().getAxisSize();
                coneTransformation.setTranslation(new Vector3d(0, normDistance, 0));
                axisArrow = new Cone(0.125f, (float) this.getYAxis().getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            } else if (i == 2) {

                /* z axis */
                normDistance = 0.5 * this.getZAxis().getAxisSize() * percentage + this.getZAxis().getAxisSize();
                coneTransformation.rotX(90 * Math.PI / 180.0d);
                coneTransformation.setTranslation(new Vector3d(0, 0, normDistance));
                axisArrow = new Cone(0.125f, (float) this.getZAxis().getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            }
            final TransformGroup axisArrowTG = new TransformGroup(coneTransformation);
            axisArrowTG.addChild(axisArrow);

            this.rootTransform.addChild(axisArrowTG);
        }
    }

    /**
     * Creates and assigns the specified background color to the root BranchGroup.
     * 
     * @param color
     *            the background color.
     */
    protected void createBackground(final Color3f color) {
        final BoundingSphere bigSphere = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE);

        final Background hg = new Background();
        hg.setColor(color);
        hg.setApplicationBounds(bigSphere);

        this.rootTransform.addChild(hg);
    }

    /**
     * Creates a Cube at the desired position. Note that this cube,
     * when scaling on the y axis, doesn't grow uniformly, it rather
     * grows only in the positive y direction.
     * 
     * @param position
     *            the position of the cube.
     * @param scale
     *            the scale value of the cube.
     * @param material
     *            the material of the cube.
     */
    public void createCube(final Vector3d position, final Vector3d scale, final Appearance material) {

        /* change the position to the right value */
        final Vector3d newPosition = new Vector3d(position);
        newPosition.setY((scale.y) / 2 + position.y);

        /* change the scale factor to the right value */
        final Vector3d newScale = new Vector3d(scale);
        newScale.setY(scale.y / 2);

        final TransformGroup objMove = this.createTransformGroup(newPosition, newScale);
        objMove.addChild(new Box(1, 1, 1, material));

        this.rootTransform.addChild(objMove);
    }

    /** Creates a grid which is automatically placed into the root BranchGroup. */
    protected void createGrid() {
        final TransformGroup gridTransform = this.createTransformGroup(new Vector3d(0, 0, 0), new Vector3d(1, 1, 1));

        if (this.numberOfAxes == 2) {
            final Transform3D tempTransform = new Transform3D();
            gridTransform.getTransform(tempTransform);
            tempTransform.rotZ(90 * Math.PI / 180.0d);
            gridTransform.setTransform(tempTransform);
        }

        for (int i = 0; i <= 0; ++i) {

            for (int j = 0; j <= 1; ++j) {

                for (int k = 0; k <= JAbstract3DView.GRIDDENSITYFACTOR; ++k) {

                    /* the transformation of each grid element */
                    final Transform3D currentGridTransform = new Transform3D();

                    if (j == 0) {
                        currentGridTransform.rotX(90 * Math.PI / 180.0d);
                        currentGridTransform.setTranslation(new Vector3d(
                                (k * this.getYAxis().getAxisSize() / JAbstract3DView.GRIDDENSITYFACTOR), 0, this
                                        .getYAxis().getAxisSize() / 2.0));
                        currentGridTransform.setScale(new Vector3d(0.025, this.getYAxis().getAxisSize(), 0.025));
                    } else if (j == 1) {
                        currentGridTransform.rotZ(90 * Math.PI / 180.0d);
                        currentGridTransform.setTranslation(new Vector3d(this.getXAxis().getAxisSize() / 2.0, 0, k
                                * this.getXAxis().getAxisSize() / JAbstract3DView.GRIDDENSITYFACTOR));
                        currentGridTransform.setScale(new Vector3d(0.025, this.getXAxis().getAxisSize(), 0.025));
                    }
                    final Appearance appearance = this.basicMaterial(0.0f, 0.0f, 1.0f);
                    final Cylinder gridGeo = new Cylinder(0.5f, 1f, appearance);
                    final TransformGroup objGrid = new TransformGroup(currentGridTransform);
                    objGrid.addChild(gridGeo);

                    gridTransform.addChild(objGrid);
                }
            }
        }
        this.rootTransform.addChild(gridTransform);
    }

    /**
     * Specifies the labels that are displayed next to each axis.
     * 
     * @param xAxis
     *            The label of the xAxis.
     * @param zAxis
     *            The label of the zAxis.
     * @param yAxis
     *            The label of the yAxis.
     */
    protected void createLabels(final String xAxis, final String yAxis, final String zAxis) {
        final double offset = 2.5d;
        final double size = 0.42d;
        final Appearance appearance = this.basicMaterial(1, 1, 1);

        this.createText(new Vector3d(offset + this.getXAxis().getAxisSize(), 0, 0), new Vector3d(size, size, size),
                appearance, xAxis);
        this.createText(new Vector3d(0, 0, offset + this.getZAxis().getAxisSize()), new Vector3d(size, size, size),
                appearance, zAxis);
        this.createText(new Vector3d(0, offset + this.getYAxis().getAxisSize(), 0), new Vector3d(size, size, size),
                appearance, yAxis);
    }

    private OffScreenCanvas3D createOffScreenCanvas(final Canvas3D onScreenCanvas3D) {

        /* Create the off-screen Canvas3D object. Request an off-screen Canvas3D with a single buffer configuration */
        final GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
        template.setDoubleBuffer(GraphicsConfigTemplate.UNNECESSARY);

        final GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getBestConfiguration(template);

        this.offScreenCanvas3D = new OffScreenCanvas3D(gc, true);

        /* Set the off-screen size based on a scale factor times the on-screen size. */
        final Screen3D sOn = onScreenCanvas3D.getScreen3D();
        final Screen3D sOff = this.offScreenCanvas3D.getScreen3D();
        final Dimension dim = sOn.getSize();

        dim.width *= JAbstract3DView.OFF_SCREEN_SCALE;
        dim.height *= JAbstract3DView.OFF_SCREEN_SCALE;

        sOff.setSize(dim);
        sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth() * JAbstract3DView.OFF_SCREEN_SCALE);
        sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight() * JAbstract3DView.OFF_SCREEN_SCALE);

        /* attach the off-screen canvas to the view */
        this.simpleUniverse.getViewer().getView().addCanvas3D(this.offScreenCanvas3D);

        return this.offScreenCanvas3D;
    }

    /**
     * Creates a Sphere.
     * 
     * @param position
     *            the position of the sphere.
     * @param scale
     *            the scale value of the sphere.
     * @param material
     *            the material of the sphere.
     */
    public void createSphere(final Vector3d position, final Vector3d scale, final Appearance material) {
        final TransformGroup objMove = this.createTransformGroup(position, scale);
        objMove.addChild(new Sphere(1, Primitive.GENERATE_NORMALS, JAbstract3DView.GEODETAIL, material));

        this.rootTransform.addChild(objMove);
    }

    /**
     * Creates Text.
     * 
     * @param position
     *            the position of the text.
     * @param scale
     *            the scale of the text.
     * @param material
     *            the material of the text.
     * @param text
     *            the text itself.
     */
    protected void createText(final Vector3d position, final Vector3d scale, final Appearance material,
            final String text) {
        final TransformGroup objSpin = new TransformGroup();
        final Billboard mybillboard = new Billboard(objSpin);
        mybillboard.setSchedulingBounds(new BoundingSphere());
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        final TransformGroup objMove = this.createTransformGroup(position, scale);
        objMove.addChild(objSpin);

        final Appearance textAppearance = new Appearance();
        final ColoringAttributes textColor = new ColoringAttributes();
        textColor.setColor(1.0f, 1.0f, 1.0f);
        textAppearance.setColoringAttributes(textColor);

        final Font3D font3D = new Font3D(new Font(Messages.getString("JAbstract3DView.0"), Font.PLAIN, 1),
                new FontExtrusion());
        final Text3D textGeom = new Text3D(font3D, new String(text));
        final Shape3D textShape = new Shape3D();
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppearance);

        if (this.textAutoRotate) {
            objSpin.addChild(textShape);
            this.objRoot.addChild(mybillboard);
        } else {
            objMove.addChild(textShape);
        }
        this.rootTransform.addChild(objMove);
    }

    /**
     * Creates a TransformGroup with the specified position and scale.
     * 
     * @param position
     *            the position of the TransformGroup.
     * @param scale
     *            the scale of the TransformGroup.
     * @return The transform group of the specified position and scale.
     */
    protected TransformGroup createTransformGroup(final Vector3d position, final Vector3d scale) {
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(position);
        transform3D.setScale(new Vector3d(scale.x, scale.y, scale.z));

        return new TransformGroup(transform3D);
    }

    /** Specifies the contents which are to be drawn in the canvas3D. */
    public abstract void generateContent();

    @Override
    public Component getDiagram() {
        return this.canvas3D;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {
        final Point location = this.canvas3D.getLocationOnScreen();
        this.offScreenCanvas3D.setOffScreenLocation(location);

        final Dimension dim = this.canvas3D.getSize();
        dim.width *= JAbstract3DView.OFF_SCREEN_SCALE;
        dim.height *= JAbstract3DView.OFF_SCREEN_SCALE;

        return this.offScreenCanvas3D.doRender(dim.width, dim.height);
    }

    /**
     * Returns the pick canvas of the current 3D view.
     * 
     * @return the pick canvas object.
     */
    public PickCanvas getPickCanvas() {
        return this.pickCanvas;
    }

    /** Cycles to the next view */
    public void nextPerspective() {

        if (this.currentPerspective < Perspectives.values().length - 1) {
            this.currentPerspective++;
        } else {
            this.currentPerspective = 0;
        }
        this.setCameraPerspective(Perspectives.values()[this.currentPerspective]);
    }

    private void postInitialize() {

        /* add antialiasing - turn off if view is laggy */
        this.simpleUniverse.getViewer().getView().setSceneAntialiasingEnable(true);

        /* ensure at least 5 msec per frame (i.e., < 200Hz) */
        this.simpleUniverse.getViewer().getView().setMinimumFrameCycleTime(5);

        /*
         * set the back clipping plane to a relative high value to ensure that
         * the view is drawn even if the view is zoomed out very far
         */
        this.canvas3D.getView().setBackClipDistance(500);

        this.createBackground(new Color3f(0.7f, 0.7f, 0.7f));

        if (this.showGrid) {
            this.createGrid();
        }

        /* set the camera perspective */
        this.perspective = Perspectives.PERSPECTIVE;

        this.createAxis();
        this.createSegmentDescription();
        this.createLabels(this.getXAxis().getDescription(), this.getYAxis().getDescription(), this.getZAxis()
                .getDescription());

        /* create the off-screen Canvas3D object */
        this.createOffScreenCanvas(this.canvas3D);

        /* add the root transforum group to the object root */
        this.objRoot.addChild(this.rootTransform);

        /* add scene to branch graph */
        this.simpleUniverse.addBranchGraph(this.objRoot);

        /* add picking functionality */
        this.pickCanvas = new PickCanvas(this.canvas3D, this.objRoot);
        this.pickCanvas.setMode(PickInfo.PICK_GEOMETRY);

        this.canvas3D.addMouseListener(new View3DClickController(this));
        
    }

    /** Specifies the preinitialization routine which is executed before every scene draw. */
    public abstract void preInitialize();

    /**
     * Sets the camera perspective to one of the predefined perspectives in the perspective enumeration.
     * 
     * @param perspEnum
     *            the perspective to change to.
     */
    public void setCameraPerspective(final Perspectives perspEnum) {
        switch (perspEnum) {
            case XYPLANE:
                this.changeCamtoFaceYXPlane();
                break;
            case XZPLANE:
                this.changeCamtoFaceXZPlane();
                break;
            case YZPLANE:
                this.changeCamtoFaceYZPlane();
                break;
            case PERSPECTIVE:
                this.changeCamtoPerspective();
                break;
            default:
                assert false;
                break;
        }
    }

    /**
     * Sets the current picture which is displayed outside of the 3D view with the specified EXIF parameters.
     * 
     * @param picture
     *            the picture which will be displayed outside of the 3D view.
     */
    public void setCurrentDescription(final PictureInterface picture) {

        if (picture != null) {
            picture.initThumbnails();
        }

        if (this.leftPanel != null) {
            this.leftPanel.removeAll();
            this.leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.PAGE_AXIS));

            for (final Axis3D element : this.axis3D) {
                final JLabel titleLabel = new JLabel(element.getDescription().toString());
                this.leftPanel.add(titleLabel);

                if ((element.getExifParameter() != null) && (picture != null)) {
                    String displayText = Messages.getString("JAbstract3DView.1");

                    if (element.getExifParameter() == ExifParameter.DATE) {
                        final Date tempDate = new Date();
                        tempDate.setTime((Long) picture.getExifParameter(element.getExifParameter()));
                        final DateFormat dateFormat = new SimpleDateFormat(Messages.getString("JAbstract3DView.2"));
                        displayText = dateFormat.format(tempDate);
                    } else {
                        displayText = picture.getExifParameter(element.getExifParameter()).toString();
                    }
                    final JLabel exifParametersLabel = new JLabel(displayText);
                    final Font font = exifParametersLabel.getFont();
                    exifParametersLabel.setFont(font.deriveFont(font.getStyle() ^ Font.BOLD));

                    this.leftPanel.add(exifParametersLabel);
                } else {
                    this.leftPanel.add(new JLabel(Messages.getString("JAbstract3DView.3")));
                }
                this.leftPanel.add(javax.swing.Box.createRigidArea(new Dimension(0, 20)));
            }

            int size = 50;
            if (picture != null) {
                this.leftPanel.add(new JLabel(picture.getName()));
                this.leftPanel.add(new JLabel(new ImageIcon(picture.getSmallThumbnail())));
            }

            /* define the space */
            size = size + 75;

            final JLabel spacer = new JLabel();
            spacer.setPreferredSize(new Dimension(size, 0));
            spacer.setMinimumSize(new Dimension(size, 0));
            spacer.setMaximumSize(new Dimension(size, 0));

            this.leftPanel.add(spacer);
            this.pack();
            this.repaint();
        }
    }

    /**
     * Defines the description of each segment of an axis.
     */
    protected void createSegmentDescription() {

        /* define the size of each text label */
        final double size = 0.33d;

        if ((this.numberOfAxes >= 1) && this.getYAxis().isShowSegments()) {

            /* create the y-axis */
            final String[] yAxis = this.getYAxis().getSegmentDescription();

            for (int i = 0; i < yAxis.length; ++i) {

                if (yAxis[i] != null) {
                    this.createText(new Vector3d(-0.75d, i * this.getYAxis().getSegmentSize(), 0), new Vector3d(size,
                            size, size), this.basicMaterial(1, 1, 1), yAxis[i]);
                }
            }
        }

        if ((this.numberOfAxes >= 2) && this.getXAxis().isShowSegments()) {

            /* create the x-axis */
            final String[] xAxis = this.getXAxis().getSegmentDescription();

            for (int i = 0; i < xAxis.length; ++i) {

                if (xAxis[i] != null) {
                    this.createText(new Vector3d(i * this.getXAxis().getSegmentSize(), -0.5d, -0.5d), new Vector3d(
                            size, size, size), this.basicMaterial(1, 1, 1), xAxis[i]);
                }
            }
        }

        if ((this.numberOfAxes >= 3) && this.getZAxis().isShowSegments()) {

            /* Create the z-axis */
            final String[] zAxis = this.getZAxis().getSegmentDescription();

            for (int i = 0; i < zAxis.length; ++i) {

                if (zAxis[i] != null) {
                    this.createText(new Vector3d(0, -0.75d, i * this.getZAxis().getSegmentSize()), new Vector3d(size,
                            size, size), this.basicMaterial(1, 1, 1), zAxis[i]);
                }
            }
        }
    }

    @Override
    public void showDiagram() {

        if (this.displayDiagram) {
            this.setLayout(new BorderLayout());

            final Component diagramView = this.canvas3D;
            diagramView.setPreferredSize(new Dimension(800, 600));

            this.add(diagramView, BorderLayout.CENTER);

            if (this.registeredButtons != null) {
                this.add(this.registeredButtons, BorderLayout.SOUTH);
            }

            if (this.leftPanel != null) {
                this.add(this.leftPanel, BorderLayout.WEST);
            }

            if (this.rightPanel != null) {
                this.add(this.rightPanel, BorderLayout.EAST);
            }
            
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            
            for (JAbstractDiagram<?> diagram : this.furtherDiagrams) {
            	diagram.showDiagram();
            }
            
        }
    }

    /**
     * Returns the x axis.
     * 
     * @return the x axis.
     */
    public Axis3D getXAxis() {
        return this.axis3D[1];
    }

    /**
     * Returns the y axis.
     * 
     * @return the y axis.
     */
    public Axis3D getYAxis() {
        return this.axis3D[0];
    }

    /**
     * Returns the z axis.
     * 
     * @return the z axis.
     */
    public Axis3D getZAxis() {
        return this.axis3D[2];
    }

    /**
     * Creates a legend with the specified picture container. Uses the colors defined in utils.Resources.
     * 
     * @param pictureContainer
     *            the picture container you want to create a legend upon.
     */
    protected void createLegend(final List<PictureContainer> pictureContainer) {
        this.rightPanel = new JPanel();
        this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.PAGE_AXIS));
        this.rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.rightPanel.add(new JLabel(Messages.getString("JAbstract3DView.4")));

        int i = 0;
        for (final PictureContainer content : pictureContainer) {
            final JLabel currentPictureSetLabel = new JLabel(content.getName());
            currentPictureSetLabel.setIcon(new ColorRectangle(Resource.getColor(i)));
            this.rightPanel.add(currentPictureSetLabel);
            i++;
        }
    }

    /**
     * Defines a simple colored rectangle.
     * 
     * @author David Kaufman
     * 
     */
    private class ColorRectangle implements Icon {

        private final Color color;

        /**
         * This constructor initialized a Icon with the given color.
         * 
         * @param color
         *            the color of the rectangle.
         */
        public ColorRectangle(final Color color) {
            this.color = color;
        }

        private final int width = 16;
        private final int height = 16;

        /**
         * {@inheritDoc}
         */
        public int getIconWidth() {
            return this.width;
        }

        /**
         * {@inheritDoc}
         */
        public int getIconHeight() {
            return this.height;
        }

        /**
         * {@inheritDoc}
         */
        public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
            final Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(this.color);
            g2d.fillRect(x, y, this.width, this.height);
            g2d.dispose();
        }
    }

    /**
     * This class is responsible for handling the off screen buffer of the assign graphics
     * configuration and outputting the generated buffered image via the doRender() function
     * 
     * @author David Kaufman
     * 
     */
    private class OffScreenCanvas3D extends Canvas3D {

        private static final long serialVersionUID = 7632725339641761565L;

        /**
         * This constructor initializes the off screen canvas 3D with the specified parameters
         * 
         * @param graphicsConfiguration
         *            The graphics configuration of the view
         * @param offScreen
         *            Specifies if the generated view is calculated off screen or on screen
         *            true if offScreen, false if onScreen
         */
        OffScreenCanvas3D(final GraphicsConfiguration graphicsConfiguration, final boolean offScreen) {
            super(graphicsConfiguration, offScreen);
        }

        /**
         * Renders the view and generates a buffered image with the specified with and height
         * 
         * @param width
         *            the width of the generated image
         * @param height
         *            the height of the generated image
         * 
         * @return the generated buffered image
         */
        BufferedImage doRender(final int width, final int height) {
            BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            final ImageComponent2D buffer = new ImageComponent2D(ImageComponent.FORMAT_RGBA, bImage);

            this.setOffScreenBuffer(buffer);
            this.renderOffScreenBuffer();
            this.waitForOffScreenRendering();

            bImage = this.getOffScreenBuffer().getImage();

            /* to release the reference of buffer inside Java 3D. */
            this.setOffScreenBuffer(null);
            return bImage;
        }

        @Override
        public void postSwap() {
            /* No operation since we always wait for off-screen rendering to complete */
        }
    }
}