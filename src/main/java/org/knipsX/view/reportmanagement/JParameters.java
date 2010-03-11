package org.knipsX.view.reportmanagement;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.knipsX.Messages;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.model.reportmanagement.BoxplotModel;
import org.knipsX.model.reportmanagement.Cluster3DModel;
import org.knipsX.model.reportmanagement.Histogram2DModel;
import org.knipsX.model.reportmanagement.Histogram3DModel;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Resource;

/**
 * This class represents the panel where the user is able to assign a
 * parameter with an optional description to each available axis.
 * 
 * @author David Kaufman
 * 
 */
public class JParameters extends JAbstractSinglePanel {

    private static final long serialVersionUID = -3829461274586923051L;

    private final AxisParameter[] axisParameters = new AxisParameter[ReportHelper.getCurrentReport().getNumberOfAxes()];

    private final JPanel singlePanel;

    final String[] axesDescription = { Messages.getString("JParameters.5"), Messages.getString("JParameters.6"),
            Messages.getString("JParameters.7") };

    /**
     * Constructor which initialized this parameter panel.
     */
    public JParameters() {

        /* set the title name of this panel */
        this.title = Messages.getString("JParameters.8");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.singlePanel = new JPanel();
        this.singlePanel.setLayout(new BoxLayout(this.singlePanel, BoxLayout.PAGE_AXIS));

        /* add the axis parameters to singlepanel */
        this.addAxisParameters();

        this.add(Box.createVerticalGlue());

        /* fill the view with model information */
        this.fillViewWithModelInfo();
    }

