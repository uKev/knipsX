package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    
    
    /**
     * Constructor
     * 
     * @param abstractModel
     *            the model from which the drawing information is taken
     */
    public JTableDiagram(M model) {
		super(model);
		
	    AbstractTableModel dataModel = new AbstractTableModel() {
	          public int getColumnCount() { return 10; }
	          public int getRowCount() { return 20;}
	          public Object getValueAt(int row, int col) { return new Integer(row*col); }
	      };
	      this.table = new JTable(dataModel);
	      this.scrollpane = new JScrollPane(table);
	     
    }

    @Override
	public BufferedImage getDiagramScreenshot() {
		// TODO Auto-generated method stub
		return null;
    }

	@Override
	public Component getDiagram() {		
		return this.scrollpane;
	}

	@Override
	void showDiagram() {
		// TODO Auto-generated method stub
		
	}
}
