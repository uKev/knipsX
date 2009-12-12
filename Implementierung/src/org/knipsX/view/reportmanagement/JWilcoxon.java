package org.knipsX.view.reportmanagement;
import javax.swing.Icon;
import javax.swing.JButton;

import org.knipsX.model.reportmanagement.WilcoxonTestType;


public class JWilcoxon extends JAbstractSinglePanel {

	private static final long serialVersionUID = 1L;

    public JWilcoxon(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		if(this.title == null || this.title == "") {
			this.title = "Wilcoxon Test";
		}
		
        setSize(300, 200);

        setLayout(null);

        JButton beep = new JButton("Beep");


       JButton close = new JButton("Close");
       close.setBounds(50, 60, 80, 30);

        add(beep);
        add(close);

    }
    
    public boolean getStatus() {
    	//TODO
    	return true;
    }
    
    public WilcoxonTestType getTestType() {
    	//TODO
    	return WilcoxonTestType.LEFT;
    }
    
    public float getStatisticatSignificance() {
    	//TODO
    	return 1;
    }

}
