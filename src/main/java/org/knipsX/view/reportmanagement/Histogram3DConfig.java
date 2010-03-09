package org.knipsX.view.reportmanagement;

import org.knipsX.Messages;

/**
 * This class represents the 3D Histogram configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 * 
 */
public class Histogram3DConfig extends AbstractReportCompilation {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     */
    public Histogram3DConfig() {

        /* Define diagram description */
        this.diagramDescription = Messages.getString("Histogram3DConfig.0")
        		+ Messages.getString("Histogram3DConfig.1")
        		+ Messages.getString("Histogram3DConfig.2")
        		+ Messages.getString("Histogram3DConfig.3");

        
        /* Add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* Add the parameters panel to the report */
        addPanel(new JParameters());
        
        /* Add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
