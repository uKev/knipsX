package org.knipsX.view.projectview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.knipsX.controller.projectview.PictureListClickOnController;
import org.knipsX.controller.projectview.PictureSetContentListAddController;
import org.knipsX.controller.projectview.PictureSetContentListClickOnController;
import org.knipsX.controller.projectview.PictureSetContentListDeleteController;
import org.knipsX.controller.projectview.PictureSetContentListRefreshController;
import org.knipsX.controller.projectview.PictureSetListClickOnController;
import org.knipsX.controller.projectview.PictureSetListCopyController;
import org.knipsX.controller.projectview.PictureSetListCreateController;
import org.knipsX.controller.projectview.PictureSetListDeleteController;
import org.knipsX.controller.projectview.ProjectEditDescriptionController;
import org.knipsX.controller.projectview.ProjectEditNameController;
import org.knipsX.controller.projectview.ProjectSaveController;
import org.knipsX.controller.projectview.ProjectSwitchController;
import org.knipsX.controller.projectview.ReportClickOnController;
import org.knipsX.controller.projectview.ReportCreateController;
import org.knipsX.controller.projectview.ReportDeleteController;
import org.knipsX.controller.projectview.ReportOpenController;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.Resource;
import org.knipsX.view.JAbstractView;
import org.knipsX.view.reportmanagement.ReportHelper;

/**
 * Represents the view for an active project.
 * 
 * Sets all GUI Elements which are described in our Pflichtenheft.
 */
public class JProjectView<M extends ProjectModel> extends JAbstractView<M> {

    private static final long serialVersionUID = 6747507429332590686L;

    private JPanel jContentPane = null;
    private JPanel jPanelProjectOptions = null;
    private JPanel jPanelProjectDescription = null;
    private JPanel jPanelPictureSet = null;
    private JPanel jPanelPictureSetOptions = null;
    private JPanel jPanelPictureSetContent = null;
    private JPanel jPanelPictureSetContentOptions = null;
    private JPanel jPanelPictureSetActive = null;
    private JPanel jPanelReport = null;
    private JPanel jPanelReportOptions = null;

    private JPanel jPanelExif = null;

    private JEditorPane jEditorPaneProjectDescription = null;

    private JTextField jTextFieldProjectName = null;

    private JButton jButtonProjectSave = null;
    private JButton jButtonProjectChange = null;
    private JButton jButtonPictureSetCreate = null;
    private JButton jButtonPictureSetDelete = null;
    private JButton jButtonPictureSetCopy = null;
    private JButton jButtonPictureSetContentAdd = null;
    private JButton jButtonPictureSetContentDelete = null;
    private JButton jButtonPictureSetContentRefresh = null;
    private JButton jButtonReportCreate = null;
    private JButton jButtonReportOpen = null;
    private JButton jButtonReportDelete = null;

    private JList jListPictureSet = null;
    private JList jListPictureSetContent = null;
    private JList jListPictureSetActive = null;
    private JList jListReport = null;
    private JTable jTableExif = null;

    /**
     * Creates a project view connected with an appropriate model.
     */
    public JProjectView(final M model) {

        /* use the functionality of the super class to connect view and model */
        super(model);

        /* report helper connects to the current model */
        ReportHelper.setCurrentProjectModel(this.model);

        this.initialize();

        /* TWEAK Search the right position */
        this.model.loadData();
    }

