package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.TableModel;

/**
 * This class represents the Table configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class TableConfig<M extends TableModel> extends AbstractReportCompilation<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     * @param tablemodel
     *            the model which is used the panels
     */
    public TableConfig(M model) {
		super(model);
		ReportHelper.currentReport = ReportHelper.Table;
		this.diagramDescription = "Eine Tabelle ist eine geordnete Zusammenstellung von Texten oder Daten. Die darzustellenden Inhalte werden dabei in Zeilen und Spalten gegliedert, die grafisch aneinander ausgerichtet werden. Die erste Spalte in der nachfolgenden Tabelle heißt Vorspalte, die erste Zeile Kopfzeile. ";
		
		addPanel(new JDiagramType("", null, "", this.diagramDescription));
		addPanel(new JPictureSetExif("", null, ""));
    }

}
