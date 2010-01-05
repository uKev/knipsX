package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
public class JReportWizard<M extends AbstractReportModel, V extends AbstractReportCompilation<M>> extends JAbstractReportUtil<M, V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7846052667877784072L;	
	private int wizardcounter = 0;
	private JComponent basic;
	
	/**
	 * Starts the wizard utility on a specified report configuration
	 * @param reportconfig the report configuration to operate on
	 */
	@SuppressWarnings("unchecked")
	public JReportWizard() {
	    super(null);
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>>) this;
		this.reportCompilation = (AbstractReportCompilation<AbstractReportModel>) ReportHelper.defaultReport.createReportCompilation();
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	private void initialize() {
	
		this.basic = new JPanel();
		this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.Y_AXIS));
		this.basic.setPreferredSize(new Dimension(800,600));
		this.add(this.basic);
		
		JAbstractSinglePanel currentpanel = this.reportCompilation.getRegisteredPanels().get(wizardcounter);		
        setTitle(currentpanel.title);
        this.basic.add(currentpanel);              
        this.basic.add(Box.createVerticalGlue());
        JPanel bottom = new JPanel();
        bottom.setAlignmentX(0.5f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        JButton ok = new JButton("OK");
        ok.setToolTipText(currentpanel.tip);
        JButton next = new JButton("Weiter");
        
        if((this.wizardcounter) >= this.reportCompilation.getRegisteredPanels().size()-1 ) {
        	next.setEnabled(false);
        }
        
        next.addActionListener(new WizardNextPanelController(this));
        JButton previous = new JButton("Zurück");
        
        if(0 >= this.wizardcounter) {
        	previous.setEnabled(false);
        }
        
        
        previous.addActionListener(new WizardPreviousPanelController(this));        
        bottom.add(previous);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));   
        bottom.add(next);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(ok);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        this.basic.add(bottom);
        this.basic.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(basic);  
        pack();
        setVisible(true);
	}
	
		
	/**
	 * Switches the current panel to the next panel if possible
	 */
	public void nextPanel() {
		this.wizardcounter++;
		this.remove(this.basic);
		initialize();
	}
	
	/**
	 * Switches the current panel to the previous panel if possible
	 */
	public void previousPanel() {
		this.wizardcounter--;
		this.remove(this.basic);
		initialize();
	}

	@Override
	public void update(Observable model, Object argument) {
		// Do nothing	    
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setReportType(AbstractReportCompilation<?> reportconfig) {
	    remove(this.basic);
		this.reportCompilation = (AbstractReportCompilation<AbstractReportModel>) reportconfig;
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation<AbstractReportModel>>) this;
		initialize();		
		repaint();
	}	
}
