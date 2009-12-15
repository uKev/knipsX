package org.knipsX.view.reportmanagement;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JList;

import org.knipsX.controller.reportmanagement.AddExifKeywordToReportController;
import org.knipsX.controller.reportmanagement.AddPictureSetToReportController;
import org.knipsX.controller.reportmanagement.RemoveExifKeywordOfReportController;
import org.knipsX.controller.reportmanagement.RemovePictureSetOfReportController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureSet;



public class JPictureSet extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PictureSet[] pictureContainer;
	private String [] exifFilterTags;
	private JList availablePictureSets;
	private JList associatedPictureSets;
	

	/**
	 * Constructor which initialized this picture set management and the exifkeyword management panel
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
        beep.addActionListener((new AddPictureSetToReportController(this)));
        beep.setBounds(150, 60, 80, 30);
        
        Object[] copyFrom = { new PictureSet("HALLO", 1)};
        this.availablePictureSets = new JList(copyFrom);
        //this.availablePictureSets.setCellRenderer(new MyPictureSetContentListCellRenderer());
        availablePictureSets.setBounds(50, 140, 200, 200);
        this.associatedPictureSets = new JList();
        associatedPictureSets.setBounds(300,140,200,200);
        add(availablePictureSets);
        add(associatedPictureSets);

       JButton close = new JButton("<<");
       close.addActionListener(new RemovePictureSetOfReportController(this));
       close.setBounds(50, 60, 80, 30);

        add(beep);
        add(close);
        add(availablePictureSets);
        add(associatedPictureSets);

    }

    
    
	public void setPictureContainer(PictureSet[] pictureContainer) {
		this.pictureContainer = pictureContainer;
		this.availablePictureSets.setListData(new Object[1]);
		this.associatedPictureSets.setListData(this.pictureContainer);
	}

	public PictureSet[] getPictureContainer() {
		PictureSet[] pix = new PictureSet[this.availablePictureSets.getSelectedValues().length];
		int i = 0;
		for(Object pictureset : this.availablePictureSets.getSelectedValues()) {
			if(pictureset instanceof PictureSet) {
				pix[i] = (PictureSet) pictureset;
			}
			i++;
		}
		return pix;
	}

	public void setExifFilterTags(String [] exifFilterTags) {
		this.exifFilterTags = exifFilterTags;
	}

	public String [] getExifFilterTags() {
		return exifFilterTags;
	}


}
