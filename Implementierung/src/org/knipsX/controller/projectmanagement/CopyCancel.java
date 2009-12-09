package org.knipsX.controller.projectmanagement;

import java.awt.event.ActionEvent;
import org.knipsX.controller.AbstractController;
import org.knipsX.view.projectmanagement.CopyProjectView;

public class CopyCancel extends AbstractController {
	
	private CopyProjectView copyProjectView;

	public CopyCancel(CopyProjectView copyProjectView) {
		this.copyProjectView = copyProjectView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		copyProjectView.dispose();		
	}

}
