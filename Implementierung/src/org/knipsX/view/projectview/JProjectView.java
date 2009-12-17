/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/* import things from our program */
import org.knipsX.controller.projectview.PictureSetContentListAddController;
import org.knipsX.controller.projectview.PictureSetListCopyController;
import org.knipsX.controller.projectview.PictureSetListCreateController;
import org.knipsX.controller.projectview.PictureSetContentListDeleteController;
import org.knipsX.controller.projectview.PictureSetListDeleteController;
import org.knipsX.controller.projectview.PictureSetContentListRefreshController;
import org.knipsX.controller.projectview.ProjectSaveController;
import org.knipsX.controller.projectview.ProjectSwitchController;
import org.knipsX.controller.projectview.ReportCreateController;
import org.knipsX.controller.projectview.ReportDeleteController;
import org.knipsX.controller.projectview.ReportOpenController;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.view.JAbstractView;

/**
 * Represents the view for an active project.
 * 
 * Sets all GUI Elements which are described in our Pflichtenheft.
 */
public class JProjectView<M extends ProjectModel> extends JAbstractView<M> {

	private static final long serialVersionUID = 6747507429332590686L;

	/* represents the panel for the view */
	private JPanel jContentPane = null;

	/* represents the panel for the project options */
	private JPanel jPanelProjectOptions = null;

	/* represents the panel for the project description */
	private JPanel jPanelProjectDescription = null;

	/* represents the panel for the picture set */
	private JPanel jPanelPictureSet = null;

	/* represents the panel for the picture set options */
	private JPanel jPanelPictureSetOptions = null;

	/* represents the panel for the picture set content */
	private JPanel jPanelPictureSetContent = null;

	/* represents the panel for the picture set content options */
	private JPanel jPanelPictureSetContentOptions = null;

	/* represents the panel for the image view of the active project */
	private JPanel jPanelPictureSetActive = null;

	/* represents the panel for the reports */
	private JPanel jPanelReport = null;

	/* represents the panel for the report options */
	private JPanel jPanelReportOptions = null;

	/* represents the panel for the exif data of an active image */
	private JPanel jPanelExif = null;

	/* adds scrollbars to the project description */
	private JScrollPane jScrollPaneProjectDescription = null;

	/* adds scrollbars to the exif data */
	private JScrollPane jScrollPaneExif = null;

	/* represents the project description field */
	private JEditorPane jEditorPaneProjectDescription = null;

	/* represents the project name field */
	private JTextField jTextFieldProjectName = null;

	/* represents the button which handles the project save action */
	private JButton jButtonProjectSave = null;

	/* represents the button which handles the project change action */
	private JButton jButtonProjectChange = null;

	/* represents the button which handles the picture set create action */
	private JButton jButtonPictureSetCreate = null;

	/* represents the button which handles the picture set delete action */
	private JButton jButtonPictureSetDelete = null;

	/* represents the button which handles the picture set copy action */
	private JButton jButtonPictureSetCopy = null;

	/* represents the button which handles the picture set content add action */
	private JButton jButtonPictureSetContentAdd = null;

	/* represents the button which handles the picture set content delete action */
	private JButton jButtonPictureSetContentDelete = null;

	/* represents the button which handles the picture set content refresh action */
	private JButton jButtonPictureSetContentRefresh = null;

	/* represents the button which handles the report create action */
	private JButton jButtonReportCreate = null;

	/* represents the button which handles the report open action */
	private JButton jButtonReportOpen = null;

	/* represents the button which handles the report delete action */
	private JButton jButtonReportDelete = null;

	/* represents the list which contains all picture sets of a project */
	private JList jListPictureSet = null;

	/*
	 * represents the list which contains all picture containers of an active picture set
	 */
	private JList jListPictureSetContent = null;

	/*
	 * represents the list which contains all images of an active picture container
	 */
	private JList jListPictureSetActive = null;

	/* represents the list which contains all reports of a project */
	private JList jListReport = null;

	/*
	 * represents the table which contains all exif parameters of an active image
	 */
	private JTable jTableExif = null;

	/**
	 * Creates a project view connected with an appropriate model.
	 */
	public JProjectView(M model) {
		super(model);

		/* renders the view */
		this.initialize();
	}