    /* initializes the view */
    private void initialize() {

        /* set the main panel */
        this.setContentPane(this.getJContentPane());

        /* set standard close action */
        /* TODO We have to edit the close action! */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* change size to preferred size */
        this.pack();

        /* set location to the center of the screen */
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    /*
     * ################################################################################################################
     * THE BUTTONS
     * ################################################################################################################
     */

    /* creates a new button with some functionality */
    private JButton getNewButton(final String text, final String toolTip, final ActionListener listener) {
        final JButton newButton = new JButton(text);

        newButton.setFont(new Font("SanSerif", Font.BOLD, 10));
        newButton.addActionListener(listener);

        return newButton;
    }

    private JButton getJButtonProjectChange() {

        /* create only if not set */
        if (this.jButtonProjectChange == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectSwitchController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonProjectChange = this.getNewButton("Wechseln", "", listener);
        }
        return this.jButtonProjectChange;
    }

    private JButton getJButtonProjectSave() {

        /* create only if not set */
        if (this.jButtonProjectSave == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ProjectSaveController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonProjectSave = this.getNewButton("Speichern", "", listener);
        }
        return this.jButtonProjectSave;
    }

    private JButton getJButtonPictureSetCopy() {

        /* create only if not set */
        if (this.jButtonPictureSetCopy == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetListCopyController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetCopy = this.getNewButton("Kopieren", "", listener);
        }
        return this.jButtonPictureSetCopy;
    }

    private JButton getJButtonPictureSetCreate() {

        /* create only if not set */
        if (this.jButtonPictureSetCreate == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetListCreateController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetCreate = this.getNewButton("Erstellen", "", listener);
        }
        return this.jButtonPictureSetCreate;
    }

    private JButton getJButtonPictureSetDelete() {

        /* create only if not set */
        if (this.jButtonPictureSetDelete == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetListDeleteController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetDelete = this.getNewButton("Entfernen", "", listener);
        }
        return this.jButtonPictureSetDelete;
    }

    private JButton getJButtonPictureSetContentAdd() {

        /* create only if not set */
        if (this.jButtonPictureSetContentAdd == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetContentListAddController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetContentAdd = this.getNewButton("Hinzufügen", "", listener);
        }
        return this.jButtonPictureSetContentAdd;
    }

    private JButton getJButtonPictureSetContentDelete() {

        /* create only if not set */
        if (this.jButtonPictureSetContentDelete == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetContentListDeleteController<M, JProjectView<M>>(this.model,
                    this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetContentDelete = this.getNewButton("Entfernen", "", listener);
        }
        return this.jButtonPictureSetContentDelete;
    }

    private JButton getJButtonPictureSetContentRefresh() {

        /* create only if not set */
        if (this.jButtonPictureSetContentRefresh == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new PictureSetContentListRefreshController<M, JProjectView<M>>(this.model,
                    this);

            /* INTERNATIONALIZE */
            this.jButtonPictureSetContentRefresh = this.getNewButton("Aktualisieren", "", listener);
        }
        return this.jButtonPictureSetContentRefresh;
    }

    private JButton getJButtonReportCreate() {

        /* create only if not set */
        if (this.jButtonReportCreate == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ReportCreateController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonReportCreate = this.getNewButton("Erstellen", "", listener);
        }
        return this.jButtonReportCreate;
    }

    private JButton getJButtonReportOpen() {

        /* create only if not set */
        if (this.jButtonReportOpen == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ReportOpenController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonReportOpen = this.getNewButton("Öffnen", "", listener);
        }
        return this.jButtonReportOpen;
    }

    private JButton getJButtonReportDelete() {

        /* create only if not set */
        if (this.jButtonReportDelete == null) {

            /* the action listener must know view and model */
            final ActionListener listener = new ReportDeleteController<M, JProjectView<M>>(this.model, this);

            /* INTERNATIONALIZE */
            this.jButtonReportDelete = this.getNewButton("Entfernen", "", listener);
        }
        return this.jButtonReportDelete;
    }

    /*
     * ################################################################################################################
     * THE MAIN PANE
     * ################################################################################################################
     */

    private JPanel getJContentPane() {

        /* create only if not set */
        if (this.jContentPane == null) {

            this.jContentPane = new JPanel();

            /* create new layout for this panel */
            final GroupLayout jContentPaneLayout = new GroupLayout(this.jContentPane);

            /* represents the horizontal left area of the window */
            final ParallelGroup horiLeftArea = jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);

            /* the maximum width of the left area */
            final int widthLeft = 300;

            horiLeftArea.addComponent(this.getJPanelProjectOptions(), widthLeft, widthLeft, widthLeft);
            horiLeftArea.addComponent(this.getJPanelProjectDescription(), widthLeft, widthLeft, widthLeft);
            horiLeftArea.addComponent(this.getJPanelPictureSet(), widthLeft, widthLeft, widthLeft);
            horiLeftArea.addComponent(this.getJPanelPictureSetContent(), widthLeft, widthLeft, widthLeft);

            /* represents the horizontal right area of the window */
            final ParallelGroup horiRightArea = jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING);

            /* the maximum width of the right area */
            final int widthRight = 250;

            horiRightArea.addComponent(this.getJPanelReport(), widthRight, widthRight, widthRight);
            horiRightArea.addComponent(this.getJPanelExif(), widthRight, widthRight, widthRight);

            /* we have three horizontal areas: left, middle, right */
            final SequentialGroup horiMainArea = jContentPaneLayout.createSequentialGroup();

            /* add the groups horizontal left and right and the component for the horizontal middle area */
            horiMainArea.addGroup(horiLeftArea);
            horiMainArea.addComponent(this.getJPanelPictureSetActive());
            horiMainArea.addGroup(horiRightArea);

            /* set the horizontal assignment */
            jContentPaneLayout.setHorizontalGroup(horiMainArea);

            /* represents the vertical left area of the window */
            final SequentialGroup vertLeftArea = jContentPaneLayout.createSequentialGroup();

            vertLeftArea.addComponent(this.getJPanelProjectOptions(), 75, 75, 75);
            vertLeftArea.addComponent(this.getJPanelProjectDescription(), 100, 100, 100);
            vertLeftArea.addComponent(this.getJPanelPictureSet(), 100, 100, Short.MAX_VALUE);
            vertLeftArea.addComponent(this.getJPanelPictureSetContent(), 100, 100, Short.MAX_VALUE);

            /* represents the vertical right area of the window */
            final SequentialGroup vertRightArea = jContentPaneLayout.createSequentialGroup();

            vertRightArea.addComponent(this.getJPanelReport(), 250, 250, Short.MAX_VALUE);
            vertRightArea.addComponent(this.getJPanelExif(), 200, 200, Short.MAX_VALUE);

            /* we have three vertical areas: left, middle, right */
            final ParallelGroup vertMainArea = jContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING);

            /* add the groups vertical left and right and the component for the vertical middle area */
            vertMainArea.addGroup(GroupLayout.Alignment.TRAILING, vertLeftArea);
            vertMainArea.addComponent(this.getJPanelPictureSetActive());
            vertMainArea.addGroup(vertRightArea);

            /* set the vertical assignment */
            jContentPaneLayout.setVerticalGroup(vertMainArea);

            /* set the layout to the panel */
            this.jContentPane.setLayout(jContentPaneLayout);

            /* draw an invisible border around the panel to make it more readable */
            this.jContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
        return this.jContentPane;
    }

