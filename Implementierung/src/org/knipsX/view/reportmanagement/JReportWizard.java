package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

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
public class JReportWizard<M extends AbstractReportModel, V extends AbstractReportCompilation> extends JAbstractReportUtil<M, V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7846052667877784072L;	
	private int wizardcounter = 0;
	private JComponent basic;
	private JButton nextPanelButton;
	private JButton previousPanelButton;
	private final int[] mysize = {800,600};
	
	/**
	 * Starts the wizard utility on a specified report configuration
	 * @param reportconfig the report configuration to operate on
	 */
	@SuppressWarnings("unchecked")
	public JReportWizard() {
	    super(null);
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation>) this;
		this.reportCompilation = (AbstractReportCompilation) ReportHelper.defaultReport.createReportCompilation(null);
		
		this.nextPanelButton = new JButton("Weiter");
		this.previousPanelButton = new JButton("Zurück");
		
		this.close.addActionListener(new ReportCloseController(this));        
        this.save.addActionListener(new ReportSaveController(this, false));        
        this.show.addActionListener(new ReportSaveController(this, true));
        this.nextPanelButton.addActionListener(new WizardNextPanelController(this));
        this.previousPanelButton.addActionListener(new WizardPreviousPanelController(this));
        
		initialize();	

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void initialize() {
		
		
		this.basic = new JPanel();
		this.basic.setLayout(new BoxLayout(this.basic, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(this.mysize[0], this.mysize[1]));
		this.add(this.basic);
		
		JAbstractSinglePanel currentpanel = this.reportCompilation.getRegisteredPanels().get(wizardcounter);	
		
        setTitle(currentpanel.title);
        this.basic.add(currentpanel);              
        this.basic.add(Box.createVerticalGlue());
                 
        pack();
        setVisible(true);
        
        if((this.wizardcounter) >= this.reportCompilation.getRegisteredPanels().size()-1 ) {
        	this.nextPanelButton.setEnabled(false);
        } else {
        	this.nextPanelButton.setEnabled(true);
        }
        
        if(this.wizardcounter == 0) {
        	this.previousPanelButton.setEnabled(false);
        } else {
        	this.previousPanelButton.setEnabled(true);
        }
        
        ReportHelper.currentReportUtil.revalidateDisplayability();
        ReportHelper.currentReportUtil.revalidateSaveability();
        
        JPanel bottom = new JPanel();
        bottom.setAlignmentX(0.5f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        bottom.add(this.close);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.previousPanelButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));   
        bottom.add(this.nextPanelButton);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        bottom.add(this.save);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));        
        bottom.add(this.show);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        this.basic.add(bottom);
        this.basic.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(basic); 
        pack();
}
	
		
	/**
	 * Switches the current panel to the next panel if possible
	 */
	public void nextPanel() {
		resize();
		this.wizardcounter++;
		this.remove(this.basic);
		initialize();
	}
	
	/**
	 * Switches the current panel to the previous panel if possible
	 */
	public void previousPanel() {
		resize();
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
	protected void setReportType(AbstractReportCompilation reportconfig) {
		resize();
	    remove(this.basic);
		this.reportCompilation = (AbstractReportCompilation) reportconfig;
		ReportHelper.currentReportUtil = (JAbstractReportUtil<AbstractReportModel, AbstractReportCompilation>) this;
		initialize();		
		repaint();
	}
	
	private void resize() {
		this.mysize[1] = this.getBounds().height;
		this.mysize[0] = this.getBounds().width;
	}
}
