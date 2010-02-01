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
 * Acts in harmony with a JProjectview.
 */
public class PictureSetListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    public PictureSetListClickOnController(M model, V view) {
        super(model, view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
            int index = theList.locationToIndex(mouseEvent.getPoint());
            if (index >= 0) {
                Object o = theList.getModel().getElementAt(index);
                this.model.setSelectedPictureSet((PictureSet)o);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
