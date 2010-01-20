package org.knipsX.view.reportmanagement;

import javax.swing.JButton;

import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

/**
 * This class is the parent class of every configuration utility.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public abstract class JAbstractReportUtil<M extends AbstractReportModel> extends JAbstractView<M> {

    private static final long serialVersionUID = -4795242146881401222L;

    /*
     * The reportID is responsible for tracking the currently active report which is
     * selected in the view
     */
    protected int reportID = -1;

    /* Define the close button */
    // INTERNATIONALIZE
    protected JButton closeButton = new JButton("Schließen");

    /* Define the save button */
    // INTERNATIONALIZE
    protected JButton saveButton = new JButton("Speichern");

    /* Define the show button */
    // INTERNATIONALIZE
    protected JButton showButton = new JButton("Anzeigen");

    /**
     * Each configuration utility has to implement this method which
     * registers a new report type to the current configuration utility
     * and updates it
     * 
     * @param jAbstractReport
     *            the new report type
     */
    abstract protected void setReportType(AbstractReportCompilation jAbstractReport);

    /* The current reportCompilation of the report configuration utility */
    protected AbstractReportCompilation reportCompilation;

    /**
     * The constructor of the report configuration utility
     * 
     * @param model
     *            the model which the configuration utility should configure
     */
    public JAbstractReportUtil(M model) {
        super(model);
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
    public int getReportID() {
        return this.reportID;
    }

    /**
     * Revalidates if the current report configuration utility is able to display the diagram
     * of the report in question
     */
    protected void revalidateDisplayability() {
        boolean isDisplayable = true;

        for (JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {
            if (!singlepanel.isDiagramDisplayable()) {
                isDisplayable = false;

                /*
                 * Don't use break; here otherwise some views might
                 * not receive the isDiagramDisplayable() call
                 */
            }
        }

        if (isDisplayable) {
            this.showButton.setEnabled(true);
        } else {
            this.showButton.setEnabled(false);
        }

    }

    /**
     * Revalidates if the current report configuration utility is able to save the diagram
     * of the report in question
     */
    protected void revalidateSaveability() {
        boolean isSaveable = true;

        for (JAbstractSinglePanel singlepanel : this.reportCompilation.getRegisteredPanels()) {
            if (!singlepanel.isDiagramSaveable()) {
                isSaveable = false;
                /*
                 * Don't use break; here otherwise some views might
                 * not receive the isDiagramSaveable() call
                 */
            }
        }

        if (isSaveable) {
            this.saveButton.setEnabled(true);
        } else {
            this.saveButton.setEnabled(false);
        }

    }

}