    private void addAxisParameters() {

        for (int i = -1; i < ReportHelper.getCurrentReport().getNumberOfAxes(); ++i) {

            if (i >= 0) {
                this.axisParameters[i] = new AxisParameter(this.axesDescription[i]);
                this.singlePanel.add(this.axisParameters[i]);
            } else {
                final JPanel panel = new JPanel();

                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

                /* Set minimum and maximum size to prevent cutting */
                panel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 32));
                panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

                panel.add(new JLabel(Messages.getString("JParameters.9")));
                panel.add(Box.createRigidArea(new Dimension(215, 0)));
                panel.add(new JLabel(Messages.getString("JParameters.10")));

                this.singlePanel.add(panel);
            }
            this.singlePanel.add(Box.createRigidArea(new Dimension(0, 32)));
            this.add(this.singlePanel);
        }
    }

    /* add action listeners, after model has filled view with information */
    private void addActionListenersToComboBox() {

        for (final AxisParameter axisParameter : this.axisParameters) {
            axisParameter.addActionListener();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillViewWithModelInfo() {

        if (ReportHelper.getCurrentModel() != null) {
            final AbstractReportModel currentModel = ReportHelper.getCurrentModel();

            if (currentModel instanceof BoxplotModel) {
                this.axisParameters[0].setAxis(((BoxplotModel) currentModel).getXAxis());
            } else if (currentModel instanceof Histogram2DModel) {
                this.axisParameters[0].setAxis(((Histogram2DModel) currentModel).getXAxis());
            } else if (currentModel instanceof Histogram3DModel) {
                this.axisParameters[0].setAxis(((Histogram3DModel) currentModel).getXAxis());

                if (this.axisParameters.length == 2) {
                    this.axisParameters[1].setAxis(((Histogram3DModel) currentModel).getZAxis());
                }
            } else if (currentModel instanceof Cluster3DModel) {

                if (this.axisParameters.length >= 1) {
                    this.axisParameters[0].setAxis(((Cluster3DModel) currentModel).getXAxis());
                }

                if (this.axisParameters.length >= 2) {
                    this.axisParameters[1].setAxis(((Cluster3DModel) currentModel).getZAxis());
                }

                if (this.axisParameters.length == 3) {
                    this.axisParameters[2].setAxis(((Cluster3DModel) currentModel).getYAxis());
                }
            }
        }
        this.addActionListenersToComboBox();
    }

    /**
     * Returns the EXIF parameters and axes description specified
     * 
     * @return the EXIF parameters and axes description
     */
    public List<Axis> getAxes() {
        final List<Axis> returnAxis = new ArrayList<Axis>();

        for (final AxisParameter axisParameter : this.axisParameters) {
            returnAxis.add(new Axis(axisParameter.getAxisDescription(), axisParameter.getExifParameter()));
        }
        return returnAxis;
    }

    @Override
    public boolean isDiagramDisplayable() {

        for (final AxisParameter axisParameter : this.axisParameters) {

            if (axisParameter.invalid) {
                this.showErrorIcon();
                return false;
            }
        }
        this.resetIcon();
        return true;
    }

    /* FIXME what is this?! */
    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

    /**
     * This method is responsible for revalidating the wilcoxon panel, since changes made to the paramters
     * effect configuration of the wilcoxon panel.
     */
    protected void revalidateWilcoxon() {

        if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {
            boolean enabled = false;

            if ((this.axisParameters[0] != null)
                    && (this.axisParameters[0].getExifParameter() instanceof ExifParameter)) {
                enabled = this.axisParameters[0].getExifParameter().isOrdinal();
            }

            final List<JAbstractSinglePanel> registeredPanels = ReportHelper.getCurrentReportUtility().reportCompilation
                    .getRegisteredPanels();

            for (final JAbstractSinglePanel singlepanel : registeredPanels) {

                if (singlepanel instanceof JWilcoxon) {
                    ((JWilcoxon) singlepanel).setOrdianlEnabled(enabled);
                }
            }
        }
    }

    /**
     * 
     * This class represents an axis parameter in the JParameters panel.
     * 
     * It generally is an arbitrary axis with an associated EXIF parameter
     * and a description.
     * 
     * @author David Kaufman
     * 
     */
    public class AxisParameter extends JPanel {

        private final Logger logger = Logger.getLogger(this.getClass());

        private static final long serialVersionUID = 3315636512790006885L;

        private final ExifParamComboBox exifParamComboBox;

        private final JTextField axisDescription;

        private final JLabel validLabel = new JLabel();

        private final String axisParameterName;

        private boolean invalid = true;

        /**
         * This constructor creates an axis parameter object with the parameters specified.
         * 
         * @param axisParameterName
         *            the axis parameter name, e.g. x-Axis.
         */
        public AxisParameter(final String axisParameterName) {
            super();

            this.axisDescription = new JTextField();
            this.axisParameterName = axisParameterName;
            this.exifParamComboBox = new ExifParamComboBox(this);

            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

            try {
                this.validLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
            } catch (final FileNotFoundException e) {
                this.logger.error(e.getMessage());
            }

            this.add(new JLabel(this.axisParameterName));
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.add(this.exifParamComboBox);
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.add(this.validLabel);
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.add(this.axisDescription);
        }

        /**
         * Returns the associated axis description.
         * 
         * @return the axis description.
         */
        public String getAxisDescription() {
            return new String(this.axisDescription.getText());
        }

        /**
         * Sets the axis description to the specified value.
         * 
         * @param description
         *            the new axis description.
         */
        public void setAxisDescription(final String description) {
            this.axisDescription.setText(new String(description));
        }

        /**
         * Returns the associated EXIF parameter.
         * 
         * @return the EXIF parameter, if no EXIF parameter is selected, return null.
         */
        public ExifParameter getExifParameter() {
            if (this.exifParamComboBox.getSelectedItem() instanceof ExifParameter) {
                return (ExifParameter) this.exifParamComboBox.getSelectedItem();
            }
            return null;
        }

        /**
         * Returns if the parameter specified is invalid or not.
         * 
         * @return true, if invalid, false otherwise.
         */
        public boolean isInvalid() {
            return this.invalid;
        }

        /**
         * Sets the current axis panel to the specified axis object.
         * 
         * @param axis
         *            the axis you want to insert.
         */
        public void setAxis(final Axis axis) {

            if (axis != null) {
                this.axisDescription.setText(axis.getDescription());

                if (axis.getParameter() == null) {
                    this.exifParamComboBox.setSelectedIndex(0);
                } else {
                    this.exifParamComboBox.setSelectedItem(axis.getParameter());
                    this.setInvalid(false);
                }
            }
        }

        /**
         * This method is responsible for updating the view if the invalidity changes.
         * 
         * @param invalid
         *            the invalidity.
         */
        public void setInvalid(final boolean invalid) {

            if (!invalid) {
                this.validLabel.setIcon(null);
                this.validLabel.setPreferredSize(new Dimension(32, 32));
            } else {

                try {
                    this.validLabel.setIcon(Resource.createImageIcon("status/dialog-error.png", "", "32"));
                } catch (final FileNotFoundException e) {
                    this.logger.error(e.getMessage());
                }

                if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {

                    if ((this.getExifParameter() != null) && !this.getExifParameter().isOrdinal()) {
                        this.validLabel.setToolTipText(Messages.getString("JParameters.2"));
                    }
                }
            }
            this.repaint();
            this.invalid = invalid;
        }

        /**
         * Add the action listener to this object.
         */
        public void addActionListener() {
            this.exifParamComboBox.addActionListener(this.exifParamComboBox);
        }

    }

    /**
     * This class represents the combo box which displays the various EXIF parameters in the JParamters panel.
     * 
     * @author David Kaufman
     */
    public class ExifParamComboBox extends JComboBox implements ActionListener {

        private static final long serialVersionUID = -6260424764891224456L;

        private final AxisParameter axisParameter;

        /**
         * This constructor create an ExifParamComboBox object and also stores an axis parameter object
         * which is later used to manipulate the panel if the user selected a valid or invalid entry in the
         * combo box.
         * 
         * @param axisParameter
         *            the axis paramter object which is to be modified.
         */
        public ExifParamComboBox(final AxisParameter axisParameter) {
            this.axisParameter = axisParameter;

            if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {
                this.setRenderer(new OrdinalCellRenderer());
            }
            final Object[] exifparams = new Object[ExifParameter.values().length + 1];

            for (int i = 0; i < exifparams.length - 1; ++i) {

                if (i == 0) {
                    exifparams[i] = "-";
                } else {
                    exifparams[i] = ExifParameter.values()[i - 1];
                }
                this.addItem(exifparams[i]);
            }
            this.setSelectedIndex(0);
        }

        /**
         * This cell renderer specifies that only ordinal EXIF parameters are drawn as the normal
         * foreground color. Non ordinal EXIF parameters are drawn gray.
         * 
         * @author David Kaufman
         */
        public class OrdinalCellRenderer extends DefaultListCellRenderer {

            private static final long serialVersionUID = -8188427562977471722L;

            /**
             * {@inheritDoc}
             */
            @Override
            public Component getListCellRendererComponent(final JList list, final Object value, final int index,
                    final boolean isSelected, final boolean cellHasFocus) {
                final Component renderer = super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);

                if (value instanceof ExifParameter) {

                    if (((ExifParameter) value).isOrdinal()) {
                        renderer.setForeground(list.getForeground());
                    } else {
                        renderer.setForeground(Color.LIGHT_GRAY);
                    }
                }
                return renderer;
            }
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final JComboBox cb = (JComboBox) e.getSource();

            if (cb.getSelectedIndex() != 0) {
                this.axisParameter.setInvalid(false);
            } else {
                this.axisParameter.setInvalid(true);
            }

            /* reset axis description after an event was fired */
            this.axisParameter.setAxisDescription(Messages.getString("JParameters.4"));

            /* prohibit non ordinal EXIF Parameters if report is boxplot */
            if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {

                if (cb.getSelectedItem() instanceof ExifParameter) {

                    if (!((ExifParameter) cb.getSelectedItem()).isOrdinal()) {
                        this.axisParameter.setInvalid(true);
                    } else {
                        this.axisParameter.setInvalid(false);
                    }
                }
            }
            JParameters.this.revalidateReport();
            JParameters.this.revalidateWilcoxon();
        }
    }
}