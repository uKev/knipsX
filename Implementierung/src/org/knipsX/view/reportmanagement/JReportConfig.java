package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import org.knipsX.controller.reportmanagement.SaveReportController;
import org.knipsX.model.reportmanagement.AbstractReportModel;

public class JReportConfig<M extends AbstractReportModel, V extends JAbstractReport<M>> extends
	JAbstractReportType<M, V> {

    private static final long serialVersionUID = 1L;

    private JAbstractReport<AbstractReportModel> reportConfig;

    private JTabbedPane tabbedpane;

    private JPanel basic;
    private JPanel mainpanel;

    private final int[] mysize = {800,600};

    @SuppressWarnings("unchecked")
    public JReportConfig(final M model, final V view) {
	super(model);
	this.reportConfig = (JAbstractReport<AbstractReportModel>) view;
	
	
	this.tabbedpane = this.getJTabbedPane();
	Report.myconfig = (JAbstractReportType<AbstractReportModel, JAbstractReport<AbstractReportModel>>) this;

	this.setTitle("Auswertung Konfigurieren");	
	
	this.initialize();
	
	this.setSize(new Dimension(this.mysize[0], this.mysize[1]));
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	
	
    }

    private JTabbedPane getJTabbedPane() {
		final JTabbedPane tabbedpane = new JTabbedPane();
	
		for (final JAbstractSinglePanel item : this.reportConfig.getregisteredPanels()) {
		    tabbedpane.addTab(item.getTitle(), item.getIcon(), item, item.getTip());
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
		this.mainpanel.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));
	
		this.basic.add(this.mainpanel);
	
		final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
		final JButton close = new JButton("Schließen");
		final JButton apply = new JButton("Übernehmen");
		final JButton show = new JButton("Anzeigen");
		show.addActionListener(new SaveReportController<AbstractReportModel, JAbstractReport<AbstractReportModel>>(
			Report.currentModel, this.reportConfig, true));
	
		bottom.add(close);
		bottom.add(apply);
		bottom.add(show);
		this.basic.add(bottom);
	
		bottom.setMaximumSize(new Dimension(450, 0));
		this.pack();
}
    

    @Override
    @SuppressWarnings("unchecked")
    public void setReportType(final JAbstractReport<?> reportconfig) {
		this.mysize[1] = this.mainpanel.getBounds().height;
		this.mysize[0] = this.mainpanel.getBounds().width;
	
		this.remove(this.basic);
		this.reportConfig = (JAbstractReport<AbstractReportModel>) reportconfig;
		this.tabbedpane = this.getJTabbedPane();
		Report.myconfig = (JAbstractReportType<AbstractReportModel, JAbstractReport<AbstractReportModel>>) this;
		this.initialize();
		this.pack();

    }

    @Override
    public void update(final Observable model, final Object argument) {
	// Do nothing
    }

}