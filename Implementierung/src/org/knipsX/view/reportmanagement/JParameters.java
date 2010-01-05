package org.knipsX.view.reportmanagement;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knipsX.model.reportmanagement.Axis;
import org.knipsX.utils.ExifParameter;

/**
 * This class represents the panel where the user is able to assign a 
 * parameter with an optional description to each available axis. 
 * 
 * @author David Kaufman
 *
 */
public class JParameters extends JAbstractSinglePanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AxisParameter[] AxisParameters = new AxisParameter[ReportHelper.currentReport.getNumberOfAxes()];
	private JPanel singlepanel;
	
	/**
	 * Constructor which initialized this parameter panel
	 * 
	 * @param titel The title which is registered with this panel.
	 * @param icon The icon which is registered with this panel.
	 * @param tip The tooltip which is registered with this panel.
	 */
    public JParameters(String titel, Icon icon, String tip) {
		this.title = titel;
		this.icon = icon;
		this.tip = tip;
		
    	
		if(this.title == null || this.title == "") {
			this.title = "Parameter";
		}
        
        BoxLayout container = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(container);  
        
        this.singlepanel = new JPanel();
        this.singlepanel.setLayout(new BoxLayout(singlepanel, BoxLayout.PAGE_AXIS)); 
        String[] axes = {"x-Achse", "z-Achse", "y-Achse"};
        
        for(int i = -1; i < ReportHelper.currentReport.getNumberOfAxes(); i++) {

        	
        	if(i>=0) {
        		if (AxisParameters[i] == null)
        			AxisParameters[i] = new AxisParameter(axes[i], null, null);        		
        		this.singlepanel.add(AxisParameters[i]);
        		this.singlepanel.add(Box.createRigidArea(new Dimension(0, 20)));

        	} else {
            	JPanel mypanel = new JPanel();
            	BoxLayout myboxlayoutintern = new BoxLayout(mypanel, BoxLayout.X_AXIS);
            	mypanel.setLayout(myboxlayoutintern);
	        	mypanel.add(new JLabel("Achsen"));
	        	mypanel.add(Box.createRigidArea(new Dimension(225,0)));
	        	mypanel.add(new JLabel("Beschreibung"));
	        	mypanel.setMinimumSize(new Dimension(Integer.MAX_VALUE,32));
	        	mypanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,64));
	        	this.singlepanel.add(mypanel);
	        	this.singlepanel.add(Box.createRigidArea(new Dimension(0,32)));
	        	
        	}
          add(this.singlepanel);

        }

        add(Box.createVerticalGlue());

    }
    
    /**
     * Returns the Exif-parameters and axes desription specified
     * @return the Exif-parameters and axes desription 
     */
    public ArrayList<Axis> getAxes() {
    	//TODO
		return null;    	
    }
    
    
    public class AxisParameter extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private boolean invalid = true;
		private String AxisParameterName;
		private ExifParameter exifparam;
		private String AxisDescription;
		
		private JLabel ValidLabel = new JLabel(createImageIcon("../../images/userwarning.png", null));
		
		public AxisParameter(String axisParameterName, ExifParameter exifparam, String axisDescription) {
			super();
			this.AxisParameterName = axisParameterName;
			this.exifparam = exifparam;
			this.AxisDescription = axisDescription;
			
			
        	BoxLayout myboxlayoutintern = new BoxLayout(this, BoxLayout.X_AXIS);
        	this.setLayout(myboxlayoutintern);			
        	
        	this.add(new JLabel(this.AxisParameterName));
        	this.add(Box.createRigidArea(new Dimension(20,0)));	
        	this.add(new ExifParamsComboBox(this));	        	
        	this.add(Box.createRigidArea(new Dimension(20,0)));
        	this.add(this.ValidLabel);
        	        	
        	this.add(Box.createRigidArea(new Dimension(20,0)));
        	this.add(new JTextField(this.AxisDescription));		
        	this.setMaximumSize(new Dimension(Integer.MAX_VALUE,20));	
		}
		
		
		public void setInvalid(boolean invalid) {
			if (invalid == false) {
				System.out.println("valid");
				this.ValidLabel.setIcon(null);
				this.ValidLabel.setPreferredSize(new Dimension(32,32));
			}
			else {
				System.out.println("invalid");
				this.ValidLabel.setIcon(createImageIcon("../../images/userwarning.png", null));
			}
			
			repaint();
			this.invalid = invalid;
		}	
		
		
		public boolean isInvalid() {
			return this.invalid;
		}
		
		public String getAxisParameterName() {
			return AxisParameterName;
		}


		public ExifParameter getExifparam() {
			return exifparam;
		}


		public String getAxisDescription() {
			return AxisDescription;
		}
    	
    }
    
    
    public class ExifParamsComboBox extends JComboBox implements ActionListener {

    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private AxisParameter axisparam;
    	
    	public ExifParamsComboBox(AxisParameter axisparam) {
    		
    		this.axisparam = axisparam;
    		
    		
	        Object[] exifparams = new Object[ExifParameter.values().length + 1];
	        for(int i = 0; i < exifparams.length - 1; i++) {
	        	if(i==0)
	        		exifparams[0] = "-";
	        	else 
	        		exifparams[i] = ExifParameter.values()[i-1];
	        	
	        	this.addItem(exifparams[i]);
	        }  
	        
	        this.setSelectedIndex(0);
	        this.addActionListener(this);
	        
    	}
    	
		@Override
		public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
	        
	        if(cb.getSelectedIndex() != 0) {
	        	updateParameter(false);		
	        } else {
	        	updateParameter(true);
	        }
		}
		
		
		protected void updateParameter(boolean invalid) {
				if(axisparam.isInvalid() != invalid) {
					axisparam.setInvalid(invalid);
				}		
		}	
    	
    }

}
