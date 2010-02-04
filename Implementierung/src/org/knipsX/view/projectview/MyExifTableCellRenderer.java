package org.knipsX.view.projectview;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

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

        String theText = "";

        if (value != null) {

            if (value instanceof Object[]) {
                Object[] objectArray = (Object[]) value;
                for (int i = 0; i < objectArray.length; i++) {
                    theText = objectArray[i].toString() + " ,";
                }

            } else {
                theText = value.toString();
            }

            renderer.setText(theText);
            renderer.setToolTipText(value.toString());

        }
        return renderer;
    }
}