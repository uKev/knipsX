package org.knipsX.view.reportmanagement;

import java.awt.Component;
import java.util.ArrayList;

import org.knipsX.Messages;
import org.knipsX.images.dummypictures.DummyPictures;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.utils.ExifParameter;
import org.knipsX.view.diagrams.JAbstractDiagram;
import org.knipsX.view.diagrams.JBoxplot;
import org.knipsX.view.diagrams.JCluster3D;
import org.knipsX.view.diagrams.JHistogram2D;
import org.knipsX.view.diagrams.JHistogram3D;
import org.knipsX.view.diagrams.JTableDiagram;

/**
 * This class is the mediator between the report configuration utility and
 * the model it creates.
 * 
 * It is responsible for saving the currently active report and also for
 * refreshing the specified configuration utility.
 * 
 * 
 * @author David Kaufman
 * 
 */
public enum ReportHelper {

    /** The report which identifies a boxplot report **/
    Boxplot {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportCompilation createReportCompilation() {
            ReportHelper.currentReport = ReportHelper.Boxplot;
            return new BoxplotConfig();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportModel createReportModel() {
            return new BoxplotModel();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public JAbstractDiagram<?> displayDiagram(final AbstractReportModel model, final int reportID) {
            return new JBoxplot<BoxplotModel>((BoxplotModel) model, reportID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Component getDiagramView() {
            BoxplotModel boxplotModel = new BoxplotModel();
            boxplotModel.setPictureContainer(getDummyPictureSet());
            boxplotModel.setXAxis(new Axis(ExifParameter.EXPOSURETIME));            
            return new JBoxplot<BoxplotModel>(boxplotModel, -1).getDiagram();
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public int getNumberOfAxes() {
            return 1;
        }

        @Override
        public String toString() {
            return Messages.getString("ReportHelper.0");
        }
    },

    /** The report which identifies a 2D histogram report **/
    Histogram2D {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportCompilation createReportCompilation() {
            ReportHelper.currentReport = ReportHelper.Histogram2D;
            return new Histogram2DConfig();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportModel createReportModel() {
            return new Histogram2DModel();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public JAbstractDiagram<Histogram2DModel> displayDiagram(final AbstractReportModel model, final int reportID) {
            return new JHistogram2D<Histogram2DModel>((Histogram2DModel) model, reportID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Component getDiagramView() {
            Histogram2DModel histogram2DModel = new Histogram2DModel();            
            histogram2DModel.setPictureContainer(getDummyPictureSet());
            histogram2DModel.setXAxis(new Axis(ExifParameter.EXPOSURETIME));            
            return new JHistogram2D<Histogram2DModel>(histogram2DModel, -1).getDiagram();
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public int getNumberOfAxes() {
            return 1;
        }

        @Override
        public String toString() {
            //
            return Messages.getString("ReportHelper.1");
        }
    },

    /** The report which identifies a 3D histogram report **/
    Histogram3D {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportCompilation createReportCompilation() {
            ReportHelper.currentReport = ReportHelper.Histogram3D;
            return new Histogram3DConfig();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportModel createReportModel() {
            return new Histogram3DModel();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public JAbstractDiagram<Histogram3DModel> displayDiagram(final AbstractReportModel model, final int reportID) {
            return new JHistogram3D<Histogram3DModel>((Histogram3DModel) model, reportID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Component getDiagramView() {
            Histogram3DModel histogram3DModel = new Histogram3DModel();            
            histogram3DModel.setPictureContainer(getDummyPictureSet());
            histogram3DModel.setXAxis(new Axis(ExifParameter.DATE));
            histogram3DModel.setZAxis(new Axis(ExifParameter.EXPOSURETIME));  
            return new JHistogram3D<Histogram3DModel>(histogram3DModel, -1).getDiagram();
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public int getNumberOfAxes() {
            return 2;
        }

        @Override
        public String toString() {
            //
            return Messages.getString("ReportHelper.2");
        }

    },

    /** The report which identifies a 3D cluster report **/
    Cluster3D {
        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportCompilation createReportCompilation() {
            ReportHelper.currentReport = ReportHelper.Cluster3D;
            return new Cluster3DConfig();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportModel createReportModel() {
            return new Cluster3DModel();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public JAbstractDiagram<?> displayDiagram(final AbstractReportModel model, final int reportID) {
            return new JCluster3D<Cluster3DModel>((Cluster3DModel) model, reportID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Component getDiagramView() {
            Cluster3DModel cluster3DModel = new Cluster3DModel();            
            cluster3DModel.setPictureContainer(getDummyPictureSet());
            cluster3DModel.setXAxis(new Axis(ExifParameter.EXPOSURETIME));
            cluster3DModel.setZAxis(new Axis(ExifParameter.EXPOSURETIME));
            cluster3DModel.setYAxis(new Axis(ExifParameter.DATE));
            return new JCluster3D<Cluster3DModel>(cluster3DModel, -1).getDiagram();
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public int getNumberOfAxes() {
            return 3;
        }

        @Override
        public String toString() {
            //
            return Messages.getString("ReportHelper.3");
        }

    },

    /** The report which identifies a table report **/
    Table {

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportCompilation createReportCompilation() {
            ReportHelper.currentReport = ReportHelper.Table;
            return new TableConfig();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AbstractReportModel createReportModel() {
            return new TableModel(null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public JAbstractDiagram<TableModel> displayDiagram(final AbstractReportModel model, final int reportID) {
            return new JTableDiagram<TableModel>((TableModel) model, reportID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Component getDiagramView() {
            TableModel tableModel = new TableModel(); 
            tableModel.setPictureContainer(getDummyPictureSet());
            return new JTableDiagram<TableModel>(tableModel, -1).getDiagram();
        };

        /**
         * {@inheritDoc}
         */
        @Override
        public int getNumberOfAxes() {
            return 0;
        }

        @Override
        public String toString() {
            //
            return Messages.getString("ReportHelper.4");
        }
    };

    /**
     * The current report of the current report configuration run
     */
    private static ReportHelper currentReport;

    /**
     * The default report which is selected automatically when you start the Wizard
     */
    private static ReportHelper defaultReport = ReportHelper.Boxplot;

    /**
     * The current configuration utility of the current report configuration run
     */
    private static JAbstractReportUtil<?> currentReportUtility;

    /**
     * The current model of the current report configuration run
     * 
     */
    // public because controller needs access
    private static AbstractReportModel currentModel;

    // public because controller needs access
    private static ProjectModel currentProjectModel;
    
    /**
     * {@inheritDoc}
     */
    public abstract String toString();

    /**
     * Returns the current model
     * 
     * @return The current model which is associated with the current report
     */
    public static AbstractReportModel getCurrentModel() {
        return ReportHelper.currentModel;
    }

    /**
     * Returns the current model of the report
     * 
     * @return the current model
     */
    public static ProjectModel getCurrentProjectModel() {
        return ReportHelper.currentProjectModel;
    }

    /**
     * Returns the current report
     * 
     * @return the current report
     */
    public static ReportHelper getCurrentReport() {
        return ReportHelper.currentReport;
    }

    /**
     * Returns the default report which is preselected in the wizard
     * 
     * @return the default report
     */
    public static ReportHelper getDefaultReport() {
        return ReportHelper.defaultReport;
    }

    /**
     * Returns a reference to the project model
     * 
     * @return the current project model
     */
    public static ProjectModel getProjectModel() {
        return ReportHelper.currentProjectModel;
    }

    /**
     * Sets the current report model to the specified model
     * 
     * @param currentModel
     *            the current report model
     */
    public static void setCurrentModel(final AbstractReportModel currentModel) {
        ReportHelper.currentModel = currentModel;
    }

    /**
     * Sets the current project model
     * 
     * @param currentProjectModel
     *            the current project model
     */
    public static void setCurrentProjectModel(final ProjectModel currentProjectModel) {
        ReportHelper.currentProjectModel = currentProjectModel;
    }

    /**
     * Sets the current report to the specified report
     * 
     * @param currentReport
     *            the current report
     */
    public static void setCurrentReport(final ReportHelper currentReport) {
        ReportHelper.currentReport = currentReport;
    }

    /**
     * Sets the project model to the specified model
     * 
     * @param projectModel
     *            the current project model
     */
    public static void setProjectModel(final ProjectModel projectModel) {
        ReportHelper.currentProjectModel = projectModel;
    }

    /**
     * Sets the current report and updates the configuration utility
     * Use this method if you want explicitly update the configuration utility
     * 
     * @param myreport
     *            The report you want to update
     */
    public static void updateReport(final ReportHelper myreport) {        
        ReportHelper.currentReport = myreport;
        ReportHelper.currentReportUtility.setReportType(ReportHelper.currentReport.createReportCompilation());
    }

    /**
     * Returns the report type associated with the specified report enum
     * 
     * @return the report type associated with the specified report enum
     */
    public abstract AbstractReportCompilation createReportCompilation();

    /**
     * Creates and returns the associated report model with the specified report enum
     * 
     * @return the associated reportmodel with the specified report enum
     */
    public abstract AbstractReportModel createReportModel();

    /**
     * Returns the complete diagram with associated buttons. To show the diagram on screen call the
     * respective method.
     * 
     * @param model
     *            the report model of the report you want to visualize
     * @param reportID
     *            the report id of the report
     * @return the diagram
     */
    public abstract JAbstractDiagram<?> displayDiagram(AbstractReportModel model, int reportID);

    /**
     * Returns the Component of the Diagram without the associated button
     * 
     * @return the component diagram
     */
    public abstract Component getDiagramView();

    /**
     * Returns the number of axes each diagram type uses
     * 
     * @return the number of axes each diagram type uses <br>
     *         returns 0 if no axis is used
     */
    public abstract int getNumberOfAxes();

    /**
     * Returns the current report utility
     * @return the current report utility
     */
    public static JAbstractReportUtil<?> getCurrentReportUtility() {
        return currentReportUtility;
    }

    /**
     * Sets the current report utility to the specified value
     * @param currentReportUtility the current report utility
     */
    public static void setCurrentReportUtility(JAbstractReportUtil<?> currentReportUtility) {
        ReportHelper.currentReportUtility = currentReportUtility;
    }
    
    private static ArrayList<PictureContainer> getDummyPictureSet() {        
        ArrayList<PictureContainer> container = new ArrayList<PictureContainer>();
        container.add(DummyPictures.getDummyPictureSet());        
        return container;        
    }
    

}
