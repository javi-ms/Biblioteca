/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class Libros {
    private static List<Libro> listaLibros = new ArrayList();

    public List<Libro> getListaLibros() {
        return listaLibros;
    }
    public int getSize(){
        //
        return listaLibros.size();
    }
    public Libro getLibro(int index){
        return this.listaLibros.get(index);
    }

    public static void setListaLibros(List<Libro> listaLibros) {
        Libros.listaLibros = listaLibros;
    }
    
}
