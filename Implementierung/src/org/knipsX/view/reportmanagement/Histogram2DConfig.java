package org.knipsX.view.reportmanagement;

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
        
        /* Define diagram description*/
        //INTERNATIONALIZE
        this.diagramDescription = "Ein Histogramm dient der graphischen Darstellung der Häufigkeitsverteilung"
        		+ " metrisch skalierter klassierter Merkmale. Im Gegensatz zum Balken- bzw. "
        		+ "Säulendiagramm (engl. bar chart) werden die Häufigkeiten beim Histogramm nicht durch "
        		+ "die Höhe von Balken, sondern durch die Fläche der Balken repräsentiert.";

        /* add the diagram panel to the report */
        addPanel(new JDiagramType(this.diagramDescription));
        
        /* add the parameters panel to the report */
        addPanel(new JParameters());
        
        /* add the picture set management and EXIF keyword panel to the report */
        addPanel(new JPictureSetExif());
    }

}
