package org.knipsX.view.reportmanagement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;

import org.knipsX.controller.reportmanagement.AddExifKeywordToReportController;
import org.knipsX.controller.reportmanagement.AddPictureSetToReportController;
import org.knipsX.controller.reportmanagement.RemoveExifKeywordOfReportController;
import org.knipsX.controller.reportmanagement.RemovePictureSetOfReportController;
import org.knipsX.model.picturemanagement.PictureContainer;



public class JPictureSet extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit toolkit;
	private ArrayList<PictureContainer> pictureContainer;
	private String [] exifFilterTags;

    public JPictureSet(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		if(this.title == null || this.title == "") {
			this.title = "Bildmenge";
		}
		
        setSize(300, 200);

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);


        setLayout(null);
        
        
        // Alle verwendeten Controller hier mal hingeschrieben
        new AddPictureSetToReportController(this);
        new RemovePictureSetOfReportController(this);
        new RemoveExifKeywordOfReportController(this);
        new AddExifKeywordToReportController(this);
        

        JButton beep = new JButton(">>");
        beep.setBounds(150, 60, 80, 30);
        beep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                toolkit.beep();
            }
        });

       JButton close = new JButton("<<");
       close.setBounds(50, 60, 80, 30);
       close.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent event) {
               System.exit(0);
          }
       });

        add(beep);
        add(close);

    }

	public void setPictureContainer(ArrayList<PictureContainer> pictureContainer) {
		this.pictureContainer = pictureContainer;
	}

	public ArrayList<PictureContainer> getPictureContainer() {
		return pictureContainer;
	}

	public void setExifFilterTags(String [] exifFilterTags) {
		this.exifFilterTags = exifFilterTags;
	}

	public String [] getExifFilterTags() {
		return exifFilterTags;
	}


}
