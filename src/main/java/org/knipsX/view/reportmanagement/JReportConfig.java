package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
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

    private static final long serialVersionUID = 7028621688633924200L;

    private JTabbedPane tabbedPane;

    private JPanel basic;
    private JPanel mainpanel;
    
    private Logger logger = Logger.getLogger(this.getClass());

    //TODO don't use fixed sizes here. Get size from a utility class
    private final int[] mysize = { 800, 600 };

    /**
     * This constructor creates a new report configuration utility of an existing
     * report model
     * 
     * @param model
     *            the report model you want to configure
     * @param reportID
     *            the report id of the report in the view
     */
    public JReportConfig(final M model, final int reportID) {
        super(model);

        this.setTitle(Messages.getString("JReportConfig.0"));

        ReportHelper.setCurrentModel(this.model);
        ReportHelper.setCurrentReportUtility(this);

        this.addCloseOperation();
        this.addCurrentReportConfig();
        
        

        this.reportId = reportID;
        this.reportCompilation = ReportHelper.getCurrentReport().createReportCompilation();

        this.closeButton.addActionListener(new ReportCloseController<AbstractModel, JReportConfig<?, ?>>(this));
        this.saveButton.addActionListener(new ReportSaveController<AbstractReportModel, JReportConfig<?, ?>>(this,
                false));
        this.showButton
                .addActionListener(new ReportSaveController<AbstractReportModel, JReportConfig<?, ?>>(this, true));

        this.initialize();
        
        
        logger.info("Assoicated Picture Sets " + this.model.getPictureContainer());
        
        
        
    }

    private void initialize() {
        final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setMaximumSize(new Dimension(450, 0));
        bottom.add(this.closeButton);
        bottom.add(this.saveButton);
        bottom.add(this.showButton);

        this.mainpanel = new JPanel(new BorderLayout());
        this.mainpanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        this.mainpanel.add(this.getJTabbedPane());

        this.basic = new JPanel();
        this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.PAGE_AXIS));
        this.basic.add(this.mainpanel);
        this.basic.add(bottom);

        this.add(this.basic);

       
        this.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));
        this.setSize(new Dimension(this.mysize[0], this.mysize[1]));
        this.pack();  
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.armAllPanels();
        this.revalidateDisplayability();
        this.revalidateSaveability();
    }

    private void addCurrentReportConfig() {

        if (this.model instanceof BoxplotModel) {
            ReportHelper.setCurrentReport(ReportHelper.Boxplot);
        } else if (this.model instanceof TableModel) {
            ReportHelper.setCurrentReport(ReportHelper.Table);
        } else if (this.model instanceof Cluster3DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Cluster3D);
        } else if (this.model instanceof Histogram2DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Histogram2D);
        } else if (this.model instanceof Histogram3DModel) {
            ReportHelper.setCurrentReport(ReportHelper.Histogram3D);
        }
    }

    private JTabbedPane getJTabbedPane() {
        this.tabbedPane = new JTabbedPane();

        for (final JAbstractSinglePanel panel : this.reportCompilation.getRegisteredPanels()) {
            this.tabbedPane.addTab(panel.getTitle(), panel.getIcon(), panel);

            /*
             * Revalidate the Wilcoxon panel because picture sets might have been registered
             * with the current report and a non ordinal EXIF parameter might have been
             * associated with the available parameter
             */
            if (panel instanceof JPictureSetExif) {
                ((JPictureSetExif) panel).revalidateWilcoxon();
            } else if (panel instanceof JParameters) {
                ((JParameters) panel).revalidateWilcoxon();
            }
            panel.revalidateReport();
        }
        return this.tabbedPane;
    }

    @Override
    public void setReportType(final AbstractReportCompilation reportconfig) {

        /* remember the size of the current configuration utility so that it can be resized properly */
        this.mysize[1] = this.getBounds().height;
        this.mysize[0] = this.getBounds().width;

        /* remove the basic panel from the view because it is no longer needed */
        this.remove(this.basic);

        /* update necessary variables */
        this.reportCompilation = reportconfig;

        /* generate the JTabbedPane */
        this.tabbedPane = this.getJTabbedPane();

        ReportHelper.setCurrentReportUtility(this);

        /* reinitialize the panel */
        this.initialize();
    }

    @Override
    public void update(final Observable model, final Object argument) {
        /* Do nothing */
    }
}