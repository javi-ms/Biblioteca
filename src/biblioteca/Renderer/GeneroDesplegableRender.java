/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca.Renderer;
import biblioteca.Desplegables.Editorial;
import biblioteca.Desplegables.Genero;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Javier
 */
public class GeneroDesplegableRender extends DefaultListCellRenderer{
@Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        Genero genero = (Genero)value;
        
        setText(genero.getNombreGenero());
        
        if(isSelected) {
            this.setBackground(Color.red);
        } else {
            this.setBackground(Color.green);
        }
        return this;
    }
}
