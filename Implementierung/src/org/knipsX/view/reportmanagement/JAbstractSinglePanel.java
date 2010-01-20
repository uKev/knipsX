package org.knipsX.view.reportmanagement;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
	 * Specifies if the current panel has all the specified information to
	 * display the report
	 */
	public abstract boolean isDiagramDisplayable();
	
	
	/**
	 * Specifies if the current panel has all the specified information to
	 * display the report
	 */
	public abstract boolean isDiagramSaveable();
	
	
	public JAbstractSinglePanel() {
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}
	
	public void revalidateReport() {
		revalidateDisplayability();
		revalidateSaveability();
	}
	
	public void revalidateDisplayability() {
		ReportHelper.currentReportUtil.revalidateDisplayability();
	}
	
	public void revalidateSaveability() {
		ReportHelper.currentReportUtil.revalidateSaveability();
	}
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}



	
    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path The absolute or relative path to the image icon
     * @param description The description of the image icon
     * @return ImageIcon object
     */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
}
