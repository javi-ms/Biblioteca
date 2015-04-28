/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Javier
 */
public class ControlNumerico extends PlainDocument{
  private int maxLength;
    // Se le debe pasar por parámetro el número máximo de caracteres permitidos
    public ControlNumerico(int maxLength) {
        this.maxLength = maxLength;
    }
    
    public void insertInt(int offs, String str, AttributeSet a) throws BadLocationException {
        // Sólo se ejecuta el insertString original si no se sobrepasa el
        //  límite establecido
        //comrpobar el str este entre cero y nuevo
//        if (str>=0 && str<10) {
            
        
       if(getLength() + str.length() <= maxLength) {
            super.insertString(offs, str, a);
       }
        }
          
}
