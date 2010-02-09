package org.knipsX.controller.projectview;

import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
 * @param <M> a model.
 * 
 * @param <V> a view.
 */
public class PictureListClickOnController<M extends ProjectModel, V extends JProjectView<M>> extends
        AbstractController<M, V> implements MouseListener {

    private final int MOUSE_LEFT = 1;
    
    private Logger log = Logger.getLogger(this.getClass());
    
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

    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == this.MOUSE_LEFT) {
            final JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 1) {
                final int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    final Picture pic = (Picture) theList.getModel().getElementAt(index);
                    this.model.setSelectedPicture(pic);
                }
            }
        }
    }

    public void mouseEntered(final MouseEvent mouseEvent) {
    	
    	   	
    	final JList theList = (JList) mouseEvent.getSource();
    	
    	final int index = theList.locationToIndex(mouseEvent.getPoint());
        if (index >= 0) {
            Picture pic = (Picture) theList.getModel().getElementAt(index);

            tooltipWindow = new JFrame();
            tooltipWindow.setLocation(mouseEvent.getLocationOnScreen());
            tooltipWindow.setUndecorated(true);
    		
    		try { 
    		ImageIcon image = new ImageIcon(pic.getBigThumbnail());
    		tooltipWindow.setSize(image.getIconHeight(),image.getIconWidth());
    		while (image.getImageLoadStatus() == MediaTracker.LOADING);  		
    		JLabel label = new JLabel(image);
    		image.setImageObserver(label);
    		JPanel panel = new JPanel();				 				
    		panel.add(label);
    		
    		tooltipWindow.setContentPane(panel);
    		tooltipWindow.setVisible(true);
    		tooltipWindow.pack();
    		} catch (NullPointerException e) {
    			log.info("Can not display the thumbnail because at this time it is not initialized");
			}
        } 
    }
    

    public void mouseExited(final MouseEvent mouseEvent) {
    	if (tooltipWindow != null) {
    		tooltipWindow.dispose();
    	}
    	
    }

    public void mousePressed(final MouseEvent mouseEvent) {
    }

    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
