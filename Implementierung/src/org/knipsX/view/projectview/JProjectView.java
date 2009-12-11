/**
 * This package is the root of all files regarding the "project view".
 */
package org.knipsX.view.projectview;

/* import things from the java sdk */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

/* import things from our program */
import org.knipsX.controller.projectview.AddToPictureSetContentController;
import org.knipsX.controller.projectview.CopyPictureSetController;
import org.knipsX.controller.projectview.CreatePictureSetController;
import org.knipsX.controller.projectview.DeleteFromPictureSetContentController;
import org.knipsX.controller.projectview.DeletePictureSetController;
import org.knipsX.controller.projectview.RefreshProjectViewController;
import org.knipsX.controller.projectview.SaveProjectController;
import org.knipsX.controller.projectview.SwitchProjectController;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JProjectView extends JAbstractView {

    /** Only for serialisation */
    private static final long serialVersionUID = 6747507429332590686L;
    
    /* represents the main pane for the view */
    private JPanel jContentPane = null;
    
    /* represents the main pane for the project options */
    private JPanel jPanelProjectOptions = null;
    
    /* represents the main pane for the project description */
    private JPanel jPanelProjectDescription = null;
    
    /* represents the main pane for the picture set */
    private JPanel jPanelPictureSet = null;
    
    /* represents the main pane for the picture set options */
    private JPanel jPanelPictureSetOptions = null;
    
    /* represents the main pane for the picture set content */
    private JPanel jPanelPictureSetContent = null;
    
    /* represents the main pane for the picture set content options */
    private JPanel jPanelPictureSetContentOptions = null;
    
    /* represents the main pane for the image view of the active project */
    private JPanel jPanelPictureSetActive = null;
    
    /* represents the main pane for the reports */
    private JPanel jPanelReport = null;
    
    /* represents the main pane for the exif data of an active image */
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

    /* represents the list which contains all picture sets of a project */
    private JList jListPictureSet = null;
    
    /* represents the list which contains all picture containers of an active picture set */
    private JList jListPictureSetContent = null;
    
    /* represents the list which contains all images of an active picture container */
    private JList jListPictureSetActive = null;
    
    /* represents the list which contains all reports of a project */
    private JList jListReport = null;
    
    /* represents the table which contains all exif parameters of an active image */
    private JTable jTableExif = null;

    /**
     * Creates a project view connected with an appropriate model.
     */
    public JProjectView(final AbstractModel model) {
	
	/* sets the model */
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
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetContentAdd() {
	
	/* create if not set */
	if (this.jButtonPictureSetContentAdd == null) {
	    
	    /* create new button */
	    this.jButtonPictureSetContentAdd = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetContentAdd.setText("Hinzufügen");
	    this.jButtonPictureSetContentAdd.addActionListener(new AddToPictureSetContentController(model));
	}
	
	/* return the button */
	return this.jButtonPictureSetContentAdd;
    }

    /**
     * This method initializes jButtonPictureSetContentDelete.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetContentDelete() {
	
	/* create if not set */
	if (this.jButtonPictureSetContentDelete == null) {
	    
	    /* create new button */
	    this.jButtonPictureSetContentDelete = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetContentDelete.setText("Entfernen");
	    this.jButtonPictureSetContentDelete.addActionListener(new DeleteFromPictureSetContentController (model));
	}
	return this.jButtonPictureSetContentDelete;
    }

    /**
     * This method initializes jButtonPictureSetContentRefresh.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetContentRefresh() {

	/* create if not set */
	if (this.jButtonPictureSetContentRefresh == null) {
	    this.jButtonPictureSetContentRefresh = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetContentRefresh.setText("Aktualisieren");
	    this.jButtonPictureSetContentRefresh.addActionListener(new RefreshProjectViewController(model));
	}
	return this.jButtonPictureSetContentRefresh;
    }

    /**
     * This method initializes jButtonPictureSetCopy.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetCopy() {

	/* create if not set */
	if (this.jButtonPictureSetCopy == null) {
	    this.jButtonPictureSetCopy = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetCopy.setText("Kopieren");
	    this.jButtonPictureSetCopy.addActionListener(new CopyPictureSetController(model));
	}
	return this.jButtonPictureSetCopy;
    }

    /**
     * This method initializes jButtonPictureSetCreate.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetCreate() {

	/* create if not set */
	if (this.jButtonPictureSetCreate == null) {
	    this.jButtonPictureSetCreate = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetCreate.setText("Erstellen");
	    this.jButtonPictureSetCreate.addActionListener(new CreatePictureSetController(model));
	}
	return this.jButtonPictureSetCreate;
    }

    /**
     * This method initializes jButtonPictureSetDelete.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonPictureSetDelete() {

	/* create if not set */
	if (this.jButtonPictureSetDelete == null) {
	    this.jButtonPictureSetDelete = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonPictureSetDelete.setText("Entfernen");
	    this.jButtonPictureSetDelete.addActionListener(new DeletePictureSetController(model, getSelectedIndicesFromPictureSetList()));
	}
	return this.jButtonPictureSetDelete;
    }

    /**
     * This method initializes jButtonProjectChange.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonProjectChange() {

	/* create if not set */
	if (this.jButtonProjectChange == null) {
	    this.jButtonProjectChange = new JButton();
	    
	    /* set the text of the button */
	    /* TODO change to internationalisation */
	    this.jButtonProjectChange.setText("Wechseln");
	    this.jButtonProjectChange.addActionListener(new SwitchProjectController(model));
	}
	return this.jButtonProjectChange;
    }

    /**
     * This method initializes jButtonProjectSave.
     * 
     * @return javax.swing.JButton the appropriate JButton.
     */
    private JButton getJButtonProjectSave() {

	/* create if not set */
	if (this.jButtonProjectSave == null) {
	    this.jButtonProjectSave = new JButton();
	    this.jButtonProjectSave.setText("Speichern");
	    this.jButtonProjectSave.addActionListener(new SaveProjectController(model));
	}
	return this.jButtonProjectSave;
    }

    /**
     * This method initializes jContentPane.
     * 
     * @return javax.swing.JPanel the pane.
     */
    private JPanel getJContentPane() {

	/* create if not set */
	if (this.jContentPane == null) {
	    this.jContentPane = new JPanel();

	    final GroupLayout jContentPaneLayout = new GroupLayout(this.jContentPane);

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

	    this.jContentPane.setLayout(jContentPaneLayout);

	    this.jContentPane.add(this.getJPanelProjectOptions(), null);
	    this.jContentPane.add(this.getJPanelProjectDescription(), null);
	    this.jContentPane.add(this.getJPanelPictureSet(), null);
	    this.jContentPane.add(this.getJPanelPictureSetContent(), null);
	    this.jContentPane.add(this.getJPanelPictureSetActive(), null);
	    this.jContentPane.add(this.getJPanelReport(), null);
	    this.jContentPane.add(this.getJPanelExif(), null);
	}
	return this.jContentPane;
    }

    /**
     * This method initializes jEditorPaneProjectDescription
     * 
     * @return javax.swing.JEditorPane the editor pane.
     */
    private JEditorPane getJEditorPaneProjectDescription() {

	/* create if not set */
	if (this.jEditorPaneProjectDescription == null) {
	    this.jEditorPaneProjectDescription = new JEditorPane();
	}
	return this.jEditorPaneProjectDescription;
    }

    /**
     * This method initializes jListPictureSet
     * 
     * @return javax.swing.JList the list.
     */
    private JList getJListPictureSet() {

	/* create if not set */
	if (this.jListPictureSet == null) {
	    this.jListPictureSet = new JList(new Object[] { "Apfel", "Kürbis", "Paprika", "Tomate" });
	    this.jListPictureSet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    this.jListPictureSet.setLayoutOrientation(JList.VERTICAL);
	}
	return this.jListPictureSet;
    }

    /**
     * This method initializes jListPictureSetActive.
     * 
     * @return javax.swing.JList the list.
     */
    private JList getJListPictureSetActive() {

	/* create if not set */
	if (this.jListPictureSetActive == null) {
	    this.jListPictureSetActive = new JList(new Object[] { "Apfel", "Kürbis", "Paprika", "Tomate" });
	    this.jListPictureSetActive.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    this.jListPictureSetActive.setLayoutOrientation(JList.VERTICAL);
	}
	return this.jListPictureSetActive;
    }

    /**
     * This method initializes jListPictureSetContent.
     * 
     * @return javax.swing.JList the list.
     */
    private JList getJListPictureSetContent() {

	/* create if not set */
	if (this.jListPictureSetContent == null) {
	    this.jListPictureSetContent = new JList(new Object[] { "Apfel", "Kürbis", "Paprika", "Tomate" });
	    this.jListPictureSetContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    this.jListPictureSetContent.setLayoutOrientation(JList.VERTICAL);
	}
	return this.jListPictureSetContent;
    }

    /**
     * This method initializes jListReport.
     * 
     * @return javax.swing.JList the list.
     */
    private JList getJListReport() {

	/* create if not set */
	if (this.jListReport == null) {
	    this.jListReport = new JList(new Object[] { "Apfel", "Kürbis", "Paprika", "Tomate" });
	    this.jListReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    this.jListReport.setLayoutOrientation(JList.VERTICAL);
	}
	return this.jListReport;
    }

    /**
     * This method initializes jPanelExif.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelExif() {

	/* create if not set */
	if (this.jPanelExif == null) {
	    this.jPanelExif = new JPanel();

	    final GroupLayout jPanelExifLayout = new GroupLayout(this.jPanelExif);

	    jPanelExifLayout.setHorizontalGroup(jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addGap(0, 228, Short.MAX_VALUE).addGroup(
			    jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				    GroupLayout.Alignment.TRAILING,
				    jPanelExifLayout.createSequentialGroup().addContainerGap().addComponent(
					    this.getJScrollPaneExif(), GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					    .addContainerGap())));
	    jPanelExifLayout.setVerticalGroup(jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addGap(0, 222, Short.MAX_VALUE).addGroup(
			    jPanelExifLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				    jPanelExifLayout.createSequentialGroup().addContainerGap().addComponent(
					    this.getJScrollPaneExif(), GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
					    .addContainerGap())));

	    this.jPanelExif.setLayout(jPanelExifLayout);

	    final TitledBorder title = BorderFactory
		    .createTitledBorder(BorderFactory.createEmptyBorder(), "Exif-Daten");
	    this.jPanelExif.setBorder(title);

	    this.jPanelExif.add(this.getJScrollPaneExif());

	    this.jPanelReport.setMinimumSize(new Dimension(250, 245));
	    this.jPanelReport.setPreferredSize(new Dimension(250, 245));
	}
	return this.jPanelExif;
    }

    /**
     * This method initializes jPanelPictureSet.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelPictureSet() {

	/* create if not set */
	if (this.jPanelPictureSet == null) {
	    this.jPanelPictureSet = new JPanel();
	    final TitledBorder title = BorderFactory
		    .createTitledBorder(BorderFactory.createEmptyBorder(), "Bildmengen");
	    this.jPanelPictureSet.setBorder(title);
	    this.jPanelPictureSet.setLayout(new BorderLayout());
	    this.jPanelPictureSet.add(this.getJListPictureSet(), BorderLayout.NORTH);
	    this.jPanelPictureSet.add(this.getJPanelPictureSetOptions(), BorderLayout.CENTER);

	    this.jPanelPictureSet.setMinimumSize(new Dimension(250, 135));
	    this.jPanelPictureSet.setPreferredSize(new Dimension(250, 135));
	}
	return this.jPanelPictureSet;
    }

    /**
     * This method initializes jPanelPictureSetActive.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelPictureSetActive() {

	/* create if not set */
	if (this.jPanelPictureSetActive == null) {
	    this.jPanelPictureSetActive = new JPanel();

	    final GroupLayout jPanelPictureSetActiveLayout = new GroupLayout(this.jPanelPictureSetActive);

	    jPanelPictureSetActiveLayout.setHorizontalGroup(jPanelPictureSetActiveLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    jPanelPictureSetActiveLayout.createSequentialGroup().addContainerGap().addComponent(
			    this.getJListPictureSetActive(), GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
			    .addContainerGap()));
	    jPanelPictureSetActiveLayout.setVerticalGroup(jPanelPictureSetActiveLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    jPanelPictureSetActiveLayout.createSequentialGroup().addContainerGap().addComponent(
			    this.getJListPictureSetActive(), GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
			    .addContainerGap()));

	    final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
		    "Bildmenge: Bla");
	    this.jPanelPictureSetActive.setBorder(title);

	    this.jPanelPictureSetActive.setLayout(jPanelPictureSetActiveLayout);

	    this.jPanelPictureSetActive.add(this.getJListPictureSetActive());

	    this.jPanelPictureSetActive.setMinimumSize(new Dimension(220, 500));
	    this.jPanelPictureSetActive.setPreferredSize(new Dimension(220, 500));
	}
	return this.jPanelPictureSetActive;
    }

    /**
     * This method initializes jPanelPictureSetContent.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelPictureSetContent() {

	/* create if not set */
	if (this.jPanelPictureSetContent == null) {
	    this.jPanelPictureSetContent = new JPanel();
	    final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Inhalt");
	    this.jPanelPictureSetContent.setBorder(title);
	    this.jPanelPictureSetContent.setLayout(new BorderLayout());
	    this.jPanelPictureSetContent.add(this.getJListPictureSetContent(), BorderLayout.NORTH);
	    this.jPanelPictureSetContent.add(this.getJPanelPictureSetContentOptions(), BorderLayout.CENTER);

	    this.jPanelPictureSetContent.setMinimumSize(new Dimension(250, 135));
	    this.jPanelPictureSetContent.setPreferredSize(new Dimension(250, 135));
	}
	return this.jPanelPictureSetContent;
    }

    /**
     * This method initializes jPanelPictureSetContentOptions.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelPictureSetContentOptions() {

	/* create if not set */
	if (this.jPanelPictureSetContentOptions == null) {
	    this.jPanelPictureSetContentOptions = new JPanel();
	    this.jPanelPictureSetContentOptions.setLayout(new FlowLayout());
	    this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentAdd(), null);
	    this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentDelete(), null);
	    this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentRefresh(), null);
	}
	return this.jPanelPictureSetContentOptions;
    }

    /**
     * This method initializes jPanelPictureSetOptions.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelPictureSetOptions() {

	/* create if not set */
	if (this.jPanelPictureSetOptions == null) {
	    this.jPanelPictureSetOptions = new JPanel();
	    this.jPanelPictureSetOptions.setLayout(new FlowLayout());
	    this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCreate(), null);
	    this.jPanelPictureSetOptions.add(this.getJButtonPictureSetDelete(), null);
	    this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCopy(), null);
	}
	return this.jPanelPictureSetOptions;
    }

    /**
     * This method initializes jPanelProjectDescription.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelProjectDescription() {

	/* create if not set */
	if (this.jPanelProjectDescription == null) {
	    this.jPanelProjectDescription = new JPanel();

	    final GroupLayout jPanelProjectDescriptionLayout = new GroupLayout(this.jPanelProjectDescription);

	    jPanelProjectDescriptionLayout.setHorizontalGroup(jPanelProjectDescriptionLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    GroupLayout.Alignment.TRAILING,
		    jPanelProjectDescriptionLayout.createSequentialGroup().addContainerGap().addComponent(
			    this.getJScrollPaneProjectDescription(), GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
			    .addContainerGap()));
	    jPanelProjectDescriptionLayout.setVerticalGroup(jPanelProjectDescriptionLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    jPanelProjectDescriptionLayout.createSequentialGroup().addContainerGap().addComponent(
			    this.getJScrollPaneProjectDescription(), GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
			    .addContainerGap()));

	    this.jPanelProjectDescription.setLayout(jPanelProjectDescriptionLayout);

	    final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
		    "Projektbeschreibung");
	    this.jPanelProjectDescription.setBorder(title);

	    this.jPanelProjectDescription.add(this.getJScrollPaneProjectDescription());

	    this.jPanelProjectDescription.setMinimumSize(new Dimension(250, 135));
	    this.jPanelProjectDescription.setPreferredSize(new Dimension(250, 135));
	}
	return this.jPanelProjectDescription;
    }

    /**
     * This method initializes jPanelProjectOptions.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelProjectOptions() {

	/* create if not set */
	if (this.jPanelProjectOptions == null) {
	    this.jPanelProjectOptions = new JPanel();

	    final GroupLayout jPanelProjectOptionsLayout = new GroupLayout(this.jPanelProjectOptions);

	    jPanelProjectOptionsLayout.setHorizontalGroup(jPanelProjectOptionsLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    GroupLayout.Alignment.TRAILING,
		    jPanelProjectOptionsLayout.createSequentialGroup().addContainerGap().addGroup(
			    jPanelProjectOptionsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				    .addComponent(this.getJTextFieldProjectName(), GroupLayout.DEFAULT_SIZE,
					    240, Short.MAX_VALUE).addGroup(
					    jPanelProjectOptionsLayout.createSequentialGroup().addComponent(
						    this.getJButtonProjectChange()).addPreferredGap(
						    LayoutStyle.ComponentPlacement.RELATED).addComponent(
						    this.getJButtonProjectSave()))).addContainerGap()));
	    jPanelProjectOptionsLayout.setVerticalGroup(jPanelProjectOptionsLayout.createParallelGroup(
		    GroupLayout.Alignment.LEADING).addGroup(
		    jPanelProjectOptionsLayout.createSequentialGroup().addGroup(
			    jPanelProjectOptionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(this.getJButtonProjectSave()).addComponent(
					    this.getJButtonProjectChange())).addPreferredGap(
			    LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE).addComponent(
			    this.getJTextFieldProjectName(), GroupLayout.PREFERRED_SIZE,
			    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

	    final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Projekt");
	    this.jPanelProjectOptions.setBorder(title);

	    this.jPanelProjectOptions.setLayout(jPanelProjectOptionsLayout);

	    this.jPanelProjectOptions.add(this.getJTextFieldProjectName());
	    this.jPanelProjectOptions.add(this.getJButtonProjectSave());
	    this.jPanelProjectOptions.add(this.getJButtonProjectChange());

	    this.jPanelProjectOptions.setMinimumSize(new Dimension(250, 60));
	    this.jPanelProjectOptions.setPreferredSize(new Dimension(250, 60));
	}
	return this.jPanelProjectOptions;
    }

    /**
     * This method initializes jPanelReport.
     * 
     * @return javax.swing.JPanel the panel.
     */
    private JPanel getJPanelReport() {

	/* create if not set */
	if (this.jPanelReport == null) {
	    this.jPanelReport = new JPanel();

	    final GroupLayout jPanelReportLayout = new GroupLayout(this.jPanelReport);

	    jPanelReportLayout.setHorizontalGroup(jPanelReportLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addGroup(
			    jPanelReportLayout.createSequentialGroup().addContainerGap().addComponent(
				    this.getJListReport(), GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
				    .addContainerGap()));
	    jPanelReportLayout.setVerticalGroup(jPanelReportLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    .addGroup(
			    GroupLayout.Alignment.TRAILING,
			    jPanelReportLayout.createSequentialGroup().addContainerGap().addComponent(
				    this.getJListReport(), GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
				    .addContainerGap()));

	    final TitledBorder title = BorderFactory
		    .createTitledBorder(BorderFactory.createEmptyBorder(), "Auswertung");
	    this.jPanelReport.setBorder(title);

	    this.jPanelReport.setLayout(jPanelReportLayout);

	    this.jPanelReport.add(this.getJListReport());

	    this.jPanelReport.setMinimumSize(new Dimension(250, 245));
	    this.jPanelReport.setPreferredSize(new Dimension(250, 245));
	}
	return this.jPanelReport;
    }

    /**
     * This method initializes jScrollPaneExif.
     * 
     * @return javax.swing.JScrollPane the scrollpane.
     */
    private JScrollPane getJScrollPaneExif() {

	/* create if not set */
	if (this.jScrollPaneExif == null) {
	    this.jScrollPaneExif = new JScrollPane();
	    this.jScrollPaneExif.setViewportView(this.getJTableExif());
	}
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
	    this.jScrollPaneProjectDescription = new JScrollPane();
	    this.jScrollPaneProjectDescription.setViewportView(this.getJEditorPaneProjectDescription());
	}
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
	    final String[] columnNames = { "First Name", "Last Name" };
	    final Object[][] data = { { "Mary", "Campione" }, { "Alison", "Huml" }, { "Kathy", "Walrath" },
		    { "Sharon", "Zakhour" }, { "Philip", "Milne" } };

	    this.jTableExif = new JTable(data, columnNames);
	}
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
	    this.jTextFieldProjectName = new JTextField();
	}
	return this.jTextFieldProjectName;
    }
    
    /**
     * This method checks which entrys are marked in the
     * PicturesetContentList and gives them back as indices
     * 
     * @return Array of Inices.
     */
    public int[] getSelectedIndicesFromPictureSetContentList() {
    	return this.jListPictureSetContent.getSelectedIndices();
    }
    
    /**
     * This method checks which entrys are marked in the
     * PicturesetList and gives them back as indices
     * 
     * @return Array of Inices.
     */
    public int[] getSelectedIndicesFromPictureSetList() {
    	return this.jListPictureSet.getSelectedIndices();
    }
    
    /**
     * This method checks which entrys are marked in the
     * ReportList and gives them back as indices
     * 
     * @return Array of Inices.
     */
    public int[] getSelectedIndicesFromReportList() {
    	return this.jListReport.getSelectedIndices();
    }
    
    /**
     * This method checks which entrys are marked in the
     * PictureList of a PictureSet and gives them back as indices
     * 
     * @return Array of Inices.
     */
    public int[] getSelectedIndicesFromPictureList() {
    	return this.jListPictureSetActive.getSelectedIndices();
    }

   

    @Override
    public void update(final Observable o, final Object arg) {
    	
    	final ProjectViewModel model = (ProjectViewModel) o;
    	
    	this.setTitle("Projektansicht für " + model.getProjectName());
    	
    	/* Fenster aktualisieren */
    	this.repaint();

		/* Je nach Programmstatus */
		if (model.getModelStatus() == ProjectViewModel.USERSELECT) {
	
		    /* Setze Fenster auf aktiv */
		    this.setEnabled(true);
	
		    /* Zeige Fenster an */
		    this.setVisible(true);
		} else if (model.getModelStatus() == ProjectViewModel.SWITCHPROJECT) {
	
		    /* Lösche Fenster */
		    this.dispose();
		} else {
			
	    /* Setze Fenster auf inaktiv */
		this.setEnabled(false);
		}
    }
}