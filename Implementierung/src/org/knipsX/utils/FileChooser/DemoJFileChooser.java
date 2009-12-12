package org.knipsX.utils.FileChooser;

import javax.swing.*;
import org.knipsX.model.AbstractModel;
import org.knipsX.model.projectview.ProjectViewModel;
import org.knipsX.view.JAbstractView;
import java.awt.*;
import java.io.*;
import java.util.Observable;

public class DemoJFileChooser extends JAbstractView {

	private static final long serialVersionUID = 1L;

	JFileChooser chooser;
	String choosertitle;

	public DemoJFileChooser(AbstractModel model) {
		super(model);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exc) {
			System.err.println("Error loading  Look and Feel: " + exc);
		}

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle(choosertitle);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(true);
		//
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): "
					+ chooser.getCurrentDirectory());
			for (File file : chooser.getSelectedFiles()) {
				System.out.println("Pfad: " + file.getAbsolutePath() + " dir? "
						+ file.isDirectory() + " file? " + file.isFile());
			}
		} else {
			model.setModelStatus(ProjectViewModel.USERSELECT);
			System.out.println("No Selection ");
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	@Override
	public void update(Observable model, Object argument) {
		
		/* cast to model */
		final ProjectViewModel projectViewModel = (ProjectViewModel) model;
		
		if (projectViewModel.getModelStatus() != ProjectViewModel.ADDTOPICTURESET) {

		    /* delete view */
		    this.dispose();
		}
		
	}
}