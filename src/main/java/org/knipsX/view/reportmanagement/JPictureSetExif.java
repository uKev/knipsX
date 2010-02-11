package org.knipsX.view.reportmanagement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

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
import org.knipsX.controller.reportmanagement.ReportAddExifKeywordController;
import org.knipsX.controller.reportmanagement.ReportAddPictureSetController;
import org.knipsX.controller.reportmanagement.ReportPictureSetRemoveController;
import org.knipsX.controller.reportmanagement.ReportRemoveExifKeywordController;
import org.knipsX.images.dummypictures.DummyPictures;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.picturemanagement.Directory;
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
 * 
 */
public class JPictureSetExif extends JAbstractSinglePanel {

    private static final long serialVersionUID = 5672963172972999584L;

    /**
     * 
     * This is a utility class which implements a JList in a way that it is
     * more flexible. It is capable of inserting and removing multiple objects
     * 
     * @author David Kaufman
     * 
     */
    class JFlexibleList extends JList {

        private static final long serialVersionUID = 4010043419553712109L;

        /**
         * The default constructor of the JFlexibleList
         */
        JFlexibleList() {
            super(new DefaultListModel());
        }

        /**
         * This constructor takes an additional array of type object which inserts the
         * specified elements in the list
         * 
         * @param elements
         *            the elements you want to insert
         */
        JFlexibleList(final Object[] elements) {
            this.setModel(new DefaultListModel());
            this.addElements(elements);

        }

        /**
         * Adds the specified elements to the list
         * 
         * @param elements
         *            the list you want to insert
         */
        public void addElements(final Object[] elements) {
            for (final Object element : elements) {
                this.getContents().addElement(element);
            }
        }

        /**
         * Returns the default list models contents
         * 
         * @return the contents of the list
         */
        DefaultListModel getContents() {
            return (DefaultListModel) this.getModel();
        }

        /**
         * Returns the elements which are stored in the list
         * 
         * @return the elements in the list
         */
        public Object[] getElements() {
            final Object[] returnObject = new Object[this.getContents().size()];

            for (int i = 0; i < returnObject.length; i++) {
                returnObject[i] = this.getContents().get(i);
            }

            return returnObject;
        }

        /**
         * Removes the specified elements from the list
         * 
         * @param elements
         *            the elements which you want to remove from the list
         */
        public void removeElements(final Object[] elements) {
            for (final Object element : elements) {
                this.getContents().removeElement(element);
            }
        }

        /**
         * Removes all elements in the list
         */
        public void removeAllElements() {
            this.getContents().clear();
        }
    }

    /**
     * This nested class renders a picture set content cell for the picture set content list of an active project.
     */
    class PictureSetListCellRenderer implements ListCellRenderer {

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
                /* Reset iterator because you never know how someone used it before */
                pictureContainer.resetIterator();
                int numberOfElements = 0;

                for (@SuppressWarnings("unused") Picture picture : pictureContainer) {
                    numberOfElements++;
                }
                
                theText = pictureContainer.getName() + " (" + Integer.toString(numberOfElements) + ")"; //$NON-NLS-1$ //$NON-NLS-2$

            }
            renderer.setText(theText);

