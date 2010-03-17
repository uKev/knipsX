package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on the picture set list.
 * 
 * Acts in harmony with a JProjectview.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private static final int MOUSE_LEFT = 1;

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetListClickOnController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    /**
     * If something was clicked on.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     * 
     * @throws IllegalArgumentException
     *             if you connect the controller to no JList, you get this error.
     */
    public void mouseClicked(final MouseEvent mouseEvent) throws IllegalArgumentException {
        if (mouseEvent.getButton() == PictureSetListClickOnController.MOUSE_LEFT) {
            if (mouseEvent.getSource() instanceof JList) {
                final JList theList = (JList) mouseEvent.getSource();

                if (mouseEvent.getClickCount() == 1) {
                    final int index = theList.locationToIndex(mouseEvent.getPoint());

                    if (index >= 0) {
                        final Object o = theList.getModel().getElementAt(index);
                        this.model.setSelectedPictureSet((PictureSet) o);
                        this.model.setSelectedPictureSetContent(null);
                        this.model.setSelectedPicture(null);
                    }
                }
            } else {
                throw new IllegalArgumentException("This controller can only handle JLists.");
            }
        }
    }

    /**
     * If something was entered.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseEntered(final MouseEvent mouseEvent) {
    }

    /**
     * If something was exited.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseExited(final MouseEvent mouseEvent) {
    }

    /**
     * If something was pressed.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mousePressed(final MouseEvent mouseEvent) {
    }

    /**
     * If something was released.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
