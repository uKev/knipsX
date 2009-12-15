package org.knipsX.view.reportmanagement;
import javax.swing.Icon;
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
	 * The icon which is registered with this panel.
	 */
	protected Icon icon;
	
	/**
	 * The tooltip which is registered with this panel.
	 */
	protected String tip;
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Icon getIcon() {
		return icon;
	}


	public void setIcon(Icon icon) {
		this.icon = icon;
	}


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}
