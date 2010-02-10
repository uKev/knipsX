package org.knipsX.view.reportmanagement;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.WilcoxonTest;
import org.knipsX.model.reportmanagement.WilcoxonTestType;
import org.knipsX.utils.Resource;

/**
 * This class represents the panel where the user is able to configure
 * the wilcoxon test for the boxplot report.
 * 
 * @author David Kaufman
 * 
 */
public class JWilcoxon extends JAbstractSinglePanel {

    /**
     * 
     * This class implements a extends JSlider which is capable of handling
     * float numbers.
     * 
     * @author David Kaufman
     * 
     */
    public class JFloatSlider extends JSlider {

        private static final long serialVersionUID = 1L;

        /* This variable defines how long the decimal value will be */
        private static final int DECIMALPLACE = 2;

        /**
         * This constructor creates a JFloatSlider and configures it with the specified values
         * 
         * @param min
         *            the minimum value the float slider can display
         * @param max
         *            the maximum value the float slider can display
         * @param value
         *            the current value
         */
        public JFloatSlider(final float min, final float max, final float value) {
            super((int) (min * Math.pow(10, JFloatSlider.DECIMALPLACE)), (int) (max * Math.pow(10,
                    JFloatSlider.DECIMALPLACE)), (int) (value * Math.pow(10, JFloatSlider.DECIMALPLACE)));
            this.setMajorTickSpacing((int) Math.pow(10, JFloatSlider.DECIMALPLACE));
            this.setMinorTickSpacing((int) Math.pow(10, JFloatSlider.DECIMALPLACE));
            this.setPaintTicks(true);
        }

        /**
         * Returns the amount of decimal places the JFloatSlider can display
         * 
         * @return the amount of decimal places
         */
        public int getDecimalPlace() {
            return JFloatSlider.DECIMALPLACE;
        }

        /**
         * Returns the current float value of the JFloatSlider *
         * 
         * @return the float value
         */
        public float getFloatValue() {
            return this.getValue() / (float) Math.pow(10, JFloatSlider.DECIMALPLACE);
        }

        /**
         * Sets the JFloatSlider to the specified value
         * 
         * @param value
         *            the value the JFloatSlider should have
         */
        public void setFloatValue(final float value) {
            this.setValue((int) (value * Math.pow(10, JFloatSlider.DECIMALPLACE)));
        }
    }

    /**
     * This class combines a float slider and simple text field which makes it possible to interact with
     * both of them. Entering a float value in the text field updates the slider and vice versa.
     * 
     * @author David Kaufman
     * 
     */
    public class JSignifanceSlider extends JPanel {

        private static final long serialVersionUID = 3080047522555659996L;
        private final JFloatSlider floatSlider = new JFloatSlider(1f, 10f, 1f);
        private final JTextField percentField;