    /*
     * ################################################################################################################
     * THE FIRST PANE (PROJECT OPTIONS)
     * ################################################################################################################
     */

    private JPanel getJPanelProjectOptions() {

        /* create only if not set */
        if (this.jPanelProjectOptions == null) {

            this.jPanelProjectOptions = new JPanel();

            /* create new layout for this panel */
            final GroupLayout jPanelProjectOptionsLayout = new GroupLayout(this.jPanelProjectOptions);

            final SequentialGroup horiButtons = jPanelProjectOptionsLayout.createSequentialGroup();

            /* define horizontal placement of the buttons */
            horiButtons.addComponent(this.getJButtonProjectChange());
            horiButtons.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
            horiButtons.addComponent(this.getJButtonProjectSave());

            /* define horizontal placement of the area */
            final ParallelGroup horiWholeArea = jPanelProjectOptionsLayout
                    .createParallelGroup(GroupLayout.Alignment.TRAILING);

            horiWholeArea.addGroup(horiButtons);
            horiWholeArea.addComponent(this.getJTextFieldProjectName());

            /* set the horizontal assignment */
            jPanelProjectOptionsLayout.setHorizontalGroup(horiWholeArea);

            /* define vertical placement of the buttons */
            final ParallelGroup vertButtons = jPanelProjectOptionsLayout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE);

            vertButtons.addComponent(this.getJButtonProjectSave());
            vertButtons.addComponent(this.getJButtonProjectChange());

            /* define vertical placement of the area */
            final SequentialGroup vertWholeArea = jPanelProjectOptionsLayout.createSequentialGroup();

            vertWholeArea.addGroup(vertButtons);
            vertWholeArea.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
            vertWholeArea.addComponent(this.getJTextFieldProjectName());

            /* set the vertical assignment */
            jPanelProjectOptionsLayout.setVerticalGroup(vertWholeArea);

            /* set the layout to the panel */
            this.jPanelProjectOptions.setLayout(jPanelProjectOptionsLayout);

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Projekt");
            this.jPanelProjectOptions.setBorder(title);
        }
        return this.jPanelProjectOptions;
    }

    private JTextField getJTextFieldProjectName() {

        /* create only if not set */
        if (this.jTextFieldProjectName == null) {
            this.jTextFieldProjectName = new JTextField(this.model.getName());
            this.jTextFieldProjectName.getDocument().addDocumentListener(
                    new ProjectEditNameController<M, JProjectView<M>>(this.model, this));
        }
        return this.jTextFieldProjectName;
    }

    /*
     * ################################################################################################################
     * THE SECOND PANE (PROJECT DESCRIPTION)
     * ################################################################################################################
     */

    private JPanel getJPanelProjectDescription() {

        /* create only if not set */
        if (this.jPanelProjectDescription == null) {

            this.jPanelProjectDescription = new JPanel();

            this.jPanelProjectDescription.setLayout(new BorderLayout());

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                    "Projektbeschreibung");
            this.jPanelProjectDescription.setBorder(title);

            this.jPanelProjectDescription.add(this.getProjectDescriptionEditorPane(), BorderLayout.CENTER);
        }
        return this.jPanelProjectDescription;
    }

    private JScrollPane getProjectDescriptionEditorPane() {

        /* create only if not set */
        if (this.jEditorPaneProjectDescription == null) {
            this.jEditorPaneProjectDescription = new JEditorPane();
            this.jEditorPaneProjectDescription.setText(this.model.getDescription());
            this.jEditorPaneProjectDescription.getDocument().addDocumentListener(
                    new ProjectEditDescriptionController<M, JProjectView<M>>(this.model, this));
        }
        return new JScrollPane(this.jEditorPaneProjectDescription);
    }

    /*
     * ################################################################################################################
     * THE THIRD PANE (PICTURE SETS)
     * ################################################################################################################
     */

    private JPanel getJPanelPictureSet() {

        /* create only if not set */
        if (this.jPanelPictureSet == null) {

            this.jPanelPictureSet = new JPanel();

            this.jPanelPictureSet.setLayout(new BoxLayout(this.jPanelPictureSet, BoxLayout.Y_AXIS));

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory
                    .createTitledBorder(BorderFactory.createEmptyBorder(), "Bildmengen");
            this.jPanelPictureSet.setBorder(title);

            this.jPanelPictureSet.add(this.getPictureSetList());
            this.jPanelPictureSet.add(this.getJPanelPictureSetOptions());
        }
        return this.jPanelPictureSet;
    }

    private JScrollPane getPictureSetList() {

        /* create only if not set */
        if (this.jListPictureSet == null) {

            /* creates a new list with options */
            this.jListPictureSet = new JList(this.model.getPictureSets());

            this.jListPictureSet.setLayoutOrientation(JList.VERTICAL);
            this.jListPictureSet.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            this.jListPictureSet.setVisibleRowCount(-1);
            this.jListPictureSet.addMouseListener(new PictureSetListClickOnController<M, JProjectView<M>>(this.model,
                    this));

            /* we store picture set objects in the list, so we have to set a special rendering */
            this.jListPictureSet.setCellRenderer(new MyPictureSetListCellRenderer());
        }
        final JScrollPane listScroller = new JScrollPane(this.jListPictureSet);

        return listScroller;
    }

    private JPanel getJPanelPictureSetOptions() {

        /* create only if not set */
        if (this.jPanelPictureSetOptions == null) {

            this.jPanelPictureSetOptions = new JPanel();

            this.jPanelPictureSetOptions.setLayout(new BoxLayout(this.jPanelPictureSetOptions, BoxLayout.X_AXIS));

            this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCreate());
            this.jPanelPictureSetOptions.add(this.getJButtonPictureSetDelete());
            this.jPanelPictureSetOptions.add(this.getJButtonPictureSetCopy());

            /* draw an invisible border around the panel to make it more readable */
            this.jPanelPictureSetOptions.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        }
        return this.jPanelPictureSetOptions;
    }

    /*
     * ################################################################################################################
     * THE FORTH PANE (PICTURE SETS CONTENTS)
     * ################################################################################################################
     */

    private JPanel getJPanelPictureSetContent() {

        /* create only if not set */
        if (this.jPanelPictureSetContent == null) {

            this.jPanelPictureSetContent = new JPanel();

            this.jPanelPictureSetContent.setLayout(new BoxLayout(this.jPanelPictureSetContent, BoxLayout.Y_AXIS));

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Inhalt");
            this.jPanelPictureSetContent.setBorder(title);

            this.jPanelPictureSetContent.add(this.getPictureSetContentList());
            this.jPanelPictureSetContent.add(this.getJPanelPictureSetContentOptions());
        }
        return this.jPanelPictureSetContent;
    }

    private JScrollPane getPictureSetContentList() {

        /* create only if not set */
        if (this.jListPictureSetContent == null) {
            try {
                final PictureSet pictureSet = this.model.getPictureSets()[0];

                final List<PictureContainer> list = this.extractPictureSetContents(pictureSet);

                /* creates a new list with options */
                this.jListPictureSetContent = new JList(list.toArray());
            } catch (final ArrayIndexOutOfBoundsException exception) {
                /* we could have a new and empty project */
                this.jListPictureSetContent = new JList();
            }

            this.jListPictureSetContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.jListPictureSetContent.setLayoutOrientation(JList.VERTICAL);
            this.jListPictureSetContent
                    .addMouseListener(new PictureSetContentListClickOnController<M, JProjectView<M>>(this.model, this));

            /* we store different objects in the list, so we have to set a special rendering */
            this.jListPictureSetContent.setCellRenderer(new MyPictureSetContentListCellRenderer());

        }
        final JScrollPane listScroller = new JScrollPane(this.jListPictureSetContent);

        return listScroller;
    }

    private JPanel getJPanelPictureSetContentOptions() {

        /* create only if not set */
        if (this.jPanelPictureSetContentOptions == null) {

            this.jPanelPictureSetContentOptions = new JPanel();

            this.jPanelPictureSetContentOptions.setLayout(new BoxLayout(this.jPanelPictureSetContentOptions,
                    BoxLayout.X_AXIS));

            this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentAdd());
            this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentDelete());
            this.jPanelPictureSetContentOptions.add(this.getJButtonPictureSetContentRefresh());

            /* draw an invisible border around the panel to make it more readable */
            this.jPanelPictureSetContentOptions.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        }
        return this.jPanelPictureSetContentOptions;
    }

    /*
     * ################################################################################################################
     * THE FIFTH PANE (ACTIVE PICTURES)
     * ################################################################################################################
     */

    private JPanel getJPanelPictureSetActive() {

        /* create only if not set */
        if (this.jPanelPictureSetActive == null) {

            this.jPanelPictureSetActive = new JPanel();

            this.jPanelPictureSetActive.setLayout(new BorderLayout());

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            if (this.model.getSelectedPictureSetContent() != null) {
                final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                        "Bilder von Bildmengeninhalt " + this.model.getSelectedPictureSetContent().getName());
                this.jPanelPictureSetActive.setBorder(title);
            } else {
                final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                        "Bilder von Bildmenge " + this.model.getSelectedPictureSet().getName());
                this.jPanelPictureSetActive.setBorder(title);
            }

            this.jPanelPictureSetActive.add(this.getPictureSetActiveList(), BorderLayout.CENTER);
        }
        return this.jPanelPictureSetActive;
    }

    private JScrollPane getPictureSetActiveList() {

        /* create only if not set */
        if (this.jListPictureSetActive == null) {

            /* creates a new list with options */
            this.jListPictureSetActive = new JList(this.model.getAllPictures());

            this.jListPictureSetActive.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.jListPictureSetActive.setLayoutOrientation(JList.VERTICAL);
            this.jListPictureSetActive.addMouseListener(new PictureListClickOnController<M, JProjectView<M>>(
                    this.model, this));

            /* we store picture objects in the list, so we have to set a special rendering */
            this.jListPictureSetActive.setCellRenderer(new MyPictureListCellRenderer());
        }
        final JScrollPane listScroller = new JScrollPane(this.jListPictureSetActive);

        /* FIXME correct size */
        listScroller.setMinimumSize(new Dimension(146, 400));
        listScroller.setPreferredSize(new Dimension(146, 400));

        return listScroller;
    }

    /*
     * ################################################################################################################
     * THE SIXTH PANE (REPORTS)
     * ################################################################################################################
     */

    private JPanel getJPanelReport() {

        /* create only if not set */
        if (this.jPanelReport == null) {

            this.jPanelReport = new JPanel();

            this.jPanelReport.setLayout(new BoxLayout(this.jPanelReport, BoxLayout.Y_AXIS));

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory
                    .createTitledBorder(BorderFactory.createEmptyBorder(), "Auswertung");
            this.jPanelReport.setBorder(title);

            this.jPanelReport.add(this.getReportList());
            this.jPanelReport.add(this.getJPanelReportOptions());
        }
        return this.jPanelReport;
    }

    private JScrollPane getReportList() {

        /* create only if not set */
        if (this.jListReport == null) {

            /* creates a new list with options */
            this.jListReport = new JList(this.model.getReports());

            this.jListReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.jListReport.setLayoutOrientation(JList.VERTICAL);
            this.jListReport.addMouseListener(new ReportClickOnController<M, JProjectView<M>>(this.model, this));

            /* we store different objects in the list, so we have to set a special rendering */
            this.jListReport.setCellRenderer(new MyReportListCellRenderer());
        }
        final JScrollPane listScroller = new JScrollPane(this.jListReport);

        return listScroller;
    }

    private JPanel getJPanelReportOptions() {

        /* create only if not set */
        if (this.jPanelReportOptions == null) {

            this.jPanelReportOptions = new JPanel();

            this.jPanelReportOptions.setLayout(new BoxLayout(this.jPanelReportOptions, BoxLayout.X_AXIS));

            this.jPanelReportOptions.add(this.getJButtonReportCreate());
            this.jPanelReportOptions.add(this.getJButtonReportOpen());
            this.jPanelReportOptions.add(this.getJButtonReportDelete());

            /* draw an invisible border around the panel to make it more readable */
            this.jPanelReportOptions.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        }
        return this.jPanelReportOptions;
    }

    /*
     * ################################################################################################################
     * THE SEVENTH PANE (EXIF DATA)
     * ################################################################################################################
     */

    private JPanel getJPanelExif() {

        /* create only if not set */
        if (this.jPanelExif == null) {

            this.jPanelExif = new JPanel();

            this.jPanelExif.setLayout(new BorderLayout());

            /* add a border to the panel */
            /* INTERNATIONALIZE */
            final TitledBorder title = BorderFactory
                    .createTitledBorder(BorderFactory.createEmptyBorder(), "Exif-Daten");

            this.jPanelExif.setBorder(title);

            this.jPanelExif.add(this.getExifTable(), BorderLayout.CENTER);
        }
        return this.jPanelExif;
    }

    private JScrollPane getExifTable() {

        /* create only if not set */
        if (this.jTableExif == null) {
            this.jTableExif = createExifTable();
        }
        return new JScrollPane(this.jTableExif);
    }

    private JTable createExifTable() {
        final String[] columnNames = { "Parameter", "Wert" };
        final Object[][] data = this.model.getExifParameter();

        /* create new table for the exif parameters of an active image */
        JTable table = new JTable(data, columnNames);
        final TableColumn para = table.getColumnModel().getColumn(0);
        final TableColumn value = table.getColumnModel().getColumn(1);
        para.setCellRenderer(new MyTableCellRenderer());
        value.setCellRenderer(new MyTableCellRenderer());
        
        return table;
    }
    /*
     * ################################################################################################################
     * SOME METHODS WHICH ARE USED BY THE CONNECTED MODEL
     * ################################################################################################################
     */

    /**
     * This method checks which entries are marked in the PictureSet list and gives them back as indices.
     * 
     * @return an array of indices.
     */
    public PictureSet[] getSelectedPictureSets() {
        assert this.jListPictureSet != null;

        final Object[] values = this.jListPictureSet.getSelectedValues();
        final PictureSet[] sets = new PictureSet[values.length];

        for (int i = 0; i < values.length; ++i) {
            sets[i] = (PictureSet) values[i];
        }
        return sets;
    }

    /**
     * This method checks which entries are marked in the PictureSetContent list and gives them back as indices.
     * 
     * @return an array of indices.
     */
    public PictureContainer[] getSelectedPictureSetContents() {
        assert this.jListPictureSetContent != null;

        final Object[] values = this.jListPictureSetContent.getSelectedValues();
        final PictureContainer[] container = new PictureContainer[values.length];

        for (int i = 0; i < values.length; ++i) {
            container[i] = (PictureContainer) values[i];
        }
        return container;
    }

    /**
     * This method checks which entries are marked in the Picture list of a PictureSet and gives them back as indices.
     * 
     * @return array of indices.
     */
    public int[] getSelectedPictures() {
        assert this.jListPictureSet != null;
        return this.jListPictureSet.getSelectedIndices();
    }

    /**
     * This method checks which entries are marked in the Report list and gives them back as indices.
     * 
     * @return array of indices.
     */
    public int[] getSelectedReports() {
        assert this.jListReport != null;
        return this.jListReport.getSelectedIndices();
    }

    public String getProjectName() {
        return new String(this.jTextFieldProjectName.getText());
    }

    public String getProjectDescription() {
        return new String(this.jEditorPaneProjectDescription.getText());
    }

    @Override
    public void update(final Observable o, final Object arg) {

        /* cast to model */
        final ProjectModel model = (ProjectModel) o;

        /* INTERNATIONALIZE */
        this.setTitle("Projektansicht für " + model.getName());

        /* things about the project */
        final int caretProjectName = this.jTextFieldProjectName.getCaretPosition();
        this.jTextFieldProjectName.setText(model.getName());
        this.jTextFieldProjectName.setCaretPosition(caretProjectName);

        final int caretProjectDescription = this.jEditorPaneProjectDescription.getCaretPosition();
        this.jEditorPaneProjectDescription.setText(model.getDescription());
        this.jEditorPaneProjectDescription.setCaretPosition(caretProjectDescription);

        final int[] selectedPictureSets = this.jListPictureSet.getSelectedIndices();
        final int[] selectedPictureSetContents = this.jListPictureSetContent.getSelectedIndices();
        final int[] selectedPictures = this.jListPictureSetActive.getSelectedIndices();
        final int[] selectedReports = this.jListReport.getSelectedIndices();

        /* setup the lists */
        this.jListPictureSet.setListData(model.getPictureSets());
        this.jListPictureSetContent
                .setListData(this.extractPictureSetContents(model.getSelectedPictureSet()).toArray());
        this.jListPictureSetActive.setListData(model.getAllPictures());
        this.jListReport.setListData(model.getReports());

        /* change border of the panel */
        /* INTERNATIONALIZE */
        if (model.getSelectedPictureSetContent() != null) {
            final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                    "Bilder von Bildmengeninhalt " + model.getSelectedPictureSetContent().getName());
            this.jPanelPictureSetActive.setBorder(title);
        } else {
            final TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
                    "Bilder von Bildmenge " + model.getSelectedPictureSet().getName());
            this.jPanelPictureSetActive.setBorder(title);
        }
        
        if (this.model.getStatus() == ProjectModel.ACTIVE) {
            this.setFocusableWindowState(true);
            this.setFocusable(true);
            this.setEnabled(true);
        } else {
            this.setFocusableWindowState(false);
            this.setEnabled(false);
            this.setFocusable(false);
        }

        /* setup the exif-table */
        this.jTableExif = createExifTable();
        
        /* refresh view */
        this.repaint();

        this.jListPictureSet.setSelectedIndices(selectedPictureSets);
        this.jListPictureSetContent.setSelectedIndices(selectedPictureSetContents);
        this.jListPictureSetActive.setSelectedIndices(selectedPictures);
        this.jListReport.setSelectedIndices(selectedReports);
    }

    /* generates the three separated parts of picture set contents */
    private List<PictureContainer> extractPictureSetContents(final PictureSet pictureSet) {

        final List<PictureContainer> allContents = new ArrayList<PictureContainer>();

        /* we show three different types of picture containers */
        final List<PictureContainer> list = new ArrayList<PictureContainer>();

        for (final PictureSet element : this.model.getPictureSetsOfAPictureSet(pictureSet)) {
            list.add(element);
        }
        Collections.sort(list);
        allContents.addAll(list);

        list.clear();
        for (final Directory element : this.model.getDirectoriesOfAPictureSet(pictureSet)) {
            list.add(element);
        }
        Collections.sort(list);
        allContents.addAll(list);

        list.clear();
        for (final Picture element : this.model.getPicturesOfAPictureSet(pictureSet)) {
            list.add(element);
        }
        Collections.sort(list);
        allContents.addAll(list);

        return allContents;
    }
}

