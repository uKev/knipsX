package org.knipsX.view;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.knipsX.model.AbstractModel;

/**
 * This view is the top of our view structure. It manages a model, which is connected to it. The connection is
 * implemented through the observer pattern.
 * 
 * @author Kai Bouché
 * 
 * @param <M>
 *            you must set this to a model which is a child from the top of our model structure.
 */
public abstract class JAbstractView<M extends AbstractModel> extends JFrame implements Observer {

    private static final long serialVersionUID = -5981384605515636896L;

    protected M model;

    /**
     * Creates a new view which is connected to a model.
     * 
     * @param model
     *            the model which the view should connect to.
     * 
     */
    public JAbstractView(final M model) {

        /* calls the JFrame constructor */
        super();

        /* Note that some views don't ever have a associated model */

        if (model != null) {
            this.model = model;
            /* register to model */
            this.model.addObserver(this);
        }

        /* sets the knipsX icon image which is associated with every subclass */
        try {
            this.setIconImage(this.createImageIcon("../../images/appicon.png", null).getImage());
        } catch (final FileNotFoundException exception) {
            System.err.println(exception);
        }
    }

    /*
     * Returns an ImageIcon, or null if the path was invalid.
     * 
     * @param path
     *            the absolute or relative path to the image icon
     * 
     * @param description
     *            the description of the image icon
     * 
     * @return ImageIcon object
     * 
     * @throws FileNotFoundException
     *             if there's no image available at the given path.
     */
    private ImageIcon createImageIcon(final String path, final String description) throws FileNotFoundException {

        ImageIcon imageIcon = null;

        /* return the path, where knipsX is installed, connected with the relative path to the icon */
        final URL imgURL = this.getClass().getResource(path);

        if (imgURL == null) {
            throw new FileNotFoundException("[JAbstractView::createImageIcon()] - Couldn't find file: " + imgURL);
        } else {
            imageIcon = new ImageIcon(imgURL, description);
        }

        return imageIcon;
    }

    /**
     * Has the same behavior like the normal dispose(), but also disconnects the view from a model.
     */
    @Override
    public void dispose() {

        /* calls the normal dispose */
        super.dispose();

        /* unregister from the model */
        if (this.model != null) {
            /* Note that some views don't ever have a associated model */
            this.model.deleteObserver(this);
        }
    }

    /**
     * Has to be implemented by a subclass.
     * 
     * It is called every time a model is updated. It decides what the view has to show.
     * 
     * @param model
     *            this is a reference to the model which a view observe.
     * @param argument
     *            this is a reference of an argument passed to the model.
     * 
     * @see #java.util.Observer.update(Observable, Object)
     */
    public abstract void update(final Observable model, final Object argument);
}
