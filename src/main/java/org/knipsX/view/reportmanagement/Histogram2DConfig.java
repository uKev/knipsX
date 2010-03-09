package org.knipsX.view.reportmanagement;

import org.knipsX.Messages;

/**
 * This class represents the 2D Histogram configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 * 
 */
public class Histogram2DConfig extends AbstractReportCompilation {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     */
    public Histogram2DConfig() {
        
        /* Define diagram description */
        this.diagramDescription = Messages.getString("Histogram2DConfig.0")
        		+ Messages.getString("Histogram2DConfig.1")
        		+ Messages.getString("Histogram2DConfig.2")
        		+ Messages.getString("Histogram2DConfig.3")
        		+ Messages.getString("Histogram2DConfig.4");

        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the parameters panel to the report */
        addPanel(new JParameters());
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
