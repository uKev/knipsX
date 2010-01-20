package org.knipsX.view.reportmanagement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

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
     * The default constructor of the class which is invoked by every subclass
     */
    public JAbstractSinglePanel() {
        
        /* Draw an invisible border around the panel to make it more readable*/
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path
     *            The absolute or relative path to the image icon
     * @param description
     *            The description of the image icon
     * @return ImageIcon object
     */
    protected ImageIcon createImageIcon(final String path, final String description) {
        final java.net.URL imgURL = this.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
     * Specifies if the current panel has all the specified information to display the report
     * 
     * @return true if the current panel as the appropriate amount of information to be displayed
     * otherwise it returns false
     */
    public abstract boolean isDiagramDisplayable();

    /**
     * Specifies if the current panel has all the specified information to save the report
     * 
     * @return true if the current panel as the appropriate amount of information to be save
     * otherwise it returns false
     */
    public abstract boolean isDiagramSaveable();

    /**
     * Revalidates the report. It checks to see if the report is displayable and saveable
     */
    public void revalidateReport() {
        ReportHelper.currentReportUtil.revalidateDisplayability();
        ReportHelper.currentReportUtil.revalidateSaveability();
    }



}
