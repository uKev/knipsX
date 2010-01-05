package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import org.knipsX.controller.reportmanagement.ReportSaveController;
import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * This class represents the report configuration utility for an existing report. It
 * offers the user the functionally to alter an existing report in any possible way.
 * 
 * @author David Kaufman
 *
 * @param <M>
 * @param <V>
 */
public class JReportConfig<M extends AbstractReportModel, V extends AbstractReportCompilation<M>> extends JAbstractReportUtil<M, V> {

    private static final long serialVersionUID = 1L;

    private JTabbedPane tabbedpane;
    private JPanel basic;
    private JPanel mainpanel;
    private JButton show;

    private final int[] mysize = {800,600};

    @SuppressWarnings("unchecked")
    public JReportConfig(final M model, final V view) {
		super(model);
		this.reportCompilation  = (AbstractReportCompilation<AbstractReportModel>) view;	
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>>) this;
		this.tabbedpane = this.getJTabbedPane();		
		this.setTitle("Auswertung konfigurieren");		
		this.initialize();		
		this.setSize(new Dimension(this.mysize[0], this.mysize[1]));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//For debugging purposes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	
    }

    private JTabbedPane getJTabbedPane() {
		final JTabbedPane tabbedpane = new JTabbedPane();
	
		for (final JAbstractSinglePanel item : this.reportCompilation.getRegisteredPanels()) {
		    tabbedpane.addTab(item.getTitle(), item.getIcon(), item, item.getTip());
		    
		    /*
		     *  revalidate the boxplot panel because picture sets might have been registered
		     *  with the current report 
		     */
		    
		    if(item instanceof JPictureSetExif) {
		    	((JPictureSetExif) item).revalidateBoxplot();
		    }
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
	
		final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
		final JButton close = new JButton("Schließen");
		final JButton apply = new JButton("Übernehmen");
		this.show = new JButton("Anzeigen");
		
		show.addActionListener(new ReportSaveController<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>>(ReportHelper.currentModel, this.reportCompilation, true));
	
		bottom.add(close);
		bottom.add(apply);
		bottom.add(show);
		this.basic.add(bottom);
	
		bottom.setMaximumSize(new Dimension(450, 0));
		this.pack();
    }
    

    @Override
    @SuppressWarnings("unchecked")
    public void setReportType(final AbstractReportCompilation<?> reportconfig) {
		this.mysize[1] = this.mainpanel.getBounds().height;
		this.mysize[0] = this.mainpanel.getBounds().width;
	
		this.remove(this.basic);
		this.reportCompilation = (AbstractReportCompilation<AbstractReportModel>) reportconfig;
		this.tabbedpane = this.getJTabbedPane();
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>>) this;
		this.initialize();
	}

    @Override
    public void update(final Observable model, final Object argument) {
    	// Do nothing
    }

}