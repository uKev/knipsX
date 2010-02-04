package org.knipsX.controller.projectview;

import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knipsX.controller.AbstractController;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.projectmanagement.ProjectManagementModel;
import org.knipsX.view.projectmanagement.JProjectManagement;

public class PictureShowImageTooltipController <M extends ProjectManagementModel, V extends JProjectManagement<?>> extends
AbstractController<M, V> implements MouseListener {
	
	JFrame pictureFrame;

	public PictureShowImageTooltipController(M model, V view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Point locationOnScreen = e.getLocationOnScreen();
		
		pictureFrame = new JFrame();
		pictureFrame.setLocation(locationOnScreen);
		pictureFrame.setUndecorated(true);
		
		JPanel panel = new JPanel();
		pictureFrame.setContentPane(panel);	
		
		(JLable).getComponent().getParent()		
		ImageIcon icon = new ImageIcon();
        while ( icon.getImageLoadStatus() == MediaTracker.LOADING );
        
        
        JLabel label = new JLabel(icon);
        icon.setImageObserver(label);
        
        JLabel label = new JLabel(icon);
        icon.setImageObserver(label);
				
		ImageIcon image = new ImageIcon();
	
		while ( image.getImageLoadStatus() == MediaTracker.LOADING );
		
		JLabel label = new JLabel(image);
		image.setImageObserver(label);
				
		System.out.println("Adding Image Label");
		panel.add( label );

		pictureFrame.setSize(image.getIconHeight(),image.getIconWidth());
		//pictureFrame.setSize(950, 650);
		pictureFrame.setVisible(true);
		
				
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (pictureFrame != null) {
			pictureFrame.dispose();
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