        /**
         * This constructor creates and configures the JSignificanceSlider for proper use
         */
        public JSignifanceSlider() {

            this.percentField = new JTextField(this.floatSlider.getDecimalPlace() + 2);
            this.percentField.setHorizontalAlignment(SwingConstants.RIGHT);
            this.percentField.setText(this.floatSlider.getFloatValue() + " %");

            this.floatSlider.addChangeListener(new ChangeListener() {

                public void stateChanged(final ChangeEvent e) {
                    final float temp = JSignifanceSlider.this.floatSlider.getFloatValue();
                    JSignifanceSlider.this.percentField.setText(String.valueOf(temp) + " %");
                }
            });

            this.percentField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(final KeyEvent ke) {
                    /* If enter key is pressed, set float slider to the appropriate value */
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        final String typed = JSignifanceSlider.this.percentField.getText();
                        JSignifanceSlider.this.floatSlider.setFloatValue(Float.parseFloat(typed.replaceAll("%", "")
                                .replaceAll(",", ".")));
                    }
                }
            });

            this.add(this.percentField);
            this.add(this.floatSlider);
        }

        /**
         * Return the float value of the slider
         * 
         * @return the float value of the slider
         */
        public float getValue() {
            return this.floatSlider.getFloatValue();
        }

        @Override
        public void setEnabled(final boolean enabled) {
            this.floatSlider.setEnabled(enabled);
            this.percentField.setEnabled(enabled);
        }

    }

    private static final long serialVersionUID = -7288529798669810178L;

    private JComboBox wilcoxoncombobox;

    private JSignifanceSlider significanceSlider;

    private JCheckBox wilcoxonCheckBox;

    private JLabel errorImageSetMessageLabel;
    private boolean errorImageSet = false;

    private JLabel errorNonOrdinalMessageLabel;
    private boolean errorNonOrdinal = false;

    /**
     * Constructor which initialized this wilcoxon test panel
     * 
     */
    public JWilcoxon() {

        /* Set the title name of this panel */
        // INTERNATIONALIZE
        this.title = "Wilcoxon Test";

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        final JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));

        final JPanel singlepanel = new JPanel();
        singlepanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        singlepanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        singlepanel.setLayout(new GridLayout(4, 2, 20, 20));

        /* Add wilcoxon status elements */
        this.addWilcoxonStatus(singlepanel);

        /* Add wilcoxon test type elements */
        this.addWilcoxonTestType(singlepanel);

        /* Add wilcoxon significance slider elements */
        this.addWilcoxonSignificanceSlider(singlepanel);

        mainpanel.add(singlepanel);

        /* Add image set error message label */
        addImageSetErrorMessage(mainpanel);

        /* Add non ordinal error message label */
        addNonOrdinaleErrorMessage(mainpanel);

        mainpanel.add(Box.createVerticalGlue());
        this.add(mainpanel);
        
        /* Disable this panel. Note that other panes will active the pane if
         * all required conditions are met 
         */
        this.setEnabled(false);

        this.fillViewWithModelInfo();

    }

    /* Add non ordinal error message label */
    private void addNonOrdinaleErrorMessage(JPanel mainpanel) {
        this.errorNonOrdinalMessageLabel = new JLabel();
        this.errorNonOrdinalMessageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainpanel.add(errorNonOrdinalMessageLabel);
    }

    /* Add image set error message label */
    private void addImageSetErrorMessage(JPanel mainpanel) {
        this.errorImageSetMessageLabel = new JLabel();
        this.errorImageSetMessageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainpanel.add(this.errorImageSetMessageLabel);
    }

    /* Add wilcoxon significance slider elements to the specified panel */
    private void addWilcoxonSignificanceSlider(final JPanel singlepanel) {
        // INTERNATIONALIZE
        singlepanel.add(new JLabel("Signifikanzniveau (1-10 %)"));
        this.significanceSlider = new JSignifanceSlider();
        this.significanceSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        singlepanel.add(this.significanceSlider);
    }

    /* Add wilcoxon status elements to the specified panel */
    private void addWilcoxonStatus(final JPanel singlepanel) {
        // INTERNATIONALIZE
        singlepanel.add(new JLabel("Wilcoxon Test aktiviert"));
        this.wilcoxonCheckBox = new JCheckBox();
        singlepanel.add(this.wilcoxonCheckBox);
    }

    /* Add wilcoxon test type elements to the specified panel */
    private void addWilcoxonTestType(final JPanel singlepanel) {
        // INTERNATIONALIZE
        final JLabel testType = new JLabel("Testart");
        singlepanel.add(testType);
        this.wilcoxoncombobox = new JComboBox(WilcoxonTestType.values());
        this.wilcoxoncombobox.setSelectedItem(WilcoxonTestType.LESS);
        singlepanel.add(this.wilcoxoncombobox);
    }

    @Override
    public void fillViewWithModelInfo() {
        if (ReportHelper.getCurrentModel() != null) {
            if (ReportHelper.getCurrentModel() instanceof BoxplotModel) {
                WilcoxonTest wilcoxonTest = ((BoxplotModel) ReportHelper.getCurrentModel()).getWilcoxonTest();
                
                if (wilcoxonTest != null) {
                    this.wilcoxonCheckBox.setSelected(wilcoxonTest.isActive());
                    this.significanceSlider.floatSlider.setFloatValue((float) wilcoxonTest.getSignificance());
                    if (wilcoxonTest.getWilcoxonTestType() != null) {
                        this.wilcoxoncombobox.setSelectedItem(wilcoxonTest.getWilcoxonTestType());
                    }                    
                }                
            }
        }
    }

    /**
     * Returns the specified statistical significance value
     * 
     * @return the statistical significance
     */
    public float getStatisticalSignificance() {
        return this.significanceSlider.getValue();
    }

    /**
     * Returns the specified status of the wilcoxon test
     * 
     * @return the status
     */
    public boolean getStatus() {
        return this.wilcoxonCheckBox.isSelected();
    }

    /**
     * Returns the specified statistical wilcoxon test type
     * 
     * @return the wilcoxon test type
     */
    public WilcoxonTestType getTestType() {
        return (WilcoxonTestType) this.wilcoxoncombobox.getSelectedItem();
    }

    @Override
    public boolean isDiagramDisplayable() {
        return true;
    }

    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            this.wilcoxoncombobox.setEnabled(true);
            this.significanceSlider.setEnabled(true);
            this.wilcoxonCheckBox.setEnabled(true);
        } else {
            this.wilcoxoncombobox.setEnabled(false);
            this.significanceSlider.setEnabled(false);
            this.wilcoxonCheckBox.setEnabled(false);
        }
    }

    /**
     * Specifies if this pane should show the error message indicating that there were not
     * exactly two image sets associated with the current report
     * 
     * @param enabled
     *            true if the error message should not be show, false otherwise
     */
    public void setImageSetEnabled(final boolean enabled) {
        if (enabled) {
            this.errorImageSetMessageLabel.setIcon(null);
            this.errorImageSetMessageLabel.setText("");

        } else {
            // INTERNATIONALIZE
            this.errorImageSetMessageLabel.setText("Es müssen genau zwei Bildmengen ausgewählt sein, "
                    + "damit der Wilcoxon Test aktiviert werden kann.");
            try {
                this.errorImageSetMessageLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        this.errorImageSet = enabled;
        if (this.errorImageSet && this.errorNonOrdinal) {
            this.setEnabled(enabled);
        }
    }

    /**
     * Specifies if this pane should show the error message indicating that there was no
     * ordinal EXIF parameter selected
     * 
     * @param enabled
     *            true if the error message should not be show, false otherwise
     */
    public void setOrdianlEnabled(final boolean enabled) {
        if (enabled) {
            this.errorNonOrdinalMessageLabel.setIcon(null);
            this.errorNonOrdinalMessageLabel.setText("");

        } else {
            try {
                this.errorNonOrdinalMessageLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // INTERNATIONALIZE
            this.errorNonOrdinalMessageLabel.setText("Es muss ein ordinaler Exif-Paramter ausgewählt werden.");

        }

        this.errorNonOrdinal = enabled;
        if (this.errorImageSet && this.errorNonOrdinal) {
            this.setEnabled(enabled);
        }
    }

}
