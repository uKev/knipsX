package org.knipsX.view.diagrams;

import java.awt.Component;
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

    private static final long serialVersionUID = 2423794061738381566L;

    private final JTable table;

    private final JScrollPane scrollpane;

    private final JPanel mainpanel;

    /**
     * Constructor
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportId
     *            the report id of the report.
     */
    public JTableDiagram(final M model, final int reportId) {
        super(model, reportId);

        /* TODO when implementing the controller, we must set this to the right controller */
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

        /* set the size of the diagram based on the preferred size of its components */
        this.pack();
    }

    @Override
    public Component getDiagram() {
        return this.scrollpane;
    }

    @Override
    public BufferedImage getDiagramScreenshot() {
        final Rectangle bounds = this.table.getBounds();
        final BufferedImage image = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);

        this.table.paint(image.createGraphics());
        return image;
    }

    @Override
    public void showDiagram() {

        /* set the diagramm to the center of the screen */
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
