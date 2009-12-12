package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.knipsX.controller.reportmanagement.SaveReportController;
import org.knipsX.model.reportmanagement.DummyModel;

public class JReportConfig extends JAbstractReportConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JAbstractReport reportconfig;
	private JTabbedPane tabbedpane;
	private JPanel basic;
	private int[] mysize = new int[2];
	private JPanel mainpanel;
	
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }   
	
	
    public JReportConfig(JAbstractReport reportconfig) {
    	super(new DummyModel());
    	this.reportconfig = reportconfig;
    	this.tabbedpane = getJTabbedPane();
    	Report.myconfig = this;    	
    	mysize[0] = 800;
    	mysize[1] = 600;
 
        setTitle("Auswertung Konfigurieren");
 
        initialize();
 
        setSize(new Dimension(mysize[0], mysize[1]));        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }	

    private void initialize() {
    	this.basic = new JPanel();
        this.basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        this.mainpanel = new JPanel(new BorderLayout());
        mainpanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        mainpanel.add(this.tabbedpane);
        mainpanel.setPreferredSize(new Dimension(mysize[0],mysize[1]));

        
        basic.add(mainpanel);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton close = new JButton("Schließen");
        JButton apply = new JButton("Übernehmen");
        JButton show = new JButton("Anzeigen");       
        show.addActionListener(new SaveReportController(this.reportconfig.getCurrentModel(), this.reportconfig));
        

        bottom.add(close);
        bottom.add(apply);
        bottom.add(show);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        pack();
    }
    
	public void setReportConfig(JAbstractReport reportconfig) {
		this.mysize[1] = this.mainpanel.getBounds().height;
		this.mysize[0] = this.mainpanel.getBounds().width;
		
		remove(this.basic);		
		this.reportconfig = reportconfig;
		this.tabbedpane = this.getJTabbedPane();
		Report.myconfig = this;
		initialize();	
		pack();	
		
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
