package org.knipsX.view.reportmanagement;

/**
 * This class represents the Table configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 */
public class TableConfig extends AbstractReportCompilation {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     */
    public TableConfig() {
        
        /* Define diagram description */
        // INTERNATIONALIZE
        this.diagramDescription = Messages.getString("TableConfig.0") //$NON-NLS-1$
        		+ Messages.getString("TableConfig.1") //$NON-NLS-1$
        		+ Messages.getString("TableConfig.2") //$NON-NLS-1$
        		+ Messages.getString("TableConfig.3"); //$NON-NLS-1$

        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
