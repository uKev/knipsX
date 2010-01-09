package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class represents the Boxplot configuration with all its necessary
 * panels.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class BoxplotConfig<M extends BoxplotModel> extends
		AbstractReportCompilation<M> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which initialized the report with all its panels
	 * 
	 * @param boxplotmodel
	 *            the model which is used by the views
	 */
	public BoxplotConfig(M model) {
		super(model);

		ReportHelper.currentReport = ReportHelper.Boxplot;
		this.diagramDescription = "Der Boxplot (auch Box-Whisker-Plot oder deutsch Kastengrafik) ist ein Diagramm, das zur grafischen Darstellung der Verteilung statistischer Daten verwendet wird. Er fasst dabei verschiedene robuste Streuungs- und Lagemaße in einer Darstellung zusammen. Ein Boxplot soll schnell einen Eindruck darüber vermitteln, in welchem Bereich die Daten liegen und wie sie sich über diesen Bereich verteilen. Deshalb werden alle Werte der sogenannten Fünf-Punkte-Zusammenfassung, also der Median, die zwei Quartile und die beiden Extremwerte, dargestellt.";

		addPanel(new JDiagramType("", null, "", this.diagramDescription));
		addPanel(new JParameters("", null, ""));
		addPanel(new JPictureSetExif("", null, ""));
		addPanel(new JWilcoxon("", null, ""));

	}

}
