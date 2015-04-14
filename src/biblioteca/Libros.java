/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class Libros {

    private ArrayList<Libro> listaLibros = new ArrayList();

    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }
    public int getSize(){
        //
        return listaLibros.size();
        
    }
}
