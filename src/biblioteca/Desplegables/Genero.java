/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Desplegables;

/**
 *
 * @author Javier
 */
public class Genero {
    private int genero;
    private String nombreGenero;

    public Genero() {
    }

    public Genero(int genero, String nombreGenero) {
        this.genero = genero;
        this.nombreGenero = nombreGenero;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
    //es necesario el toString para que muestre los datos nuevos
    @Override
    public String toString() {
        return nombreGenero;
    }
    
}
