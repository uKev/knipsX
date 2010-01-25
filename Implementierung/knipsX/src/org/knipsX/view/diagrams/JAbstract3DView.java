package org.knipsX.view.diagrams;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
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
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.knipsX.controller.diagrams.View3DClickController;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.ExifParameter;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.pickfast.PickCanvas;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * This class manages all java 3d interactions and offers methods than simplify the
 * utilization of the java 3d functions significantly
 * 
 * @author David Kaufman
 * 
 * @param <M>
 *            the model which is to be drawn
 */
public abstract class JAbstract3DView<M extends AbstractReportModel> extends JAbstractDiagram<M> {

    /**
     * This class is responsible for handling the off screen buffer of the assign graphics
     * configuration and outputting the generated buffered image
     * 
     * @author David Kaufman
     * 
     */
    class OffScreenCanvas3D extends Canvas3D {

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

            /* To release the reference of buffer inside Java 3D. */
            this.setOffScreenBuffer(null);

            return bImage;
        }

        @Override
        public void postSwap() {
            /* No operation since we always wait for off-screen rendering to complete */
        }
    }

    private static final long serialVersionUID = 1305626160044284511L;

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
     * Specifies how dense the grid is to be drawn,
     * a high number indicates a high grid density
     */
    protected final static double GRIDDENSITYFACTOR = 10;

    /**
     * Specifies at which level of detail the geometry is to be drawn
     */
    protected final static int GEODETAIL = 10;

    /**
     * Specifies the number of axes used
     */
    protected int numberOfAxes = 3;

    /**
     * Specifies if the text in the current view should point to the camera
     */
    protected boolean textautorotate = true;

    /**
     * Specifies the off screen scale of the off screen canvas3d
     */
    private static final int OFF_SCREEN_SCALE = 2;

    /**
     * Specifies the left Panel which is visible in diagram view
     */
    protected JPanel leftPanel;

    /**
     * Specifies the right Panel which is visible in diagram view
     */
    protected JPanel rightPanel;

    /**
     * Stores the reference to the OffScreenCanvas3D object
     */
    private OffScreenCanvas3D offScreenCanvas3D;

    /**
     * Create three axis3D objects for each axis one axis3D object
     */
    protected Axis3D[] axis3D = { new Axis3D(), new Axis3D(), new Axis3D() };

    /**
     * Stores the current perspective
     */
    private int currentPerspective = 0;

    /**
     * The pick canvas which realizes object picking in the 3D view
     */
    private PickCanvas pickCanvas;

    /**
     * Constructor initialized the canvas3D
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JAbstract3DView(final M model, final int reportID) {
        super(model, reportID);

        /* Initialize the object root branch group. All elements are in the view are added to this group */
        this.objRoot = new BranchGroup();

        /* Initialize the canvas 3D */
        this.canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        /* Initialize the universe of the 3D view */
        this.simpleU = new SimpleUniverse(this.canvas3D);

        /* Call the preinitialization routine */
        this.preinitialize();

        /* Call the generateContent routine which is implemented by every diagram type */
        this.generateContent();

        /* Call the postinitialization routine */
        this.postinitialize();

    }

    /**
     * Creates and assigns a default light setup to the root BranchGroup
     */
    protected void addLights() {

        /* directional light */
        for (int p = 0; p <= 2; p++) {
            final DirectionalLight dLight = new DirectionalLight();
            dLight.setInfluencingBounds(new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE));
            dLight.setColor(new Color3f(0.88f, 0.88f, 0.88f));

            Vector3f dir = null;
            if (p == 0) {
                dir = new Vector3f(-1.0f, -2.0f, 1.0f);
            } else if (p == 1) {
                dir = new Vector3f(1.0f, 2.0f, -1.0f);
            } else {
                dir = new Vector3f(1.0f, 1.0f, 1.0f);
            }
            dir.normalize();
            dLight.setDirection(dir);
            this.objRoot.addChild(dLight);
        }

        /* ambient light */
        final AmbientLight aLight = new AmbientLight();
        aLight.setInfluencingBounds(new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE));
        aLight.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        this.objRoot.addChild(aLight);

    }

    /**
     * Creates a basicMaterial with the specified color
     * 
     * @param r
     *            the amount of red of the material
     * @param g
     *            the amount of green of the material
     * @param b
     *            the amount of blue of the material
     * 
     * @return the appearance object with the specified color
     */
    protected Appearance basicMaterial(final float r, final float g, final float b) {
        final Appearance a = new Appearance();
        final Material mat = new Material();
        mat.setShininess(100.0f);
        mat.setDiffuseColor(new Color3f(r, g, b));
        mat.setSpecularColor(new Color3f(r, g, b));
        a.setMaterial(mat);
        return a;
    }

    /**
     * Changes the camera position to the specified location
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param z
     *            the z coordinate
     */
    protected void changeCameraPosition(final float x, final float y, final float z) {
        final TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        this.simpleU.getViewingPlatform().getViewPlatform();
        final Vector3f translate = new Vector3f(x, y, z);
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(translate);
        vpTrans.setTransform(transform3D);
    }

    private void changeCamtoFaceXZPlane() {
        final TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(this.axis3D[0].getAxisSize() / 2.0, this.axis3D[1].getAxisSize() / 2.0,
                3 * this.axis3D[2].getAxisSize()));
        vpTrans.setTransform(transform3D);
    }

    private void changeCamtoFaceYXPlane() {
        final TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(-90 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(-2 * this.axis3D[0].getAxisSize(), this.axis3D[1].getAxisSize() / 2.0,
                this.axis3D[2].getAxisSize() / 2));
        vpTrans.setTransform(transform3D);
    }

    private void changeCamtoFaceYZPlane() {
        final TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(180 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(this.axis3D[0].getAxisSize() / 2.0, this.axis3D[1].getAxisSize() / 2.0,
                -2 * this.axis3D[2].getAxisSize()));
        vpTrans.setTransform(transform3D);
    }

    private void changeCamtoPerspective() {
        final TransformGroup vpTrans = this.simpleU.getViewingPlatform().getViewPlatformTransform();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(60 * Math.PI / 180.0);
        transform3D.rotX(-12.5 * Math.PI / 180.0);
        transform3D.setTranslation(new Vector3d(this.axis3D[0].getAxisSize() / 2.0,
                2 * this.axis3D[1].getAxisSize() / 2.0, 3 * this.axis3D[2].getAxisSize()));
        vpTrans.setTransform(transform3D);
    }

    /**
     * Creates the axes which are automatically placed into the root BranchGroup.
     * To change the number of axis you must change the numberOfAxes variable
     * before calling this function.
     * 
     */
    protected void createAxis() {

        for (int i = 0; i < this.numberOfAxes; i++) {

            /* The transformation information of the axis */
            final Transform3D axisTrans = new Transform3D();

            if (i == 0) {
                axisTrans.rotX(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(0, 0, this.axis3D[0].getAxisSize() / 2.0));
                axisTrans.setScale(new Vector3d(0.15d, this.axis3D[0].getAxisSize(), 0.15d));
            } else if (i == 1) {
                axisTrans.rotY(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(0, this.axis3D[1].getAxisSize() / 2.0, 0));
                axisTrans.setScale(new Vector3d(0.15d, this.axis3D[1].getAxisSize(), 0.15d));
            } else {
                axisTrans.rotZ(90 * Math.PI / 180.0d);
                axisTrans.setTranslation(new Vector3d(this.axis3D[2].getAxisSize() / 2.0, 0, 0));
                axisTrans.setScale(new Vector3d(0.15d, this.axis3D[2].getAxisSize(), 0.15d));
            }

            /* Create transformation group */
            final TransformGroup objAxis = new TransformGroup(axisTrans);

            final Appearance myAppearance = this.basicMaterial(0.0f, 0.0f, 0.0f);

            final Cylinder myAxisGeo = new Cylinder(0.3f, 1f, myAppearance);

            objAxis.addChild(myAxisGeo);

            /* Add both groups to the root */
            this.objRoot.addChild(objAxis);
        }

        /* Create ticks for each axis */
        for (int q = 0; q < this.numberOfAxes; q++) {
            for (int i = 1; i < this.axis3D[q].getNumberOfSegments(); i++) {
                final Transform3D segment = new Transform3D();

                if (q == 0 && this.axis3D[0].isShowSegments()) {
                    final double normDistance = i * this.axis3D[0].getSegmentSize();
                    segment.rotX(90 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(0, normDistance, 0));
                } else if (q == 1 && this.axis3D[1].isShowSegments()) {
                    final double normDistance = i * this.axis3D[1].getSegmentSize();
                    segment.rotY(90 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(0, 0, normDistance));
                } else if (q == 2 && this.axis3D[2].isShowSegments()) {
                    final double normDistance = i * this.axis3D[2].getSegmentSize();
                    segment.rotX(0 * Math.PI / 180.0d);
                    segment.setTranslation(new Vector3d(normDistance, 0, 0));
                }

                if (this.axis3D[0].isShowSegments() || this.axis3D[1].isShowSegments()
                        || this.axis3D[2].isShowSegments()) {
                    final TransformGroup objSeg = new TransformGroup(segment);
                    final Box myAxisGeo = new Box(0.0125f, 0.25f, 0.0125f, 1, this.basicMaterial(1f, 1f, 1f));
                    objSeg.addChild(myAxisGeo);
                    this.objRoot.addChild(objSeg);
                }
            }
        }

        /* Create axis arrows */
        for (int i = 0; i < this.numberOfAxes; i++) {
            final Transform3D coneTransformation = new Transform3D();
            Cone axisArrow = null;
            final float percentage = 1.0f / 20.0f;

            if (i == 0) {
                final double normDistance = 0.5 * this.axis3D[0].getAxisSize() * percentage
                        + this.axis3D[0].getAxisSize();
                coneTransformation.rotX(90 * Math.PI / 180.0d);
                coneTransformation.setTranslation(new Vector3d(0, 0, normDistance));
                axisArrow = new Cone(0.125f, (float) this.axis3D[0].getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            } else if (i == 1) {
                final double normDistance = 0.5 * this.axis3D[1].getAxisSize() * percentage
                        + this.axis3D[1].getAxisSize();
                coneTransformation.setTranslation(new Vector3d(0, normDistance, 0));
                axisArrow = new Cone(0.125f, (float) this.axis3D[1].getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            } else if (i == 2) {
                final double normDistance = 0.5 * this.axis3D[2].getAxisSize() * percentage
                        + this.axis3D[2].getAxisSize();
                coneTransformation.rotZ(270 * Math.PI / 180.0d);
                coneTransformation.setTranslation(new Vector3d(normDistance, 0, 0));
                axisArrow = new Cone(0.125f, (float) this.axis3D[2].getAxisSize() * percentage, this.basicMaterial(0,
                        0, 0));
            }

            final TransformGroup axisArrowTG = new TransformGroup(coneTransformation);
            axisArrowTG.addChild(axisArrow);
            this.objRoot.addChild(axisArrowTG);

        }

    }

    /**
     * Creates and assigns the specified background color to the root BranchGroup
     * 
     * @param color
     *            the background color
     */
    protected void createBackground(final Color3f color) {
        final BoundingSphere riesenkugel = new BoundingSphere(new Point3d(0.0d, 0.0d, 0.0d), Double.MAX_VALUE);

        final Background hg = new Background();
        hg.setColor(color);
        hg.setApplicationBounds(riesenkugel);
        this.objRoot.addChild(hg);
    }

    /**
     * Creates a Cube
     * 
     * @param position
     *            the position of the cube
     * @param scale
     *            the scale value of the cube
     * @param material
     *            the material of the cube
     */
    public void createCube(final Vector3d position, final Vector3d scale, final Appearance material) {
        final TransformGroup objMove = this.createTransformGroup(position, scale);
        final Box myBox = new Box(1, 1, 1, material);
        objMove.addChild(myBox);
        this.objRoot.addChild(objMove);
    }

    /**
     * Creates a grid which is automatically placed into the root BranchGroup
     */
    protected void createGrid() {

        final TransformGroup gridtransform = this.createTransformGroup(
                new Vector3d(this.axis3D[0].getAxisSize(), 0, 0), new Vector3d(1, 1, 1));

        if (this.numberOfAxes == 2) {
            final Transform3D mytemptrans = new Transform3D();
            gridtransform.getTransform(mytemptrans);
            mytemptrans.rotZ(90 * Math.PI / 180.0d);
            gridtransform.setTransform(mytemptrans);
        }

        for (int q = 0; q <= 0; q++) {
            for (int p = 0; p <= 1; p++) {
                for (int i = 0; i <= JAbstract3DView.GRIDDENSITYFACTOR; i++) {
                    /* The transformation of each grid element */
                    final Transform3D gridTrans = new Transform3D();

                    if (p == 0) {
                        gridTrans.rotX(90 * Math.PI / 180.0d);
                        gridTrans.setTranslation(new Vector3d(
                                (i * this.axis3D[0].getAxisSize() / JAbstract3DView.GRIDDENSITYFACTOR), 0,
                                this.axis3D[0].getAxisSize() / 2.0));
                        gridTrans.setScale(new Vector3d(0.025, this.axis3D[0].getAxisSize(), 0.025));
                    } else if (p == 1) {
                        gridTrans.rotZ(90 * Math.PI / 180.0d);
                        gridTrans.setTranslation(new Vector3d(this.axis3D[1].getAxisSize() / 2.0, 0, i
                                * this.axis3D[1].getAxisSize() / JAbstract3DView.GRIDDENSITYFACTOR));
                        gridTrans.setScale(new Vector3d(0.025, this.axis3D[1].getAxisSize(), 0.025));
                    }

                    final TransformGroup objGrid = new TransformGroup(gridTrans);
                    final Appearance myAppearance = this.basicMaterial(0.0f, 0.0f, 1.0f);
                    final Cylinder myGridGeo = new Cylinder(0.5f, 1f, myAppearance);
                    objGrid.addChild(myGridGeo);
                    gridtransform.addChild(objGrid);
                }
            }
        }

        this.objRoot.addChild(gridtransform);

    }

    /**
     * Specifies the labels that are displayed next to each axis
     * 
     * @param xAxis
     *            The label of the xAxis
     * @param zAxis
     *            The label of the zAxis
     * @param yAxis
     *            The label of the yAxis
     */
    protected void createLabels(final String xAxis, final String zAxis, final String yAxis) {
        final double offset = 1.5;
        final double size = 0.42d;
        final Appearance myapp = this.basicMaterial(1, 1, 1);
        this.createText(new Vector3d(0, 0, offset + this.axis3D[0].getAxisSize()), new Vector3d(size, size, size),
                myapp, xAxis);
        this.createText(new Vector3d(offset + this.axis3D[1].getAxisSize(), 0, 0), new Vector3d(size, size, size),
                myapp, yAxis);
        this.createText(new Vector3d(0, offset + this.axis3D[2].getAxisSize(), 0), new Vector3d(size, size, size),
                myapp, zAxis);
    }

    private OffScreenCanvas3D createOffScreenCanvas(final Canvas3D onScreenCanvas3D) {
        /*
         * Create the off-screen Canvas3D object
         * request an off-screen Canvas3D with a single buffer configuration
         */
        final GraphicsConfigTemplate3D template = new GraphicsConfigTemplate3D();
        template.setDoubleBuffer(GraphicsConfigTemplate.UNNECESSARY);
        final GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getBestConfiguration(template);

        this.offScreenCanvas3D = new OffScreenCanvas3D(gc, true);
        /*
         * Set the off-screen size based on a scale factor times the
         * on-screen size
         */
        final Screen3D sOn = onScreenCanvas3D.getScreen3D();
        final Screen3D sOff = this.offScreenCanvas3D.getScreen3D();
        final Dimension dim = sOn.getSize();
        dim.width *= JAbstract3DView.OFF_SCREEN_SCALE;
        dim.height *= JAbstract3DView.OFF_SCREEN_SCALE;
        sOff.setSize(dim);
        sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth() * JAbstract3DView.OFF_SCREEN_SCALE);
        sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight() * JAbstract3DView.OFF_SCREEN_SCALE);

        /* attach the off-screen canvas to the view */
        this.simpleU.getViewer().getView().addCanvas3D(this.offScreenCanvas3D);

        return this.offScreenCanvas3D;

    }

    /**
     * Creates a Sphere
     * 
     * @param position
     *            the position of the sphere
     * @param scale
     *            the scale value of the sphere
     * @param material
     *            the material of the sphere
     */
    public void createSphere(final Vector3d position, final Vector3d scale, final Appearance material) {
        final TransformGroup objMove = this.createTransformGroup(position, scale);
        final Sphere mySphere = new Sphere(1, Primitive.GENERATE_NORMALS, JAbstract3DView.GEODETAIL, material);
        objMove.addChild(mySphere);
        this.objRoot.addChild(objMove);
    }

    /**
     * Creates Text
     * 
     * @param position
     *            the position of the text
     * @param scale
     *            the scale of the text
     * @param material
     *            the material of the text
     * @param text
     *            the text itself
     */
    protected void createText(final Vector3d position, final Vector3d scale, final Appearance material,
            final String text) {

        final TransformGroup objMove = this.createTransformGroup(position, scale);

        final TransformGroup objSpin = new TransformGroup();
        final Billboard mybillboard = new Billboard(objSpin);
        final BoundingSphere bSphere = new BoundingSphere();
        mybillboard.setSchedulingBounds(bSphere);
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objMove.addChild(objSpin);

        final Appearance textAppear = new Appearance();
        final ColoringAttributes textColor = new ColoringAttributes();
        textColor.setColor(1.0f, 1.0f, 1.0f);
        textAppear.setColoringAttributes(textColor);
        final Font3D font3D = new Font3D(new Font("Arial", Font.PLAIN, 1), new FontExtrusion());
        final Text3D textGeom = new Text3D(font3D, new String(text));
        textGeom.setAlignment(Text3D.ALIGN_CENTER);
        final Shape3D textShape = new Shape3D();
        textShape.setGeometry(textGeom);
        textShape.setAppearance(textAppear);

        if (this.textautorotate) {
            objSpin.addChild(textShape);
            this.objRoot.addChild(mybillboard);
        } else {
            objMove.addChild(textShape);
        }

        this.objRoot.addChild(objMove);
    }

    /**
     * Creates a TransformGroup with the specified position and scale
     * 
     * @param position
     *            the position of the TransformGroup
     * @param scale
     *            the scale of the TransformGroup
     * @return The transform group of the specified position and scale
     */
    protected TransformGroup createTransformGroup(final Vector3d position, final Vector3d scale) {
        final Transform3D t3D = new Transform3D();
        t3D.rotY(-90 * Math.PI / 180.0d);
        t3D.setTranslation(position);
        t3D.setScale(new Vector3d(scale.x, scale.y, scale.z));
        final TransformGroup objMove = new TransformGroup(t3D);
        return objMove;
    }

    /**
     * Specifies the contents which are to be drawn in the canvas3D
     */
    public abstract void generateContent();

    @Override
    public Component getDiagram() {
        return this.canvas3D;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {

        final Point loc = this.canvas3D.getLocationOnScreen();
        this.offScreenCanvas3D.setOffScreenLocation(loc);
        final Dimension dim = this.canvas3D.getSize();
        dim.width *= JAbstract3DView.OFF_SCREEN_SCALE;
        dim.height *= JAbstract3DView.OFF_SCREEN_SCALE;
        final BufferedImage bImage = this.offScreenCanvas3D.doRender(dim.width, dim.height);

        return bImage;
    }

    /**
     * Returns the pick canvas of the current 3D view
     * 
     * @return the pick canvas object
     */
    public PickCanvas getPickCanvas() {
        return this.pickCanvas;
    }

    /**
     * Cycles to the next view
     */
    public void nextPerspective() {

        if (this.currentPerspective < Perspectives.values().length - 1) {
            this.currentPerspective++;
        } else {
            this.currentPerspective = 0;
        }
        this.setCameraPerspective(Perspectives.values()[this.currentPerspective]);
    }

    private void postinitialize() {
        /* Add antialiasing - turn off if view is laggy */
        this.simpleU.getViewer().getView().setSceneAntialiasingEnable(true);

        /* Ensure at least 5 msec per frame (i.e., < 200Hz) */
        this.simpleU.getViewer().getView().setMinimumFrameCycleTime(5);

        /*
         * Set the back clipping plane to a relative high value to ensure that
         * the view is drawn even if the view is zoomed out very far
         */
        this.canvas3D.getView().setBackClipDistance(500);

        this.createBackground(new Color3f(0.7f, 0.7f, 0.7f));

        /* Create the grid */
        this.createGrid();

        /* Create the axis */
        this.createAxis();

        /* Create the segment description */
        this.createSegmentDescription();
        
        /* Create labels */
        this.createLabels(this.axis3D[0].getDescription(), this.axis3D[1].getDescription(), this.axis3D[2].getDescription());

        /* Create the off-screen Canvas3D object */
        this.createOffScreenCanvas(this.canvas3D);

        /* Add scene to branch graph */
        this.simpleU.addBranchGraph(this.objRoot);

        /* Add picking functionality */
        this.pickCanvas = new PickCanvas(this.canvas3D, this.objRoot);
        this.pickCanvas.setMode(PickInfo.PICK_GEOMETRY);
        this.canvas3D.addMouseListener(new View3DClickController(this));

    }

    /**
     * Specifies the preinitialization routine which is executed before every scene draw
     */
    public abstract void preinitialize();

    /**
     * Sets the camera perspective to one of the predefined perspectives
     * in the perspective enumeration
     * 
     * @param perspEnum
     *            the perspective to change to
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
     * Sets the current picture which is displayed outside of the 3D view with the
     * specified EXIF parameters
     * 
     * @param pic
     *            the picture which will be displayed outside of the 3D view
     */
    public void setCurrentDescription(final Picture pic) {
        // TODO
    }

    /**
     * Defines the description of each segment of an axis.
     */
    protected void createSegmentDescription() {

        /* Define the size of each text label */
        final double size = 0.33d;

        if (this.numberOfAxes >= 1 && this.axis3D[0].isShowSegments()) {
            /* Create the x Axis */
            final String[] xAxis = this.axis3D[0].getSegmentDescription();

            for (int q = 0; q < xAxis.length; q++) {
                if (xAxis[q] != null) {
                    this.createText(new Vector3d(0, q * this.axis3D[0].getSegmentSize(), -0.75d), new Vector3d(size,
                            size, size), this.basicMaterial(1, 1, 1), xAxis[q]);
                }

            }
        }

        if (this.numberOfAxes >= 2 && this.axis3D[1].isShowSegments()) {
            /* Create the z Axis */
            final String[] zAxis = this.axis3D[1].getSegmentDescription();

            for (int q = 0; q < zAxis.length; q++) {
                if (zAxis[q] != null) {
                    this.createText(new Vector3d(0, -0.75d, q * this.axis3D[1].getSegmentSize()), new Vector3d(size,
                            size, size), this.basicMaterial(1, 1, 1), zAxis[q]);
                }
            }
        }

        if (this.numberOfAxes >= 3 && this.axis3D[2].isShowSegments()) {
            /* Create the y Axis */
            final String[] yAxis = this.axis3D[2].getSegmentDescription();

            for (int q = 0; q < yAxis.length; q++) {
                if (yAxis[q] != null) {
                    this.createText(new Vector3d(q * this.axis3D[2].getSegmentSize(), -0.5d, -0.5d), new Vector3d(size,
                            size, size), this.basicMaterial(1, 1, 1), yAxis[q]);
                }
            }
        }
    }

    @Override
    public void showDiagram() {

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
    }

}