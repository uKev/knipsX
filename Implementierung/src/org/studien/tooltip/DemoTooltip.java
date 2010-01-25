/*
 *      DemoTooltip.java
 *
 *      Copyright 2009 Kevin Zuber <Kevin.Zuber@student.kit.edu>
 *
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;

public class DemoTooltip {

  public static void main(String[] args) {

    // Get current delay
    int initialDelay = ToolTipManager.sharedInstance().getInitialDelay();

    // Show tool tips immediately
    ToolTipManager.sharedInstance().setInitialDelay(0);

    // Show tool tips after a second
    initialDelay = 1;
    ToolTipManager.sharedInstance().setInitialDelay(initialDelay);
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Strahlhorn.jpg");
    label.setToolTipText(
      "<html><img width=\"5px\" height=\"10px\" src=\"" +
      DemoTooltip.class.getResource("Strahlhorn.jpg") +
      "\"> "
      );
    System.out.println("<html><img width=\"5px\" height=\"10px\" src=\"" +
      DemoTooltip.class.getResource("Strahlhorn.jpg") +
      "\"> ");
    label.setHorizontalAlignment(JLabel.CENTER);
    frame.setContentPane(label);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 200, 100);
    frame.setVisible(true);

  }
}
