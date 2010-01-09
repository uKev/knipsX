/**
 * This package is the root of all views of knipsX.
 */
package org.knipsX.view;

/* import things from the java sdk */
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.model.AbstractModel;

public abstract class JAbstractView<M extends AbstractModel> extends JFrame implements Observer {

	/** Only for serialization */
	private static final long serialVersionUID = -5981384605515636896L;

	protected M model;

	/**
	 * Creates a new view which is connected to a model.
	 * 
	 * @param model
	 *            the model which the view should connect to.
	 */
	public JAbstractView(final M model) {

		/* calls the JFrame constructor */
		super();

		this.model = model;
		
		/* sets the icon image which is associated with every subclass*/
		this.setIconImage(createImageIcon("../../images/appicon.png", null).getImage());
		
		/* register to model */
		if (this.model != null) {
			this.model.addObserver(this);
		}
	}
	
	
	/**
	 * Has to be implemented by a subclass.
	 * 
	 * It is called every time a model is updated. It decides (based on the program state) what the view shows.
	 * 
	 * @param model
	 *            this is a reference to the model which a view observe.
	 * @param argument
	 *            this is a reference of an argument passed to the model.
	 * 
	 * @see #java.util.Observer.update(Observable, Object)
	 */
	public abstract void update(final Observable model, final Object argument);

	/**
	 * Has the same behavior as a normal dispose(), but also disconnects the view from a model.
	 */
	@Override
	public void dispose() {

		/* calls the normal dispose */
		super.dispose();

		/* unregister from the model */
		if(this.model != null) {
			this.model.deleteObserver(this);
		}
	}
	
    /**
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path The absolute or relative path to the image icon
     * @param description The description of the image icon
     * @return ImageIcon object
     */
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}