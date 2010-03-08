package org.knipsX.view.projectmanagement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;

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
import org.knipsX.view.projectview.Messages;

/**
 * Represents the management view for all user projects.
 * 
 * Sets all GUI Elements which are described in the Functional specification.
 * 
 * @param <M>
 *            the Model which is connected with this view.
 */
public class JProjectManagement<M extends ProjectManagementModel> extends JAbstractView<M> {

    private static final long serialVersionUID = 2746903025575471227L;

    private JPanel jContentPane = null;
    private JPanel jButtonPane = null;

    private JButton jButtonCopyProject = null;
    private JButton jButtonCreateProject = null;
    private JButton jButtonDeleteProject = null;
    private JButton jButtonOpenProject = null;

    private JList jListProject = null;

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Create a new view which is connected to a appropriate model.
     * 
     * @param model
     *            the model which will be connected.
     */
    public JProjectManagement(final M model) {
        super(model);

        this.initialize();
    }

    private void initialize() {

        this.setTitle(Messages.getString("JProjectManagement.0"));

        /* show main panel */
        this.setContentPane(this.getJContentPane());

        this.setMinimumSize(new Dimension(700, 75));

        this.setPreferredSize(new Dimension(700, 300));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* change size to preferred size */
        this.pack();

        /* set location to the center of the screen */
        this.setLocationRelativeTo(null);

        /* show view */
        this.setVisible(true);
    }

    /*
     * ################################################################################################################
     * THE BUTTONS
     * ################################################################################################################
     */

    /* creates a new button with some functionality */
    private JButton getNewButton(final String text, final String toolTip, final String icon,
            final ActionListener listener) {
        final JButton newButton = new JButton(text);

        try {
            newButton.setIcon(Resource.createImageIcon(icon, "SanSerif", "16"));
        } catch (final FileNotFoundException e) {
            this.logger.error("Icon for button not found - " + icon);
        }
        newButton.addActionListener(listener);

        return newButton;
    }

    private JButton getJButtonCopyProject() {

        /* create if not set */
        if (this.jButtonCopyProject == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectCopyController<M, JProjectManagement<M>>(this.model, this);

            this.jButtonCopyProject = this.getNewButton(Messages.getString("JProjectManagement.1"), "",
                    "actions/edit-copy.png", listener);
        }
        return this.jButtonCopyProject;
    }

    private JButton getJButtonCreateProject() {

        /* create if not set */
        if (this.jButtonCreateProject == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectCreateController<M, JProjectManagement<M>>(this.model, this);

            this.jButtonCreateProject = this.getNewButton(Messages.getString("JProjectManagement.2"), "",
                    "actions/document-new.png", listener);
        }
        return this.jButtonCreateProject;
    }

    private JButton getJButtonDeleteProject() {

        /* create if not set */
        if (this.jButtonDeleteProject == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectDeleteController<M, JProjectManagement<M>>(this.model, this);

            this.jButtonDeleteProject = this.getNewButton(Messages.getString("JProjectManagement.3"), "",
                    "actions/edit-delete.png", listener);
        }
        return this.jButtonDeleteProject;
    }

    private JButton getJButtonOpenProject() {

        /* create if not set */
        if (this.jButtonOpenProject == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectOpenController<M, JProjectManagement<M>>(this.model, this);

            this.jButtonOpenProject = this.getNewButton(Messages.getString("JProjectManagement.4"), "",
                    "actions/document-open.png", listener);
        }
        return this.jButtonOpenProject;
    }

    /*
     * ################################################################################################################
     * THE MAIN PANE
     * ################################################################################################################
     */

    private JPanel getJContentPane() {

        /* create if not set */
        if (this.jContentPane == null) {

            this.jContentPane = new JPanel();

            this.jContentPane.setLayout(new BoxLayout(this.jContentPane, BoxLayout.PAGE_AXIS));

            this.jContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            /* adds the first pane */
            this.jContentPane.add(this.getJButtonPane(), BorderLayout.PAGE_START);

            /* adds the second pane */
            this.jContentPane.add(this.getJProjectList(), BorderLayout.LINE_START);

        }
        return this.jContentPane;
    }

    /*
     * ################################################################################################################
     * THE FIRST PANE (PROJECT BUTTONS)
     * ################################################################################################################
     */

    private JPanel getJButtonPane() {

        /* create if not set */
        if (this.jButtonPane == null) {

            this.jButtonPane = new JPanel();

            this.jButtonPane.setLayout(new BoxLayout(this.jButtonPane, BoxLayout.LINE_AXIS));

            this.jButtonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

            this.jButtonPane.add(this.getJButtonCreateProject());
            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            this.jButtonPane.add(this.getJButtonOpenProject());
            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            this.jButtonPane.add(this.getJButtonDeleteProject());
            this.jButtonPane.add(Box.createRigidArea(new Dimension(10, 0)));
            this.jButtonPane.add(this.getJButtonCopyProject());

        }
        return this.jButtonPane;
    }

    /*
     * ################################################################################################################
     * THE SECOND PANE (PROJECTLIST)
     * ################################################################################################################
     */

    private JScrollPane getJProjectList() {

        /* create if not set */
        if (this.jListProject == null) {

            this.jListProject = new JList(this.model.getProjects().toArray());

            this.jListProject.setCellRenderer(new CustomListCellRenderer());

            this.jListProject
                    .addMouseListener(new ProjectClickOnController<M, JProjectManagement<M>>(this.model, this));
        }
        final JScrollPane listScroller = new JScrollPane(this.jListProject);

        return listScroller;
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
     * @param observedModel
     *            this is a reference to the model which a view observe.
     * @param arg
     *            this is a reference of an argument passed to the model
     */
    @Override
    public void update(final Observable observedModel, final Object arg) {

        final ProjectManagementModel model = (ProjectManagementModel) observedModel;

        this.jListProject.setListData(model.getProjects().toArray());

        /* refresh view */
        this.repaint();

        /* if not active, destroy the whole JFrame */
        if (this.model.getStatus() != ProjectManagementModel.ACTIVE) {
            this.dispose();
        }
    }
}

/**
 * This nested class renders a project cell for the project list.
 */
class CustomListCellRenderer implements ListCellRenderer {

    /* defines the default renderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    /**
     * Renders the cell.
     * 
     * @param list
     *            The JList we're painting.
     * @param value
     *            The value returned by list.getModel().getElementAt(index).
     * @param index
     *            The cells index.
     * @param isSelected
     *            True if the specified cell was selected.
     * @param cellHasFocus
     *            True if the specified cell has the focus.
     * 
     * @return the representation of the cell.
     */
    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
            final boolean isSelected, final boolean cellHasFocus) {

        /* the texts for the cell */
        String theText = null;
        String toolTipText = null;

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        /* if the selected item is a "ProjectEntry" -> set the name and the description */
        if (value instanceof ProjectModel) {
            final ProjectModel projectModel = (ProjectModel) value;
            theText = projectModel.getName() + " " + projectModel.calendarToString();
            toolTipText = projectModel.getDescription();
        }
        renderer.setText(theText);
        renderer.setToolTipText(toolTipText);

        return renderer;
    }
}