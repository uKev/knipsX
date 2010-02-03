package org.knipsX.view.diagrams;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.TableModel;
import org.knipsX.utils.ExifParameter;

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

        AbstractTableModel dataModel = null;
        
        if (this.model != null && this.model.isModelValid()) {
            final ArrayList<Picture> pictures = this.model.getPictures();
            
            dataModel = new AbstractTableModel() {
                
                private static final long serialVersionUID = -136606257319989327L;
               
                
                public int getColumnCount() {
                    return ExifParameter.values().length + 1;
                }

                public int getRowCount() {
                    return pictures.size();
                }

                public Object getValueAt(final int row, final int col) { 
                    if (col == 0) {
                        return pictures.get(row).getName();
                    }                
                    return pictures.get(row).getExifParameter(ExifParameter.values()[col - 1]);
                }
                
                public String getColumnName(int column) {
                    if (column == 0) {
                        return "Name";
                    } else {
                        return ExifParameter.values()[column - 1].toString();
                    }
                }
            };
        
        
        }
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