	/**
	 * This method initializes this.
	 * 
	 * @return void
	 */
	private void initialize() {

		/* show main panel */
		this.setContentPane(this.getJContentPane());

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
	 * This method initializes jButtonPictureSetContentAdd.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetContentAdd() {

		/* create if not set */
		if (this.jButtonPictureSetContentAdd == null) {

			/* create new button */
			this.jButtonPictureSetContentAdd = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetContentAdd.setText("Hinzufügen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonPictureSetContentAdd
					.addActionListener(new PictureSetContentListAddController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetContentAdd;
	}

	/**
	 * This method initializes jButtonPictureSetContentDelete.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetContentDelete() {

		/* create if not set */
		if (this.jButtonPictureSetContentDelete == null) {

			/* create new button */
			this.jButtonPictureSetContentDelete = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetContentDelete.setText("Entfernen");

			/* create an action listener (which knows the model and the selected contents) to the button */
			this.jButtonPictureSetContentDelete
					.addActionListener(new PictureSetContentListDeleteController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetContentDelete;
	}

	/**
	 * This method initializes jButtonPictureSetContentRefresh.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetContentRefresh() {

		/* create if not set */
		if (this.jButtonPictureSetContentRefresh == null) {

			/* create new button */
			this.jButtonPictureSetContentRefresh = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetContentRefresh.setText("Aktualisieren");

			/* create an action listener (which knows the model) to the button */
			this.jButtonPictureSetContentRefresh
					.addActionListener(new PictureSetContentListRefreshController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetContentRefresh;
	}

	/**
	 * This method initializes jButtonPictureSetCopy.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetCopy() {

		/* create if not set */
		if (this.jButtonPictureSetCopy == null) {

			/* create new button */
			this.jButtonPictureSetCopy = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetCopy.setText("Kopieren");

			/* create an action listener (which knows the model) to the button */
			this.jButtonPictureSetCopy.addActionListener(new PictureSetListCopyController<M, JProjectView<M>>(
					this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetCopy;
	}

	/**
	 * This method initializes jButtonPictureSetCreate.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetCreate() {

		/* create if not set */
		if (this.jButtonPictureSetCreate == null) {

			/* create new button */
			this.jButtonPictureSetCreate = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetCreate.setText("Erstellen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonPictureSetCreate.addActionListener(new PictureSetListCreateController<M, JProjectView<M>>(
					this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetCreate;
	}

	/**
	 * This method initializes jButtonPictureSetDelete.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonPictureSetDelete() {

		/* create if not set */
		if (this.jButtonPictureSetDelete == null) {

			/* create new button */
			this.jButtonPictureSetDelete = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonPictureSetDelete.setText("Entfernen");

			/* create an action listener (which knows the model and the selected picture sets) to the button */
			this.jButtonPictureSetDelete.addActionListener(new PictureSetListDeleteController<M, JProjectView<M>>(
					this.model, this));
		}

		/* return the button */
		return this.jButtonPictureSetDelete;
	}

	/**
	 * This method initializes jButtonProjectChange.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonProjectChange() {

		/* create if not set */
		if (this.jButtonProjectChange == null) {

			/* create new button */
			this.jButtonProjectChange = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonProjectChange.setText("Wechseln");

			/* create an action listener (which knows the model) to the button */
			this.jButtonProjectChange.addActionListener(new ProjectSwitchController<M, JProjectView<M>>(this.model,
					this));
		}

		/* return the button */
		return this.jButtonProjectChange;
	}

	/**
	 * This method initializes jButtonProjectSave.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonProjectSave() {

		/* create if not set */
		if (this.jButtonProjectSave == null) {

			/* create new button */
			this.jButtonProjectSave = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonProjectSave.setText("Speichern");

			/* create an action listener (which knows the model) to the button */
			this.jButtonProjectSave.addActionListener(new ProjectSaveController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonProjectSave;
	}

	/**
	 * This method initializes jButtonReportCreate.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonReportCreate() {

		/* create if not set */
		if (this.jButtonReportCreate == null) {

			/* create new button */
			this.jButtonReportCreate = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonReportCreate.setText("Erstellen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonReportCreate
					.addActionListener(new ReportCreateController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonReportCreate;
	}

	/**
	 * This method initializes jButtonReportOpen.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonReportOpen() {

		/* create if not set */
		if (this.jButtonReportOpen == null) {

			/* create new button */
			this.jButtonReportOpen = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonReportOpen.setText("Öffnen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonReportOpen.addActionListener(new ReportOpenController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonReportOpen;
	}

	/**
	 * This method initializes jButtonReportDelete.
	 * 
	 * @return the button.
	 */
	private JButton getJButtonReportDelete() {

		/* create if not set */
		if (this.jButtonReportDelete == null) {

			/* create new button */
			this.jButtonReportDelete = new JButton();

			/* set the text of the button */
			/* TODO change to internationalisation */
			this.jButtonReportDelete.setText("Entfernen");

			/* create an action listener (which knows the model) to the button */
			this.jButtonReportDelete
					.addActionListener(new ReportDeleteController<M, JProjectView<M>>(this.model, this));
		}

		/* return the button */
		return this.jButtonReportDelete;
	}

	/**
	 * This method initializes jContentPane.
	 * 
	 * @return the button.
	 */
	private JPanel getJContentPane() {

		/* create if not set */
		if (this.jContentPane == null) {

			/* create new panel */
			this.jContentPane = new JPanel();

			/* create new layout for this panel */
			final GroupLayout jContentPaneLayout = new GroupLayout(this.jContentPane);

			/* set the horizontal assignment */
			jContentPaneLayout.setHorizontalGroup(jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(
							jContentPaneLayout.createSequentialGroup().addGroup(
									jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
											.addComponent(this.getJPanelProjectOptions(),
													GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272,
													Short.MAX_VALUE).addComponent(this.getJPanelProjectDescription(),
													GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272,
													Short.MAX_VALUE).addComponent(this.getJPanelPictureSet(),
													GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272,
													Short.MAX_VALUE).addComponent(this.getJPanelPictureSetContent(),
													GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272,
													Short.MAX_VALUE)).addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED).addComponent(
									this.getJPanelPictureSetActive(), GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(6, 6, 6).addGroup(
									jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
											this.getJPanelReport(), GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
											.addComponent(this.getJPanelExif(), 0, 240, Short.MAX_VALUE)).addGap(0, 0,
									0)));

			/* set the vertical assignment */
			jContentPaneLayout.setVerticalGroup(jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(
							GroupLayout.Alignment.TRAILING,
							jContentPaneLayout.createSequentialGroup().addComponent(this.getJPanelProjectOptions(),
									GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE).addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED).addComponent(
									this.getJPanelProjectDescription(), GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED).addComponent(this.getJPanelPictureSet(),
									GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(
											this.getJPanelPictureSetContent(), GroupLayout.DEFAULT_SIZE,
											GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(
							jContentPaneLayout.createSequentialGroup().addComponent(this.getJPanelReport(),
									GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(
											this.getJPanelExif(), GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
					.addComponent(this.getJPanelPictureSetActive(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE));

			/* set the layout to the panel */
			this.jContentPane.setLayout(jContentPaneLayout);

			/* add the panel for the project options */
			this.jContentPane.add(this.getJPanelProjectOptions(), null);

			/* add the panel for the project description */
			this.jContentPane.add(this.getJPanelProjectDescription(), null);

			/* add the panel for the picture sets of a project */
			this.jContentPane.add(this.getJPanelPictureSet(), null);

			/* add the panel for the content of an active picture set */
			this.jContentPane.add(this.getJPanelPictureSetContent(), null);

			/* add the panel for the images of an active picture container */
			this.jContentPane.add(this.getJPanelPictureSetActive(), null);

			/* add the panel for the reports of a project */
			this.jContentPane.add(this.getJPanelReport(), null);

			/* add the panel for the exif parameters of an active project */
			this.jContentPane.add(this.getJPanelExif(), null);
		}

		/* return the panel */
		return this.jContentPane;
	}

	/**
	 * This method initializes jEditorPaneProjectDescription.
	 * 
	 * @return the editor pane.
	 */
	private JEditorPane getJEditorPaneProjectDescription() {

		/* create if not set */
		if (this.jEditorPaneProjectDescription == null) {

			/* cretae a new editor pane for the project description */
			this.jEditorPaneProjectDescription = new JEditorPane();
		}

		/* return the editor pane */
		return this.jEditorPaneProjectDescription;
	}

	/**
	 * This method initializes jListPictureSet.
	 * 
	 * @return the list.
	 */
	private JList getJListPictureSet() {

		/* create if not set */
		if (this.jListPictureSet == null) {

			/* creates a new list with options */
			/* TODO method for this list content */
			this.jListPictureSet = new JList(this.model.getPictureSets());

			/* allow to select only one row at once */
			this.jListPictureSet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			/*
			 * set ordering layout to vertical (see java api for more information)
			 */
			this.jListPictureSet.setLayoutOrientation(JList.VERTICAL);

			this.jListPictureSet.setCellRenderer(new MyPictureSetListCellRenderer());
		}

		/* return the list */
		return this.jListPictureSet;
	}

	/**
	 * This method initializes jListPictureSetActive.
	 * 
	 * @return the list.
	 */
	private JList getJListPictureSetActive() {

		/* create if not set */
		if (this.jListPictureSetActive == null) {

			/* creates a new list with options */
			/* TODO method for this list content */
			this.jListPictureSetActive = new JList(this.model.getAllPictures());

			/* allow to select only one row at once */
			this.jListPictureSetActive.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			/*
			 * set ordering layout to vertical (see java api for more information)
			 */
			this.jListPictureSetActive.setLayoutOrientation(JList.VERTICAL);

			this.jListPictureSetActive.setCellRenderer(new MyPictureListCellRenderer());

		}

		/* return the list */
		return this.jListPictureSetActive;
	}

	/**
	 * This method initializes jListPictureSetContent.
	 * 
	 * @return the list.
	 */
	private JList getJListPictureSetContent() {

		/* create if not set */
		if (this.jListPictureSetContent == null) {

			List<PictureContainer> list = new ArrayList<PictureContainer>();

			PictureSet pictureSet = (PictureSet) this.model.getPictureSets()[0];

			for (PictureSet element : this.model.getPictureSetsOfAPictureSet(pictureSet)) {
				list.add(element);
			}

			for (Directory element : this.model.getDirectoriesOfAPictureSet(pictureSet)) {
				list.add(element);
			}

			for (Picture element : this.model.getPicturesOfAPictureSet(pictureSet)) {
				list.add(element);
			}

			/* creates a new list with options */
			/* TODO method for this list content */
			this.jListPictureSetContent = new JList(list.toArray());

			/* allow to select only one row at once */
			this.jListPictureSetContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			/*
			 * set ordering layout to vertical (see java api for more information)
			 */
			this.jListPictureSetContent.setLayoutOrientation(JList.VERTICAL);

			this.jListPictureSetContent.setCellRenderer(new MyPictureSetContentListCellRenderer());
		}

		/* return the list */
		return this.jListPictureSetContent;
	}

	/**
	 * This method initializes jListReport.
	 * 
	 * @return the list.
	 */
	private JList getJListReport() {

		/* create if not set */
		if (this.jListReport == null) {

			/* creates a new list with options */
			/* TODO method for this list content */
			this.jListReport = new JList(this.model.getReports());

			/* allow to select only one row at once */
			this.jListReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			/*
			 * set ordering layout to vertical (see java api for more information)
			 */
			this.jListReport.setLayoutOrientation(JList.VERTICAL);

			this.jListReport.setCellRenderer(new MyReportListCellRenderer());
		}

		/* return the list */
		return this.jListReport;
	}

	/**
	 * This method initializes jPanelExif.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelExif() {

		/* create if not set */
		if (this.jPanelExif == null) {

			/* create new panel */
			this.jPanelExif = new JPanel();

			/* create new layout for this panel */
			final GroupLayout jPanelExifLayout = new GroupLayout(this.jPanelExif);

			/* set the horizontal assignment */
			jPanelExifLayout.setHorizontalGroup(jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGap(0, 228, Short.MAX_VALUE).addGroup(
							jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
									GroupLayout.Alignment.TRAILING,
									jPanelExifLayout.createSequentialGroup().addContainerGap().addComponent(
											this.getJScrollPaneExif(), GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
											.addContainerGap())));

			/* set the vertical assignment */
			jPanelExifLayout.setVerticalGroup(jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGap(0, 222, Short.MAX_VALUE).addGroup(
							jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
									jPanelExifLayout.createSequentialGroup().addContainerGap().addComponent(
											this.getJScrollPaneExif(), GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
											.addContainerGap())));

			/* set the layout to the panel */
			this.jPanelExif.setLayout(jPanelExifLayout);

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory
					.createTitledBorder(BorderFactory.createEmptyBorder(), "Exif-Daten");
			this.jPanelExif.setBorder(title);

			/* add the scroll pane for the exif parameters */
			this.jPanelExif.add(this.getJScrollPaneExif());

			/* set minimum size of the panel */
			this.jPanelReport.setMinimumSize(new Dimension(250, 245));

			/* set preferred size of the panel */
			this.jPanelReport.setPreferredSize(new Dimension(250, 245));
		}

		/* return the panel */
		return this.jPanelExif;
	}

	/**
	 * This method initializes jPanelPictureSet.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelPictureSet() {

		/* create if not set */
		if (this.jPanelPictureSet == null) {

			/* create new panel */
			this.jPanelPictureSet = new JPanel();

			/* set the layout to the panel */
			this.jPanelPictureSet.setLayout(new BorderLayout());

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory
					.createTitledBorder(BorderFactory.createEmptyBorder(), "Bildmengen");
			this.jPanelPictureSet.setBorder(title);

			/* add a list for picture sets */
			this.jPanelPictureSet.add(this.getJListPictureSet(), BorderLayout.NORTH);

			/* add a pane for picture set options */
			this.jPanelPictureSet.add(this.getJPanelPictureSetOptions(), BorderLayout.CENTER);

			/* set minimum size of the panel */
			this.jPanelPictureSet.setMinimumSize(new Dimension(250, 135));

			/* set preferred size of the panel */
			this.jPanelPictureSet.setPreferredSize(new Dimension(250, 135));
		}

		/* return the panel */
		return this.jPanelPictureSet;
	}

	/**
	 * This method initializes jPanelPictureSetActive.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelPictureSetActive() {

		/* create if not set */
		if (this.jPanelPictureSetActive == null) {

			/* create new panel */
			this.jPanelPictureSetActive = new JPanel();

			/* create new layout for this panel */
			final GroupLayout jPanelPictureSetActiveLayout = new GroupLayout(this.jPanelPictureSetActive);

			/* set the horizontal assignment */
			jPanelPictureSetActiveLayout.setHorizontalGroup(jPanelPictureSetActiveLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					jPanelPictureSetActiveLayout.createSequentialGroup().addContainerGap().addComponent(
							this.getJListPictureSetActive(), GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
							.addContainerGap()));

			/* set the vertical assignment */
			jPanelPictureSetActiveLayout.setVerticalGroup(jPanelPictureSetActiveLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					jPanelPictureSetActiveLayout.createSequentialGroup().addContainerGap().addComponent(
							this.getJListPictureSetActive(), GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
							.addContainerGap()));

			/* set the layout to the panel */
			this.jPanelPictureSetActive.setLayout(jPanelPictureSetActiveLayout);

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Bilder:");
			this.jPanelPictureSetActive.setBorder(title);

			/* add a list with images of an active picture container */
			this.jPanelPictureSetActive.add(this.getJListPictureSetActive());

			/* set minimum size of the panel */
			this.jPanelPictureSetActive.setMinimumSize(new Dimension(220, 500));

			/* set preferred size of the panel */
			this.jPanelPictureSetActive.setPreferredSize(new Dimension(220, 500));
		}

		/* return the panel */
		return this.jPanelPictureSetActive;
	}

	/**
	 * This method initializes jPanelPictureSetContent.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelPictureSetContent() {

		/* create if not set */
		if (this.jPanelPictureSetContent == null) {

			/* create new panel */
			this.jPanelPictureSetContent = new JPanel();

			/* set the layout to the panel */
			this.jPanelPictureSetContent.setLayout(new BorderLayout());

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Inhalt");
			this.jPanelPictureSetContent.setBorder(title);

			/* add a list with contents of a picture set */
			this.jPanelPictureSetContent.add(this.getJListPictureSetContent(), BorderLayout.NORTH);

			/* add a panel with options for the picture set contents */
			this.jPanelPictureSetContent.add(this.getJPanelPictureSetContentOptions(), BorderLayout.CENTER);

			/* set minimum size of the panel */
			this.jPanelPictureSetContent.setMinimumSize(new Dimension(250, 135));

			/* set preferred size of the panel */
			this.jPanelPictureSetContent.setPreferredSize(new Dimension(250, 135));
		}

		/* return the panel */
		return this.jPanelPictureSetContent;
	}

	/**
	 * This method initializes jPanelPictureSetContentOptions.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelPictureSetContentOptions() {

		/* create if not set */
		if (this.jPanelPictureSetContentOptions == null) {

			/* create new panel */
			this.jPanelPictureSetContentOptions = new JPanel();

			/* set the layout to the panel */
			this.jPanelPictureSetContentOptions.setLayout(new FlowLayout());

			/* add the button for adding a picture set content */
			this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentAdd(), null);

			/* add the button for deleting a picture set content */
			this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentDelete(), null);

			/* add the button for refreshing a picture set content */
			this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentRefresh(), null);
		}

		/* return the panel */
		return this.jPanelPictureSetContentOptions;
	}

	/**
	 * This method initializes jPanelPictureSetOptions.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelPictureSetOptions() {

		/* create if not set */
		if (this.jPanelPictureSetOptions == null) {

			/* create new panel */
			this.jPanelPictureSetOptions = new JPanel();

			/* set the layout to the panel */
			this.jPanelPictureSetOptions.setLayout(new FlowLayout());

			/* add the button for creating a picture set */
			this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCreate(), null);

			/* add the button for deleting a picture set */
			this.jPanelPictureSetOptions.add(this.getJButtonPictureSetDelete(), null);

			/* add the button for copying a picture set */
			this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCopy(), null);
		}

		/* return the panel */
		return this.jPanelPictureSetOptions;
	}

	/**
	 * This method initializes jPanelProjectDescription.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelProjectDescription() {

		/* create if not set */
		if (this.jPanelProjectDescription == null) {

			/* create new panel */
			this.jPanelProjectDescription = new JPanel();

			/* create new layout for this panel */
			final GroupLayout jPanelProjectDescriptionLayout = new GroupLayout(this.jPanelProjectDescription);

			/* set the horizontal assignment */
			jPanelProjectDescriptionLayout.setHorizontalGroup(jPanelProjectDescriptionLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					GroupLayout.Alignment.TRAILING,
					jPanelProjectDescriptionLayout.createSequentialGroup().addContainerGap().addComponent(
							this.getJScrollPaneProjectDescription(), GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addContainerGap()));

			/* set the vertical assignment */
			jPanelProjectDescriptionLayout.setVerticalGroup(jPanelProjectDescriptionLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					jPanelProjectDescriptionLayout.createSequentialGroup().addContainerGap().addComponent(
							this.getJScrollPaneProjectDescription(), GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
							.addContainerGap()));

			/* set the layout to the panel */
			this.jPanelProjectDescription.setLayout(jPanelProjectDescriptionLayout);

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
					"Projektbeschreibung");
			this.jPanelProjectDescription.setBorder(title);

			/* add scrollbars for the project description */
			this.jPanelProjectDescription.add(this.getJScrollPaneProjectDescription());

			/* set minimum size of the panel */
			this.jPanelProjectDescription.setMinimumSize(new Dimension(250, 135));

			/* set preferred size of the panel */
			this.jPanelProjectDescription.setPreferredSize(new Dimension(250, 135));
		}

		/* return the panel */
		return this.jPanelProjectDescription;
	}

	/**
	 * This method initializes jPanelProjectOptions.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelProjectOptions() {

		/* create if not set */
		if (this.jPanelProjectOptions == null) {

			/* create new panel */
			this.jPanelProjectOptions = new JPanel();

			/* create new layout for this panel */
			final GroupLayout jPanelProjectOptionsLayout = new GroupLayout(this.jPanelProjectOptions);

			/* set the horizontal assignment */
			jPanelProjectOptionsLayout.setHorizontalGroup(jPanelProjectOptionsLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					GroupLayout.Alignment.TRAILING,
					jPanelProjectOptionsLayout.createSequentialGroup().addContainerGap().addGroup(
							jPanelProjectOptionsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(this.getJTextFieldProjectName(), GroupLayout.DEFAULT_SIZE, 240,
											Short.MAX_VALUE).addGroup(
											jPanelProjectOptionsLayout.createSequentialGroup().addComponent(
													this.getJButtonProjectChange()).addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED).addComponent(
													this.getJButtonProjectSave()))).addContainerGap()));

			/* set the vertical assignment */
			jPanelProjectOptionsLayout.setVerticalGroup(jPanelProjectOptionsLayout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(
					jPanelProjectOptionsLayout.createSequentialGroup().addGroup(
							jPanelProjectOptionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(this.getJButtonProjectSave()).addComponent(
											this.getJButtonProjectChange())).addPreferredGap(
							LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE).addComponent(
							this.getJTextFieldProjectName(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE)));

			/* set the layout to the panel */
			this.jPanelProjectOptions.setLayout(jPanelProjectOptionsLayout);

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Projekt");
			this.jPanelProjectOptions.setBorder(title);

			/* add the textfield with the project name */
			this.jPanelProjectOptions.add(this.getJTextFieldProjectName());

			/* add the button for the project save action */
			this.jPanelProjectOptions.add(this.getJButtonProjectSave());

			/* add the button for the project change action */
			this.jPanelProjectOptions.add(this.getJButtonProjectChange());

			/* set minimum size of the panel */
			this.jPanelProjectOptions.setMinimumSize(new Dimension(250, 60));

			/* set preferred size of the panel */
			this.jPanelProjectOptions.setPreferredSize(new Dimension(250, 60));
		}

		/* return the panel */
		return this.jPanelProjectOptions;
	}

	/**
	 * This method initializes jPanelReport.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelReport() {

		/* create if not set */
		if (this.jPanelReport == null) {

			/* create new panel */
			this.jPanelReport = new JPanel();

			/* set the layout to the panel */
			this.jPanelReport.setLayout(new BorderLayout());

			/* add a border to the panel */
			/* TODO change to internationalisation */
			final TitledBorder title = BorderFactory
					.createTitledBorder(BorderFactory.createEmptyBorder(), "Auswertung");
			this.jPanelReport.setBorder(title);

			/* add the list with the reports for the project */
			this.jPanelReport.add(this.getJListReport(), BorderLayout.NORTH);

			/* add the list with the reportoptions for the project */
			this.jPanelReport.add(this.getJPanelReportOptions(), BorderLayout.CENTER);

			/* set minimum size of the panel */
			this.jPanelReport.setMinimumSize(new Dimension(250, 245));

			/* set preferred size of the panel */
			this.jPanelReport.setPreferredSize(new Dimension(250, 245));
		}

		/* return the panel */
		return this.jPanelReport;
	}

	/**
	 * This method initializes jPanelPictureSetContentOptions.
	 * 
	 * @return the panel.
	 */
	private JPanel getJPanelReportOptions() {

		/* create if not set */
		if (this.jPanelReportOptions == null) {

			/* create new panel */
			this.jPanelReportOptions = new JPanel();

			/* set the layout to the panel */
			this.jPanelReportOptions.setLayout(new FlowLayout());

			/* add the button for creating a report */
			this.jPanelReportOptions.add(this.getJButtonReportCreate(), null);

			/* add the button for open a report */
			this.jPanelReportOptions.add(this.getJButtonReportOpen(), null);

			/* add the button for deleting a report */
			this.jPanelReportOptions.add(this.getJButtonReportDelete(), null);
		}

		/* return the panel */
		return this.jPanelReportOptions;
	}

	/**
	 * This method initializes jScrollPaneExif.
	 * 
	 * @return the scrollpane.
	 */
	private JScrollPane getJScrollPaneExif() {

		/* create if not set */
		if (this.jScrollPaneExif == null) {

			/* create new scrollpane */
			this.jScrollPaneExif = new JScrollPane();

			/* add the exif parameter table to the pane */
			this.jScrollPaneExif.setViewportView(this.getJTableExif());
		}

		/* return the scrollpane */
		return this.jScrollPaneExif;
	}

	/**
	 * This method initializes jScrollPaneProjectDescription.
	 * 
	 * @return javax.swing.JScrollPane the scrollpane.
	 */
	private JScrollPane getJScrollPaneProjectDescription() {

		/* create if not set */
		if (this.jScrollPaneProjectDescription == null) {

			/* create new scrollpane */
			this.jScrollPaneProjectDescription = new JScrollPane();

			/* add the project description to the pane */
			this.jScrollPaneProjectDescription.setViewportView(this.getJEditorPaneProjectDescription());
		}

		/* return the scrollpane */
		return this.jScrollPaneProjectDescription;
	}

	/**
	 * This method initializes jTableExif.
	 * 
	 * @return javax.swing.JTable the table.
	 */
	private JTable getJTableExif() {

		/* create if not set */
		if (this.jTableExif == null) {

			/* TODO set from model */
			final String[] columnNames = { "Parameter", "Wert" };
			Object[][] data = ((ProjectModel) model).getExifParameter();

			/* create new table for the exif parameters of an active image */
			this.jTableExif = new JTable(data, columnNames);
			TableColumn para = this.jTableExif.getColumnModel().getColumn(0);
			TableColumn value = this.jTableExif.getColumnModel().getColumn(1);
			para.setCellRenderer(new MyTableCellRenderer());
			value.setCellRenderer(new MyTableCellRenderer());
		}

		/* return the table */
		return this.jTableExif;
	}

	/**
	 * This method initializes jTextFieldProjectDescription.
	 * 
	 * @return javax.swing.JTextField the textfield.
	 */
	private JTextField getJTextFieldProjectName() {

		/* create if not set */
		if (this.jTextFieldProjectName == null) {

			/* create new textfield */
			this.jTextFieldProjectName = new JTextField();
			this.jTextFieldProjectName.setText(((ProjectModel) model).getProjectName());
		}
		return this.jTextFieldProjectName;
	}

	/**
	 * This method checks which entries are marked in the PictureSetContent list and gives them back as indices.
	 * 
	 * @return array of indices.
	 */
	public int[] getSelectedPictureSetContents() {
		return this.jListPictureSetContent.getSelectedIndices();
	}

	/**
	 * This method checks which entries are marked in the PictureSet list and gives them back as indices.
	 * 
	 * @return array of indices.
	 */
	public int[] getSelectedPictureSets() {
		return this.jListPictureSet.getSelectedIndices();
	}

	/**
	 * This method checks which entries are marked in the Report list and gives them back as indices.
	 * 
	 * @return array of indices.
	 */
	public int[] getSelectedReports() {
		return this.jListReport.getSelectedIndices();
	}

	/**
	 * This method checks which entries are marked in the Picture list of a PictureSet and gives them back as indices.
	 * 
	 * @return array of indices.
	 */
	public int[] getSelectedPictures() {
		return this.jListPictureSet.getSelectedIndices();
	}

	/**
	 * Returns the project name.
	 * 
	 * @return the project name.
	 */
	public String getProjectName() {
		return new String(jTextFieldProjectName.getText());
	}

	/**
	 * Returns the project description.
	 * 
	 * @return the project description.
	 */
	public String getProjectDescription() {
		return new String(jEditorPaneProjectDescription.getText());
	}

	@Override
	public void update(final Observable o, final Object arg) {

		/* cast to model */
		final ProjectModel model = (ProjectModel) o;

		/* set the title for the view */
		/* TODO change to internationalisation */
		this.setTitle("Projektansicht für " + model.getProjectName());

		this.jEditorPaneProjectDescription.setText(model.getProjectDescription());

		this.jListPictureSet.setListData(model.getPictureSets());

		/* refresh view */
		this.repaint();
	}
}

/**
 * This nested class renders a picture set cell for the picture set list.
 */
class MyPictureSetListCellRenderer implements ListCellRenderer {

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

