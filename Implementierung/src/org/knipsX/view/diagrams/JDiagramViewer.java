package org.knipsX.view.diagrams;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.knipsX.model.AbstractModel;
import org.knipsX.view.JAbstractView;

public class JDiagramViewer<M extends AbstractModel> extends JAbstractView<M> {

    /**
		 * 
		 */
    private static final long serialVersionUID = 1L;
    public JLabel xLabel;
    public JLabel yLabel;
    public JLabel zLabel;
    public JLabel jstatusbar;

    public JDiagramViewer(M model) {
	super(model);

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");

	menubar.add(file);
	setJMenuBar(menubar);

	JToolBar toolbar = new JToolBar();
	toolbar.setFloatable(false);

	JButton bexit = new JButton("Exit");
	bexit.setBorder(new EmptyBorder(0, 0, 0, 0));
	file.add(bexit);

	add(toolbar, BorderLayout.NORTH);

	JToolBar vertical = new JToolBar(JToolBar.VERTICAL);
	vertical.setFloatable(false);
	vertical.setMargin(new Insets(10, 5, 5, 5));

	JLabel selectb = new JLabel("ISO");
	selectb.setBorder(new EmptyBorder(3, 0, 3, 0));
	this.xLabel = new JLabel("-");
	selectb.setBorder(new EmptyBorder(3, 0, 3, 0));
	JLabel freehandb = new JLabel("Datum");
	freehandb.setBorder(new EmptyBorder(3, 0, 3, 0));
	this.yLabel = new JLabel("-");
	freehandb.setBorder(new EmptyBorder(3, 0, 3, 0));
	JLabel shapeedb = new JLabel("Brennweite");
	shapeedb.setBorder(new EmptyBorder(3, 0, 3, 0));
	this.zLabel = new JLabel("-");
	shapeedb.setBorder(new EmptyBorder(3, 0, 3, 0));

	vertical.add(selectb);
	vertical.add(xLabel);
	vertical.add(freehandb);
	vertical.add(yLabel);
	vertical.add(shapeedb);
	vertical.add(zLabel);

	add(vertical, BorderLayout.WEST);

	this.jstatusbar = new JLabel(" Statusbar");
	this.jstatusbar.setPreferredSize(new Dimension(-1, 22));
	this.jstatusbar.setBorder(LineBorder.createGrayLineBorder());
	add(this.jstatusbar, BorderLayout.SOUTH);

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update(Observable model, Object argument) {
	// TODO Auto-generated method stub

    }

}