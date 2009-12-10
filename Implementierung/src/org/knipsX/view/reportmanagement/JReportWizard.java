package org.knipsX.view.reportmanagement;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.knipsX.model.reportmanagement.DummyModel;
import org.knipsX.view.JAbstractView;

public class JReportWizard extends JAbstractView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7846052667877784072L;
	private JAbstractReportConfig reportconfig;
	private int wizardcounter = 0;
	private JComponent basic;
	
	
	public JReportWizard(JAbstractReportConfig reportconfig) {
		super(new DummyModel());
		this.reportconfig = reportconfig;
		initialize();
	}
	
	private void initialize() {
				
		JAbstractSinglePanel currentpanel = this.reportconfig.getregisteredPanels().get(wizardcounter);		
        setTitle(currentpanel.title);
        this.basic = currentpanel;
        this.basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        this.getContentPane().add(basic);        
        this.basic.add(Box.createVerticalGlue());
        JPanel bottom = new JPanel();
        bottom.setAlignmentX(1f);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        JButton ok = new JButton("OK");
        ok.setToolTipText(currentpanel.tip);
        JButton next = new JButton("Next");
        bottom.add(ok);
        bottom.add(Box.createRigidArea(new Dimension(5, 0)));
        bottom.add(next);
        bottom.add(Box.createRigidArea(new Dimension(15, 0)));
        this.basic.add(bottom);
        this.basic.add(Box.createRigidArea(new Dimension(0, 15)));
        pack();
        setVisible(true);
	}
	
	public void setReportConfig(JAbstractReportConfig reportconfig) {
		remove(this.basic);
		this.reportconfig = reportconfig;
		initialize();		
		repaint();
	}	
}
