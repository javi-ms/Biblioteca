/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author Javier
 */
public class ListasDeplegablesRenderer extends DefaultListCellRenderer{
    
         // Rellenar combobox de categorías
        
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Editorial editoriales = (Editorial)value;
        setText(editoriales.getNombreEditorial());
        if(isSelected) {
            this.setBackground(Color.cyan);
        } else {
            this.setBackground(Color.white);
        }
        return this;
    }
 

    
}
