package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        private static final long serialVersionUID = 3315636512790006885L;

        private boolean invalid = true;
        private String axisParameterName;
        private JTextField axisDescription;
        private ExifParamComboBox exifparamcombo;
        private final JLabel validLabel = new JLabel();

        /**
         * This constructor creates an axis parameter object with the parameters specified
         * 
         * @param axisParameterName
         *            the axis parameter name, e.g. x-Axis
         * @param exifparam
         *            the actual EXIF parameter
         * @param axisDescription
         *            the axis description
         */
        public AxisParameter(final String axisParameterName, final ExifParameter exifparam, final String axisDescription) {
            super();
            this.axisParameterName = axisParameterName;
            if (axisDescription != null) {
                this.axisDescription.setText(axisDescription);
            }

            final BoxLayout myboxlayoutintern = new BoxLayout(this, BoxLayout.X_AXIS);
            this.setLayout(myboxlayoutintern);

            this.add(new JLabel(this.axisParameterName));
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.exifparamcombo = new ExifParamComboBox(this);
            this.add(this.exifparamcombo);
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.add(this.validLabel);
            this.add(Box.createRigidArea(new Dimension(20, 0)));
            this.axisDescription = new JTextField(axisDescription);
            this.add(this.axisDescription);
            this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            try {
                this.validLabel.setIcon(Resource.createImageIcon("../images/userwarning.png", null));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        /**
         * Returns the associated axis description
         * 
         * @return the axis description
         */
        public String getAxisDescription() {
            return this.axisDescription.getText();
        }

        /**
         * Returns the associated EXIF parameter
         * 
         * @return the EXIF parameter, if no EXIF parameter is selected, return null
         */
        public ExifParameter getExifparam() {
            if (this.exifparamcombo.getSelectedItem() instanceof ExifParameter) {
                return (ExifParameter) this.exifparamcombo.getSelectedItem();
            }

            return null;

        }

        /**
         * Returns if the parameter specified is invalid or not
         * 
         * @return true, if invalid, false otherwise
         */
        public boolean isInvalid() {
            return this.invalid;
        }

        /**
         * Sets the current axis panel to the specified axis object
         * 
         * @param axis
         *            the axis you want to insert
         */
        public void setAxis(final Axis axis) {
            if (axis != null) {
                this.axisDescription.setText(axis.getDescription());
                if (axis.getParameter() == null) {
                    this.exifparamcombo.setSelectedIndex(0);
                } else {
                    this.exifparamcombo.setSelectedItem(axis.getParameter());
                }
            }
        }

        /**
         * This method is responsible for updating the view if the invalidity changes
         * 
         * @param invalid
         *            the invalidity
         */
        public void setInvalid(final boolean invalid) {
            if (!invalid) {
                this.validLabel.setIcon(null);
                this.validLabel.setPreferredSize(new Dimension(32, 32));
            } else {
                try {
                    this.validLabel.setIcon(Resource.createImageIcon("../images/userwarning.png", null));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            this.repaint();
            this.invalid = invalid;
        }

        public void addActionListener() {
            this.exifparamcombo.addActionListener(exifparamcombo);
        }

    }

    /**
     * This class represents the combo box which displays the various EXIF parameters in the JParamters panel
     * 
     * 
     * @author David Kaufman
     * 
     */
    public class ExifParamComboBox extends JComboBox implements ActionListener {

        private static final long serialVersionUID = -6260424764891224456L;

        private final AxisParameter axisparam;

        /**
         * This constructor create an ExifParamComboBox object and also stores an axis parameter object
         * which is later used to manipulate the panel if the user selected a valid or invalid entry in the
         * combo box
         * 
         * @param axisparam
         *            the axis paramter object which is to be modified
         */
        public ExifParamComboBox(final AxisParameter axisparam) {
            this.axisparam = axisparam;

            final Object[] exifparams = new Object[ExifParameter.values().length + 1];
            for (int i = 0; i < exifparams.length - 1; i++) {
                if (i == 0) {
                    exifparams[0] = "-";

                } else {
                    exifparams[i] = ExifParameter.values()[i - 1];
                }

                this.addItem(exifparams[i]);
            }

            this.setSelectedIndex(0);

        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            final JComboBox cb = (JComboBox) e.getSource();

            if (cb.getSelectedIndex() != 0) {
                this.axisparam.setInvalid(false);
            } else {
                this.axisparam.setInvalid(true);
            }

            JParameters.this.revalidateReport();
            JParameters.this.revalidateWilcoxon();

        }

    }

    private final AxisParameter[] axisParameters = new AxisParameter[ReportHelper.getCurrentReport().getNumberOfAxes()];

    private final JPanel singlepanel;

    // INTERNATIONALIZE
    final String[] axesDescription = { "x-Achse", "z-Achse", "y-Achse" };

    /**
     * Constructor which initialized this parameter panel
     */
    public JParameters() {

        /* Set the title name of this panel */
        // INTERNATIONALIZE
        this.title = "Parameters";
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.singlepanel = new JPanel();
        this.singlepanel.setLayout(new BoxLayout(this.singlepanel, BoxLayout.PAGE_AXIS));

        for (int i = -1; i < ReportHelper.getCurrentReport().getNumberOfAxes(); i++) {

            if (i >= 0) {
                this.axisParameters[i] = new AxisParameter(axesDescription[i], null, null);
                this.singlepanel.add(this.axisParameters[i]);
            } else {
                final JPanel mypanel = new JPanel();
                mypanel.setLayout(new BoxLayout(mypanel, BoxLayout.X_AXIS));

                // INTERNATIONALIZE
                mypanel.add(new JLabel("Achsen"));

                mypanel.add(Box.createRigidArea(new Dimension(225, 0)));

                // INTERNATIONALIZE
                mypanel.add(new JLabel("Beschreibung"));

                /* Set minimum and maximum size to prevent cutting */
                mypanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 32));
                mypanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
                this.singlepanel.add(mypanel);

            }

            this.singlepanel.add(Box.createRigidArea(new Dimension(0, 32)));
            this.add(this.singlepanel);
        }

        this.add(Box.createVerticalGlue());

        /* Fill the view with model information */
        fillViewWithModelInfo();

        /* Add action listeners, after model has filled view with information */
        for (int i = 0; i < axisParameters.length; i++) {
            axisParameters[i].addActionListener();
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void fillViewWithModelInfo() {

        if (ReportHelper.getCurrentModel() != null) {
            if (ReportHelper.getCurrentModel() instanceof BoxplotModel) {
                this.axisParameters[0].setAxis(((BoxplotModel) ReportHelper.getCurrentModel()).getxAxis());
            } else if (ReportHelper.getCurrentModel() instanceof Histogram2DModel) {
                this.axisParameters[0].setAxis(((Histogram2DModel) ReportHelper.getCurrentModel()).getxAxis());
            } else if (ReportHelper.getCurrentModel() instanceof Histogram2DModel) {
                this.axisParameters[0].setAxis(((Histogram3DModel) ReportHelper.getCurrentModel()).getxAxis());
            } else if (ReportHelper.getCurrentModel() instanceof Cluster3DModel) {
                if (this.axisParameters.length >= 1) {
                    this.axisParameters[0].setAxis(((Cluster3DModel) ReportHelper.getCurrentModel()).getxAxis());

                }
                if (this.axisParameters.length >= 2) {
                    this.axisParameters[1].setAxis(((Cluster3DModel) ReportHelper.getCurrentModel()).getzAxis());
                }
                if (this.axisParameters.length == 3) {
                    this.axisParameters[2].setAxis(((Cluster3DModel) ReportHelper.getCurrentModel()).getyAxis());
                }
            }
        }
    }

    /**
     * Returns the EXIF parameters and axes description specified
     * 
     * @return the EXIF parameters and axes description
     */
    public ArrayList<Axis> getAxes() {
        final ArrayList<Axis> returnAxis = new ArrayList<Axis>();

        for (final AxisParameter axisparam : this.axisParameters) {
            returnAxis.add(new Axis(axisparam.getAxisDescription(), axisparam.getExifparam()));
        }

        return returnAxis;
    }

    @Override
    public boolean isDiagramDisplayable() {
        for (final AxisParameter axisParam : this.axisParameters) {
            if (axisParam.invalid) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDiagramSaveable() {
        return true;
    }

    /**
     * This method is responsible for revalidating the wilcoxon panel, since changes made to the paramters
     * effect configuration of the wilcoxon panel
     */
    protected void revalidateWilcoxon() {
        if (ReportHelper.getCurrentReport() == ReportHelper.Boxplot) {
            boolean enabled = false;

            if (axisParameters[0] != null) {
                if (axisParameters[0].getExifparam() instanceof ExifParameter) {
                    enabled = axisParameters[0].getExifparam().isOrdinal();
                }
            }

            final ArrayList<JAbstractSinglePanel> registeredPanels = ReportHelper.currentReportUtil.reportCompilation
                    .getRegisteredPanels();

            for (final JAbstractSinglePanel singlepanel : registeredPanels) {
                if (singlepanel instanceof JWilcoxon) {
                    ((JWilcoxon) singlepanel).setOrdianlEnabled(enabled);
                }
            }

        }
    }
}
