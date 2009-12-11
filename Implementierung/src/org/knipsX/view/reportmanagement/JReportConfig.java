package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import org.knipsX.model.reportmanagement.DummyModel;
import org.knipsX.view.JAbstractView;


public class JReportConfig extends JAbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JAbstractReportConfig reportconfig;
	private JTabbedPane tabbedpane;
	private JPanel basic;
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }   
	
	
    public JReportConfig(JAbstractReportConfig reportconfig) {
    	super(new DummyModel());
    	this.reportconfig = reportconfig;
    	this.tabbedpane = getJTabbedPane();

        setTitle("Auswertung Konfigurieren");

        initialize();

        setSize(new Dimension(450, 350));        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }	

	
    private void initialize() {
    	this.basic = new JPanel();
        this.basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel mainpanel = new JPanel(new BorderLayout());
        mainpanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        mainpanel.add(this.tabbedpane);
        basic.add(mainpanel);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton close = new JButton("Schließen");
        JButton apply = new JButton("Übernehmen");
        JButton show = new JButton("Anzeigen");

        bottom.add(close);
        bottom.add(apply);
        bottom.add(show);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
    }
    
	public void setReportConfig(JAbstractReportConfig reportconfig) {
		remove(this.basic);		
		this.reportconfig = reportconfig;
		this.tabbedpane = this.getJTabbedPane();
		initialize();	
		repaint();
	}	
	
    private JTabbedPane getJTabbedPane() {
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	for(JAbstractSinglePanel item : this.reportconfig.registeredPanels) {
    		tabbedpane.addTab(item.getTitle(), item.getIcon(), item, item.getTip());
    	}
    	
    	return tabbedpane;
    }


	@Override
	public void update(Observable model, Object argument) {
		// Do nothing		
	} 
	
	
}
