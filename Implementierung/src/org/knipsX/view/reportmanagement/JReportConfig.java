package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.knipsX.view.JAbstractView;


public class JReportConfig extends JAbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedpane;
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }   
	
	
    public JReportConfig(JAbstractReportConfig reportconfig) {
    	setPreferredSize(new Dimension(200,200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.tabbedpane = reportconfig.getJTabbedPane();
        this.getContentPane().add(this.tabbedpane);
        pack();
        setVisible(true);		
    }	

	
	public void setReportConfig(JAbstractReportConfig reportconfig) {
		this.getContentPane().remove(this.tabbedpane);
		this.tabbedpane = reportconfig.getJTabbedPane();
		this.getContentPane().add(this.tabbedpane);
		repaint();
	}	
	
	
}
