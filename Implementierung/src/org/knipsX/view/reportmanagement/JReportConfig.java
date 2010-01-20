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
import org.knipsX.model.reportmanagement.*;

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

    public JReportConfig(final M model, final int reportID) {
        super(model);
        this.reportID = reportID;
        ReportHelper.currentModel = this.model;

        if (model instanceof BoxplotModel) {
            ReportHelper.currentReport = ReportHelper.Boxplot;
        } else if (model instanceof TableModel) {
            ReportHelper.currentReport = ReportHelper.Table;
        } else if (model instanceof Cluster3DModel) {
            ReportHelper.currentReport = ReportHelper.Cluster3D;
        } else if (model instanceof Histogram2DModel) {
            ReportHelper.currentReport = ReportHelper.Histogram2D;
        } else if (model instanceof Histogram3DModel) {
            ReportHelper.currentReport = ReportHelper.Histogram3D;
        }

        this.reportCompilation = (AbstractReportCompilation) ReportHelper.currentReport.createReportCompilation();
        ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel>) this;
        this.tabbedpane = this.getJTabbedPane();
        this.setTitle("Auswertung konfigurieren");

        this.initialize();

        this.closeButton.addActionListener(new ReportCloseController(this));
        this.saveButton.addActionListener(new ReportSaveController(this, false));
        this.showButton.addActionListener(new ReportSaveController(this, true));
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
        this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.Y_AXIS));
        this.add(this.basic);

        this.mainpanel = new JPanel(new BorderLayout());
        this.mainpanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        this.mainpanel.add(this.tabbedpane);
        this.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));

        this.basic.add(this.mainpanel);

        final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

        bottom.add(closeButton);
        bottom.add(saveButton);
        bottom.add(showButton);
        this.basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        this.pack();
        ReportHelper.currentReportUtil.revalidateDisplayability();
        ReportHelper.currentReportUtil.revalidateSaveability();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReportType(final AbstractReportCompilation reportconfig) {
        this.mysize[1] = this.getBounds().height;
        this.mysize[0] = this.getBounds().width;

        this.remove(this.basic);
        this.reportCompilation = (AbstractReportCompilation) reportconfig;
        this.tabbedpane = this.getJTabbedPane();
        ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel>) this;
        this.initialize();
    }

    @Override
    public void update(final Observable model, final Object argument) {
        // Do nothing
    }

}