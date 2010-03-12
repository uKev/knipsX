package org.knipsX.controller.projectview;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JList;

import org.apache.log4j.Logger;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.view.projectview.JProjectView;

/**
 * Represents the actions which are done by clicking on the picture list.
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
        AbstractController<M, V> implements MouseListener, MouseMotionListener {

    private static final int MOUSE_LEFT = 1;

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Creates a new controller which is connected to a view and a model.
     * 
     * @param model
     *            the model.
     * @param view
     *            the view.
     */
    public PictureListClickOnController(final M model, final V view) {
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
        if (mouseEvent.getButton() == PictureListClickOnController.MOUSE_LEFT) {
            if (mouseEvent.getSource() instanceof JList) {
                final JList theList = (JList) mouseEvent.getSource();

                if (mouseEvent.getClickCount() == 1) {
                    final int index = theList.locationToIndex(mouseEvent.getPoint());

                    if (index >= 0) {
                        final PictureInterface pic = (PictureInterface) theList.getModel().getElementAt(index);
                        this.model.setSelectedPicture(pic);
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
     * @throws IllegalArgumentException
     *             if you connect the controller to no JList, you get this error.
     */
    public void mouseEntered(final MouseEvent mouseEvent) throws IllegalArgumentException {
        this.drawTooltip(mouseEvent);
    }

    /**
     * If something was exited.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseExited(final MouseEvent mouseEvent) {
        this.view.removeThumbnail();
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

    /**
     * If something was dragged.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseDragged(final MouseEvent mouseEvent) {
    }

    /**
     * If something was moved.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     * @throws IllegalArgumentException
     *             if you connect the controller to no JList, you get this error.
     */
    public void mouseMoved(final MouseEvent mouseEvent) throws IllegalArgumentException {
            this.drawTooltip(mouseEvent);
    }

    /*
     * Draws the tooltip.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     * 
     * @throws IllegalArgumentException
     *             if you connect the controller to no JList, you get this error.
     */
    private void drawTooltip(final MouseEvent mouseEvent) throws IllegalArgumentException {
        if (mouseEvent.getSource() instanceof JList) {
            final JList theList = (JList) mouseEvent.getSource();
            final Point currentPoint = mouseEvent.getPoint();
            final int index = theList.locationToIndex(currentPoint);
            Rectangle rect = theList.getCellBounds(index, index + 1);
            
            if (rect != null && rect.contains(currentPoint)) {
                final PictureInterface pic = (PictureInterface) theList.getModel().getElementAt(index);
                final Point point = mouseEvent.getPoint();
                
                point.translate(25, 75);

                try {
                    this.view.setThumbnail(pic.getBigThumbnail(), point);
                } catch (final NullPointerException e) {
                    this.logger.info("Can not display the thumbnail because at this time it is not initialized.");
                }
            }
            else {
                this.view.removeThumbnail();
            }
        } else {
            throw new IllegalArgumentException("This controller can only handle JLists.");
        }
    }
}
