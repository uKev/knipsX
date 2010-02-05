package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by clicking on the picture set content list.
 * 
 * Acts in harmony with a JProjectview.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureSetContentListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private final int MOUSE_LEFT = 1;
    private final int MOUSE_RIGHT = 3;

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureSetContentListClickOnController(final M model, final V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    }

    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == this.MOUSE_LEFT) {
            final JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                final int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    final Object o = theList.getModel().getElementAt(index);
                    this.model.setSelectedPictureSetContent((PictureContainer) o);
                }
            }
        } else if (mouseEvent.getButton() == this.MOUSE_RIGHT) {
            this.model.setSelectedPictureSetContent(null);
        }
    }

    public void mouseEntered(final MouseEvent mouseEvent) {
    }

    public void mouseExited(final MouseEvent mouseEvent) {
    }

    public void mousePressed(final MouseEvent mouseEvent) {
    }

    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
