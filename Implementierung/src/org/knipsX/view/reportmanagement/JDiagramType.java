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


/**
 * This class represents the panel where the user can choose the diagram type 
 * and assign the report name and report description. 
 * Note that is panel is present in every report compilation.
 * 
 * @author David Kaufman
 *
 */
public class JDiagramType extends JAbstractSinglePanel {


	private JTextField reportname;
	private static final long serialVersionUID = 1L;
	private JList meineliste;
	
	/**
	 * Constructor which initialized this diagramm selection panel
	 * @param titel The title which is registered with this panel.
	 * @param icon The icon which is registered with this panel.
	 * @param tip The tooltip which is registered with this panel.
	 */
    public JDiagramType(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		
		if(this.title == null || this.title == "") {
			this.title = "Diagrammtyp";
		}

        setSize(300, 200);



        setLayout(null);
        
        int indexToSelect = 0;
        
        Object[] myreports = new Object[ReportHelper.values().length];
        for(int i=0; i < ReportHelper.values().length; i++ ) {
			myreports[i] = ReportHelper.values()[i];
			
			if(myreports[i] == ReportHelper.currentReport) {
				indexToSelect = i;
			}
		}
        
        // Report.currentModel.getReportName()
        this.reportname = new JTextField(); 
        this.reportname.setBounds(0, 0, 250, 25);
        add(this.reportname);
              
        
        this.meineliste = new JList(myreports); //data has type Object[]
        this.meineliste.setSelectedIndex(indexToSelect);
        this.meineliste .addListSelectionListener(new SharedListSelectionHandler(this.meineliste));
        this.meineliste .setCellRenderer(new ComplexCellRenderer());
        this.meineliste .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        this.meineliste .setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.meineliste .setPreferredSize(new Dimension(100, 100));
        this.meineliste .setBounds(0, 60, 250, 250);
        
        add(this.meineliste);    
       
        
    }
    
    
    
    
    public String getReportName() {
    	return this.reportname.getText();
    }
    
    public String getReportDescription() {
    	//TODO
    	return "";
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

	/* Wenn ein Diagram vorliegt, setze Text */
	if (value instanceof ReportHelper) {
	    final ReportHelper myReport = (ReportHelper) value;
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
    	 
    	 ReportHelper.updateReport(ReportHelper.values()[this.meineliste.getSelectedIndex()]);
    	 
    	 System.out.println(ReportHelper.currentReport.toString());
    	 
    	 
    }
}

