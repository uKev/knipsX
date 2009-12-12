package org.knipsX.view.reportmanagement;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class JDiagramType extends JAbstractSinglePanel {


	private JTextField reportname;
	private static final long serialVersionUID = 1L;
	private JList meineliste;

    public JDiagramType(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		
		if(this.title == null || this.title == "") {
			this.title = "Diagrammtyp";
		}

        setSize(300, 200);



        setLayout(null);
        
        Object[] myreports = new Object[Report.values().length];
        for(int i=0; i < Report.values().length; i++ ) {
			myreports[i] = Report.values()[i];
		}
        
        // Report.currentModel.getReportName()
        this.reportname = new JTextField(); 
        this.reportname.setBounds(0, 0, 250, 25);
        add(this.reportname);
       
        
       
        
        this.meineliste = new JList(myreports); //data has type Object[]
        this.meineliste .addListSelectionListener(new SharedListSelectionHandler(this.meineliste));
        this.meineliste .setCellRenderer(new ComplexCellRenderer());
        this.meineliste .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.meineliste .setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.meineliste .setPreferredSize(new Dimension(100, 100));
        this.meineliste .setBounds(0, 60, 250, 250);
        
        add(this.meineliste);
        
       
        
    }
    
    
    
    
    public String getReportName() {
    	return this.reportname.getText();
    }
    
    
    public void write() {
    	Report.currentModel.setReportName(this.getReportName());    	
    }

}


class ComplexCellRenderer implements ListCellRenderer {

    /* Definiere Standardrenderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
	    final boolean isSelected, final boolean cellHasFocus) {
	String theText = null;

	/* Generiert einen Renderer */
	final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
		isSelected, cellHasFocus);

	/* Wenn ein Projekt vorliegt, setze Text */
	if (value instanceof Report) {
	    final Report myReport = (Report) value;
	    theText = myReport.toString();
	}
	renderer.setText(theText);

	/* Gib Renderer zurück */
	return renderer;
    }
}

class SharedListSelectionHandler implements ListSelectionListener {
	
	private JList meineliste;
	
	public SharedListSelectionHandler(JList meineliste) {
		this.meineliste = meineliste;
	}
	
    public void valueChanged(ListSelectionEvent e) {
    	 
    	 System.out.println(this.meineliste.getSelectedIndex());   	 
    	 
    	 Report.setReport(Report.values()[this.meineliste.getSelectedIndex()]);
    	 
    	 System.out.println(Report.currentReport.toString());
    	 
    	 
    }
}

