package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;


public class JDiagramType extends JAbstractSinglePanel {


	private JTextField reportname; 
	private static final long serialVersionUID = 1L;

    public JDiagramType(String titel, Icon icon, String tip, AbstractReportModel model) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
		this.model = model;
    	
		
		if(this.title == null || this.title == "") {
			this.title = "Diagrammtyp";
		}

        setSize(300, 200);



        setLayout(null);
        
        Object[] myreports = new Object[Report.values().length];
        for(int i=0; i < Report.values().length; i++ ) {
			myreports[i] = Report.values()[i];
		}
        
        this.reportname = new JTextField("bla"); 
        this.reportname.setBounds(0, 0, 250, 25);
        add(this.reportname);
        
        JList list = new JList(myreports); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setPreferredSize(new Dimension(100, 100));
        list.setBounds(0, 30, 250, 250);
        
        add(list);

    }
    
    
    public String getReportName() {
    	return this.reportname.getText();
    }
    
    
    public void write() {
    	this.model.setReportName(this.getReportName());
    	
    }
    

}
