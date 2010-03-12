package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.knipsX.Messages;
import org.knipsX.controller.reportmanagement.ReportCloseController;
import org.knipsX.controller.reportmanagement.ReportSaveController;
import org.knipsX.controller.reportmanagement.WizardNextPanelController;
import org.knipsX.controller.reportmanagement.WizardPreviousPanelController;
import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * This class represents the report creation wizard. It is responsible for creating
 * a ReportModel.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 * @param <V>
 */
public class JReportWizard<M extends AbstractReportModel, V extends AbstractReportCompilation> extends
        JAbstractReportUtil<M> {

    private static final long serialVersionUID = -7846052667877784072L;

    private JComponent basic;

    private final JButton nextPanelButton;
    private final JButton previousPanelButton;

    private final int[] mysize = { 800, 600 };

    /* keeps track of the current panel in the wizard */
    private int wizardcounter = 0;

    /**
     * Starts the wizard utility on a specified report configuration
     */
    public JReportWizard() {
        super(null);

        ReportHelper.setCurrentModel(null);
        ReportHelper.setCurrentReportUtility(this);

        this.addCloseOperation();

        this.reportCompilation = ReportHelper.getDefaultReport().createReportCompilation();

        this.nextPanelButton = new JButton(Messages.getString("JReportWizard.0"));
        this.previousPanelButton = new JButton(Messages.getString("JReportWizard.1"));

        this.closeButton.addActionListener(new ReportCloseController<AbstractReportModel, JReportWizard<?, ?>>(this));
        this.saveButton.addActionListener(new ReportSaveController<AbstractReportModel, JReportWizard<?, ?>>(this,
                false));
        this.showButton
                .addActionListener(new ReportSaveController<AbstractReportModel, JReportWizard<?, ?>>(this, true));
        this.nextPanelButton.addActionListener(new WizardNextPanelController<AbstractReportModel, JReportWizard<?, ?>>(
                this));
        this.previousPanelButton
                .addActionListener(new WizardPreviousPanelController<AbstractReportModel, JReportWizard<?, ?>>(this));

        this.initialize();
    }

    private void initialize() {

        /* get current panel which should be drawn */
        final JAbstractSinglePanel currentPanel = this.reportCompilation.getRegisteredPanels().get(this.wizardcounter);

        this.setTitle(currentPanel.title);

        if ((this.wizardcounter) >= this.reportCompilation.getRegisteredPanels().size() - 1) {
            this.nextPanelButton.setEnabled(false);
        } else {
            this.nextPanelButton.setEnabled(true);
        }

        if (this.wizardcounter == 0) {
            this.previousPanelButton.setEnabled(false);
        } else {
            this.previousPanelButton.setEnabled(true);
        }

        ReportHelper.getCurrentReportUtility().revalidateDisplayability();
        ReportHelper.getCurrentReportUtility().revalidateSaveability();

        final JPanel bottom = new JPanel();
        bottom.setAlignmentX(0.5f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.add(this.closeButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.previousPanelButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.nextPanelButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.saveButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.showButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));

        this.basic = new JPanel();
        this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.PAGE_AXIS));
        this.basic.add(currentPanel);
        this.basic.add(Box.createVerticalGlue());
        this.basic.add(bottom);
        this.basic.add(Box.createRigidArea(new Dimension(0, 15)));

        this.add(this.basic);
        this.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        this.setVisible(true);        
    }

    /**
     * Switches the current panel to the next panel if possible
     */
    public void nextPanel() {
        this.rememberSize();
        this.wizardcounter++;
        this.remove(this.basic);
        this.initialize();
    }

    /**
     * Switches the current panel to the previous panel if possible
     */
    public void previousPanel() {
        this.rememberSize();
        this.wizardcounter--;
        this.remove(this.basic);
        this.initialize();
    }

    /* Remember the size of the current configuration utility so that it can be resized properly */
    private void rememberSize() {
        this.mysize[1] = this.getBounds().height;
        this.mysize[0] = this.getBounds().width;
    }

    @Override
    protected void setReportType(final AbstractReportCompilation reportconfig) {

        /* remember the size of the current configuration utility so that it can be resized properly */
        this.rememberSize();

        /* remove the basic panel from the view because it is no longer needed */
        this.remove(this.basic);

        /* update necessary variables */
        this.reportCompilation = reportconfig;

        ReportHelper.setCurrentReportUtility(this);

        /* Reinitialize the panel */
        this.initialize();
    }

    @Override
    public void update(final Observable model, final Object argument) {
        // Do nothing
    }
}