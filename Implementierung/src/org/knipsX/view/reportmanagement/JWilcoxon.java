package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.knipsX.model.reportmanagement.WilcoxonTestType;
import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * This class represents the panel where the user is able to configure
 * the wilcoxon test for the boxplot report.
 * 
 * @author David Kaufman
 * 
 */
public class JWilcoxon extends JAbstractSinglePanel {

    private static final long serialVersionUID = 1L;
    private JWilcoxonTestTypeComboBox wilcoxoncombobox;
    private JSignifanceSlider significanceSlider;
    private JCheckBox wilcoxonCheckBox;
    private JLabel errorMessage;

    public JWilcoxon() {

        this.title = "Wilcoxon Test";

        BoxLayout container = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(container);

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.PAGE_AXIS));

        JPanel singlepanel = new JPanel();
        singlepanel.setAlignmentX(LEFT_ALIGNMENT);
        singlepanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        singlepanel.setLayout(new GridLayout(4, 2, 20, 20));

        singlepanel.add(new JLabel("Wilcoxon Test aktiviert"));
        this.wilcoxonCheckBox = new JCheckBox();
        singlepanel.add(this.wilcoxonCheckBox);

        JLabel testType = new JLabel("Testart");
        singlepanel.add(testType);
        this.wilcoxoncombobox = new JWilcoxonTestTypeComboBox();
        singlepanel.add(this.wilcoxoncombobox);

        singlepanel.add(new JLabel("Signifikanzniveau (1-10 %)"));
        this.significanceSlider = new JSignifanceSlider();
        this.significanceSlider.setAlignmentX(LEFT_ALIGNMENT);
        singlepanel.add(this.significanceSlider);

        mainpanel.add(singlepanel);

        this.errorMessage = new JLabel();
        this.errorMessage.setAlignmentX(LEFT_ALIGNMENT);
        mainpanel.add(this.errorMessage);

        mainpanel.add(Box.createVerticalGlue());
        add(mainpanel);

        setEnabled(false);

        if (ReportHelper.currentModel != null) {
            if (ReportHelper.currentModel instanceof BoxplotModel) {
                this.wilcoxonCheckBox.setSelected(((BoxplotModel) ReportHelper.currentModel).isWilcoxonTestActive());
                this.significanceSlider.floatSlider.setFloatValue(((BoxplotModel) ReportHelper.currentModel)
                        .getWilcoxonSignificance());
                if (((BoxplotModel) ReportHelper.currentModel).getWilcoxonTestType() != null)
                    this.wilcoxoncombobox.setSelectedItem(((BoxplotModel) ReportHelper.currentModel)
                            .getWilcoxonTestType());
            }

        }

    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            this.errorMessage.setIcon(null);
            this.errorMessage.setText("");
            this.wilcoxoncombobox.setEnabled(true);
            this.significanceSlider.setEnabled(true);
            this.wilcoxonCheckBox.setEnabled(true);

        } else {
            this.wilcoxoncombobox.setEnabled(false);
            this.significanceSlider.setEnabled(false);
            this.wilcoxonCheckBox.setEnabled(false);
            this.errorMessage
                    .setText("Es müssen genau zwei Bildmengen ausgewählt sein, damit der Wilcoxon Test aktiviert werden kann");
            this.errorMessage.setIcon(createImageIcon("../../images/userwarning.png", null));
        }
    }

    public class JSignifanceSlider extends JPanel {
        /**
		 * 
		 */
        private static final long serialVersionUID = 1L;
        private JFloatSlider floatSlider = new JFloatSlider(1f, 10f, 1f);
        private JTextField percentField;

        public JSignifanceSlider() {

            this.percentField = new JTextField(this.floatSlider.getDecimalPlace() + 2);
            this.percentField.setHorizontalAlignment(JTextField.RIGHT);
            this.percentField.setText(this.floatSlider.getFloatValue() + " %");

            floatSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    float temp = floatSlider.getFloatValue();
                    percentField.setText(String.valueOf(temp) + " %");
                }
            });

            percentField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        String typed = percentField.getText();
                        floatSlider.setFloatValue(Float.parseFloat(typed.replaceAll("%", "").replaceAll(",", ".")));
                    }
                }
            });

            add(percentField);
            add(floatSlider);
        }

        public float getValue() {
            return this.floatSlider.getFloatValue();
        }

        public void setEnabled(boolean enabled) {
            this.floatSlider.setEnabled(enabled);
            this.percentField.setEnabled(enabled);
        }

    }

    public class JFloatSlider extends JSlider {

        private static final long serialVersionUID = 1L;
        private static final int decimalplace = 2;

        public JFloatSlider(float min, float max, float value) {
            super((int) (min * Math.pow(10, decimalplace)), (int) (max * Math.pow(10, decimalplace)),
                    (int) (value * Math.pow(10, decimalplace)));
            this.setMajorTickSpacing((int) Math.pow(10, decimalplace));
            this.setMinorTickSpacing((int) Math.pow(10, decimalplace));
            this.setPaintTicks(true);
        }

        public int getDecimalPlace() {
            return JFloatSlider.decimalplace;
        }

        public JFloatSlider(float min, float max) {
            super((int) (min * Math.pow(10, decimalplace)), (int) (max * Math.pow(10, decimalplace)));
        }

        public float getFloatValue() {
            return (float) this.getValue() / (float) Math.pow(10, decimalplace);
        }

        public void setFloatValue(float value) {
            setValue((int) (value * Math.pow(10, decimalplace)));
        }
    }

    public class JWilcoxonTestTypeComboBox extends JComboBox {

        private static final long serialVersionUID = 1L;

        public JWilcoxonTestTypeComboBox() {
            super(WilcoxonTestType.values());
            this.setSelectedItem(WilcoxonTestType.LEFT);
        }
    }

    public boolean getStatus() {
        return this.wilcoxonCheckBox.isSelected();
    }

    public WilcoxonTestType getTestType() {
        return (WilcoxonTestType) this.wilcoxoncombobox.getSelectedItem();
    }

    public float getStatisticalSignificance() {
        return this.significanceSlider.getValue();
    }

    @Override
    public boolean isDiagramDisplayable() {
        return true;
    }

    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

}
