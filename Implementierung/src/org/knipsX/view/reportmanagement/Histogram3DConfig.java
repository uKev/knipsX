package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram3DModel;

/**
 * This class represents the 3D Histogram configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class Histogram3DConfig<M extends Histogram3DModel> extends AbstractReportCompilation<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     * @param histogram3dmodel
     *            the model which is used by the panels
     */
    public Histogram3DConfig(M model) {
		super(model);
		ReportHelper.currentReport = ReportHelper.Histogram3D;
		this.diagramDescription = "Ein Histogramm dient der graphischen Darstellung der Häufigkeitsverteilung metrisch skalierter klassierter Merkmale. Im Gegensatz zum Balken- bzw. Säulendiagramm (engl. bar chart) werden die Häufigkeiten beim Histogramm nicht durch die Höhe von Balken, sondern durch die Fläche der Balken repräsentiert.";
		
		addPanel(new JDiagramType("", null, "",  this.diagramDescription));
		addPanel(new JParameters("", null, ""));
		addPanel(new JPictureSetExif("", null, ""));
    }

}
