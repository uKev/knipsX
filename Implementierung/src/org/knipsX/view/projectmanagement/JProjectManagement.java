/*******************************************************************************
 * This package is the root of all files regarding the "project management".
 */
package org.knipsX.view.projectmanagement;

/* import classes from the java sdk */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.util.Observable;

/* import things from our program */
import org.apache.log4j.Logger;
import org.knipsX.controller.projectmanagement.ProjectClickOnController;
import org.knipsX.controller.projectmanagement.ProjectCopyController;
import org.knipsX.controller.projectmanagement.ProjectCreateController;
import org.knipsX.controller.projectmanagement.ProjectDeleteController;
import org.knipsX.controller.projectmanagement.ProjectOpenController;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.utils.Resource;
import org.knipsX.view.JAbstractView;

/**
 * Represents the management view for all user projects.
 * 
 * Sets all GUI Elements which are described in the Functional specification.
 * 
 * @param <M> the Model which is connected with this view.
 */
public class JProjectManagement<M extends ProjectManagementModel> extends JAbstractView<M> {

    private static final long serialVersionUID = 2746903025575471227L;
    
    private JPanel jContentPane = null;
    private JPanel jButtonPane = null;
    private JPanel jListPane = null;
    
    private JButton jButtonCopyProject = null;
    private JButton jButtonCreateProject = null;
    private JButton jButtonDeleteProject = null;
    private JButton jButtonOpenProject = null;
    
    private JList jListProject = null;
    
    private JScrollPane jScrollPaneProjectList = null;

    private final Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * Create a new view which is connected to a appropriate model.
     * 
     * @param model the model which will be connected.
     */
    public JProjectManagement(M model) {
        super(model);

        /* renders the view */
        this.initialize();
    }

    private void initialize() {

        /* Set titel */
        // INTERNATIONALIZE
        this.setTitle(Messages.getString("JProjectManagement.0")); //$NON-NLS-1$

        /* show main panel */
        this.setContentPane(this.getJContentPane());

        this.setMinimumSize(new Dimension(700, 75));

        this.setPreferredSize(new Dimension(700, 300));

        /* set standard close action */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* change size to preferred size */
        this.pack();

        /* set location to the center of the screen */
        this.setLocationRelativeTo(null);

        /* show view */
        this.setVisible(true);
    }

    /*
     * This method initializes jContentPane.
     * 
     * @return the panel.
     */
    private JPanel getJContentPane() {

        /* create if not set */
        if (this.jContentPane == null) {

            /* create a new panel */
            this.jContentPane = new JPanel();

            /* sets the main layout for this window */
            this.jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.PAGE_AXIS));

            /* sets borders in this window */
            this.jContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            /* sets and adds the buttonpanel with its layout */
            this.jContentPane.add(getJButtonPane(), BorderLayout.PAGE_START);

