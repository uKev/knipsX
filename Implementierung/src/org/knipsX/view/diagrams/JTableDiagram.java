package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.knipsX.model.reportmanagement.TableModel;

/**
 * This class implements how the TableModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JTableDiagram<M extends TableModel> extends JAbstractDiagram<M> {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollpane;
	private JPanel mainpanel;

	/**
	 * Constructor
	 * 
	 * @param abstractModel
	 *            the model from which the drawing information is taken
	 */
	public JTableDiagram(M model) {
		super(model);

		AbstractTableModel dataModel = new AbstractTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public int getColumnCount() {
				return 10;
			}

			public int getRowCount() {
				return 20;
			}

			public Object getValueAt(int row, int col) {
				return new Integer(row * col);
			}
		};

		this.table = new JTable(dataModel);		
		this.scrollpane = new JScrollPane(table);
        this.mainpanel = new JPanel();
        this.mainpanel.setLayout(new BoxLayout(this.mainpanel, BoxLayout.PAGE_AXIS));       
        this.mainpanel.add(this.scrollpane);
        this.mainpanel.add(new JDiagramButtonsPlain(this));
      	add(this.mainpanel);       	
        pack();   

	}

	@Override
	public BufferedImage getDiagramScreenshot() {
		Rectangle d = this.scrollpane.getBounds();
		BufferedImage bi = new BufferedImage(d.width, d.height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();
		SwingUtilities.paintComponent(g2d, this.scrollpane, this.scrollpane.getRootPane(), 0, 0,
				d.width, d.height);
		return bi;
	}

	@Override
	public Component getDiagram() {
		return this.scrollpane;
	}

	@Override
	void showDiagram() {	
        this.setVisible(true);
	}
}
