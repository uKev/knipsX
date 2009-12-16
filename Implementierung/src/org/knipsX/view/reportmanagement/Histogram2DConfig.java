package org.knipsX.view.reportmanagement;

import org.knipsX.model.reportmanagement.Histogram2DModel;

/**
 * This class represents the 2D Histogram configuration with all its
 * necessary panels.
 * 
 * @author David Kaufman
 *
 * @param <M>
 */
public class Histogram2DConfig<M extends Histogram2DModel> extends AbstractReportCompilation<M> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor which initialized the report with all its panels
     * 
     * @param histogram2dmodel
     *            the model which is used by the panels
     */
    public Histogram2DConfig(M model) {
	super(model);
	ReportHelper.currentReport = ReportHelper.Histogram2D;

	addPanel(new JDiagramType("", null, ""));
	addPanel(new JParameters("", null, ""));
	addPanel(new JPictureSetExif("", null, ""));
    }

}