            /* return the label */
            return renderer;
        }
    }

    /* Add the available picture sets panel to the specified panel */
    private void addAvaialablePictureSetsPanel(JPanel toppanel) {
        final JPanel availablePictureSetsPanel = new JPanel();
        availablePictureSetsPanel.setLayout(new BorderLayout());
        // INTERNATIONALIZE
        final JLabel availablePictureSetsLabel = new JLabel(Messages.getString("JPictureSetExif.2")); //$NON-NLS-1$
        availablePictureSetsPanel.add(availablePictureSetsLabel, BorderLayout.NORTH);
        this.availablePictureSets = new JFlexibleList(ReportHelper.getProjectModel().getPictureSets());
        this.availablePictureSets.setCellRenderer(new PictureSetListCellRenderer());
        this.availablePictureSets.setFixedCellWidth(250);
        final JScrollPane test = new JScrollPane(this.availablePictureSets);
        availablePictureSetsPanel.add(test, BorderLayout.CENTER);
        toppanel.add(availablePictureSetsPanel);
    }

    private JFlexibleList availablePictureSets;
    private JFlexibleList associatedPictureSets;
    private JFlexibleList availableExifTags;
    private JFlexibleList associatedExifTags;
    private JLabel errorMessage;

    /* Add button panel top button panel to the specified panel */
    private void addTopButtonPanel(JPanel topbuttonpanel) {
        /* to prevent popping and fixing alignment of layout add fixed dimension */
        Dimension size = new Dimension(32, 32);
        topbuttonpanel.add(new Box.Filler(size, size, size));
        final JButton insertpictureset = new JButton(Messages.getString("JPictureSetExif.3")); //$NON-NLS-1$
        insertpictureset.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertpictureset.addActionListener(new ReportAddPictureSetController<AbstractModel, JPictureSetExif>(this));
        topbuttonpanel.add(insertpictureset);
        final JButton removepictureset = new JButton(Messages.getString("JPictureSetExif.4")); //$NON-NLS-1$
        removepictureset.setAlignmentX(Component.CENTER_ALIGNMENT);
        removepictureset.addActionListener(new ReportPictureSetRemoveController<AbstractModel, JPictureSetExif>(this));
        topbuttonpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topbuttonpanel.add(removepictureset);
        this.errorMessage = new JLabel();
        this.errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        topbuttonpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topbuttonpanel.add(this.errorMessage);
    }

    private void addAssociatedPictureSetsPanel(JPanel toppanel) {
        final JPanel associatedPictureSetsPanel = new JPanel();
        associatedPictureSetsPanel.setLayout(new BorderLayout());
        // INTERNATIONALIZE
        final JLabel associatedPictureSetsLabel = new JLabel(Messages.getString("JPictureSetExif.5")); //$NON-NLS-1$
        this.associatedPictureSets = new JFlexibleList();
        this.associatedPictureSets.setCellRenderer(new PictureSetListCellRenderer());
        this.associatedPictureSets.setFixedCellWidth(250);
        associatedPictureSetsPanel.add(associatedPictureSetsLabel, BorderLayout.NORTH);
        associatedPictureSetsPanel.add(new JScrollPane(this.associatedPictureSets), BorderLayout.CENTER);
        toppanel.add(associatedPictureSetsPanel);
    }

    private void addAvailableExifTagsPanel(JPanel bottompanel) {
        final JPanel availableExifTagsPanel = new JPanel();
        availableExifTagsPanel.setLayout(new BorderLayout());
        // INTERNATIONALIZE
        final JLabel availableExifTagsLabel = new JLabel(Messages.getString("JPictureSetExif.6")); //$NON-NLS-1$
        this.availableExifTags = new JFlexibleList();
        this.availableExifTags.setFixedCellWidth(250);
        availableExifTagsPanel.add(availableExifTagsLabel, BorderLayout.NORTH);
        availableExifTagsPanel.add(new JScrollPane(this.availableExifTags), BorderLayout.CENTER);
        bottompanel.add(availableExifTagsPanel);
    }

    private void addBottomButtonPanel(JPanel bottombuttonpanel) {
        final JButton insertexiftag = new JButton(">>");
        insertexiftag.addActionListener(new ReportAddExifKeywordController<AbstractModel, JPictureSetExif>(this));
        bottombuttonpanel.add(insertexiftag);
        final JButton removeexiftag = new JButton("<<");
        removeexiftag.addActionListener(new ReportRemoveExifKeywordController<AbstractModel, JPictureSetExif>(this));
        bottombuttonpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottombuttonpanel.add(removeexiftag);

    }

    private void addAssociatedExifTagsPanel(JPanel bottompanel) {
        final JPanel associatedExifTagsPanel = new JPanel();
        associatedExifTagsPanel.setLayout(new BorderLayout());
        // INTERNATIONALIZE
        final JLabel associatedExifTagsLabel = new JLabel(Messages.getString("JPictureSetExif.9")); //$NON-NLS-1$
        this.associatedExifTags = new JFlexibleList();
        this.associatedExifTags.setFixedCellWidth(250);
        associatedExifTagsPanel.add(associatedExifTagsLabel, BorderLayout.NORTH);
        associatedExifTagsPanel.add(new JScrollPane(this.associatedExifTags), BorderLayout.CENTER);
        bottompanel.add(associatedExifTagsPanel);
    }

    private void addTopPanelContent(JPanel toppanel) {
        /* Add the available picture sets panel */
        addAvaialablePictureSetsPanel(toppanel);

        /* Add a spacer to relax the layout */
        toppanel.add(Box.createRigidArea(new Dimension(20, 0)));

        /* Initialize the top button panel */
        final JPanel topbuttonpanel = new JPanel();
        topbuttonpanel.setLayout(new BoxLayout(topbuttonpanel, BoxLayout.PAGE_AXIS));

        /* Add button panel top button panel */
        addTopButtonPanel(topbuttonpanel);

        toppanel.add(topbuttonpanel);

        /* Add a spacer to relax the layout */
        toppanel.add(Box.createRigidArea(new Dimension(20, 0)));

        /* Add the available picture sets panel */
        addAssociatedPictureSetsPanel(toppanel);
    }

    private void addBottomPanelContent(JPanel bottompanel) {
        /* Add the available EXIF tags panel */
        addAvailableExifTagsPanel(bottompanel);

        // Add a spacer to relax the layout
        bottompanel.add(Box.createRigidArea(new Dimension(20, 0)));

        /* Initialize the bottom button panel */
        final JPanel bottombuttonpanel = new JPanel();
        bottombuttonpanel.setLayout(new BoxLayout(bottombuttonpanel, BoxLayout.PAGE_AXIS));

        /* Add button panel top button panel */
        addBottomButtonPanel(bottombuttonpanel);

        bottompanel.add(bottombuttonpanel);

        /* Add a spacer to relax the layout */
        bottompanel.add(Box.createRigidArea(new Dimension(20, 0)));

        addAssociatedExifTagsPanel(bottompanel);
    }

    /**
     * Constructor which initialized this picture set management and the EXIF keyword management panel
     * 
     */
    public JPictureSetExif() {

        /* Set the title name of this panel */
        // INTERNATIONALIZE
        this.title = Messages.getString("JPictureSetExif.10"); //$NON-NLS-1$

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /* Initialize the top row panel */
        final JPanel toppanel = new JPanel();
        toppanel.setLayout(new BoxLayout(toppanel, BoxLayout.X_AXIS));

        addTopPanelContent(toppanel);

        this.add(toppanel);

        /* Add a spacer to relax the layout */
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        /* Initialize the bottom row panel */
        final JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.LINE_AXIS));

        addBottomPanelContent(bottompanel);

        this.add(bottompanel);

        fillViewWithModelInfo();

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
     * Writes the XMP Data into the available XMP data list
     */
    private void updateXMPData() {

    

    /* adds the xmp data if a picture enters the associated picture sets list */

        ArrayList<String> xmpKeywords = new ArrayList<String>();
        ArrayList<String> associatedXMPKeywords = new ArrayList<String>(Arrays.asList(this.getExifFilterKeywords()));

        for (PictureContainer pictureContainer : this.getPictureContainer()) {
            for (Picture picture : pictureContainer) {

                String[] xmpPictureKeyword = new String[0];

                if (picture.getExifParameter(ExifParameter.KEYWORDS) != null
                        && picture.getExifParameter(ExifParameter.KEYWORDS) instanceof String[]) {
                    xmpPictureKeyword = (String[]) picture.getExifParameter(ExifParameter.KEYWORDS);
                }

                for (int i = 0; i < xmpPictureKeyword.length; i++) {
                    if (!xmpKeywords.contains(xmpPictureKeyword[i])
                            && !associatedXMPKeywords.contains(xmpPictureKeyword[i])) {
                        xmpKeywords.add(xmpPictureKeyword[i]);
                    }
                }

            }
        }

        this.availableExifTags.removeElements(xmpKeywords.toArray());
        this.availableExifTags.addElements(xmpKeywords.toArray());
    

        /* removes the xmp data if a picture enters the associated picture sets list */
        final Object[] tempObject = this.availablePictureSets.getElements();
        final ArrayList<PictureContainer> availablePictureContainer = new ArrayList<PictureContainer>();

        for (final Object element : tempObject) {
            availablePictureContainer.add((PictureContainer) element);
        }

        ArrayList<String> removeXMPKeywords = new ArrayList<String>();

        for (PictureContainer pictureContainer : availablePictureContainer) {
            for (Picture picture : pictureContainer) {
                String[] xmpPictureKeyword = new String[0];

                if (picture.getExifParameter(ExifParameter.KEYWORDS) != null
                        && picture.getExifParameter(ExifParameter.KEYWORDS) instanceof String[]) {
                    xmpPictureKeyword = (String[]) picture.getExifParameter(ExifParameter.KEYWORDS);
                }

                for (int i = 0; i < xmpPictureKeyword.length; i++) {
                    //TODO When two identical XMP picture sets are added an error occurs
                   // if (!removeXMPKeywords.contains(xmpPictureKeyword[i]) && !xmpKeywords.contains(xmpPictureKeyword[i])) {
                    if (!removeXMPKeywords.contains(xmpPictureKeyword[i]) && !xmpKeywords.contains(xmpPictureKeyword[i])) {
                        removeXMPKeywords.add(xmpPictureKeyword[i]);
                    }
                }

            }
        }

        this.availableExifTags.removeElements(removeXMPKeywords.toArray());
        this.associatedExifTags.removeElements(removeXMPKeywords.toArray());

    }

    /**
     * This function returns the associated EXIF filter keywords of the current report
     * 
     * @return the associated EXIF filter keywords
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
    public ArrayList<PictureContainer> getPictureContainer() {
        final Object[] tempObject = this.associatedPictureSets.getElements();
        final ArrayList<PictureContainer> returnPictureContainer = new ArrayList<PictureContainer>();

        for (final Object element : tempObject) {
            returnPictureContainer.add((PictureContainer) element);
        }

        return returnPictureContainer;
    }

    @Override
    public boolean isDiagramDisplayable() {

        try {
            if (this.associatedPictureSets.getContents().size() > 0) {

                /* Check to see if there are images in the specified image set */
                for (PictureContainer pictureContainer : this.getPictureContainer()) {
                    int numberOfElements = 0;

                    for (@SuppressWarnings("unused")
                    Picture picture : pictureContainer) {
                        numberOfElements++;
                    }

                    if (numberOfElements == 0) {
                        this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                        // INTERNATIONALIZE
                        this.errorMessage
                                .setToolTipText(Messages.getString("JPictureSetExif.12")); //$NON-NLS-1$
                        this.showErrorIcon();
                        return false;
                    }
                }

                /* Check to see if there is at least one picture which contains valid EXIF data */
                ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>();

                for (JAbstractSinglePanel singlepanel : ReportHelper.getCurrentReportUtility().reportCompilation
                        .getRegisteredPanels()) {
                    if (singlepanel instanceof JParameters) {
                        JParameters parameters = (JParameters) singlepanel;
                        for (Axis axis : parameters.getAxes()) {
                            if (axis.getParameter() != null) {
                                exifParameters.add(axis.getParameter());
                            }
                        }
                    }
                }

                Logger logger = Logger.getLogger(this.getClass());
                logger.debug(Messages.getString("JPictureSetExif.13") //$NON-NLS-1$
                        + Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters));

                ArrayList<String> associatedXMPKeywords = new ArrayList<String>(Arrays.asList(this
                        .getExifFilterKeywords()));

                for (PictureContainer pictureContainer : this.getPictureContainer()) {
                    logger.debug(Messages.getString("JPictureSetExif.14") //$NON-NLS-1$
                            + Validator.getValidPictures(pictureContainer, exifParameters, associatedXMPKeywords)
                                    .size());

                    if (Validator.getValidPictures(pictureContainer, exifParameters, associatedXMPKeywords).size() == 0
                            && ReportHelper.getCurrentReport() != ReportHelper.Table) {
                        this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));

                        // INTERNATIONALIZE
                        this.errorMessage.setToolTipText(Messages.getString("JPictureSetExif.16") //$NON-NLS-1$
                                + Messages.getString("JPictureSetExif.17")); //$NON-NLS-1$

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

                /* Force the report utility to update */
                ReportHelper.getCurrentReportUtility().repaint();

                return true;

            } else {
                this.errorMessage.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                // INTERNATIONALIZE
                this.errorMessage.setToolTipText(Messages.getString("JPictureSetExif.19") //$NON-NLS-1$
                        + Messages.getString("JPictureSetExif.20")); //$NON-NLS-1$
                this.showErrorIcon();
                return false;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;

    };

    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

    /**
     * This method is responsible for revalidating the wilcoxon panel, since changes made to the picture
     * sets effect configuration of the wilcoxon panel
     */
    protected void revalidateWilcoxon() {
        if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {
            boolean enabled = false;

            if (this.associatedPictureSets.getContents().size() == 2) {
                enabled = true;
            }

            final ArrayList<JAbstractSinglePanel> registeredPanels = ReportHelper.getCurrentReportUtility().reportCompilation
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

            if (ReportHelper.getCurrentModel().getExifFilterKeywords() != null
                    && ReportHelper.getCurrentModel().getExifFilterKeywords().size() > 0) {
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

}
