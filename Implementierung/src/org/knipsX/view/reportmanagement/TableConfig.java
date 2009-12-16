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

	addPanel(new JDiagramType("", null, ""));
	addPanel(new JPictureSetExif("", null, ""));
    }

}
