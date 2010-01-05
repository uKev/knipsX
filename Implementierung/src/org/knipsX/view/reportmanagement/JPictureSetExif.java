package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.knipsX.controller.reportmanagement.ReportAddExifKeywordController;
import org.knipsX.controller.reportmanagement.ReportAddPictureSetController;
import org.knipsX.controller.reportmanagement.ReportRemoveExifKeywordController;
import org.knipsX.controller.reportmanagement.ReportPictureSetRemoveController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.PictureSet;


/**
 * This class represents the panel where the user is able to assign 
 * one or more picture sets to the specified report if available.
 * Also the user has the option to assign one ore more Exif tags
 * to the current report if available.
 * 
 * @author David Kaufman
 *
 */
public class JPictureSetExif extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PictureSet[] pictureContainer = {new PictureSet("",1),new PictureSet("",2),new PictureSet("",3)};
	private String [] exifFilterKeywords = {"asas", "HAÖÖP"};
	private JFlexibleList availablePictureSets;
	private JFlexibleList associatedPictureSets;
	private JFlexibleList availableExifTags;
	private JFlexibleList associatedExifTags;
	

	/**
	 * Constructor which initialized this picture set management and the exifkeyword management panel
	 * @param titel The title which is registered with this panel.
	 * @param icon The icon which is registered with this panel.
	 * @param tip The tooltip which is registered with this panel.
	 */
    public JPictureSetExif(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
    	
		if(this.title == null || this.title == "") {
			this.title = "Bildmenge";
		}
		

        BoxLayout container = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(container);          
       
        
        // Initialize the top row panel
        JPanel toppanel = new JPanel();        
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.X_AXIS));
        	
        	JPanel availablePictureSetsPanel = new JPanel();
        	availablePictureSetsPanel.setLayout(new BorderLayout());        	
        	JLabel availablePictureSetsLabel = new JLabel("Verfügbare Bildermengen");
        	availablePictureSetsPanel.add(availablePictureSetsLabel,BorderLayout.NORTH);        	
        	this.availablePictureSets = new JFlexibleList(this.pictureContainer);
        	this.availablePictureSets.setFixedCellWidth(250);
        	JScrollPane test = new JScrollPane(this.availablePictureSets);
        	availablePictureSetsPanel.add(test,BorderLayout.CENTER);        	
        	toppanel.add(availablePictureSetsPanel);
        	
        	 // Add a spacer to relax the layout
        	toppanel.add(Box.createRigidArea(new Dimension(20, 0)));

        	JPanel topbuttonpanel = new JPanel();
        	topbuttonpanel.setLayout(new BoxLayout(topbuttonpanel, BoxLayout.Y_AXIS));        	
	        JButton insertpictureset = new JButton(">>");
	        insertpictureset.addActionListener(new ReportAddPictureSetController<AbstractModel, JPictureSetExif>(this));
	        topbuttonpanel.add(insertpictureset);	       
	        JButton removepictureset = new JButton("<<");
	        removepictureset.addActionListener(new ReportPictureSetRemoveController<AbstractModel, JPictureSetExif>(this));
	        topbuttonpanel.add(removepictureset);	        
	        toppanel.add(topbuttonpanel);
	        
	        // Add a spacer to relax the layout
	        toppanel.add(Box.createRigidArea(new Dimension(20, 0)));        	
	        
	        JPanel associatedPictureSetsPanel = new JPanel();
	        associatedPictureSetsPanel.setLayout(new BorderLayout());	        
	        JLabel associatedPictureSetsLabel = new JLabel("Verwendete Bildermengen");
        	this.associatedPictureSets = new JFlexibleList();
        	this.associatedPictureSets.setFixedCellWidth(250);
        	associatedPictureSetsPanel.add(associatedPictureSetsLabel,BorderLayout.NORTH);
        	associatedPictureSetsPanel.add(new JScrollPane(this.associatedPictureSets),BorderLayout.CENTER);
        	toppanel.add(associatedPictureSetsPanel);
	    
	    add(toppanel);
	    
	    // Add a spacer to relax the layout
        add(Box.createRigidArea(new Dimension(0, 20)));
	    
        // Initialize the bottom row panel
	    JPanel bottompanel = new JPanel();
	    bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.X_AXIS));
        	
	    	JPanel availableExifTagsPanel = new JPanel();
	    	availableExifTagsPanel.setLayout(new BorderLayout());	    	
	        JLabel availableExifTagsLabel = new JLabel("Verfügbare Exif-Keywords");
        	this.availableExifTags = new JFlexibleList(this.exifFilterKeywords );
        	this.availableExifTags.setFixedCellWidth(250);
        	availableExifTagsPanel.add(availableExifTagsLabel,BorderLayout.NORTH);
        	availableExifTagsPanel.add(new JScrollPane(this.availableExifTags),BorderLayout.CENTER);        	
        	bottompanel.add(availableExifTagsPanel);
        	
        	 // Add a spacer to relax the layout
        	bottompanel.add(Box.createRigidArea(new Dimension(20, 0)));

        	JPanel bottombuttonpanel = new JPanel();
        	bottombuttonpanel.setLayout(new BoxLayout(bottombuttonpanel, BoxLayout.Y_AXIS));        	
	        JButton insertexiftag = new JButton(">>");
	        insertexiftag.addActionListener(new ReportAddExifKeywordController<AbstractModel, JPictureSetExif>(this));
	        bottombuttonpanel.add(insertexiftag);	       
	        JButton removeexiftag = new JButton("<<");
	        removeexiftag.addActionListener(new ReportRemoveExifKeywordController<AbstractModel, JPictureSetExif>(this));
	        bottombuttonpanel.add(removeexiftag);	        
	        bottompanel.add(bottombuttonpanel);
	        
	        // Add a spacer to relax the layout
	        bottompanel.add(Box.createRigidArea(new Dimension(20, 0)));
        	
	        
	    	JPanel associatedExifTagsPanel = new JPanel();
	    	associatedExifTagsPanel.setLayout(new BorderLayout());
	        JLabel associatedExifTagsLabel = new JLabel("Verwendete Exif-Keywords");
        	this.associatedExifTags = new JFlexibleList();
        	this.associatedExifTags.setFixedCellWidth(250);
        	associatedExifTagsPanel.add(associatedExifTagsLabel,BorderLayout.NORTH);
        	associatedExifTagsPanel.add(new JScrollPane(this.associatedExifTags),BorderLayout.CENTER);
        	bottompanel.add(associatedExifTagsPanel);
        
	    
	    add(bottompanel);

    }

    
    class JFlexibleList extends JList {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JFlexibleList() {
			super(new DefaultListModel());
        }
		
		JFlexibleList(Object[] elements) {
			this.setModel(new DefaultListModel());
			addElements(elements);
			
        }
		
		public void addElements(Object[] elements) {
			for(Object element : elements) {
				this.getContents().addElement(element);
			}
		}
		
		public void removeElements(Object[] elements) {
			for(Object element : elements) {
				this.getContents().removeElement(element);
			}
		}	
		
        DefaultListModel getContents() {
        	return (DefaultListModel)getModel();
        }
    } 
    
    protected void revalidateBoxplot() {
		if(ReportHelper.currentReport == ReportHelper.Boxplot) {
			boolean enabled = false;
			
			if(this.associatedPictureSets.getContents().size() == 2) {
				enabled = true;
			}
			
			for(JAbstractSinglePanel singlepanel : ReportHelper.currentReportUtil.reportCompilation.getRegisteredPanels()) {
				if(singlepanel instanceof JWilcoxon) {
					singlepanel.setEnabled(enabled);
				}
			}
			
		}
	}

    
    
	public void associatePictureSet() {
		this.associatedPictureSets.addElements(this.availablePictureSets.getSelectedValues());
		this.availablePictureSets.removeElements(this.availablePictureSets.getSelectedValues());
		revalidateBoxplot();
	}


	public void removePictureSet() {
		this.availablePictureSets.addElements(this.associatedPictureSets.getSelectedValues());
		this.associatedPictureSets.removeElements(this.associatedPictureSets.getSelectedValues());
		revalidateBoxplot();
	}

	public void associateExifFilterKeywords() {
		this.associatedExifTags.addElements(this.availableExifTags.getSelectedValues());
		this.availableExifTags.removeElements(this.availableExifTags.getSelectedValues());
	}

	public void removeExifFilterKeywords() {
		this.availableExifTags.addElements(this.associatedExifTags.getSelectedValues());
		this.associatedExifTags.removeElements(this.associatedExifTags.getSelectedValues());
	}


}
