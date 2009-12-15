package org.knipsX.view.reportmanagement;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;

import org.knipsX.controller.reportmanagement.AddExifKeywordToReportController;
import org.knipsX.controller.reportmanagement.AddPictureSetToReportController;
import org.knipsX.controller.reportmanagement.RemoveExifKeywordOfReportController;
import org.knipsX.controller.reportmanagement.RemovePictureSetOfReportController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureContainer;



public class JPictureSet extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PictureContainer> pictureContainer;
	private String [] exifFilterTags;

	/**
	 * Constructor which initialized this picture set and the exifkeyword panel
	 * @param titel The title which is registered with this panel.
	 * @param icon The icon which is registered with this panel.
	 * @param tip The tooltip which is registered with this panel.
	 */
    public JPictureSet(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		if(this.title == null || this.title == "") {
			this.title = "Bildmenge";
		}
		
        setSize(300, 200);

        setLayout(null);        
        
        // Alle verwendeten Controller hier mal hingeschrieben
        new AddPictureSetToReportController<AbstractModel, JPictureSet>(this);
        new RemovePictureSetOfReportController(this);
        new RemoveExifKeywordOfReportController(this);
        new AddExifKeywordToReportController<AbstractModel, JPictureSet>(this);
        

        JButton beep = new JButton(">>");
        beep.setBounds(150, 60, 80, 30);


       JButton close = new JButton("<<");
       close.setBounds(50, 60, 80, 30);

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
