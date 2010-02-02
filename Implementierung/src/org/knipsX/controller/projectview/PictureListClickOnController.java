package org.knipsX.controller.projectview;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the Actions which are done by klicking on the picturelist.
 * 
 * Acts in harmony with a JProjectview.
 * 
 * @param <M>
 *            a model.
 * 
 * @param <V>
 *            a view.
 */
public class PictureListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
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
    public PictureListClickOnController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MOUSE_LEFT) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    this.model.setSelectedPicture((Picture) o);
                }
            }
        }
    }


    public void mouseEntered(MouseEvent mouseEvent) {
    }


    public void mouseExited(MouseEvent mouseEvent) {
    }


    public void mousePressed(MouseEvent mouseEvent) {
    }


    public void mouseReleased(MouseEvent mouseEvent) {
    }
}