            /* sets and adds the listpanel with its layout */
            this.jContentPane.add(getJListPane(), BorderLayout.LINE_START);

        }

        /* return the panel */
        return this.jContentPane;
    }

    /*
     * This method initializes jButtonPane.
     * 
     * @return the panel.
     */
    private JPanel getJButtonPane() {

        /* create if not set */
        if (this.jButtonPane == null) {

            /* create a new panel */
            this.jButtonPane = new JPanel();

            /* sets the layout between the buttons */
            this.jButtonPane.setLayout(new BoxLayout(jButtonPane, BoxLayout.LINE_AXIS));

            /* sets an border which surronds the buttons */
            this.jButtonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            /* add the button for project creation */
            this.jButtonPane.add(this.getJButtonCreateProject());

            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));

            /* add the button for project opening */
            this.jButtonPane.add(this.getJButtonOpenProject());

            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));

            /* add the button for project deletion */
            this.jButtonPane.add(this.getJButtonDeleteProject());

            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));

            /* add the button for project copying */
            this.jButtonPane.add(this.getJButtonCopyProject());

        }
        return this.jButtonPane;
    }

    // JLabel label = new JLabel(labelText);

    /*
     * This method initializes jListPane.
     * 
     * @return the panel.
     */
    private JPanel getJListPane() {

        /* create if not set */
        if (this.jListPane == null) {

            /* create a new panel */
            this.jListPane = new JPanel();

            /* sets the layout for the jListpane */
            this.jListPane.setLayout(new BoxLayout(jListPane, BoxLayout.PAGE_AXIS));

            /* add the scrollbars for the list */
            this.jListPane.add(this.getJScrollPaneProjectList());

        }

        return this.jListPane;
    }

    /*
     * This method initializes jButtonCopyProject.
     * 
     * @return the button.
     */
    private JButton getJButtonCopyProject() {

        /* create if not set */
        if (this.jButtonCopyProject == null) {

            /* create new button */
            // INTERNATIONALIZE
            this.jButtonCopyProject = new JButton(Messages.getString("JProjectManagement.1")); //$NON-NLS-1$

            try {
                this.jButtonCopyProject.setIcon(Resource.createImageIcon("actions/edit-copy.png", "", "32"));
            } catch (FileNotFoundException e) {
                this.logger.error("Icon for copy project button not found.");
            }
            
            /* create an action listener (which knows the model and the view) to the button */
            this.jButtonCopyProject.addActionListener(new ProjectCopyController<M, JProjectManagement<M>>(this.model,
                    this));
        }

        /* return the button */
        return this.jButtonCopyProject;
    }

    /*
     * This method initializes jButtonCreateProject.
     * 
     * @return the button.
     */
    private JButton getJButtonCreateProject() {

        /* create if not set */
        if (this.jButtonCreateProject == null) {

            /* create new button */
            // INTERNATIONALIZE
            this.jButtonCreateProject = new JButton(Messages.getString("JProjectManagement.2")); //$NON-NLS-1$

            try {
                this.jButtonCreateProject.setIcon(Resource.createImageIcon("actions/document-new.png", "", "32"));
            } catch (FileNotFoundException e) {
                this.logger.error("Icon for create project button not found.");
            }
            
            /* create an action listener (which knows the model) to the button */
            this.jButtonCreateProject.addActionListener(new ProjectCreateController<M, JProjectManagement<M>>(
                    this.model, this));
        }

        /* return the button */
        return this.jButtonCreateProject;
    }

    /*
     * This method initializes jButtonDeleteProject.
     * 
     * @return the button.
     */
    private JButton getJButtonDeleteProject() {

        /* create if not set */
        if (this.jButtonDeleteProject == null) {

            /* create new button */
            // INTERNATIONALIZE
            this.jButtonDeleteProject = new JButton(Messages.getString("JProjectManagement.3")); //$NON-NLS-1$

            try {
                this.jButtonDeleteProject.setIcon(Resource.createImageIcon("actions/edit-delete.png", "", "32"));
            } catch (FileNotFoundException e) {
                this.logger.error("Icon for delete project button not found.");
            }
            
            /* create an action listener (which knows the model and the view) to the button */
            this.jButtonDeleteProject.addActionListener(new ProjectDeleteController<M, JProjectManagement<M>>(
                    this.model, this));
        }

        /* return the button */
        return this.jButtonDeleteProject;
    }

    /*
     * This method initializes jButtonOpenProject.
     * 
     * @return the button.
     */
    private JButton getJButtonOpenProject() {

        /* create if not set */
        if (this.jButtonOpenProject == null) {

            /* create new button */
            // INTERNATIONALIZE
            this.jButtonOpenProject = new JButton(Messages.getString("JProjectManagement.4")); //$NON-NLS-1$

            try {
                this.jButtonOpenProject.setIcon(Resource.createImageIcon("actions/document-open.png", "", "32"));
            } catch (FileNotFoundException e) {
                this.logger.error("Icon for open project button not found.");
            }
            
            /* create an action listener (which knows the model an the view) to the button */
            this.jButtonOpenProject.addActionListener(new ProjectOpenController<M, JProjectManagement<M>>(this.model,
                    this));
        }

        /* return the button */
        return this.jButtonOpenProject;
    }

    /*
     * This method initializes jListProject including a scrollbar.
     * 
     * @return the list.
     */
    private JList getJProjectList() {

        /* create if not set */
        if (this.jListProject == null) {

            /* create new list */
            this.jListProject = new JList(this.model.getProjects().toArray());

            /* set a custom cell renderer */
            this.jListProject.setCellRenderer(new MyProjectListCellRenderer());
            this.jListProject.addMouseListener(new ProjectClickOnController<M, JProjectManagement<M>>(this.model,
                    this));
        }

        /* return the list */
        return this.jListProject;

    }

    /*
     * This method initializes jScrollPaneProjectList.
     * 
     * @return the scrollpane.
     */
    private JScrollPane getJScrollPaneProjectList() {

        /* create if not set */
        if (this.jScrollPaneProjectList == null) {

            /* create new scrollpane */
            this.jScrollPaneProjectList = new JScrollPane();

            /* add the list to the scrollpane */
            this.jScrollPaneProjectList.setViewportView(this.getJProjectList());
        }

        /* return the scrollpane */
        return this.jScrollPaneProjectList;
    }

    /**
     * This method checks which entries are marked in the list of projects and gives them back as indices.
     * 
     * @return array of indices.
     */
    public int[] getSelectedProjects() {
        return this.jListProject.getSelectedIndices();
    }

    /**
     * This method updates the view. This happens when the user interacts with the program.
     * More precisely the list with projects is refreshed and the decision if the view is actually needed is checked by
     * status.
     * If the view is not needed it will be disposed.
     * 
     * @param observedModel this is a reference to the model which a view observe.
     * @param arg this is a reference of an argument passed to the model
     */
    @Override
    public void update(final Observable observedModel, final Object arg) {

        /* cast to model */
        final ProjectManagementModel model = (ProjectManagementModel) observedModel;

        /* set the data for the project list */
        this.jListProject.setListData(model.getProjects().toArray());

        /* refresh view */
        this.repaint();

        if (this.model.getStatus() != ProjectManagementModel.ACTIVE) {
            this.dispose();
        }
    }
}

/**
 * This nested class renders a project cell for the project list.
 */
class MyProjectListCellRenderer implements ListCellRenderer {

    /* defines the default renderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    /**
     * Renders the cell.
     * 
     * @param list The JList we're painting.
     * @param value The value returned by list.getModel().getElementAt(index).
     * @param index The cells index.
     * @param isSelected True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * 
     * @return the representation of the cell.
     */
    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
            final boolean isSelected, final boolean cellHasFocus) {

        /* the text for the cell */
        String theText = null;
        String toolTipText = null;

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        /* if the selected item is a "ProjectEntry" -> set the name and the description */
        if (value instanceof ProjectModel) {
            final ProjectModel projectModel = (ProjectModel) value;
            theText = projectModel.getName() + " " + projectModel.calendarToString(); //$NON-NLS-1$
            toolTipText = projectModel.getDescription();
        }
        renderer.setText(theText);
        renderer.setToolTipText(toolTipText);

        /* return the label */
        return renderer;
    }
}