package org.knipsX.view.reportmanagement;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import org.knipsX.controller.reportmanagement.DiagramTypeSelectController;


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
	private JTextArea reportdescription;
	private static final long serialVersionUID = 1L;
	private JList diagramType;
	
	/**
	 * Constructor which initialized this diagram selection panel
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

        BoxLayout container = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(container);   
        
        // Initialize the left panel  	
        JPanel leftpanel = new JPanel();        
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.PAGE_AXIS));  
        
        JLabel reportnamelabel = new JLabel("Auswertungsname");
        reportnamelabel.setAlignmentX(LEFT_ALIGNMENT);
        leftpanel.add(reportnamelabel);
        
        //Add the report name text field
        this.reportname = new JTextField();        
        this.reportname.setAlignmentX(LEFT_ALIGNMENT);
        this.reportname.setPreferredSize(new Dimension(20,20));
        this.reportname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        
        leftpanel.add(this.reportname);
        leftpanel.add(Box.createRigidArea(new Dimension(0,20)));
        
        JLabel reportdescriptionlabel = new JLabel("Auswertungsbeschreibung");
        reportdescriptionlabel.setAlignmentX(LEFT_ALIGNMENT);
        leftpanel.add(reportdescriptionlabel);
        
        //Add the report description text field
        this.reportdescription = new JTextArea(); 
        this.reportdescription.setAlignmentX(LEFT_ALIGNMENT);
        this.reportdescription.setColumns(20);        
        this.reportdescription.setLineWrap(true);
        this.reportdescription.setRows(10);
        this.reportdescription.setWrapStyleWord(true);
        JScrollPane scrollpane = new JScrollPane(this.reportdescription); 
        scrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE,450));
        scrollpane.setAlignmentX(LEFT_ALIGNMENT);
        leftpanel.add(scrollpane);       
        leftpanel.add(Box.createRigidArea(new Dimension(0,20)));
        
        JLabel diagramtypelabel = new JLabel("Diagramtyp");
        diagramtypelabel.setAlignmentX(LEFT_ALIGNMENT);
        
        leftpanel.add(diagramtypelabel);           
        
        
        int indexToSelect = 0;
        
        Object[] myreports = new Object[ReportHelper.values().length];
        for(int i=0; i < ReportHelper.values().length; i++ ) {
			myreports[i] = ReportHelper.values()[i];
			
			if(myreports[i] == ReportHelper.currentReport) {
				indexToSelect = i;
			}
		}
        
        JPanel diagramTypePanel = new JPanel();
        diagramTypePanel.setAlignmentX(LEFT_ALIGNMENT);
        /*
         * put the JList inside a separate JPanel which uses a grid layout
         * to maximize the list inside the diagramTypePanel
         */
        diagramTypePanel.setLayout(new GridLayout(1,1));
        
        this.diagramType = new JList(myreports);
        this.diagramType.setSelectedIndex(indexToSelect);
        this.diagramType.addListSelectionListener(new DiagramTypeSelectController(this));
        this.diagramType.setCellRenderer(new ComplexCellRenderer());
        this.diagramType.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        this.diagramType.setLayoutOrientation(JList.HORIZONTAL_WRAP);        
        this.diagramType.setAlignmentX(LEFT_ALIGNMENT);
        
        
        diagramTypePanel.add(this.diagramType);
        leftpanel.add(diagramTypePanel);
        leftpanel.add(Box.createVerticalGlue());        
        
        
        // Initialize the right panel       
        JPanel rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS)); 
        
        Component tempcomponent = ReportHelper.currentReport.getDiagram();  
        tempcomponent.setPreferredSize(new Dimension(300,150));
        tempcomponent.setMaximumSize(new Dimension(800,800));
        rightpanel.add(tempcomponent);
        rightpanel.add(Box.createRigidArea(new Dimension(0,20)));
        
        JTextArea mytextarea = new JTextArea();
        mytextarea.setColumns(20);
        mytextarea.setRows(5);
        mytextarea.setWrapStyleWord(true);
        mytextarea.setLineWrap(true);
        rightpanel.add(mytextarea);
        JScrollPane rightscrollpane = new JScrollPane(mytextarea); 
        rightscrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE,450));
        rightscrollpane.setAlignmentX(CENTER_ALIGNMENT);
        rightpanel.add(rightscrollpane);      
        
        
        // Add them to the main layout
        add(leftpanel);
        add(Box.createRigidArea(new Dimension(25,20)));
        add(Box.createHorizontalGlue());
        add(rightpanel);
       
        
    }
    
    
    
    /**
     * Returns the currently selected index in the diagram typ
     * selection list
     * 
     * @return the current selection index
     */
    public int getSelectedDiagramType() {
    	return this.diagramType.getSelectedIndex();
    }
    
    
    public String getReportName() {
    	return this.reportname.getText();
    }
    
    public String getReportDescription() {
    	return this.reportdescription.getText();
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

