package org.knipsX.view.reportmanagement;

import java.awt.event.WindowEvent;

import javax.swing.JButton;

import org.knipsX.Messages;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

/**
 * This class is the parent class of every configuration utility
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public abstract class JAbstractReportUtil<M extends AbstractReportModel> extends JAbstractView<M> {

    private static final long serialVersionUID = -4795242146881401222L;

    /* the reportID is responsible for tracking the currently active report which is selected in the view */
    protected int reportId = -1;

    protected JButton closeButton = new JButton(Messages.getString("JAbstractReportUtil.0"));
    protected JButton saveButton = new JButton(Messages.getString("JAbstractReportUtil.1"));
    protected JButton showButton = new JButton(Messages.getString("JAbstractReportUtil.2"));

    /**
     * Each configuration utility has to implement this method which
     * registers a new report type to the current configuration utility
     * and updates it
     * 
     * @param jAbstractReport
     *            the new report type
     */
    abstract protected void setReportType(AbstractReportCompilation jAbstractReport);

    /* the current reportCompilation of the report configuration utility */
    protected AbstractReportCompilation reportCompilation;
    
    
    
    private static boolean singleton = false;

    public static boolean isSingleton() {
		return singleton;
	}

	public static void setSingleton(boolean singleton) {
		JAbstractReportUtil.singleton = singleton;
	}

	/**
     * The constructor of the report configuration utility
     * 
     * @param model
     *            the model which the configuration utility should configure
     */
    public JAbstractReportUtil(final M model) {
        super(model);
        
        /* Disables the current project view to prevent that the user changes picture sets during report creation */
        ReportHelper.getProjectModel().setStatus(ProjectModel.INACTIVE);
        
        JAbstractReportUtil.singleton = true;
    }

    /**
     * Returns the current report compilation, for example Cluster3DConfig
     * 
     * @return the current report compilation
     */
    public AbstractReportCompilation getReportCompilation() {
        return this.reportCompilation;
    }

    /**
     * Returns the reportID of the current report configuration utility
     * 
     * @return the reportID of the current report configuration utility
     */
    public int getReportId() {
        return this.reportId;
    }

    /**
     * Revalidates if the current report configuration utility is able to display the diagram
     * of the report in question
     */
    protected void revalidateDisplayability() {
        boolean isDisplayable = true;

        for (final JAbstractSinglePanel singlePanel : this.reportCompilation.getRegisteredPanels()) {

            if (!singlePanel.isDiagramDisplayable()) {
                isDisplayable = false;
                /*
                 * Don't use break; here otherwise some views might
                 * not receive the isDiagramDisplayable() call
                 */
            }
        }
        this.showButton.setEnabled(isDisplayable);
    }

    /**
     * Revalidates if the current report configuration utility is able to save the diagram
     * of the report in question
     */
    protected void revalidateSaveability() {
        boolean isSaveable = true;

        for (final JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {

            if (!singlepanel.isDiagramSaveable()) {
                isSaveable = false;
                /*
                 * Don't use break; here otherwise some views might
                 * not receive the isDiagramSaveable() call
                 */
            }
        }
        this.saveButton.setEnabled(isSaveable);
    }

    /**
     * Set all panels so that they are able to handle revalidation requests
     */
    protected void armAllPanels() {
        for (final JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {
        	singlepanel.armed = true;
        }
    }
    
    
    /**
     * Defines the default close operation when the view is closed by the user
     */
    protected void addCloseOperation() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent winEvt) {

                /* activate the current project view */
                ReportHelper.getProjectModel().setStatus(ProjectModel.ACTIVE);
                
                JAbstractReportUtil.setSingleton(false);
                
                JAbstractReportUtil.this.dispose();
            }
        });
    }
}