package org.knipsX.view.projectmanagement;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.knipsX.controller.projectmanagement.Cancel;
import org.knipsX.controller.projectmanagement.Ok;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectList;
import org.knipsX.view.AbstractViewPanel;

public class CreateNewProject extends AbstractViewPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField projectName;
	private JButton oK;
	private JButton cancel;
	
	public CreateNewProject(AbstractModel model, AbstractViewPanel view) {
		
		setTitle("Neues Projekt");
		
		JPanel panel = new JPanel();
		
		
		projectName = new JTextField(20);
		
		oK = new JButton("OK");
        oK.addActionListener(new Ok (this, view, (ProjectList) model));
        
        cancel = new JButton("Abbrechen");
        cancel.addActionListener(new Cancel(this));
               
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
		// TODO Auto-generated method stub		
	}
	
	public String gettextfield() {
		 return projectName.getText();	
	}
}
