package org.knipsX.view.projectmanagement;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.knipsX.controller.projectmanagement.CreateCancel;
import org.knipsX.controller.projectmanagement.CreateOk;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JProjectNew extends JAbstractView implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField projectName;
	private JButton oK;
	private JButton cancel;
	
	public JProjectNew(AbstractModel model, JAbstractView view) {
		
		setTitle("Neues Projekt");
		
		JPanel panel = new JPanel();
			
		projectName = new JTextField(20);
		
		oK = new JButton("OK");
        oK.addActionListener(new CreateOk (this, view, (ProjectListModel) model));
        
        cancel = new JButton("Abbrechen");
        cancel.addActionListener(new CreateCancel(this, view));
               
        panel.add(projectName);
        panel.add(oK);
        panel.add(cancel);
        this.add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		//Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind f�r einen namen.		
	}
	
	public String gettextfield() {
		 return projectName.getText();	
	}
}
