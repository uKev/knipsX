/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.util.Observable;
import javax.swing.JFrame;

/* import things from our program */
import org.knipsX.controller.projectview.DeletePictureSetRefuseController;
import org.knipsX.controller.projectview.DeletePictureSetConfirmController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractDialog;

/**
 * Represents the view for a dialog which gives the user the possibility to save
 * a project.
 */
public class JPictureSetDelete extends JAbstractDialog {

	/** Only for serialisation */
	private static final long serialVersionUID = 1833755969083155368L;

	/* stores the indices of the reports which should be deleted */
	private final int[] toDelete;

	public JPictureSetDelete(final AbstractModel abstractModel,
			final int[] toDelete) {

		/* sets the model */
		super(abstractModel, JAbstractDialog.CONFIRM_REFUSE,
				JAbstractDialog.LABEL);

		/* set the indices of the reports which should be deleted */
		this.toDelete = toDelete;

		/* renders the view */
		this.initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		/* configure the view */
		this.configure();

		/* set standard close action */
		/* TODO We have to edit the close action! */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* change size to preferred size */
		this.pack();

		/* set location to the center of the screen */
		this.setLocationRelativeTo(null);

		/* show view */
		this.setVisible(true);
	}

	/**
	 * Configures the elements.
	 * 
	 */
	private void configure() {

		/* set the title for the view */
		/* TODO change to internationalisation */
		this.setTitle("Bildmenge entfernen");

		/* set button text */
		/* TODO change to internationalisation */
		this.jButtonConfirm.setText("Ok");

		/* create an action listener (which knows the model) to the button */
		this.jButtonConfirm
				.addActionListener(new DeletePictureSetConfirmController(
						this.model, this.toDelete));

		/* set button text */
		/* TODO change to internationalisation */
		this.jButtonRefuse.setText("Abbrechen");

		/* create an action listener (which knows the model) to the button */
		this.jButtonRefuse
				.addActionListener(new DeletePictureSetRefuseController(
						this.model));

		/* set label text */
		/* TODO change to internationalisation */
		this.jLabelText.setText("Möchten Sie : " + this.generateToDeleteText()
				+ " wirklich löschen?");
	}

	/* Generiert den Text, der im Panel angezeigt wird */
	private String generateToDeleteText() {
		String deleteText = "";
		for (int n = 0; n < this.toDelete.length; ++n) {
			deleteText += ""; // TODO((ProjectListModel)
								// this.model).getProjectList().get(this.toDelete[n]).getName()
								// +
			// ";";
		}
		return deleteText;
	}

	@Override
	public void update(final Observable o, final Object arg) {

		/* cast to model */
		final ProjectViewModel model = (ProjectViewModel) o;

		/* react to program state */
		/* TODO add a status and error panel! */
		if (model.getModelStatus() != ProjectViewModel.DELETEPICTURESET) {

			/* delete view */
			this.dispose();
		}
	}
}