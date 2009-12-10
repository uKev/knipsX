package org.knipsX.view.projectview;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;

public class JProjectView extends JAbstractView {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JPanel jPanelProjectOptions = null;
	private JPanel jPanelProjectDescription = null;
	private JPanel jPanelPictureSet = null;
	private JPanel jPanelPictureSetOptions = null;
	private JPanel jPanelPictureSetContent = null;
	private JPanel jPanelPictureSetContentOptions = null;
	private JPanel jPanelPictureSetActive = null;
	private JPanel jPanelReport = null;
	private JPanel jPanelExif = null;

	private JScrollPane jScrollPaneProjectDescription = null;
	private JScrollPane jScrollPaneExif = null;

	private JEditorPane jEditorPaneProjectDescription = null;

	private JTextField jTextFieldProjectDescription = null;

	private JButton jButtonProjectSave = null;
	private JButton jButtonProjectChange = null;
	private JButton jButtonPictureSetCreate = null;
	private JButton jButtonPictureSetDelete = null;
	private JButton jButtonPictureSetCopy = null;
	private JButton jButtonPictureSetContentAdd = null;
	private JButton jButtonPictureSetContentDelete = null;
	private JButton jButtonPictureSetContentRefresh = null;

	private JList jListPictureSet = null;
	private JList jListPictureSetContent = null;
	private JList jListPictureSetActive = null;
	private JList jListReport = null;

	private JTable jTableExif = null;

	/* Die Repräsentation des aktuellen Projekts */
	private ProjectViewModel model;

