package org.knipsX.view.projectmanagement;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knipsX.controller.projectmanagement.CopyCancel;
import org.knipsX.controller.projectmanagement.CopyOk;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JProjectCopy extends JAbstractView {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	
	private JTextField jTextFieldProjectName;
	
	private JButton jButtonConfirm;
	private JButton jButtonCancel;
	
	private ProjectEntry toCopy;
	
	public JProjectCopy(AbstractModel model, ProjectEntry toCopy) {
		
		/* Rufe Konstruktor der Elternklasse auf */
		super(model);
		
		/* Der Projekteintrag, der kopiert werden soll */
		this.toCopy = toCopy;
		
		/* Rendere Komponenten */
		this.initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		/* Fenstertitel setzen */
		this.setTitle("Projekt kopieren");
		
		/* */
		this.setLocationRelativeTo(null);
		
		/* Inhaltspanel setzen */
		this.setContentPane(getJContentPane());
		
		/* Optimale Größe der Komponenten einstellen */
		this.pack();
		
		/* Sichtbar machen */
		this.setVisible(true);		
	}
	
	private JPanel getJContentPane() {
		if (this.jContentPane == null) {
			this.jContentPane = new JPanel();
			
	        jContentPane.add(this.getJTextFieldProjectName());
	        jContentPane.add(this.getJButtonConfirm());
	        jContentPane.add(this.jButtonCancel());
		}
		return this.jContentPane;
	}
	
	private JTextField getJTextFieldProjectName() {
		if (this.jTextFieldProjectName == null) {
			this.jTextFieldProjectName = new JTextField(20);
		}
		return this.jTextFieldProjectName;
	}
	
	private JButton getJButtonConfirm() {
		if (this.jButtonConfirm == null) {
			this.jButtonConfirm = new JButton("Ok");
			
			this.jButtonConfirm.addActionListener(new CopyOk(this.model, this));
		}
		return this.jButtonConfirm;
	}
	
	private JButton jButtonCancel() {
		if (this.jButtonCancel == null) {
			this.jButtonCancel = new JButton("Abbrechen");
			
			this.jButtonCancel.addActionListener(new CopyCancel(this.model));
		}
		return this.jButtonCancel;
	}

	@Override
	public void update(Observable o, Object arg) {
		//Methode muss das unsichtbare Panel aktualisieren wenn zb falsche eingaben da sind für einen namen.
		ProjectListModel model = (ProjectListModel) o;
		if (model.getModelStatus() == ProjectListModel.COPY){			
			this.setAlwaysOnTop(true);
		} else {
			this.dispose();
		}
	}
	
	public String getProjectName() {
		 return jTextFieldProjectName.getText();	
	}
	
	public ProjectEntry getProjectToCopy() {
		return this.toCopy;
	}
}
