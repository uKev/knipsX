package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import org.knipsX.controller.reportmanagement.ReportCloseController;
import org.knipsX.controller.reportmanagement.ReportSaveController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.model.reportmanagement.TableModel;

/**
 * This class represents the report configuration utility for an existing report. It
 * offers the user the functionally to alter an existing report in any possible way.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 * @param <V>
 */
public class JReportConfig<M extends AbstractReportModel, V extends AbstractReportCompilation> extends
        JAbstractReportUtil<M> {

    private static final long serialVersionUID = 1L;

    private JTabbedPane tabbedpane;
    private JPanel basic;
    private JPanel mainpanel;
    private final int[] mysize = { 800, 600 };

    /**
     * This constructor creates a new report configuration utility of an existing 
     * report model
     * @param model the report model you want to configure
     * @param reportID the report id of the report in the view
     */
    public JReportConfig(final M model, final int reportID) {
        super(model);
        this.reportID = reportID;
        ReportHelper.setCurrentModel(this.model);

        if (model instanceof BoxplotModel) {
            ReportHelper.setCurrentReport(ReportHelper.Boxplot);
        } else if (model instanceof TableModel) {
            ReportHelper.setCurrentReport(ReportHelper.Table);
        } else if (model instanceof Cluster3DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Cluster3D);
        } else if (model instanceof Histogram2DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Histogram2D);
        } else if (model instanceof Histogram3DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Histogram3D);
        }

        this.reportCompilation = ReportHelper.getCurrentReport().createReportCompilation();
        ReportHelper.currentReportUtil = this;
        this.tabbedpane = this.getJTabbedPane();
        //INTERNATIONALIZE
        this.setTitle("Auswertung konfigurieren");

        this.initialize();

        this.closeButton.addActionListener(new ReportCloseController<AbstractModel, JReportConfig<?, ?>>(this));
        this.saveButton.addActionListener(new ReportSaveController<AbstractReportModel, JReportConfig<?, ?>>(this,
                false));
        this.showButton
                .addActionListener(new ReportSaveController<AbstractReportModel, JReportConfig<?, ?>>(this, true));
        this.setSize(new Dimension(this.mysize[0], this.mysize[1]));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private JTabbedPane getJTabbedPane() {
        final JTabbedPane tabbedpane = new JTabbedPane();

        for (final JAbstractSinglePanel item : this.reportCompilation.getRegisteredPanels()) {

            tabbedpane.addTab(item.getTitle(), item);

            /*
             * revalidate the boxplot panel because picture sets might have been registered
             * with the current report
             */

            if (item instanceof JPictureSetExif) {
                ((JPictureSetExif) item).revalidateBoxplot();
            }

            item.revalidateReport();
        }

        return tabbedpane;
    }

    private void initialize() {

        this.basic = new JPanel();
        this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.PAGE_AXIS));
        this.add(this.basic);

        this.mainpanel = new JPanel(new BorderLayout());
        this.mainpanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        this.mainpanel.add(this.tabbedpane);
        
        /* Resize this window properly*/
        this.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));

        this.basic.add(this.mainpanel);

        final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

        bottom.add(this.closeButton);
        bottom.add(this.saveButton);
        bottom.add(this.showButton);
        this.basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        
        this.pack();
        
        ReportHelper.currentReportUtil.revalidateDisplayability();
        ReportHelper.currentReportUtil.revalidateSaveability();
    }

    @Override
    public void setReportType(final AbstractReportCompilation reportconfig) {
        /* Remember the size of the current configuration utility so that it can be resized properly */
        this.mysize[1] = this.getBounds().height;
        this.mysize[0] = this.getBounds().width;

        /* Remove the basic panel from the view because it is no longer needed */
        this.remove(this.basic);
        
        /* Update necessary variables */        
        this.reportCompilation = reportconfig;
        
        /* Generate the JTabbedPane */
        this.tabbedpane = this.getJTabbedPane();
        
        ReportHelper.currentReportUtil = this;
        
        /* Reinitialize the panel */
        this.initialize();
    }

    @Override
    public void update(final Observable model, final Object argument) {
        /* Do nothing */
    }

}