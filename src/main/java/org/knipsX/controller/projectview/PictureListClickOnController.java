package org.knipsX.controller.projectview;

import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.Picture;
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
        AbstractController<M, V> implements MouseListener, MouseMotionListener {

    private static final int MOUSE_LEFT = 1;

    private final Logger logger = Logger.getLogger(this.getClass());

    JFrame tooltipWindow;

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
                        final Picture pic = (Picture) theList.getModel().getElementAt(index);
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
     */
    public void mouseEntered(final MouseEvent mouseEvent) {
        final JList theList = (JList) mouseEvent.getSource();

        final int index = theList.locationToIndex(mouseEvent.getPoint());
        if (index >= 0) {
            final Picture pic = (Picture) theList.getModel().getElementAt(index);

            this.tooltipWindow = new JFrame();
            final Point point = mouseEvent.getLocationOnScreen();
            point.translate(10, 10);
            this.tooltipWindow.setLocation(point);
            this.tooltipWindow.setUndecorated(true);

            try {
                final ImageIcon image = new ImageIcon(pic.getBigThumbnail());
                this.tooltipWindow.setSize(image.getIconHeight(), image.getIconWidth());
                while (image.getImageLoadStatus() == MediaTracker.LOADING) {
                    ;
                }
                final JLabel label = new JLabel(image);
                image.setImageObserver(label);
                final JPanel panel = new JPanel();
                panel.add(label);

                this.tooltipWindow.setContentPane(panel);
                this.tooltipWindow.setVisible(true);
                this.tooltipWindow.pack();
            } catch (final NullPointerException e) {
                this.logger.info("Can not display the thumbnail because at this time it is not initialized");
            }
        }
    }

    /**
     * If something was exited.
     * 
     * @param mouseEvent
     *            the event of the mouse.
     */
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.tooltipWindow != null) {
            this.tooltipWindow.dispose();
        }

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
     */
    public void mouseMoved(final MouseEvent mouseEvent) throws IllegalArgumentException {
        if (this.tooltipWindow != null) {
            if (mouseEvent.getSource() instanceof JList) {
                final JList theList = (JList) mouseEvent.getSource();

                final int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    final Picture pic = (Picture) theList.getModel().getElementAt(index);

                    final Point point = mouseEvent.getLocationOnScreen();
                    point.translate(10, 10);
                    this.tooltipWindow.setLocation(point);

                    try {
                        final ImageIcon image = new ImageIcon(pic.getBigThumbnail());
                        this.tooltipWindow.setSize(image.getIconHeight(), image.getIconWidth());
                        while (image.getImageLoadStatus() == MediaTracker.LOADING) {
                            ;
                        }
                        final JLabel label = new JLabel(image);
                        image.setImageObserver(label);
                        final JPanel panel = new JPanel();
                        panel.add(label);

                        this.tooltipWindow.setContentPane(panel);
                        this.tooltipWindow.setVisible(true);
                    } catch (final NullPointerException e) {
                        this.logger.info("Can not display the thumbnail because at this time it is not initialized.");
                    }
                }
            } else {
                throw new IllegalArgumentException("This controller can only handle JLists.");
            }
        }
    }
}
