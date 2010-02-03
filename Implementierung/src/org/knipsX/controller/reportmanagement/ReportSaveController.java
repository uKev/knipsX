package org.knipsX.controller.reportmanagement;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
public class ReportSaveController<M extends AbstractReportModel, V extends JAbstractReportUtil<?>> extends AbstractController<M, V> {

    private boolean showDiagram;

    /**
     * The constructor which registers the controller with the specified view
     * @param view the view the controller operates on
     * @param showDiagram specifies if the diagram should be displayed after the report has been saved
     */
    public ReportSaveController(V view, boolean showDiagram) {
        super(view);
        this.showDiagram = showDiagram;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<JAbstractSinglePanel> registeredPanels = this.view.getReportCompilation().getRegisteredPanels();

        this.model = (M) ReportSaveController.createSavableModel(registeredPanels);

        ReportHelper.getProjectModel().addReport(this.model, this.view.getReportID());

        this.view.dispose();

        if (showDiagram) {
            ReportHelper.getCurrentReport().displayDiagram(this.model, this.view.getReportID()).showDiagram();
        } 
        
        /* Activate the current project view */
        ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);

    }

    /**
     * This function takes in registered panel from a report compilation and returns a model
     * @param registeredPanels the registers panels from the report compilation
     * @return a saveable model
     */
    public static AbstractReportModel createSavableModel(ArrayList<JAbstractSinglePanel> registeredPanels) {

        AbstractReportModel model = ReportHelper.getCurrentReport().createReportModel();

        for (JAbstractSinglePanel singlepanel : registeredPanels) {

            if (singlepanel instanceof JDiagramType) {

                JDiagramType mydiagram = (JDiagramType) singlepanel;
                model.setReportName(mydiagram.getReportName());
                model.setReportDescription(mydiagram.getReportDescription());

            } else if (singlepanel instanceof JParameters) {

                JParameters parametersPanel = (JParameters) singlepanel;

                if (model instanceof BoxplotModel) {
                    ((BoxplotModel) model).setxAxis(parametersPanel.getAxes().get(0));
                } else if (model instanceof Histogram2DModel) {
                    ((Histogram2DModel) model).setxAxis(parametersPanel.getAxes().get(0));                    
                } else if (model instanceof Histogram3DModel) {
                    ((Histogram3DModel) model).setxAxis(parametersPanel.getAxes().get(0));
                    ((Histogram3DModel) model).setzAxis(parametersPanel.getAxes().get(1));
                } else if (model instanceof Cluster3DModel) {
                    ((Cluster3DModel) model).setxAxis(parametersPanel.getAxes().get(0));
                    ((Cluster3DModel) model).setzAxis(parametersPanel.getAxes().get(1));
                    ((Cluster3DModel) model).setyAxis(parametersPanel.getAxes().get(2));
                }

            } else if (singlepanel instanceof JPictureSetExif) {

                JPictureSetExif pictureSetExifPanel = (JPictureSetExif) singlepanel;
                model.setPictureContainer(pictureSetExifPanel.getPictureContainer());
                model.setExifFilterKeywords(pictureSetExifPanel.getExifFilterKeywords());

            } else if (singlepanel instanceof JWilcoxon) {
                JWilcoxon wilcoxonPanel = (JWilcoxon) singlepanel;

                if (model instanceof BoxplotModel) {
                    ((BoxplotModel) model).setWilcoxonSignificance(wilcoxonPanel.getStatisticalSignificance());
                    ((BoxplotModel) model).setWilcoxonTestType(wilcoxonPanel.getTestType());
                    ((BoxplotModel) model).setWilcoxonTestActive(wilcoxonPanel.getStatus());
                    
                }

            }
        }

        return model;

    }

}
