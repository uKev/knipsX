package org.knipsX.view.reportmanagement;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;

import org.knipsX.model.reportmanagement.Axis;

/**
 * This class represents the panel where the user is able to assign a 
 * parameter with an optional description to each available axis. 
 * 
 * @author David Kaufman
 *
 */
public class JParameters extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which initialized this parameter panel
	 * @param titel The title which is registered with this panel.
	 * @param icon The icon which is registered with this panel.
	 * @param tip The tooltip which is registered with this panel.
	 */
    public JParameters(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		if(this.title == null || this.title == "") {
			this.title = "Parameter";
		}

        setSize(300, 200);
        
        setLayout(null);

        JButton beep = new JButton( String.valueOf("test"));
        beep.setBounds(150, 60, 80, 30);

       JButton close = new JButton("Close");
       close.setBounds(50, 60, 80, 30);

        add(beep);
        add(close);

    }
    
    /**
     * Returns the Exif-parameters and axes desription specified
     * @return the Exif-parameters and axes desription 
     */
    public ArrayList<Axis> getAxes() {
    	//TODO
		return null;    	
    }

}
