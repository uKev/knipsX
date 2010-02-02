import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DataModelTest
{

	
	public static JFrame exectooltip(Point p){
		
		File file = new File("C:/Dokumente und Einstellungen/All Users/Dokumente/Eigene Bilder/Beispielbilder/Blaue Berge.jpg");
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		frame.getContentPane().removeAll();
		frame.setLocation(p);
		frame.setUndecorated(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.setContentPane(panel);		
				
		ImageIcon i = null;
		i = new ImageIcon(img);
		while ( i.getImageLoadStatus() == MediaTracker.LOADING );
		
		JLabel label = new JLabel(i);
		i.setImageObserver(label);
				
		System.out.println("Adding Image Label");
		panel.add( label );

		frame.setSize(i.getIconHeight(),i.getIconWidth());
		//frame.setSize(950, 650);
		frame.setVisible(true);
		frame.pack();
		
		return frame;
	}	
}
