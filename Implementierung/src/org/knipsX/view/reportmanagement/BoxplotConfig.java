package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class represents the Boxplot configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class BoxplotConfig<M extends BoxplotModel> extends AbstractReportCompilation<M> {

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

		addPanel(new JDiagramType("", null, ""));
		addPanel(new JParameters("", null, ""));
		addPanel(new JPictureSetExif("", null, ""));
		addPanel(new JWilcoxon("", null, ""));

    }

}