/**
 * Renders a picture set cell for the picture set list.
 */
class MyPictureSetListCellRenderer implements ListCellRenderer {

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

        return renderer;
    }
}

/**
 * Renders a picture set content cell for the picture set content list of an active project.
 */
class MyPictureSetContentListCellRenderer implements ListCellRenderer {

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

        String theText = null;
        Color theColor = null;

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        /* if the selected item is a "Picture" -> set the name */
        /* TWEAK different system colors */
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

        return renderer;
    }
}

/**
 * Renders a picture cell for the picture list of an active picture container.
 */
class MyPictureListCellRenderer implements ListCellRenderer {

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private Icon noImageIcon = null;

    public MyPictureListCellRenderer() {
        try {
            this.noImageIcon = Resource.createImageIcon("../images/noimage.png", "");
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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

        String theText = null;

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        /* if the selected item is a "Picture" -> set the name */
        if (value instanceof Picture) {
            final Picture picture = (Picture) value;
            theText = picture.getName();

            final Image smallThumbnail = picture.getSmallThumbnail();
            if (smallThumbnail != null) {
                renderer.setIcon(new ImageIcon(smallThumbnail));
            } else {
                renderer.setIcon(this.noImageIcon);
            }
            renderer.setToolTipText("Picture");
        }
        renderer.setText(theText);
        renderer.setPreferredSize(new Dimension(renderer.getWidth(), 40));
        

        return renderer;
    }
}

/**
 * Renders a report cell for the report list of an active project.
 */
class MyReportListCellRenderer implements ListCellRenderer {

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