	/**
	 * This is the default constructor
	 */
	public JProjectView(ProjectViewModel model) {
		super(model);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setVisible(true);
		this.pack();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();

			GroupLayout jContentPaneLayout = new GroupLayout(jContentPane);

			jContentPaneLayout
					.setHorizontalGroup(jContentPaneLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									jContentPaneLayout
											.createSequentialGroup()
											.addGroup(
													jContentPaneLayout
															.createParallelGroup(
																	GroupLayout.Alignment.TRAILING)
															.addComponent(
																	this
																			.getJPanelProjectOptions(),
																	GroupLayout.Alignment.LEADING,
																	GroupLayout.DEFAULT_SIZE,
																	272,
																	Short.MAX_VALUE)
															.addComponent(
																	this
																			.getJPanelProjectDescription(),
																	GroupLayout.Alignment.LEADING,
																	GroupLayout.DEFAULT_SIZE,
																	272,
																	Short.MAX_VALUE)
															.addComponent(
																	this
																			.getJPanelPictureSet(),
																	GroupLayout.Alignment.LEADING,
																	GroupLayout.DEFAULT_SIZE,
																	272,
																	Short.MAX_VALUE)
															.addComponent(
																	this
																			.getJPanelPictureSetContent(),
																	GroupLayout.Alignment.LEADING,
																	GroupLayout.DEFAULT_SIZE,
																	272,
																	Short.MAX_VALUE))
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(
													this
															.getJPanelPictureSetActive(),
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)
											.addGap(6, 6, 6)
											.addGroup(
													jContentPaneLayout
															.createParallelGroup(
																	GroupLayout.Alignment.LEADING)
															.addComponent(
																	this
																			.getJPanelReport(),
																	GroupLayout.PREFERRED_SIZE,
																	240,
																	Short.MAX_VALUE)
															.addComponent(
																	this
																			.getJPanelExif(),
																	0,
																	240,
																	Short.MAX_VALUE))
											.addGap(0, 0, 0)));
			jContentPaneLayout
					.setVerticalGroup(jContentPaneLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									jContentPaneLayout
											.createSequentialGroup()
											.addComponent(
													this
															.getJPanelProjectOptions(),
													GroupLayout.DEFAULT_SIZE,
													77, Short.MAX_VALUE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(
													this
															.getJPanelProjectDescription(),
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(
													this.getJPanelPictureSet(),
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(
													this
															.getJPanelPictureSetContent(),
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE))
							.addGroup(
									jContentPaneLayout
											.createSequentialGroup()
											.addComponent(
													this.getJPanelReport(),
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(this.getJPanelExif(),
													GroupLayout.DEFAULT_SIZE,
													249, Short.MAX_VALUE))
							.addComponent(this.getJPanelPictureSetActive(),
									GroupLayout.DEFAULT_SIZE,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

			jContentPane.setLayout(jContentPaneLayout);

			jContentPane.add(getJPanelProjectOptions(), null);
			jContentPane.add(getJPanelProjectDescription(), null);
			jContentPane.add(getJPanelPictureSet(), null);
			jContentPane.add(getJPanelPictureSetContent(), null);
			jContentPane.add(getJPanelPictureSetActive(), null);
			jContentPane.add(getJPanelReport(), null);
			jContentPane.add(getJPanelExif(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanelPictureSet
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPictureSet() {
		if (jPanelPictureSet == null) {
			jPanelPictureSet = new JPanel();
			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Bildmengen");
			jPanelPictureSet.setBorder(title);
			jPanelPictureSet.setLayout(new BorderLayout());
			jPanelPictureSet.add(getJListPictureSet(), BorderLayout.NORTH);
			jPanelPictureSet.add(getJPanelPictureSetOptions(),
					BorderLayout.CENTER);

			jPanelPictureSet.setMinimumSize(new Dimension(250, 135));
			jPanelPictureSet.setPreferredSize(new Dimension(250, 135));
		}
		return jPanelPictureSet;
	}

	/**
	 * This method initializes jTextFieldProjectDescription
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldProjectDescription() {
		if (jTextFieldProjectDescription == null) {
			jTextFieldProjectDescription = new JTextField();
		}
		return jTextFieldProjectDescription;
	}

	/**
	 * This method initializes jPanelProjectOptions
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelProjectOptions() {
		if (jPanelProjectOptions == null) {
			jPanelProjectOptions = new JPanel();

			GroupLayout jPanelProjectOptionsLayout = new GroupLayout(
					jPanelProjectOptions);

			jPanelProjectOptionsLayout
					.setHorizontalGroup(jPanelProjectOptionsLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									jPanelProjectOptionsLayout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													jPanelProjectOptionsLayout
															.createParallelGroup(
																	GroupLayout.Alignment.TRAILING)
															.addComponent(
																	getJTextFieldProjectDescription(),
																	GroupLayout.DEFAULT_SIZE,
																	240,
																	Short.MAX_VALUE)
															.addGroup(
																	jPanelProjectOptionsLayout
																			.createSequentialGroup()
																			.addComponent(
																					getJButtonProjectChange())
																			.addPreferredGap(
																					LayoutStyle.ComponentPlacement.RELATED)
																			.addComponent(
																					getJButtonProjectSave())))
											.addContainerGap()));
			jPanelProjectOptionsLayout
					.setVerticalGroup(jPanelProjectOptionsLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									jPanelProjectOptionsLayout
											.createSequentialGroup()
											.addGroup(
													jPanelProjectOptionsLayout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	getJButtonProjectSave())
															.addComponent(
																	getJButtonProjectChange()))
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED,
													7, Short.MAX_VALUE)
											.addComponent(
													getJTextFieldProjectDescription(),
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)));

			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Projekt");
			jPanelProjectOptions.setBorder(title);

			jPanelProjectOptions.setLayout(jPanelProjectOptionsLayout);

			jPanelProjectOptions.add(getJTextFieldProjectDescription());
			jPanelProjectOptions.add(getJButtonProjectSave());
			jPanelProjectOptions.add(getJButtonProjectChange());

			jPanelProjectOptions.setMinimumSize(new Dimension(250, 60));
			jPanelProjectOptions.setPreferredSize(new Dimension(250, 60));
		}
		return jPanelProjectOptions;
	}

	/**
	 * This method initializes jPanelProjectDescription
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelProjectDescription() {
		if (jPanelProjectDescription == null) {
			jPanelProjectDescription = new JPanel();

			GroupLayout jPanelProjectDescriptionLayout = new GroupLayout(
					jPanelProjectDescription);

			jPanelProjectDescriptionLayout
					.setHorizontalGroup(jPanelProjectDescriptionLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									GroupLayout.Alignment.TRAILING,
									jPanelProjectDescriptionLayout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(
													this
															.getJScrollPaneProjectDescription(),
													GroupLayout.DEFAULT_SIZE,
													240, Short.MAX_VALUE)
											.addContainerGap()));
			jPanelProjectDescriptionLayout
					.setVerticalGroup(jPanelProjectDescriptionLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									jPanelProjectDescriptionLayout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(
													this
															.getJScrollPaneProjectDescription(),
													GroupLayout.DEFAULT_SIZE,
													86, Short.MAX_VALUE)
											.addContainerGap()));

			jPanelProjectDescription.setLayout(jPanelProjectDescriptionLayout);

			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Projektbeschreibung");
			jPanelProjectDescription.setBorder(title);

			jPanelProjectDescription.add(this
					.getJScrollPaneProjectDescription());

			jPanelProjectDescription.setMinimumSize(new Dimension(250, 135));
			jPanelProjectDescription.setPreferredSize(new Dimension(250, 135));
		}
		return jPanelProjectDescription;
	}

	/**
	 * This method initializes jScrollPaneProjectDescription
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneProjectDescription() {
		if (jScrollPaneProjectDescription == null) {
			jScrollPaneProjectDescription = new JScrollPane();
			jScrollPaneProjectDescription
					.setViewportView(getJEditorPaneProjectDescription());
		}
		return jScrollPaneProjectDescription;
	}

	/**
	 * This method initializes jEditorPaneProjectDescription
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPaneProjectDescription() {
		if (jEditorPaneProjectDescription == null) {
			jEditorPaneProjectDescription = new JEditorPane();
		}
		return jEditorPaneProjectDescription;
	}

	/**
	 * This method initializes jButtonProjectSave
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonProjectSave() {
		if (jButtonProjectSave == null) {
			jButtonProjectSave = new JButton();
			jButtonProjectSave.setText("Speichern");
		}
		return jButtonProjectSave;
	}

	/**
	 * This method initializes jButtonProjectChange
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonProjectChange() {
		if (jButtonProjectChange == null) {
			jButtonProjectChange = new JButton();
			jButtonProjectChange.setText("Wechseln");
		}
		return jButtonProjectChange;
	}

	/**
	 * This method initializes jListPictureSet
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListPictureSet() {
		if (jListPictureSet == null) {
			jListPictureSet = new JList(new Object[] { "Apfel", "Kürbis",
					"Paprika", "Tomate" });
			jListPictureSet
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListPictureSet.setLayoutOrientation(JList.VERTICAL);
		}
		return jListPictureSet;
	}

	/**
	 * This method initializes jPanelPictureSetOptions
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPictureSetOptions() {
		if (jPanelPictureSetOptions == null) {
			jPanelPictureSetOptions = new JPanel();
			jPanelPictureSetOptions.setLayout(new FlowLayout());
			jPanelPictureSetOptions.add(getJButtonPictureSetCreate(), null);
			jPanelPictureSetOptions.add(getJButtonPictureSetDelete(), null);
			jPanelPictureSetOptions.add(getJButtonPictureSetCopy(), null);
		}
		return jPanelPictureSetOptions;
	}

	/**
	 * This method initializes jButtonPictureSetCreate
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetCreate() {
		if (jButtonPictureSetCreate == null) {
			jButtonPictureSetCreate = new JButton();
			jButtonPictureSetCreate.setText("Erstellen");
		}
		return jButtonPictureSetCreate;
	}

	/**
	 * This method initializes jButtonPictureSetDelete
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetDelete() {
		if (jButtonPictureSetDelete == null) {
			jButtonPictureSetDelete = new JButton();
			jButtonPictureSetDelete.setText("Entfernen");
		}
		return jButtonPictureSetDelete;
	}

	/**
	 * This method initializes jButtonPictureSetCopy
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetCopy() {
		if (jButtonPictureSetCopy == null) {
			jButtonPictureSetCopy = new JButton();
			jButtonPictureSetCopy.setText("Kopieren");
		}
		return jButtonPictureSetCopy;
	}

	/**
	 * This method initializes jPanelPictureSetContent
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPictureSetContent() {
		if (jPanelPictureSetContent == null) {
			jPanelPictureSetContent = new JPanel();
			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Inhalt");
			jPanelPictureSetContent.setBorder(title);
			jPanelPictureSetContent.setLayout(new BorderLayout());
			jPanelPictureSetContent.add(getJListPictureSetContent(),
					BorderLayout.NORTH);
			jPanelPictureSetContent.add(getJPanelPictureSetContentOptions(),
					BorderLayout.CENTER);

			jPanelPictureSetContent.setMinimumSize(new Dimension(250, 135));
			jPanelPictureSetContent.setPreferredSize(new Dimension(250, 135));
		}
		return jPanelPictureSetContent;
	}

	/**
	 * This method initializes jListPictureSetContent
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListPictureSetContent() {
		if (jListPictureSetContent == null) {
			jListPictureSetContent = new JList(new Object[] { "Apfel",
					"Kürbis", "Paprika", "Tomate" });
			jListPictureSetContent
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListPictureSetContent.setLayoutOrientation(JList.VERTICAL);
		}
		return jListPictureSetContent;
	}

	/**
	 * This method initializes jPanelPictureSetContentOptions
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPictureSetContentOptions() {
		if (jPanelPictureSetContentOptions == null) {
			jPanelPictureSetContentOptions = new JPanel();
			jPanelPictureSetContentOptions.setLayout(new FlowLayout());
			jPanelPictureSetContentOptions.add(
					getJButtonPictureSetContentAdd(), null);
			jPanelPictureSetContentOptions.add(
					getJButtonPictureSetContentDelete(), null);
			jPanelPictureSetContentOptions.add(
					getJButtonPictureSetContentRefresh(), null);
		}
		return jPanelPictureSetContentOptions;
	}

	/**
	 * This method initializes jButtonPictureSetContentAdd
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetContentAdd() {
		if (jButtonPictureSetContentAdd == null) {
			jButtonPictureSetContentAdd = new JButton();
			jButtonPictureSetContentAdd.setText("Hinzufügen");
		}
		return jButtonPictureSetContentAdd;
	}

	/**
	 * This method initializes jButtonPictureSetContentDelete
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetContentDelete() {
		if (jButtonPictureSetContentDelete == null) {
			jButtonPictureSetContentDelete = new JButton();
			jButtonPictureSetContentDelete.setText("Entfernen");
		}
		return jButtonPictureSetContentDelete;
	}

	/**
	 * This method initializes jButtonPictureSetContentRefresh
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPictureSetContentRefresh() {
		if (jButtonPictureSetContentRefresh == null) {
			jButtonPictureSetContentRefresh = new JButton();
			jButtonPictureSetContentRefresh.setText("Aktualisieren");
		}
		return jButtonPictureSetContentRefresh;
	}

	/**
	 * This method initializes jPanelPictureSetActive
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPictureSetActive() {
		if (jPanelPictureSetActive == null) {
			jPanelPictureSetActive = new JPanel();

			GroupLayout jPanelPictureSetActiveLayout = new GroupLayout(
					jPanelPictureSetActive);

			jPanelPictureSetActiveLayout
					.setHorizontalGroup(jPanelPictureSetActiveLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									jPanelPictureSetActiveLayout
											.createSequentialGroup()
											.addContainerGap().addComponent(
													getJListPictureSetActive(),
													GroupLayout.DEFAULT_SIZE,
													528, Short.MAX_VALUE)
											.addContainerGap()));
			jPanelPictureSetActiveLayout
					.setVerticalGroup(jPanelPictureSetActiveLayout
							.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									jPanelPictureSetActiveLayout
											.createSequentialGroup()
											.addContainerGap().addComponent(
													getJListPictureSetActive(),
													GroupLayout.DEFAULT_SIZE,
													451, Short.MAX_VALUE)
											.addContainerGap()));

			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Bildmenge: Bla");
			jPanelPictureSetActive.setBorder(title);

			jPanelPictureSetActive.setLayout(jPanelPictureSetActiveLayout);

			jPanelPictureSetActive.add(getJListPictureSetActive());

			jPanelPictureSetActive.setMinimumSize(new Dimension(220, 500));
			jPanelPictureSetActive.setPreferredSize(new Dimension(220, 500));
		}
		return jPanelPictureSetActive;
	}

	/**
	 * This method initializes jListPictureSetActive
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListPictureSetActive() {
		if (jListPictureSetActive == null) {
			jListPictureSetActive = new JList(new Object[] { "Apfel", "Kürbis",
					"Paprika", "Tomate" });
			jListPictureSetActive
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListPictureSetActive.setLayoutOrientation(JList.VERTICAL);
		}
		return jListPictureSetActive;
	}

	/**
	 * This method initializes jPanelReport
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelReport() {
		if (jPanelReport == null) {
			jPanelReport = new JPanel();

			GroupLayout jPanelReportLayout = new GroupLayout(jPanelReport);

			jPanelReportLayout
					.setHorizontalGroup(jPanelReportLayout.createParallelGroup(
							GroupLayout.Alignment.LEADING).addGroup(
							jPanelReportLayout.createSequentialGroup()
									.addContainerGap().addComponent(
											getJListReport(),
											GroupLayout.DEFAULT_SIZE, 208,
											Short.MAX_VALUE).addContainerGap()));
			jPanelReportLayout
					.setVerticalGroup(jPanelReportLayout.createParallelGroup(
							GroupLayout.Alignment.LEADING).addGroup(
							GroupLayout.Alignment.TRAILING,
							jPanelReportLayout.createSequentialGroup()
									.addContainerGap().addComponent(
											getJListReport(),
											GroupLayout.DEFAULT_SIZE, 196,
											Short.MAX_VALUE).addContainerGap()));

			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Auswertung");
			jPanelReport.setBorder(title);

			jPanelReport.setLayout(jPanelReportLayout);

			jPanelReport.add(getJListReport());

			jPanelReport.setMinimumSize(new Dimension(250, 245));
			jPanelReport.setPreferredSize(new Dimension(250, 245));
		}
		return jPanelReport;
	}

	/**
	 * This method initializes jListReport
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJListReport() {
		if (jListReport == null) {
			jListReport = new JList(new Object[] { "Apfel", "Kürbis",
					"Paprika", "Tomate" });
			jListReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListReport.setLayoutOrientation(JList.VERTICAL);
		}
		return jListReport;
	}

	/**
	 * This method initializes jPanelExif
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelExif() {
		if (jPanelExif == null) {
			jPanelExif = new JPanel();

			GroupLayout jPanelExifLayout = new GroupLayout(jPanelExif);

			jPanelExifLayout.setHorizontalGroup(jPanelExifLayout
					.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(
							0, 228, Short.MAX_VALUE).addGroup(
							jPanelExifLayout.createParallelGroup(
									GroupLayout.Alignment.LEADING).addGroup(
									GroupLayout.Alignment.TRAILING,
									jPanelExifLayout.createSequentialGroup()
											.addContainerGap().addComponent(
													this.getJScrollPaneExif(),
													GroupLayout.DEFAULT_SIZE,
													208, Short.MAX_VALUE)
											.addContainerGap())));
			jPanelExifLayout.setVerticalGroup(jPanelExifLayout
					.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(
							0, 222, Short.MAX_VALUE).addGroup(
							jPanelExifLayout.createParallelGroup(
									GroupLayout.Alignment.LEADING).addGroup(
									jPanelExifLayout.createSequentialGroup()
											.addContainerGap().addComponent(
													this.getJScrollPaneExif(),
													GroupLayout.DEFAULT_SIZE,
													200, Short.MAX_VALUE)
											.addContainerGap())));

			jPanelExif.setLayout(jPanelExifLayout);

			TitledBorder title = BorderFactory.createTitledBorder(BorderFactory
					.createEmptyBorder(), "Exif-Daten");
			jPanelExif.setBorder(title);

			jPanelExif.add(this.getJScrollPaneExif());

			jPanelReport.setMinimumSize(new Dimension(250, 245));
			jPanelReport.setPreferredSize(new Dimension(250, 245));
		}
		return jPanelExif;
	}

	/**
	 * This method initializes jScrollPaneExif
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneExif() {
		if (jScrollPaneExif == null) {
			jScrollPaneExif = new JScrollPane();
			jScrollPaneExif.setViewportView(getJTableExif());
		}
		return jScrollPaneExif;
	}

	/**
	 * This method initializes jTableExif
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableExif() {
		if (jTableExif == null) {
			String[] columnNames = { "First Name", "Last Name" };
			Object[][] data = { { "Mary", "Campione" }, { "Alison", "Huml" },
					{ "Kathy", "Walrath" }, { "Sharon", "Zakhour" },
					{ "Philip", "Milne" } };

			jTableExif = new JTable(data, columnNames);
		}
		return jTableExif;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setTitle("Projektansicht für "
				+ ((ProjectViewModel) o).getProjectName());
		repaint();
	}
}