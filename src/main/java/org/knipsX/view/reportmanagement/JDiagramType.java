package org.knipsX.view.reportmanagement;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.controller.reportmanagement.DiagramTypeSelectController;
import org.knipsX.utils.Resource;
import org.knipsX.utils.Validator;

/**
 * This class represents the panel where the user can choose the diagram type
 * and assign the report name and report description.
 * 
 * Note that is panel is present in every report compilation.
 * 
 * @author David Kaufman
 * 
 */
public class JDiagramType extends JAbstractSinglePanel {

    private JList diagramTypeList;
    
    private JTextArea reportDescription;
    
    private JTextField reportName;
    
    private JLabel reportNameErrorLabel;
    private JLabel diagramPreviewErrorLabel;
    
    private static final long serialVersionUID = 6811769925471581664L;    
    
    private boolean java3DInstalled = false;

    /**
     * Constructor which initialized this diagram selection panel.
     * 
     * @param diagramDescription
     *            The diagram description which is registered with this panel.
     */
    public JDiagramType(final String diagramDescription) {

        /* set the title name of this panel */
        this.title = Messages.getString("JDiagramType.0");

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        /* initialize the left panel */
        final JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.PAGE_AXIS));

        /* add the report name label and text field */
        this.addReportNameAndReportTextField(leftpanel);

        /* add the report description text field and text area */
        this.addReportDescriptionAndTextArea(leftpanel);

        /* add the diagram type label and list */
        this.addDiagramTypeLabelAndList(leftpanel);

        /* initialize the right panel */
        final JPanel rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS));

        /* add diagram preview */
        this.addDiagramPreview(rightpanel);

        /* add diagram description */
        this.addDiagramDescription(rightpanel, diagramDescription);

        /* add them to the main layout */
        this.add(leftpanel);
        this.add(Box.createRigidArea(new Dimension(25, 20)));
        this.add(Box.createHorizontalGlue());
        this.add(rightpanel);

        this.fillViewWithModelInfo();
    }

    /* Add diagram description to the specified panel */
    private void addDiagramDescription(final JPanel rightpanel, final String diagramDescription) {
        final JTextArea mytextarea = new JTextArea(diagramDescription);
        
        mytextarea.setEditable(false);
        mytextarea.setColumns(20);
        mytextarea.setRows(5);
        mytextarea.setWrapStyleWord(true);
        mytextarea.setLineWrap(true);
        
        final JScrollPane rightscrollpane = new JScrollPane(mytextarea);
        
        rightscrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        rightscrollpane.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        rightpanel.add(mytextarea);
        rightpanel.add(rightscrollpane);
    }

    /* Add diagram preview to the specified panel */
    private void addDiagramPreview(final JPanel rightpanel) {
        this.diagramPreviewErrorLabel = new JLabel();
        this.diagramPreviewErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(this.diagramPreviewErrorLabel);
        /* Add a spacer to relax the layout */
        rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        try {
            final Component diagramView = ReportHelper.getCurrentReport().getDiagramView();
            diagramView.setPreferredSize(new Dimension(300, 150));
            rightpanel.add(diagramView);
            /* Add a spacer to relax the layout */
            rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        } catch (final UnsatisfiedLinkError linkError) {
            // TODO only catch java 3D link error
            this.java3DInstalled = false;
            return;
        } catch (final NoClassDefFoundError e) {
            // TODO only catch java 3D class definition error
            this.java3DInstalled = false;
            return;
        }

        this.java3DInstalled = true;
    }

    /* Add the diagram type label and list to the specified panel */
    private void addDiagramTypeLabelAndList(final JPanel leftpanel) {
        //
        final JLabel diagramtypelabel = new JLabel(Messages.getString("JDiagramType.2"));

        diagramtypelabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftpanel.add(diagramtypelabel);

        final JPanel diagramTypePanel = new JPanel();

        /*
         * Put the JList inside a separate JPanel which uses a grid layout
         * to maximize the list inside the diagramTypePanel
         */
        diagramTypePanel.setLayout(new GridLayout(1, 1));

        this.diagramTypeList = new JList(ReportHelper.values());
        this.diagramTypeList.setSelectedValue(ReportHelper.getCurrentReport(), true);
        this.diagramTypeList.addListSelectionListener(new DiagramTypeSelectController(this));
        
        /* Assign the custom list cell render */
        this.diagramTypeList.setCellRenderer(new ReportTypeRenderer());
        this.diagramTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.diagramTypeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        diagramTypePanel.add(this.diagramTypeList);
        final JScrollPane diagramScrollPane = new JScrollPane(diagramTypePanel);
        
        /* Alter alignment to accommodate layout */
        diagramScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(diagramScrollPane);
        leftpanel.add(Box.createVerticalGlue());
    }

    /* Add the report description text field and text area to the specified panel */
    private void addReportDescriptionAndTextArea(final JPanel leftpanel) {
        //
        final JLabel reportdescriptionlabel = new JLabel(Messages.getString("JDiagramType.3"));
        /* Alter alignment to accommodate layout */
        reportdescriptionlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(reportdescriptionlabel);
        this.reportDescription = new JTextArea();
        /* Alter alignment to accommodate layout */
        this.reportDescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.reportDescription.setColumns(20);
        this.reportDescription.setLineWrap(true);
        this.reportDescription.setRows(10);
        this.reportDescription.setWrapStyleWord(true);
        final JScrollPane scrollpane = new JScrollPane(this.reportDescription);
        scrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        /* Alter alignment to accommodate layout */
        scrollpane.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(scrollpane);
        leftpanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    /* Add the report name label and text field to the specified panel */
    private void addReportNameAndReportTextField(final JPanel leftpanel) {
        //
        final JLabel reportnamelabel = new JLabel(Messages.getString("JDiagramType.4"));
        reportnamelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(reportnamelabel);

        final JPanel reportNamePanel = new JPanel();
        reportNamePanel.setLayout(new BoxLayout(reportNamePanel, BoxLayout.X_AXIS));

        this.reportName = new JTextField();
        this.reportName.setPreferredSize(new Dimension(20, 20));
        this.reportName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        /*
         * Assign KeyAdapter to verify and thus revalidate the report
         * Note that this method is called every time the user enters something
         * in the report name text field
         */
        this.reportName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent ke) {
                JDiagramType.this.revalidateReport();
            }
        });

        reportNamePanel.add(this.reportName);
        this.reportNameErrorLabel = new JLabel();
        reportNamePanel.add(this.reportNameErrorLabel);
        
        /* Alter alignment to accommodate layout */
        reportNamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(reportNamePanel);
        leftpanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    /**
     * Returns the report description
     * 
     * @return the report description
     */
    public String getReportDescription() {
        return this.reportDescription.getText();
    }

    /**
     * Returns the report name
     * 
     * @return the report name
     */
    public String getReportName() {
        return this.reportName.getText();
    }

    /**
     * Returns the currently selected index in the diagram typ
     * selection list
     * 
     * @return the current selection index
     */
    public int getSelectedDiagramType() {
        return this.diagramTypeList.getSelectedIndex();
    }

    @Override
    public boolean isDiagramDisplayable() {
        final boolean displayable = this.checkReportName() & this.checkJava3DInstallation();

        if (displayable) {
            this.resetIcon();
        } else {
            this.showErrorIcon();
        }

        return displayable;
    }

    @Override
    public boolean isDiagramSaveable() {
        return this.checkReportName();
    }

    /* Checks if report name is valid if it is invalid display an error message */
    private boolean checkReportName() {

        final Logger logger = Logger.getLogger(this.getClass());
        logger.trace(Messages.getString("JDiagramType.5") + Validator.isStringOk(this.reportName.getText()));

        if (Validator.isStringOk(this.reportName.getText())) {
            this.reportNameErrorLabel.setIcon(null);
            this.reportNameErrorLabel.setText(null);
            return true;
        } else {

            try {
                this.reportNameErrorLabel
                        .setIcon(Resource
                                .createImageIcon(
                                        Messages.getString("JDiagramType.6"), Messages.getString("JDiagramType.7"), Messages.getString("JDiagramType.8"))); //$NON-NLS-2$ //$NON-NLS-3$
            } catch (final FileNotFoundException e) {
                logger.debug(e.toString());
            }
            //
            this.reportNameErrorLabel.setToolTipText(Messages.getString("JDiagramType.9"));
            return false;
        }
    }

    private boolean checkJava3DInstallation() {
        final Logger logger = Logger.getLogger(this.getClass());

        if (this.java3DInstalled) {
            this.diagramPreviewErrorLabel.setText(Messages.getString("JDiagramType.1"));
            this.diagramPreviewErrorLabel.setIcon(null);
            return true;
        } else {
            try {
                this.diagramPreviewErrorLabel.setIcon(Resource.createImageIcon(Messages.getString("JDiagramType.6"),
                        Messages.getString("JDiagramType.7"), Messages.getString("JDiagramType.8")));
            } catch (final FileNotFoundException e) {
                logger.debug(e.toString());
            }

            this.diagramPreviewErrorLabel.setText("Java 3D ist nicht installiert");
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillViewWithModelInfo() {
        if (ReportHelper.getCurrentModel() != null) {
            this.reportName.setText(ReportHelper.getCurrentModel().getReportName());
            this.reportDescription.setText(ReportHelper.getCurrentModel().getReportDescription());

        }
    }

}

/**
 * This class is responsible for rendering the elements in the diagram type selection list
 * 
 * @author David Kaufman
 * 
 */
class ReportTypeRenderer implements ListCellRenderer {

    /* Generate a default renderer */
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    /**
     * {@inheritDoc}
     */
    public Component getListCellRendererComponent(final JList list, final Object value, final int index,
            final boolean isSelected, final boolean cellHasFocus) {
        String theText = null;

        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        /* If value is a valid report helper enum get the its string representation */
        if (value instanceof ReportHelper) {
            final ReportHelper myReport = (ReportHelper) value;
            theText = myReport.toString();
        }

        renderer.setText(theText);

        return renderer;
    }
}
