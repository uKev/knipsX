package org.knipsX.view.diagrams;

import java.util.Random;

import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;

/**
 * This class implements how the Cluster3DModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JCluster3D<M extends AbstractReportModel> extends JAbstract3DDiagram<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JCluster3D(final M model, final int reportID) {
        super(model, reportID);
    }

    @Override
    public void generateContent() {

        // for (int i = 0; i <= this.model.getFrequency3DPoints().length; i++) {
        for (int i = 0; i <= 200; i++) {
            final Random random = new Random();

            final Transform3D dataTrans = new Transform3D();

            dataTrans.setTranslation(new Vector3d(random.nextDouble() * this.AXISSIZE, random.nextDouble()
                    * this.AXISSIZE, random.nextDouble() * this.AXISSIZE));

            // Create transformation group
            final TransformGroup objData = new TransformGroup(dataTrans);
            objData.setCapability(PickInfo.PICK_GEOMETRY);
            // objData.addChild(new Selectable3DShape(this.model.getFrequency3DPoints()[i]));
            Selectable3DShape selectableShape = new Selectable3DShape(null);
            float myfloat = (float) random.nextDouble();
            selectableShape.setAppearance(basicMaterial(myfloat, 2 * myfloat, myfloat));
            objData.addChild(selectableShape);
            
            this.objRoot.addChild(objData);
        }

        final String[] xAchse = this.generateSegmentDescription(null, null);
        this.setSegmentDescription(xAchse, xAchse, xAchse);
        this.createLabels("Hallo", "Test", "OMG");
        
        this.leftPanel = new JPanel();
        

    }

}
