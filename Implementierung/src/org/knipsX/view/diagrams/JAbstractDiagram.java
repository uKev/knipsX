package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.JComponent;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

/**
 * This class specifies the main functionality of ever diagram in knipsX
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public abstract class JAbstractDiagram<M extends AbstractReportModel> extends JAbstractView<M> {

    private static final long serialVersionUID = 5427649938817210196L;
    
    /* The default report id */
    private int reportID = -1;

    /**
     * This JComponent contains the button set of the specific diagram view.
     * Defining this in the abstract class allows greater flexibility, because
     * for example all 3DDiagram use the same button sets. If any diagram view wants to
     * have their individual button set this variable can be overwritten.
     * 
     */
    protected JComponent registeredButtons;

    
    
    /**
     * Specifies if the diagram should pop up.
     */
    protected boolean displayDiagram = true; 
    
    
    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     *            
     * @param reportID 
     *                the report id of the report    
     */
    public JAbstractDiagram(final M model, final int reportID) {
        super(model);
        this.reportID = reportID;
    }

    /**
     * This function returns the actual diagram which is used for
     * previsualization purposes.
     * 
     * @return The actual diagram without buttons
     */
    abstract Component getDiagram();

    /**
     * This method has to be implemented by every diagram which
     * returns a BufferedImage of the current diagram which can
     * later be exported as an image.
     * 
     * @return BufferedImage containing the current view
     */
    public abstract BufferedImage getDiagramScreenshot();

    /**
     * Returns the report ID of the current report associated with the diagram
     * 
     * @return the report id
     */
    public int getReportID() {
        return this.reportID;
    }

    /**
     * Returns the current report model of the diagram
     * 
     * @return the current report model of the diagram
     */
    public AbstractReportModel getReportModel() {
        return this.model;
    }

    /**
     * Displays the Diagram with the associated buttons
     */
    public abstract void showDiagram();

    @Override
    public void update(final Observable model, final Object argument) {
        // Does nothing
    }
}
