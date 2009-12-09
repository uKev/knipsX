package org.knipsX.view.projectmanagement;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.*;

import org.knipsX.controller.projectmanagement.CopyProject;
import org.knipsX.controller.projectmanagement.CreateProject;
import org.knipsX.controller.projectmanagement.DeleteProject;
import org.knipsX.controller.projectmanagement.OpenProject;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectmanagement.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;

import org.knipsX.view.AbstractViewPanel;

public class ProjectAdministration extends AbstractViewPanel implements Observer {
	
	private static final long serialVersionUID = 1L;
	private JButton copyProject;
	private JButton createProject;
	private JButton deleteProject;
	private JButton openProject;	
	private JList projectList;
	
	public ProjectAdministration(AbstractModel model) {
		
		model.addObserver(this);
		
		setTitle("Projekt Ansicht");
		
		JPanel administrationView = new JPanel();
		
		ProjectListModel plm = (ProjectListModel)model;
			
		projectList = new JList(plm.getProjectlist().toArray());
		projectList.setCellRenderer(new ComplexCellRenderer());
		
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
		projectList.setListData(((ProjectListModel) o).getProjectlist().toArray());
		repaint();			
	}
	
	public int[] getListPosis(){
		return projectList.getSelectedIndices();		
	}
}

class ComplexCellRenderer implements ListCellRenderer {
	
	  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	 
	  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    String theText = null;
	 
	    JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	 
	    if (value instanceof ProjectEntry) {
	      ProjectEntry projectEntry = (ProjectEntry) value;
	      theText = projectEntry.getName() + " " + projectEntry.getDate();
	    } else {
	     
	    }
	    renderer.setText(theText);
	    return renderer;
	  }
	}