		/* if the selected item is a "PictureSet" -> set the name */
		if (value instanceof PictureSet) {
			final PictureSet pictureSet = (PictureSet) value;
			theText = pictureSet.getName();
		}
		renderer.setText(theText);

		/* return the label */
		return renderer;
	}
}

/**
 * This nested class renders a picture set content cell for the picture set content list of an active project.
 */
class MyPictureSetContentListCellRenderer implements ListCellRenderer {

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

		/* the color for the cell text */
		Color theColor = null;

		/* generate the label which represents the cell */
		final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);

		/* if the selected item is a "Picture" -> set the name */
		/* TODO different colors */
		if (value instanceof Picture) {
			final PictureContainer pictureContainer = (PictureContainer) value;
			theText = pictureContainer.getName();
			theColor = Color.GREEN;
		} else if (value instanceof Directory) {
			final PictureContainer pictureContainer = (PictureContainer) value;
			theText = pictureContainer.getName();
			theColor = Color.RED;
		} else if (value instanceof PictureSet) {
			final PictureContainer pictureContainer = (PictureContainer) value;
			theText = pictureContainer.getName();
			theColor = Color.BLUE;
		}
		renderer.setText(theText);
		renderer.setForeground(theColor);

		/* return the label */
		return renderer;
	}
}

