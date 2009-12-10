package org.knipsX.view.reportmanagement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;


public class JConcreteSinglePanel1 extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit toolkit;

    public JConcreteSinglePanel1(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	

        setSize(300, 200);

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);


        setLayout(null);

        JButton beep = new JButton("Beep");
        beep.setBounds(150, 60, 80, 30);
        beep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                toolkit.beep();
            }
        });

       JButton close = new JButton("Close");
       close.setBounds(50, 60, 80, 30);
       close.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });

        add(beep);
        add(close);

    }

}
