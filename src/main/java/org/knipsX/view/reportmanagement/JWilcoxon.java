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

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.KnipsX;
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

    private static final long serialVersionUID = -7288529798669810178L;

    private final Logger logger = Logger.getLogger(KnipsX.class);

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
        this.title = Messages.getString("JWilcoxon.6");

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        final JPanel singlePanel = new JPanel();
        singlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        singlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        singlePanel.setLayout(new GridLayout(4, 2, 20, 20));

        this.addWilcoxonStatus(singlePanel);
        this.addWilcoxonTestType(singlePanel);
        this.addWilcoxonSignificanceSlider(singlePanel);

        final JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));
        this.addImageSetErrorMessage(mainpanel);
        this.addNonOrdinaleErrorMessage(mainpanel);

        mainpanel.add(singlePanel);
        mainpanel.add(Box.createVerticalGlue());

        this.add(mainpanel);
        this.setEnabled(false);
        this.fillViewWithModelInfo();
    }

    /* add non ordinal error message label */
    private void addNonOrdinaleErrorMessage(final JPanel mainPanel) {
        this.errorNonOrdinalMessageLabel = new JLabel();
        this.errorNonOrdinalMessageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(this.errorNonOrdinalMessageLabel);
    }

    /* add image set error message label */
    private void addImageSetErrorMessage(final JPanel mainPanel) {
        this.errorImageSetMessageLabel = new JLabel();
        this.errorImageSetMessageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(this.errorImageSetMessageLabel);
    }

    /* add wilcoxon significance slider elements to the specified panel */
    private void addWilcoxonSignificanceSlider(final JPanel singlePanel) {
        singlePanel.add(new JLabel(Messages.getString("JWilcoxon.7")));

        this.significanceSlider = new JSignifanceSlider();
        this.significanceSlider.setAlignmentX(Component.LEFT_ALIGNMENT);

        singlePanel.add(this.significanceSlider);
    }

    /* add wilcoxon status elements to the specified panel */
    private void addWilcoxonStatus(final JPanel singlePanel) {
        singlePanel.add(new JLabel(Messages.getString("JWilcoxon.8")));

        this.wilcoxonCheckBox = new JCheckBox();

        singlePanel.add(this.wilcoxonCheckBox);
    }

    /* add wilcoxon test type elements to the specified panel */
    private void addWilcoxonTestType(final JPanel singlePanel) {
        singlePanel.add(new JLabel(Messages.getString("JWilcoxon.9")));

        this.wilcoxoncombobox = new JComboBox(WilcoxonTestType.values());
        this.wilcoxoncombobox.setSelectedItem(WilcoxonTestType.LESS);

        singlePanel.add(this.wilcoxoncombobox);
    }

    @Override
    public void fillViewWithModelInfo() {

        if (ReportHelper.getCurrentModel() != null) {

            if (ReportHelper.getCurrentModel() instanceof BoxplotModel) {
                final WilcoxonTest wilcoxonTest = ((BoxplotModel) ReportHelper.getCurrentModel()).getWilcoxonTest();

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
     * Returns the specified statistical significance value.
     * 
     * @return the statistical significance.
     */
    public float getStatisticalSignificance() {
        return this.significanceSlider.getValue();
    }

    /**
     * Returns the specified status of the wilcoxon test.
     * 
     * @return the status.
     */
    public boolean getStatus() {
        return this.wilcoxonCheckBox.isSelected();
    }

    /**
     * Returns the specified statistical wilcoxon test type.
     * 
     * @return the wilcoxon test type.
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
    public void setEnabled(final boolean enabledStatus) {
        this.wilcoxoncombobox.setEnabled(enabledStatus);
        this.significanceSlider.setEnabled(enabledStatus);
        this.wilcoxonCheckBox.setEnabled(enabledStatus);
    }

    /**
     * Specifies if this pane should show the error message indicating that there were not
     * exactly two image sets associated with the current report.
     * 
     * @param enabled
     *            true if the error message should not be show, false otherwise.
     */
    public void setImageSetEnabled(final boolean enabled) {

        if (enabled) {
            this.errorImageSetMessageLabel.setIcon(null);
            this.errorImageSetMessageLabel.setText(Messages.getString("JWilcoxon.10"));
        } else {
            this.errorImageSetMessageLabel.setText(Messages.getString("JWilcoxon.11")
                    + Messages.getString("JWilcoxon.12"));

            try {
                this.errorImageSetMessageLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
            } catch (final FileNotFoundException e) {
                this.logger.error(e.getMessage());
            }
        }
        this.errorImageSet = enabled;

        if (this.errorImageSet && this.errorNonOrdinal) {
            this.setEnabled(enabled);
        }
    }

    /**
     * Specifies if this pane should show the error message indicating that there was no
     * ordinal EXIF parameter selected.
     * 
     * @param enabled
     *            true if the error message should not be show, false otherwise.
     */
    public void setOrdianlEnabled(final boolean enabled) {
        
        if (enabled) {
            this.errorNonOrdinalMessageLabel.setIcon(null);
            this.errorNonOrdinalMessageLabel.setText(Messages.getString("JWilcoxon.14"));
        } else {

            try {
                this.errorNonOrdinalMessageLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
            } catch (final FileNotFoundException e) {
                this.logger.error(e.getMessage());
            }
            this.errorNonOrdinalMessageLabel.setText(Messages.getString("JWilcoxon.16"));
        }
        this.errorNonOrdinal = enabled;
        
        if (this.errorImageSet && this.errorNonOrdinal) {
            this.setEnabled(enabled);
        }
    }

    /**
     * 
     * This class implements a extends JSlider which is capable of handling
     * float numbers.
     * 
     * @author David Kaufman
     * 
     */
    private class JFloatSlider extends JSlider {

        private static final long serialVersionUID = -3286423422904737235L;
        
        /* this variable defines how long the decimal value will be */
        private static final int DECIMALPLACE = 2;

        /**
         * This constructor creates a JFloatSlider and configures it with the specified values.
         * 
         * @param min
         *            the minimum value the float slider can display.
         * @param max
         *            the maximum value the float slider can display.
         * @param value
         *            the current value.
         */
        public JFloatSlider(final float min, final float max, final float value) {
            super((int) (min * Math.pow(10, JFloatSlider.DECIMALPLACE)), (int) (max * Math.pow(10,
                    JFloatSlider.DECIMALPLACE)), (int) (value * Math.pow(10, JFloatSlider.DECIMALPLACE)));

            this.setMajorTickSpacing((int) Math.pow(10, JFloatSlider.DECIMALPLACE));
            this.setMinorTickSpacing((int) Math.pow(10, JFloatSlider.DECIMALPLACE));
            this.setPaintTicks(true);
        }

        /**
         * Returns the amount of decimal places the JFloatSlider can display.
         * 
         * @return the amount of decimal places.
         */
        public int getDecimalPlace() {
            return JFloatSlider.DECIMALPLACE;
        }

        /**
         * Returns the current float value of the JFloatSlider.
         * 
         * @return the float value.
         */
        public float getFloatValue() {
            return this.getValue() / (float) Math.pow(10, JFloatSlider.DECIMALPLACE);
        }

        /**
         * Sets the JFloatSlider to the specified value.
         * 
         * @param value
         *            the value the JFloatSlider should have.
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
    private class JSignifanceSlider extends JPanel {

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
                public void keyReleased(final KeyEvent keyEvent) {

                    /* if enter key is pressed, set float slider to the appropriate value */
                    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
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
}