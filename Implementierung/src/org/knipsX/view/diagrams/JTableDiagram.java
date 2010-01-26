package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.knipsX.model.reportmanagement.AbstractReportModel;

/**
 * This class implements how the TableModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JTableDiagram<M extends AbstractReportModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 1L;
    private final JTable table;
    private final JScrollPane scrollpane;
    private final JPanel mainpanel;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from
     * 
     * @param reportID
     *            the report id of the report
     */
    public JTableDiagram(final M model, final int reportID) {
        super(model, reportID);

        final AbstractTableModel dataModel = new AbstractTableModel() {

            private static final long serialVersionUID = -136606257319989327L;

            public int getColumnCount() {
                return 10;
            }

            public int getRowCount() {
                return 20;
            }

            public Object getValueAt(final int row, final int col) {
                return new Integer(row * col);
            }
        };

        this.table = new JTable(dataModel);
        this.scrollpane = new JScrollPane(this.table);
        this.mainpanel = new JPanel();
        this.mainpanel.setLayout(new BoxLayout(this.mainpanel, BoxLayout.PAGE_AXIS));
        this.mainpanel.add(this.scrollpane);
        this.mainpanel.add(new JDiagramButtonsPlain(this));
        this.add(this.mainpanel);
        this.pack();

    }

    @Override
    public Component getDiagram() {
        return this.scrollpane;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {
        final Rectangle d = this.table.getBounds();
        final BufferedImage bi = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g2d = bi.createGraphics();
        this.table.paint(g2d);
        return bi;
    }

    @Override
    public void showDiagram() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
