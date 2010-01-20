package org.knipsX.view.reportmanagement;

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

        ReportHelper.currentReport = ReportHelper.Boxplot;
        this.diagramDescription = "Der Boxplot (auch Box-Whisker-Plot oder deutsch Kastengrafik) ist ein Diagramm, das zur grafischen Darstellung der Verteilung statistischer Daten verwendet wird. Er fasst dabei verschiedene robuste Streuungs- und Lagemaße in einer Darstellung zusammen. Ein Boxplot soll schnell einen Eindruck darüber vermitteln, in welchem Bereich die Daten liegen und wie sie sich über diesen Bereich verteilen. Deshalb werden alle Werte der sogenannten Fünf-Punkte-Zusammenfassung, also der Median, die zwei Quartile und die beiden Extremwerte, dargestellt.";

        addPanel(new JDiagramType(this.diagramDescription));
        addPanel(new JParameters());
        addPanel(new JPictureSetExif());
        addPanel(new JWilcoxon());

    }

}
