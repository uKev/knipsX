package org.knipsX.view.reportmanagement;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;


public abstract class AbstractSinglePanel extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected String title;
	protected Icon icon;
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
