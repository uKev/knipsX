package org.studien.fileChooser;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class DemoJFileChooser extends JPanel
   implements ActionListener {
   JButton go;

   JFileChooser chooser;
   String choosertitle;

  public DemoJFileChooser() {
    go = new JButton("Do it");
    go.addActionListener(this);
    add(go);
   }

  public void actionPerformed(ActionEvent e) {
    int result;

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
         +  chooser.getCurrentDirectory());
      for(File file : chooser.getSelectedFiles()) {
    	  System.out.println("Pfad: " + file.getAbsolutePath() + " dir? " + file.isDirectory() + " file? " + file.isFile());
      }
    }
    else {
      System.out.println("No Selection ");
      }
     }

  public Dimension getPreferredSize(){
    return new Dimension(200, 200);
    }

  public static void main(String s[]) {
	  try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	   } catch (Exception exc) {
	      System.err.println("Error loading  Look and Feel: " + exc);
	   }
	  JFrame frame = new JFrame("");
    DemoJFileChooser panel = new DemoJFileChooser();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
    }
}