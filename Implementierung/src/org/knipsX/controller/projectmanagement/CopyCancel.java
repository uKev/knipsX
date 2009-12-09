package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.view.projectmanagement.JProjectCopy;

public class CopyCancel extends AbstractController {
	
	private JProjectCopy jProjectCopy;

	public CopyCancel(JProjectCopy jProjectCopy) {
		this.jProjectCopy = jProjectCopy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jProjectCopy.dispose();		
	}

}
