package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.knipsX.controller.reportmanagement.SaveReportController;
import org.knipsX.model.reportmanagement.*;

public class JReportConfig extends JAbstractReportType {

	private static final long serialVersionUID = 1L;
	private JAbstractReport reportconfig;
	private JTabbedPane tabbedpane;
	private JPanel basic;
	private int[] mysize = new int[2];
	private JPanel mainpanel;
	
	/**
	 * Starts the report configuration utilty on a specified 
	 * reportconfiguraton
	 * @param reportconfig the report configuration to operate on
	 */
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


    public JReportConfig(AbstractReportModel model) {
    	super(new DummyModel());
    	
    	if(model instanceof BoxplotModel) {
        	this.reportconfig = new JBoxplotConfig((BoxplotModel)model);
    	} else if(model instanceof Cluster3DModel) {
    		this.reportconfig = new JCluster3DConfig((Cluster3DModel)model);
    	}  else if(model instanceof Histogram2DModel) {
    		this.reportconfig = new JHistogram2DConfig((Histogram2DModel)model);
    	} else if(model instanceof Histogram3DModel) {
    		this.reportconfig = new JHistogram3DConfig((Histogram3DModel)model);
    	} else if(model instanceof TableModel) {
    		this.reportconfig = new JTableConfig((TableModel)model);
    	} else if(model instanceof Cluster3DModel) {
    		this.reportconfig = new JCluster3DConfig((Cluster3DModel)model);
    	}  	


 
        initialize();
 

    }	
    
  
    public JReportConfig(BoxplotModel model) {
    	super(model);
    	this.reportconfig = new JBoxplotConfig(model);
    	initialize();
    }
    
    public JReportConfig(Cluster3DModel model) {
    	super(model);
    	this.reportconfig = new JCluster3DConfig(model);
    	initialize();
    }
    
    public JReportConfig(Histogram3DModel model) {
    	super(model);
    	this.reportconfig = new JHistogram3DConfig(model);
    	initialize();
    }
  
    public JReportConfig(Histogram2DModel model) {
    	super(model);
    	this.reportconfig = new JHistogram2DConfig(model);
    	initialize(); 	
    	
    }
    
    public JReportConfig(TableModel model) {
    	super(model);
    	this.reportconfig = new JTableConfig(model);
    	initialize();
    }
    
    
    private void initialize() {
    	preinitialize();
    	initializePane();
    	postinitialize();
    }
    
    private void preinitialize() {	
		
    	this.tabbedpane = getJTabbedPane();
    	Report.myconfig = this;  
    	mysize[0] = 800;
    	mysize[1] = 600; 
    	
    }
    
	private void initializePane() {

        setTitle("Auswertung Konfigurieren");
		
		
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
        show.addActionListener(new SaveReportController(Report.currentModel, this.reportconfig, true));
        

        bottom.add(close);
        bottom.add(apply);
        bottom.add(show);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));
        pack();      

        
    }
	
	
	private void postinitialize() {        
        setSize(new Dimension(mysize[0], mysize[1]));        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
    
	public void setReportType(JAbstractReport reportconfig) {
		this.mysize[1] = this.mainpanel.getBounds().height;
		this.mysize[0] = this.mainpanel.getBounds().width;
		
		remove(this.basic);		
		this.reportconfig = reportconfig;
		this.tabbedpane = this.getJTabbedPane();
		Report.myconfig = this;
		initializePane();	
		pack();	
		
	}	
	
    private JTabbedPane getJTabbedPane() {
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	for(JAbstractSinglePanel item : this.reportconfig.getregisteredPanels()) {
    		tabbedpane.addTab(item.getTitle(), item.getIcon(), item, item.getTip());
    	}
    	
    	return tabbedpane;
    }


	@Override
	public void update(Observable model, Object argument) {
		// Do nothing		
	} 
	
	
}
