package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import org.knipsX.controller.AbstractController;
import org.knipsX.controller.worker.InitializePictureDataWorker;
import org.knipsX.controller.worker.InitializePictureThumbnailWorker;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectmanagement.JProjectManagement;
import org.knipsX.view.projectview.JProjectView;

/****************************************************************************************
 * Represents the Actions which are done by klicking on the picturesetcontentlist.
 * Acts in harmony with a JProjectview.
 * 
 * @param <M>
 *            The related model
 * @param <V>
 *            The related view
 ***************************************************************************************/
public class ProjectClickOnController<M extends ProjectManagementModel, V extends JProjectManagement<?>> extends
        AbstractController<M, V> implements MouseListener {

    /* The left mouse button */
    private static final int MOUSE_LEFT = 1;

    /**
     * Constructor for ProjectClickOnController
     * 
     * @param model
     *            The related model
     * @param view
     *            The related view
     */
    public ProjectClickOnController(final M model, final V view) {
        super(model, view);
    }

    /**
     * @see org.knipsX.controller.AbstractController#actionPerformed(java.awt.event.ActionEvent)
     *      This Action will stay empty because this is a mouseevent
     * @param e
     *            The action event
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        /* Not implemented */
    }

    /**
     * If the left mouse button is pressed twice on a proeject the project will open.
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     * @param mouseEvent
     *            The mouse event
     */
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == ProjectClickOnController.MOUSE_LEFT) {
            final JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                final int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    final Object o = theList.getModel().getElementAt(index);
                    final ProjectModel projectModel = (ProjectModel) o;
                    projectModel.loadData();
                    
                    new JProjectView<ProjectModel>(projectModel);

                    this.model.setStatus(ProjectManagementModel.INACTIVE);
                }
            }
        }
    }

    /**
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     * @param e
     *            The mouse event
     */
    public void mouseEntered(final MouseEvent e) {
        /* Not implemented */
    }

    /**
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     * @param e
     *            The mouse event
     */
    public void mouseExited(final MouseEvent e) {
        /* Not implemented */
    }

    /**
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     * @param e
     *            The mouse event
     */
    public void mousePressed(final MouseEvent e) {
        /* Not implemented */
    }

    /**
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     * @param e
     *            The mouse event
     */
    public void mouseReleased(final MouseEvent e) {
        /* Not implemented */
    }
}
