package org.knipsX.view.projectmanagement;

import java.awt.Component;
import java.util.Observable;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import org.knipsX.controller.projectmanagement.CopyProject;
import org.knipsX.controller.projectmanagement.CreateProject;
import org.knipsX.controller.projectmanagement.DeleteProject;
import org.knipsX.controller.projectmanagement.OpenProject;
import org.knipsX.model.common.ProjectEntry;
import org.knipsX.model.projectmanagement.ProjectListModel;
import org.knipsX.view.JAbstractView;

public class JProjectAdministration extends JAbstractView {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButtonCopyProject = null;
	private JButton jButtonCreateProject = null;
	private JButton jButtonDeleteProject = null;
	private JButton jButtonOpenProject = null;

	private JList projectList = null;
	
	private JScrollPane jScrollPaneProjectList = null;

	public JProjectAdministration(ProjectListModel model) {
		super(model);
		initialize();
		setContentPane(getJContentPane());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Projekt Ansicht");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
		}
		jContentPane.add(getjButtonCreateProject());
		jContentPane.add(getjButtonOpenProject());
		jContentPane.add(getjButtonDeleteProject());
		jContentPane.add(getjButtonCopyProject());
		jContentPane.add(getJScrollPaneProjectList());

		return jContentPane;
	}

	/**
	 * This method initializes jButtonCopyProject
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonCopyProject() {
		if (jButtonCopyProject == null) {
			jButtonCopyProject = new JButton();
			jButtonCopyProject.setText("Projekt kopieren");
			jButtonCopyProject.addActionListener(new CopyProject(this, model));
		}
		return jButtonCopyProject;
	}

	/**
	 * This method initializes jButtonCreateProject
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonCreateProject() {
		if (jButtonCreateProject == null) {
			jButtonCreateProject = new JButton();
			jButtonCreateProject.setText("Projekt erstellen");
			jButtonCreateProject.addActionListener(new CreateProject(model));
		}
		return jButtonCreateProject;
	}

	/**
	 * This method initializes jButtonDeleteProject
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonDeleteProject() {
		if (jButtonDeleteProject == null) {
			jButtonDeleteProject = new JButton();
			jButtonDeleteProject.setText("Projekt löschen");
			jButtonDeleteProject.addActionListener(new DeleteProject(model, this));
		}
		return jButtonDeleteProject;
	}

	/**
	 * This method initializes jButtonOpenProject
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getjButtonOpenProject() {
		if (jButtonOpenProject == null) {
			jButtonOpenProject = new JButton();
			jButtonOpenProject.setText("Projekt öffnen");
			jButtonOpenProject.addActionListener(new OpenProject(model, this));
		}
		return jButtonOpenProject;
	}
	
	/**
	 * This method initializes jScrollPaneProjectList
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneProjectList() {
		if (jScrollPaneProjectList == null) {
			jScrollPaneProjectList = new JScrollPane();
			jScrollPaneProjectList.setViewportView(getjListProject());
		}
		return jScrollPaneProjectList;
	}

	private JList getjListProject() {
		if (projectList == null) {
			projectList = new JList(((ProjectListModel) model).getProjectlist().toArray());
			projectList.setCellRenderer(new ComplexCellRenderer());
		}
		return projectList;

	}

	@Override
	public void update(Observable o, Object arg) {
		ProjectListModel model = (ProjectListModel) o;
		projectList.setListData(model.getProjectlist().toArray());
		repaint();
		if (model.getModelStatus() == ProjectListModel.SELECT){
			this.setEnabled(true);
			this.setVisible(true);
		} else if (model.getModelStatus() == ProjectListModel.OPEN) {
			this.dispose();
		} else {
			this.setEnabled(false);
		}
	}

	public int[] getListPosis() {
		return projectList.getSelectedIndices();
	}

}

class ComplexCellRenderer implements ListCellRenderer {

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String theText = null;

		JLabel renderer = (JLabel) defaultRenderer
				.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

		if (value instanceof ProjectEntry) {
			ProjectEntry projectEntry = (ProjectEntry) value;
			theText = projectEntry.getName() + " "
					+ projectEntry.getCreationDate();
		} else {

		}
		renderer.setText(theText);
		return renderer;
	}
}
