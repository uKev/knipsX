package org.knipsX.view;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.knipsX.model.AbstractModel;

public class JOptionPaneWithModel extends JOptionPane {

	private static final long serialVersionUID = 8194739270258351606L;
	
	@SuppressWarnings("unused")
	private AbstractModel abstractModel;
	
	public JOptionPaneWithModel(AbstractModel model) {
		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message, int messageType, int optionType,
			Icon icon, Object[] options, Object initialValue) {
		super(message, messageType, optionType, icon, options, initialValue);

		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message, int messageType, int optionType,
			Icon icon, Object[] options) {
		super(message, messageType, optionType, icon, options);

		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message, int messageType, int optionType,
			Icon icon) {
		super(message, messageType, optionType, icon);

		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message, int messageType, int optionType) {
		super(message, messageType, optionType);

		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message, int messageType) {
		super(message, messageType);

		this.abstractModel = model;
	}

	public JOptionPaneWithModel(AbstractModel model, Object message) {
		super(message);

		this.abstractModel = model;
	}
   
}
