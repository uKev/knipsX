package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.knipsX.model.picturemanagement.PictureInterface;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Values;
import org.knipsX.view.projectview.MyExifTableCellRenderer;

/**
 * This class implements how the TableModel is to be drawn.
 * 
 * @author David Kaufman
 * 
 * @param <M>
 */
public class JTableDiagram<M extends TableModel> extends JAbstractDiagram<M> {

    private static final long serialVersionUID = 2423794061738381566L;

    private final JTable table;

    private final JScrollPane scrollpane;

    private final JPanel mainpanel;

    /**
     * Constructor.
     * 
     * @param model
     *            the model from which the drawing information is taken from.
     * 
     * @param reportId
     *            the report id of the report.
     */
    public JTableDiagram(final M model, final int reportId) {
        super(model, reportId);

        AbstractTableModel dataModel = null;

        if ((this.model != null) && this.model.isModelValid()) {
            final List<PictureInterface> pictures = this.model.getPictures();

            dataModel = new AbstractTableModel() {

                private static final long serialVersionUID = -136606257319989327L;

                public int getColumnCount() {
                    return ExifParameter.values().length + 1;
                }

                public int getRowCount() {
                    return pictures.size();
                }

                public Object getValueAt(final int row, final int column) {
                    if (column == 0) {
                        return pictures.get(row).getName();
                    }
                    return pictures.get(row).getExifParameter(ExifParameter.values()[column - 1]);
                }

                @Override
                public String getColumnName(final int column) {
                    if (column == 0) {
                        return "Name";
                    } else {
                        return ExifParameter.values()[column - 1].toString();
                    }
                }
            };
        }
        this.table = new JTable(dataModel);
        this.table.getTableHeader().setReorderingAllowed( false );
        this.table.setDefaultRenderer(Object.class, new MyExifTableCellRenderer());

        this.scrollpane = new JScrollPane(this.table);

        this.mainpanel = new JPanel();
        this.mainpanel.setLayout(new BoxLayout(this.mainpanel, BoxLayout.PAGE_AXIS));
        this.mainpanel.add(this.scrollpane);
        this.mainpanel.add(new JDiagramButtonsTable(this));
        this.add(this.mainpanel);

        /* set the size of the diagram based on the preferred size of its components */
        this.setPreferredSize(new Dimension(Values.PREFERRED_WINDOW_WIDTH, Values.PREFERRED_WINDOW_HEIGHT));
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
        
        /* set the diagram to the center of the screen */
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}