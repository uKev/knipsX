package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;

import org.knipsX.controller.AbstractController;
import org.knipsX.view.projectmanagement.JProjectNew;

public class Cancel extends AbstractController {
	
	private JProjectNew jProjectNew;

	public Cancel(JProjectNew jProjectNew) {
		this.jProjectNew = jProjectNew;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jProjectNew.dispose();		
	}

}
