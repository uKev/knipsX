package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;
import java.util.List;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.view.reportmanagement.JAbstractReportUtil;
import org.knipsX.view.reportmanagement.JAbstractSinglePanel;
import org.knipsX.view.reportmanagement.JDiagramType;
import org.knipsX.view.reportmanagement.JParameters;
import org.knipsX.view.reportmanagement.JPictureSetExif;
import org.knipsX.view.reportmanagement.JWilcoxon;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * This controller is responsible for saving a report configuration
 * into its corresponding model and returning that model to
 * the ProjectModel.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 * @param <V>
 */
public class ReportSaveController<M extends AbstractReportModel, V extends JAbstractReportUtil<?>> extends
        AbstractController<M, V> {

    private final boolean showDiagram;

    /**
     * The constructor which registers the controller with the specified view
     * 
     * @param view
     *            the view the controller operates on
     * @param showDiagram
     *            specifies if the diagram should be displayed after the report has been saved
     */
    public ReportSaveController(final V view, final boolean showDiagram) {
        super(view);
        this.showDiagram = showDiagram;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(final ActionEvent e) {
        final List<JAbstractSinglePanel> registeredPanels = this.view.getReportCompilation().getRegisteredPanels();

        this.model = (M) ReportSaveController.createSavableModel(registeredPanels);

        ReportHelper.getProjectModel().addReport(this.model, this.view.getReportId());

        this.view.dispose();

        if (this.showDiagram) {
            ReportHelper.getCurrentReport().displayDiagram(this.model, this.view.getReportId()).showDiagram();
        }

        /* Activate the current project view */
        ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);

    }

    /**
     * This function takes in registered panel from a report compilation and returns a model
     * 
     * @param registeredPanels
     *            the registers panels from the report compilation
     * @return a saveable model
     */
    public static AbstractReportModel createSavableModel(final List<JAbstractSinglePanel> registeredPanels) {

        final AbstractReportModel model = ReportHelper.getCurrentReport().createReportModel();

        for (final JAbstractSinglePanel singlepanel : registeredPanels) {

            if (singlepanel instanceof JDiagramType) {

                final JDiagramType mydiagram = (JDiagramType) singlepanel;
                model.setReportName(mydiagram.getReportName());
                model.setReportDescription(mydiagram.getReportDescription());

            } else if (singlepanel instanceof JParameters) {

                final JParameters parametersPanel = (JParameters) singlepanel;

                if (model instanceof BoxplotModel) {
                    ((BoxplotModel) model).setXAxis(parametersPanel.getAxes().get(0));
                } else if (model instanceof Histogram2DModel) {
                    ((Histogram2DModel) model).setXAxis(parametersPanel.getAxes().get(0));
                } else if (model instanceof Histogram3DModel) {
                    ((Histogram3DModel) model).setXAxis(parametersPanel.getAxes().get(0));
                    ((Histogram3DModel) model).setZAxis(parametersPanel.getAxes().get(1));
                } else if (model instanceof Cluster3DModel) {
                    final Cluster3DModel cluster3DModel = ((Cluster3DModel) model);
                    cluster3DModel.setXAxis(parametersPanel.getAxes().get(0));
                    cluster3DModel.setZAxis(parametersPanel.getAxes().get(1));
                    cluster3DModel.setYAxis(parametersPanel.getAxes().get(2));
                }

            } else if (singlepanel instanceof JPictureSetExif) {

                final JPictureSetExif pictureSetExifPanel = (JPictureSetExif) singlepanel;
                model.setPictureContainer(pictureSetExifPanel.getPictureContainer());
                model.setExifFilterKeywords(pictureSetExifPanel.getExifFilterKeywords());

            } else if (singlepanel instanceof JWilcoxon) {
                final JWilcoxon wilcoxonPanel = (JWilcoxon) singlepanel;

                if (model instanceof BoxplotModel) {
                    final BoxplotModel boxplotModel = ((BoxplotModel) model);
                    boxplotModel.getWilcoxonTest().setSignificance(wilcoxonPanel.getStatisticalSignificance());
                    boxplotModel.getWilcoxonTest().setActiveStatus(wilcoxonPanel.getStatus());
                    boxplotModel.getWilcoxonTest().setWilcoxonTestType(wilcoxonPanel.getTestType());

                }

            }
        }

        return model;

    }

}