        return renderer;
    }
}

/**
 * Renders a table cell for the table which shows the exif parameters of an active picture.
 */
class MyTableCellRenderer extends JLabel implements TableCellRenderer {

    private static final long serialVersionUID = -5528480925908374362L;

    protected DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();

    /**
     * Renders the cell of a JTable.
     * 
     * @param table
     *            the JTable we're painting.
     * @param value
     *            the value of a cell.
     * @param isSelected
     *            true if the specified cell was selected.
     * @param hasFocus
     *            true if the specified cell has the focus.
     * @param row
     *            the selected row index.
     * @param column
     *            the selected column index.
     * 
     * @return the representation of the cell.
     */
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);

        if (value != null) {
            renderer.setText(value.toString());
            renderer.setToolTipText(value.toString());
        }
        return renderer;
    }
}

/*
 * ################################################################################################################
 * TOOLTIP
 * FIXME implement this shit correct!
 * ################################################################################################################
 */

// class ImageToolTipListCellRenderer extends DefaultListCellRenderer {
//
// private static final long serialVersionUID = 6235304707730082310L;
//
// private Image image;
//    
// public void setImage(Image image) {
// this.image = image;
// }
//    
// @Override
// public JToolTip createToolTip() {
// return new ImageToolTip(this.image);
// }
// }
//
// class ImageToolTip extends JToolTip {
//    
// private static final long serialVersionUID = 9162241243828840210L;
//
// public ImageToolTip(Image image) {
// setUI(new ImageToolTipUI(image));
// }
// }
//
// class ImageToolTipUI extends MetalToolTipUI {
//
// private Image image;
//
// public ImageToolTipUI(Image image) {
// super();
// this.image = image;
// }
//
// public void paint(Graphics g, JComponent c) {
// FontMetrics metrics = c.getFontMetrics(g.getFont());
// g.setColor(c.getForeground());
// g.drawString(((JToolTip) c).getTipText(), 1, 1);
// g.drawImage(this.image, 1, metrics.getHeight(), c);
// }
//
// public Dimension getPreferredSize(JComponent c) {
// FontMetrics metrics = c.getFontMetrics(c.getFont());
// String tipText = ((JToolTip) c).getTipText();
// if (tipText == null) {
// tipText = "";
// }
// int width = SwingUtilities.computeStringWidth(metrics, tipText);
// int height = metrics.getHeight() + this.image.getHeight(c);
//
// if (width < this.image.getWidth(c)) {
// width = this.image.getWidth(c);
// }
// return new Dimension(width, height);
// }
// }