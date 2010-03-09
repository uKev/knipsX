package org.knipsX.view.reportmanagement;

import org.knipsX.Messages;

/**
 * This class represents the boxplot configuration with all its necessary panels.
 * 
 * @author David Kaufman
 * 
 */
public class BoxplotConfig extends AbstractReportCompilation {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     */
    public BoxplotConfig() {

        /* Define diagram description */
        this.diagramDescription = Messages.getString("BoxplotConfig.0") 
                                  + Messages.getString("BoxplotConfig.1") 
                                  + Messages.getString("BoxplotConfig.2")
                                  + Messages.getString("BoxplotConfig.3")
                                  + Messages.getString("BoxplotConfig.4")
                                  + Messages.getString("BoxplotConfig.5") 
                                  + Messages.getString("BoxplotConfig.6") 
                                  + Messages.getString("BoxplotConfig.7");

        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the parameters panel to the report */
        addPanel(new JParameters());
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
        
        /* add the wilcoxon test panel to the report */
        addPanel(new JWilcoxon());

    }

}
