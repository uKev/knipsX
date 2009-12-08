package org.knipsX.view.projectmanagement;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import org.knipsX.controller.projectmanagement.CopyProject;
import org.knipsX.controller.projectmanagement.CreateProject;
import org.knipsX.controller.projectmanagement.DeleteProject;
import org.knipsX.controller.projectmanagement.OpenProject;
import org.knipsX.model.projectmanagement.ProjectList;
import org.knipsX.view.AbstractViewPanel;


public class Projectadministration extends AbstractViewPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JButton copyProject;
	private JButton createProject;
	private JButton deleteProject;
	private JButton openProject;	
	private JList projectList;
	
	public Projectadministration(ProjectList model) {
		
		setTitle("Projekt Ansicht");
		
		JPanel administrationView = new JPanel();
				
		projectList = new JList(model.getProjectlist().toArray());
		projectList.setLayoutOrientation(JList.VERTICAL);
				
		copyProject = new JButton("Projekt kopieren");
		copyProject.addActionListener(new CopyProject(this,model));
        
        createProject = new JButton("Projekt erstellen");
        createProject.addActionListener(new CreateProject(this,model));
        
        deleteProject = new JButton("Projekt löschen");
        deleteProject.addActionListener(new DeleteProject(this,model));
        
        openProject = new JButton("Projekt öffnen");
        openProject.addActionListener(new OpenProject(this,model));
        
        administrationView.add(createProject);
        administrationView.add(openProject);
        administrationView.add(deleteProject);
        administrationView.add(copyProject);
        administrationView.add(projectList);
        this.add(administrationView);       
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		projectList.setListData(((ProjectList) o).getProjectlist().toArray());
		repaint();			
	}
	
	public int[] getListPosis(){
		return projectList.getSelectedIndices();		
	}
}