/**
 * This nested class renders a picture cell for the picture list of an active picture container.
 */
class MyPictureListCellRenderer implements ListCellRenderer {

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

		/* if the selected item is a "Picture" -> set the name */
		if (value instanceof Picture) {
			final Picture picture = (Picture) value;
			theText = picture.getName();
		}
		renderer.setText(theText);

		/* return the label */
		return renderer;
	}
}

/**
 * This nested class renders a report cell for the report list of an active project.
 */
class MyReportListCellRenderer implements ListCellRenderer {

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

		/* if the selected item is a "ReportEntry" -> set the name */
		if (value instanceof AbstractReportModel) {
			final AbstractReportModel reportEntry = (AbstractReportModel) value;
			theText = reportEntry.getReportName();
		}
		renderer.setText(theText);

		/* return the label */
		return renderer;
	}
}

/**
 * This nested class renders a table cell for the table which shows the exif parameters of an active picture.
 */
class MyTableCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

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
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int rowIndex, int vColIndex) {
		// 'value' is value contained in the cell located at
		// (rowIndex, vColIndex)

		if (isSelected) {
			// cell (and perhaps other cells) are selected
		}

		if (hasFocus) {
			// this cell is the anchor and the table has the focus
		}

		// Configure the component with the specified value
		setText(((String) value));

		// Set tool tip if desired
		setToolTipText((String) value);

		// Since the renderer is a component, return itself
		return this;
	}
}