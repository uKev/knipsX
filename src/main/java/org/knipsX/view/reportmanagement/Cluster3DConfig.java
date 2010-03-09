package org.knipsX.view.reportmanagement;

import org.knipsX.Messages;

/**
 * This class represents the 3D Cluster configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 * 
 */
public class Cluster3DConfig extends AbstractReportCompilation {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     */
    public Cluster3DConfig() {        
        
        /* Define diagram description */
        this.diagramDescription = Messages.getString("Cluster3DConfig.0")
        		+ Messages.getString("Cluster3DConfig.1")
        		+ Messages.getString("Cluster3DConfig.2")
        		+ Messages.getString("Cluster3DConfig.3")
        		+ Messages.getString("Cluster3DConfig.4")
        		+ Messages.getString("Cluster3DConfig.5")
        		+ Messages.getString("Cluster3DConfig.6");

        
        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the parameters panel to the report */
        addPanel(new JParameters());
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
