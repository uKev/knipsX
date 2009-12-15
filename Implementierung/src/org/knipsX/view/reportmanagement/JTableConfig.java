package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.TableModel;

public class JTableConfig<M extends TableModel> extends JAbstractReport<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     * @param tablemodel
     *            the model which is used the panels
     */
    public JTableConfig(M model) {
	super(model);
	Report.currentReport = Report.Table;

	addPanel(new JDiagramType("", null, ""));
	addPanel(new JPictureSet("", null, ""));
    }

}
