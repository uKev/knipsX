package org.knipsX.view.reportmanagement;

import java.awt.Image;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.knipsX.utils.Resource;

/**
 * This class represents a single panel in a report configuration.
 * 
 * @author David Kaufman
 * 
 */
public abstract class JAbstractSinglePanel extends JComponent {

    private static final long serialVersionUID = 1L;

    /**
     * The title which is registered with this panel.
     */
    protected String title;

    /**
     * The icon which is placed in every tab
     */
    protected ImageIcon icon = new ImageIcon();

    /**
     * The default constructor of the class which is invoked by every subclass
     */
    public JAbstractSinglePanel() {

        /* Draw an invisible border around the panel to make it more readable */
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Returns the title of the current panel
     * 
     * @return the title of the current panel
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the of the current panel
     * 
     * @return the icon
     */
    public ImageIcon getIcon() {
        return this.icon;
    }

    /**
     * Resets the current icon to an empty image
     */
    public void resetIcon() {
        if (this.icon != null) {
            byte[] someData = new byte[1];
            Image image = new ImageIcon(someData).getImage();
            this.icon.setImage(image);
            /* Force the report utility to update */
            ReportHelper.getCurrentReportUtility().repaint();
        }
    }

    /**
     * Specifies if the current panel has all the specified information to display the report
     * 
     * @return true if the current panel as the appropriate amount of information to be displayed
     *         otherwise it returns false
     */
    public abstract boolean isDiagramDisplayable();

    /**
     * Specifies if the current panel has all the specified information to save the report
     * 
     * @return true if the current panel as the appropriate amount of information to be save
     *         otherwise it returns false
     */
    public abstract boolean isDiagramSaveable();

    /**
     * Revalidates the report. It checks to see if the report is displayable and saveable
     */
    public void revalidateReport() {
        ReportHelper.getCurrentReportUtility().revalidateDisplayability();
        ReportHelper.getCurrentReportUtility().revalidateSaveability();
    }

    /**
     * Sets the current icon of the panel to a error icon
     */
    public void showErrorIcon() {
        try {
            this.icon.setImage(Resource.createImageIcon("../images/userwarning_small.png", null).getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /* Force the report utility to update */
        ReportHelper.getCurrentReportUtility().repaint();
    }

    /**
     * This method is responsible for filling the view with the information from the model
     */
    public abstract void fillViewWithModelInfo();

}
