/**
 * This package is the root of all views of knipsX.
 */
package org.knipsX.view;

/* import things from the java sdk */
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.model.AbstractModel;

public abstract class JAbstractView extends JFrame implements Observer {

    /** Only for serialisation */
    private static final long serialVersionUID = -5981384605515636896L;

    /** define an abstract model */
    protected AbstractModel model;

    /**
     * Creates a new view which is connected to a model.
     * 
     * @param abstractModel
     *            the model which the view should connect to.
     */
    public JAbstractView(final AbstractModel abstractModel) {

	/* calls the JFrame constructor */
	super();

	/* set the model */
	this.model = abstractModel;

	/* register to model */
	this.model.addObserver(this);
    }

    /**
     * Have to be implemented by an subclass.
     * 
     * It is called every time a model is updatet. It decides (based on the program state) what the view shows.
     * 
     * @param model
     *            this is a reference to the model which a view observ.
     * @param argument
     *            this is a reference of an argument passed to the model.
     * 
     * @see #java.util.Observer.update(Observable, Object)
     */
    public abstract void update(final Observable model, final Object argument);

    /**
     * Has the same behaviour as a normal dispose(), but also disconnects the view from a model.
     */
    @Override
    public void dispose() {

	/* calls the normal dispose */
	super.dispose();

	/* deregister from the model */
	this.model.deleteObserver(this);
    }
}