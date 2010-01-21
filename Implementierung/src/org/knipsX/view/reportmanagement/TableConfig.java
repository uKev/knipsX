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
        this.diagramDescription = "Eine Tabelle ist eine geordnete Zusammenstellung von Texten oder Daten."
        		+ " Die darzustellenden Inhalte werden dabei in Zeilen und Spalten gegliedert, "
        		+ "die grafisch aneinander ausgerichtet werden. Die erste Spalte in der nachfolgenden"
        		+ " Tabelle heißt Vorspalte, die erste Zeile Kopfzeile.";

        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
