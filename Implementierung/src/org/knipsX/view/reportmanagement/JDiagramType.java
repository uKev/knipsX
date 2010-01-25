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

import org.knipsX.controller.reportmanagement.DiagramTypeSelectController;
import org.knipsX.utils.Resource;

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

    private JTextField reportname;
    private JLabel reportNameErrorLabel;
    private JTextArea reportdescription;
    private static final long serialVersionUID = 1L;
    private JList diagramTypeList;

    /**
     * Constructor which initialized this diagram selection panel
     * 
     * @param diagramDescription
     *            The diagram description which is registered with this panel.
     */

    public JDiagramType(final String diagramDescription) {

        /* Set the title name of this panel */
        //INTERNATIONALIZE
        this.title = "Diagrammtyp";

        final BoxLayout container = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(container);

        /* Initialize the left panel */
        final JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.PAGE_AXIS));

        /* Add the report name label and text field */
        this.addReportNameAndReportTextField(leftpanel);

        /* Add the report description text field and text area */
        this.addReportDescriptionAndTextArea(leftpanel);

        /* Add the diagram type label and list */
        this.addDiagramTypeLabelAndList(leftpanel);

        /* Initialize the right panel */
        final JPanel rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.PAGE_AXIS));

        /* Add diagram preview */
        this.addDiagramPreview(rightpanel);

        /* Add diagram description */
        this.addDiagramDescription(rightpanel, diagramDescription);

        /* Add them to the main layout */
        this.add(leftpanel);
        this.add(Box.createRigidArea(new Dimension(25, 20)));
        this.add(Box.createHorizontalGlue());
        this.add(rightpanel);

        fillViewWithModelInfo();

    }

    /* Add diagram description to the specified panel */
    private void addDiagramDescription(final JPanel rightpanel, final String diagramDescription) {
        final JTextArea mytextarea = new JTextArea(diagramDescription);
        mytextarea.setEditable(false);
        mytextarea.setColumns(20);
        mytextarea.setRows(5);
        mytextarea.setWrapStyleWord(true);
        mytextarea.setLineWrap(true);
        rightpanel.add(mytextarea);
        final JScrollPane rightscrollpane = new JScrollPane(mytextarea);
        rightscrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        rightscrollpane.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightpanel.add(rightscrollpane);
    }

    /* Add diagram preview to the specified panel */
    private void addDiagramPreview(final JPanel rightpanel) {
        final Component diagramView = ReportHelper.getCurrentReport().getDiagramView();
        diagramView.setPreferredSize(new Dimension(300, 150));
        rightpanel.add(diagramView);
        /* Add a spacer to relax the layout */
        rightpanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    /* Add the diagram type label and list to the specified panel */
    private void addDiagramTypeLabelAndList(final JPanel leftpanel) {
        //INTERNATIONALIZE
        final JLabel diagramtypelabel = new JLabel("Diagramtyp");
        
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
        /* Assign the custom list cell render*/
        this.diagramTypeList.setCellRenderer(new ReportTypeRenderer());
        this.diagramTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.diagramTypeList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        diagramTypePanel.add(this.diagramTypeList);
        final JScrollPane diagramScrollPane = new JScrollPane(diagramTypePanel);
        /* Alter alignment to accommodate layout*/
        diagramScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(diagramScrollPane);
        leftpanel.add(Box.createVerticalGlue());
    }

    /* Add the report description text field and text area to the specified panel */
    private void addReportDescriptionAndTextArea(final JPanel leftpanel) {
        //INTERNATIONALIZE
        final JLabel reportdescriptionlabel = new JLabel("Auswertungsbeschreibung");
        /* Alter alignment to accommodate layout*/
        reportdescriptionlabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(reportdescriptionlabel);
        this.reportdescription = new JTextArea();
        /* Alter alignment to accommodate layout*/
        this.reportdescription.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.reportdescription.setColumns(20);
        this.reportdescription.setLineWrap(true);
        this.reportdescription.setRows(10);
        this.reportdescription.setWrapStyleWord(true);
        final JScrollPane scrollpane = new JScrollPane(this.reportdescription);
        scrollpane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));
        /* Alter alignment to accommodate layout*/
        scrollpane.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(scrollpane);
        leftpanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    /* Add the report name label and text field to the specified panel */
    private void addReportNameAndReportTextField(final JPanel leftpanel) {
        //INTERNATIONALIZE
        final JLabel reportnamelabel = new JLabel("Auswertungsname");
        reportnamelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftpanel.add(reportnamelabel);

        final JPanel reportNamePanel = new JPanel();
        reportNamePanel.setLayout(new BoxLayout(reportNamePanel, BoxLayout.X_AXIS));

        this.reportname = new JTextField();
        this.reportname.setPreferredSize(new Dimension(20, 20));
        this.reportname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        
        /* Assign KeyAdapter to verify and thus revalidate the report 
         * Note that this method is called every time the user enters something
         * in the report name text field
         */        
        this.reportname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent ke) {
                JDiagramType.this.revalidateReport();
            }
        });

        reportNamePanel.add(this.reportname);
        this.reportNameErrorLabel = new JLabel();
        reportNamePanel.add(this.reportNameErrorLabel);
        /* Alter alignment to accommodate layout*/
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
        return this.reportdescription.getText();
    }

    /**
     * Returns the report name
     * 
     * @return the report name
     */
    public String getReportName() {
        return this.reportname.getText();
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
        if (this.reportname.getText().length() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDiagramSaveable() {
        if (this.reportname.getText().length() > 0) {
            this.reportNameErrorLabel.setIcon(null);
            this.reportNameErrorLabel.setText(null);
            return true;

        } else {
            try {
                this.reportNameErrorLabel.setIcon(Resource.createImageIcon("../images/userwarning.png", null));
            } catch (FileNotFoundException e) {                
                e.printStackTrace();
            }
            //INTERNATIONALIZE
            this.reportNameErrorLabel
                    .setToolTipText("Um die Auswertung speichern zu können muss ein Auswertungsname definiert werden");
            return false;
        }
    }

    /**
     * {@inheritDoc}}
     */
    public void fillViewWithModelInfo() {
        if (ReportHelper.getCurrentModel() != null) {
            
            this.reportname.setText(ReportHelper.getCurrentModel().getReportName());
            this.reportdescription.setText(ReportHelper.getCurrentModel().getReportDescription());
            
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
