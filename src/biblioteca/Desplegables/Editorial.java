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
public class Editorial {

    private int idEditorial;
    private String nombreEditorial;

    public Editorial() {
    }

    @Override
    public String toString() {
        return nombreEditorial;
    }

    public Editorial(int idEditorial, String nombreEditorial) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
    }

    public int getIdEditorial(int idEditorial) {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

}
