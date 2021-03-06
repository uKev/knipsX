package org.knipsX.view.projectview;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;

/**
 * Renders a table cell for the table which shows the EXIF parameters of an active picture.
 */
public class MyExifTableCellRenderer extends JLabel implements TableCellRenderer {

    private static final long serialVersionUID = -5528480925908374362L;

    protected DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();

    /**
     * Renders the cell of a JTable.
     * 
     * @param table
     *            the JTable we're painting.
     * @param value
     *            the value of a cell.
     * @param isSelected
     *            true if the specified cell was selected.
     * @param hasFocus
     *            true if the specified cell has the focus.
     * @param row
     *            the selected row index.
     * @param column
     *            the selected column index.
     * 
     * @return the representation of the cell.
     */
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
            final boolean hasFocus, final int row, final int column) {

        /* generate the label which represents the cell */
        final JLabel renderer = (JLabel) this.defaultRenderer.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);

        if (value != null) {

            String theText = Converter.objectToString(value);
            
            //TWEAK: Might split this renderer up into two, not every long value is a date
            if (value instanceof Long && (column == ExifParameter.DATE.ordinal() + 1 ||  row == ExifParameter.DATE.ordinal())) {       
               
                Date tempDate = new Date();
                tempDate.setTime(((Long) value));

                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                
                theText = dateFormat.format(tempDate);
            }


            renderer.setText(theText);
            renderer.setToolTipText(value.toString());

        }
        return renderer;
    }
}