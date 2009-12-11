package org.knipsX.view.reportmanagement;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.knipsX.model.AbstractModel;


public class JDiagramType extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit toolkit;

    public JDiagramType(String titel, Icon icon, String tip, AbstractModel model) {
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
        
        JList list = new JList(myreports); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setPreferredSize(new Dimension(100, 100));
        list.setBounds(0, 0, 250, 250);
        
        add(list);

    }

}
