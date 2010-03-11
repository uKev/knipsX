package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.controller.reportmanagement.ReportAddExifKeywordController;
import org.knipsX.controller.reportmanagement.ReportAddPictureSetController;
import org.knipsX.controller.reportmanagement.ReportPictureSetRemoveController;
import org.knipsX.controller.reportmanagement.ReportRemoveExifKeywordController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Resource;
import org.knipsX.utils.Validator;

/**
 * This class represents the panel where the user is able to assign
 * one or more picture sets to the specified report if available.
 * Also the user has the option to assign one ore more Exif tags
 * to the current report if available.
 * 
 * @author David Kaufman
 */
public class JPictureSetExif extends JAbstractSinglePanel {

    private final Logger logger = Logger.getLogger(this.getClass());

    private static final long serialVersionUID = 5672963172972999584L;

    private JFlexibleList availablePictureSets;
    private JFlexibleList associatedPictureSets;
    private JFlexibleList availableExifTags;
    private JFlexibleList associatedExifTags;

    private JLabel errorMessage;

    /**
     * Constructor which initialized this picture set management and the EXIF keyword management panel
     * 
     */
    public JPictureSetExif() {
        this.title = Messages.getString("JPictureSetExif.10");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /* Initialize the top row panel */
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        this.addTopPanelContent(topPanel);

        this.add(topPanel);

        /* add a spacer to relax the layout */
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        /* initialize the bottom row panel */
        final JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.LINE_AXIS));

        this.addBottomPanelContent(bottompanel);

        this.add(bottompanel);

        this.fillViewWithModelInfo();
    }

    /* add the available picture sets panel to the specified panel */
    private void addAvaialablePictureSetsPanel(final JPanel topPanel) {
        final JPanel availablePictureSetsPanel = new JPanel();

        this.availablePictureSets = new JFlexibleList(ReportHelper.getProjectModel().getPictureSets());
        this.availablePictureSets.setCellRenderer(new PictureSetListCellRenderer());
        this.availablePictureSets.setFixedCellWidth(250);

        availablePictureSetsPanel.setLayout(new BorderLayout());
        availablePictureSetsPanel.add(new JLabel(Messages.getString("JPictureSetExif.2")), BorderLayout.NORTH);
        availablePictureSetsPanel.add(new JScrollPane(this.availablePictureSets), BorderLayout.CENTER);

        topPanel.add(availablePictureSetsPanel);
    }

    /* add button panel top button panel to the specified panel */
    private void addTopButtonPanel(final JPanel topButtonPanel) {
        final JButton insertPictureSet = new JButton(Messages.getString("JPictureSetExif.3"));
        final JButton removePictureSet = new JButton(Messages.getString("JPictureSetExif.4"));

        /* to prevent popping and fixing alignment of layout add fixed dimension */
        final Dimension size = new Dimension(32, 32);

        insertPictureSet.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertPictureSet.addActionListener(new ReportAddPictureSetController<AbstractModel, JPictureSetExif>(this));

        removePictureSet.setAlignmentX(Component.CENTER_ALIGNMENT);
        removePictureSet.addActionListener(new ReportPictureSetRemoveController<AbstractModel, JPictureSetExif>(this));

        this.errorMessage = new JLabel();
        this.errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        topButtonPanel.add(new Box.Filler(size, size, size));
        topButtonPanel.add(insertPictureSet);
        topButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topButtonPanel.add(removePictureSet);
        topButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topButtonPanel.add(this.errorMessage);
    }

    private void addAssociatedPictureSetsPanel(final JPanel topPanel) {
        final JPanel associatedPictureSetsPanel = new JPanel();

        this.associatedPictureSets = new JFlexibleList();
        this.associatedPictureSets.setCellRenderer(new PictureSetListCellRenderer());
        this.associatedPictureSets.setFixedCellWidth(250);

        associatedPictureSetsPanel.setLayout(new BorderLayout());
        associatedPictureSetsPanel.add(new JLabel(Messages.getString("JPictureSetExif.5")), BorderLayout.NORTH);
        associatedPictureSetsPanel.add(new JScrollPane(this.associatedPictureSets), BorderLayout.CENTER);

        topPanel.add(associatedPictureSetsPanel);
    }

    private void addAvailableExifTagsPanel(final JPanel bottomPanel) {
        final JPanel availableExifTagsPanel = new JPanel();

        this.availableExifTags = new JFlexibleList();
        this.availableExifTags.setFixedCellWidth(250);

        availableExifTagsPanel.setLayout(new BorderLayout());
        availableExifTagsPanel.add(new JLabel(Messages.getString("JPictureSetExif.6")), BorderLayout.NORTH);
        availableExifTagsPanel.add(new JScrollPane(this.availableExifTags), BorderLayout.CENTER);

        bottomPanel.add(availableExifTagsPanel);
    }

    private void addBottomButtonPanel(final JPanel bottomButtonPanel) {
        final JButton insertexiftag = new JButton(">>");
        final JButton removeexiftag = new JButton("<<");

        insertexiftag.addActionListener(new ReportAddExifKeywordController<AbstractModel, JPictureSetExif>(this));
        removeexiftag.addActionListener(new ReportRemoveExifKeywordController<AbstractModel, JPictureSetExif>(this));

        bottomButtonPanel.add(insertexiftag);
        bottomButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomButtonPanel.add(removeexiftag);
    }

    private void addAssociatedExifTagsPanel(final JPanel bottomPanel) {
        final JPanel associatedExifTagsPanel = new JPanel();

        this.associatedExifTags = new JFlexibleList();
        this.associatedExifTags.setFixedCellWidth(250);

        associatedExifTagsPanel.setLayout(new BorderLayout());
        associatedExifTagsPanel.add(new JLabel(Messages.getString("JPictureSetExif.9")), BorderLayout.NORTH);
        associatedExifTagsPanel.add(new JScrollPane(this.associatedExifTags), BorderLayout.CENTER);

        bottomPanel.add(associatedExifTagsPanel);
    }

    private void addTopPanelContent(final JPanel topPanel) {

        /* initialize the top button panel */
        final JPanel topButtonPanel = new JPanel();

        topButtonPanel.setLayout(new BoxLayout(topButtonPanel, BoxLayout.PAGE_AXIS));

        /* add button panel top button panel */
        this.addTopButtonPanel(topButtonPanel);

        /* add the available picture sets panel */
        this.addAvaialablePictureSetsPanel(topPanel);
        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(topButtonPanel);
        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        /* add the available picture sets panel */
        this.addAssociatedPictureSetsPanel(topPanel);
    }

    private void addBottomPanelContent(final JPanel bottomPanel) {

        /* Initialize the bottom button panel */
        final JPanel bottombuttonpanel = new JPanel();

        bottombuttonpanel.setLayout(new BoxLayout(bottombuttonpanel, BoxLayout.PAGE_AXIS));

        /* Add button panel top button panel */
        this.addBottomButtonPanel(bottombuttonpanel);

        /* add the available EXIF tags panel */
        this.addAvailableExifTagsPanel(bottomPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        bottomPanel.add(bottombuttonpanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        this.addAssociatedExifTagsPanel(bottomPanel);
    }

    /**
     * This method is responsible for associating the selected EXIF filter keywords with the report
     */
    public void associateExifFilterKeywords() {
        this.associatedExifTags.addElements(this.availableExifTags.getSelectedValues());
        this.availableExifTags.removeElements(this.availableExifTags.getSelectedValues());
        this.revalidateReport();
        this.updateXMPData();
    }

    /**
     * This method is responsible for associating the selected picturesets with the report
     */
    public void associatePictureSet() {
        this.associatedPictureSets.addElements(this.availablePictureSets.getSelectedValues());
        this.availablePictureSets.removeElements(this.availablePictureSets.getSelectedValues());
        this.revalidateReport();
        this.revalidateWilcoxon();
        this.updateXMPData();
    }

    /**
     * This method is responsible for removing the selected picture sets keywords from the report
     */
    public void removePictureSet() {
        this.availablePictureSets.addElements(this.associatedPictureSets.getSelectedValues());
        this.associatedPictureSets.removeElements(this.associatedPictureSets.getSelectedValues());
        this.revalidateReport();
        this.revalidateWilcoxon();
        this.updateXMPData();
    }

    /**
     * This method is responsible for removing the selected EXIF filter keywords from the report
     */
    public void removeExifFilterKeywords() {
        this.availableExifTags.addElements(this.associatedExifTags.getSelectedValues());
        this.associatedExifTags.removeElements(this.associatedExifTags.getSelectedValues());
        this.revalidateReport();
        this.updateXMPData();
    }

    
    /**
     * Returns a list of XMP keywords for the specified picture set
     * @param pictureSet the picture set you want to retrieve the XMP keywords from
     * 
     * @return a list of keywords
     */
    private List<String> getKeywordsFromPictureSet(PictureSet pictureSet) {
    	final List<String> xmpKeywords = new ArrayList<String>();
    	
    	Picture[] pictures = ReportHelper.getProjectModel().getAllPicturesFromPictureSet(pictureSet);
    	
    	
    	for(Picture picture : pictures) {
    		
    		 String[] xmpPictureKeyword = new String[0];
    		
            if ((picture.getExifParameter(ExifParameter.KEYWORDS) != null) && (picture.getExifParameter(ExifParameter.KEYWORDS) instanceof String[])) {
                xmpPictureKeyword = (String[]) picture.getExifParameter(ExifParameter.KEYWORDS);
            }
            
            for (int i = 0; i < xmpPictureKeyword.length; ++i) {
                if (!xmpKeywords.contains(xmpPictureKeyword[i])) {
                    xmpKeywords.add(xmpPictureKeyword[i]);
                }
            }
    	}
    	
    	return xmpKeywords;
    }
    
    /**
     * Writes the XMP Data into the available XMP data list
     */
    private void updateXMPData() {

    	/* remove the keywords */
    	for (Object pictureSet : this.availablePictureSets.getElements()) {
    		if (pictureSet instanceof PictureSet) {
    			this.availableExifTags.removeElements(this.getKeywordsFromPictureSet((PictureSet) pictureSet).toArray());
    		}
    	}
    	
    	/* Get a list of all associated picture keywords */
    	final List<String> xmpKeywords = new ArrayList<String>();
    	
    	/* add the associated picture set's keyword to the available keywords list */
    	for (Object pictureSet : this.associatedPictureSets.getElements()) {
    		if (pictureSet instanceof PictureSet) {
    			
    			
    			for (String keyword : this.getKeywordsFromPictureSet((PictureSet) pictureSet)) {
    				if (!xmpKeywords.contains(keyword)) {
    					xmpKeywords.add(keyword);
    				}
    			}
    			
    			this.availableExifTags.addElements(this.getKeywordsFromPictureSet((PictureSet) pictureSet).toArray());    			
    		}
    	}
    	
    	
    	/* remove some more keywords */
    	this.availableExifTags.removeElements(this.associatedExifTags.getElements());
    	
    	
    	/* remove those keywords in the associated pane which have no associated picture set */
    	for (Object keyword : this.associatedExifTags.getElements()) {
    		if (!xmpKeywords.contains(keyword)) {
    			this.associatedExifTags.removeElement(keyword);
    		}
    	}
    	
    }

    /**
     * This function returns the associated EXIF filter keywords of the current report.
     * 
     * @return the associated EXIF filter keywords.
     */
    public String[] getExifFilterKeywords() {
        final Object[] tempObject = this.associatedExifTags.getElements();
        final String[] returnString = new String[tempObject.length];

        for (int i = 0; i < tempObject.length; i++) {
            returnString[i] = (String) tempObject[i];
        }
        return returnString;
    }

    /**
     * This function returns the associated picture sets of the current report
     * 
     * @return the associated picture sets
     */
    public List<PictureContainer> getPictureContainer() {
        final Object[] tempObject = this.associatedPictureSets.getElements();
        final List<PictureContainer> returnPictureContainer = new ArrayList<PictureContainer>();

        for (final Object element : tempObject) {
            returnPictureContainer.add((PictureContainer) element);
        }
        return returnPictureContainer;
    }

    @Override
    public boolean isDiagramDisplayable() {

        try {

            if (this.associatedPictureSets.getContents().size() > 0) {

                /* check to see if there are images in the specified image set */
                for (final PictureContainer pictureContainer : this.getPictureContainer()) {

                    int numberOfElements = ReportHelper.getProjectModel().getAllPicturesFromPictureSet((PictureSet) pictureContainer).length;                    

                    if (numberOfElements == 0) {
                        this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                        this.errorMessage.setToolTipText(Messages.getString("JPictureSetExif.12"));
                        this.showErrorIcon();
                        return false;
                    }
                }

                /* check to see if there is at least one picture which contains valid EXIF data */
                final List<ExifParameter> exifParameters = new ArrayList<ExifParameter>();

                for (final JAbstractSinglePanel singlePanel : ReportHelper.getCurrentReportUtility().reportCompilation
                        .getRegisteredPanels()) {

                    if (singlePanel instanceof JParameters) {
                        final JParameters parameters = (JParameters) singlePanel;

                        for (final Axis axis : parameters.getAxes()) {

                            if (axis.getParameter() != null) {
                                exifParameters.add(axis.getParameter());
                            }
                        }
                    }
                }

                this.logger.debug(Messages.getString("JPictureSetExif.13")
                        + Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters));

                final List<String> associatedXMPKeywords = new ArrayList<String>(Arrays.asList(this
                        .getExifFilterKeywords()));

                for (final PictureContainer pictureContainer : this.getPictureContainer()) {

                    this.logger.debug(Messages.getString("JPictureSetExif.14")
                            + Validator.getValidPictures(pictureContainer, exifParameters, associatedXMPKeywords)
                                    .size());

                    if ((Validator.getValidPictures(pictureContainer, exifParameters, associatedXMPKeywords).size() == 0)
                            && (ReportHelper.getCurrentReport() != ReportHelper.Table)) {
                        this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                        this.errorMessage.setToolTipText(Messages.getString("JPictureSetExif.16")
                                + Messages.getString("JPictureSetExif.17"));
                        this.showErrorIcon();
                        return false;
                    }
                }

                this.errorMessage.setIcon(null);

                /* to prevent popping of layout add fixed dimension */
                this.errorMessage.setMinimumSize(new Dimension(32, 32));
                this.errorMessage.setPreferredSize(new Dimension(32, 32));
                this.errorMessage.setMaximumSize(new Dimension(32, 32));
                this.errorMessage.setToolTipText(null);

                this.resetIcon();

                /* force the report utility to update */
                ReportHelper.getCurrentReportUtility().repaint();
                return true;
            } else {
                this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                this.errorMessage.setToolTipText(Messages.getString("JPictureSetExif.19")
                        + Messages.getString("JPictureSetExif.20"));
                this.showErrorIcon();
                return false;
            }
        } catch (final FileNotFoundException e) {
            this.logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

    /**
     * This method is responsible for revalidating the wilcoxon panel, since changes made to the picture
     * sets effect configuration of the wilcoxon panel.
     */
    protected void revalidateWilcoxon() {

        if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {
            boolean enabled = false;

            if (this.associatedPictureSets.getContents().size() == 2) {
                enabled = true;
            }

            final List<JAbstractSinglePanel> registeredPanels = ReportHelper.getCurrentReportUtility().reportCompilation
                    .getRegisteredPanels();

            for (final JAbstractSinglePanel singlepanel : registeredPanels) {

                if (singlepanel instanceof JWilcoxon) {
                    ((JWilcoxon) singlepanel).setImageSetEnabled(enabled);
                }
            }
        }
    }

    @Override
    public void fillViewWithModelInfo() {

        if (ReportHelper.getCurrentModel() != null) {

            if (ReportHelper.getCurrentModel().getPictureContainer() != null) {
                this.associatedPictureSets.addElements(ReportHelper.getCurrentModel().getPictureContainer().toArray());
                this.availablePictureSets
                        .removeElements(ReportHelper.getCurrentModel().getPictureContainer().toArray());
            }

            if ((ReportHelper.getCurrentModel().getExifFilterKeywords() != null)
                    && (ReportHelper.getCurrentModel().getExifFilterKeywords().size() > 0)) {
                this.associatedExifTags.addElements((ReportHelper.getCurrentModel().getExifFilterKeywords()
                        .toArray(new String[] {})));
                this.availableExifTags.removeElements((ReportHelper.getCurrentModel().getExifFilterKeywords()
                        .toArray(new String[] {})));
            }
        }

        if (this.getPictureContainer().size() > 0) {
            this.updateXMPData();
        }
    }

    /**
     * 
     * This is a utility class which implements a JList in a way that it is
     * more flexible. It is capable of inserting and removing multiple objects.
     * 
     * @author David Kaufman
     */
    private class JFlexibleList extends JList {

        private static final long serialVersionUID = 4010043419553712109L;
        
        /**
         * The default constructor of the JFlexibleList
         */
        JFlexibleList() {
            super(new DefaultListModel());
        }

        /**
         * This constructor takes an additional array of type object which inserts the
         * specified elements in the list.
         * 
         * @param elements
         *            the elements you want to insert.
         */
        JFlexibleList(final Object[] elements) {
            this.setModel(new DefaultListModel());
            this.addElements(elements);
        }

        /**
         * Adds the specified elements to the list.
         * 
         * @param elements
         *            the list you want to insert.
         */
        public void addElements(final Object[] elements) {

            for (final Object element : elements) {
            	if(!this.getContents().contains(element)) {
            		this.getContents().addElement(element);
            	}
            }
        }

        /**
         * Returns the default list models contents.
         * 
         * @return the contents of the list.
         */
        DefaultListModel getContents() {
            return (DefaultListModel) this.getModel();
        }

        /**
         * Returns the elements which are stored in the list.
         * 
         * @return the elements in the list.
         */
        public Object[] getElements() {
            final Object[] returnObject = new Object[this.getContents().size()];

            for (int i = 0; i < returnObject.length; ++i) {
                returnObject[i] = this.getContents().get(i);
            }
            return returnObject;
        }

        /**
         * Removes the specified elements from the list.
         * 
         * @param elements
         *            the elements which you want to remove from the list.
         */
        public void removeElements(final Object[] elements) {
            for (final Object element : elements) {
                this.getContents().removeElement(element);
            }
        }
        
        /**
         * Removes the specified element
         * @param element the element you want to delete
         */
        public void removeElement(final Object element) {
        	this.getContents().removeElement(element);
        }
        

        
    }

    /**
     * This nested class renders a picture set content cell for the picture set content list of an active project.
     */
    private class PictureSetListCellRenderer implements ListCellRenderer {

        /* defines the default renderer */
        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        /**
         * Renders the cell.
         * 
         * @param list
         *            the JList we're painting.
         * @param value
         *            the value returned by list.getModel().getElementAt(index).
         * @param index
         *            the cells index.
         * @param isSelected
         *            true if the specified cell was selected.
         * @param cellHasFocus
         *            true if the specified cell has the focus.
         * 
         * @return the representation of the cell.
         */
        public Component getListCellRendererComponent(final JList list, final Object value, final int index,
                final boolean isSelected, final boolean cellHasFocus) {

            /* the text for the cell */
            String theText = null;

            /* generate the label which represents the cell */
            final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);

            if (value instanceof PictureSet) {
                final PictureSet pictureContainer = (PictureSet) value;
                final PictureContainer[] containers = ReportHelper.getProjectModel().getAllPicturesFromPictureSet(
                        pictureContainer);
                final int numberOfElements = containers.length;
                theText = pictureContainer.getName() + " (" + numberOfElements + ")";
            }
            renderer.setText(theText);

            return renderer;
        }
    }
}