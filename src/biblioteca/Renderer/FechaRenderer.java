/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Renderer;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;

public class FechaRenderer extends DefaultTableCellRenderer {

    @Override
    protected void setValue(Object value) {
        DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.SHORT);
        setText(fechaFormato.format((Date)value));
        setHorizontalAlignment(RIGHT);
    }

}
