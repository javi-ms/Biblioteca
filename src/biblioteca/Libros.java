/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

/**
 *
 * @author Javier
 */
public class Libros {

    private int id ;
    private String nombreLibro ;
    private String Autor ;
    private int ISB ;
    //provisional
    private String fechaPublicacion;
    private int nºEdicion ;
    private String editorial ;
    private String tipoDeLibro ;
    private String colegio;
    private boolean prestado;
    private String prestadoA;
    private String FechaPrestamo;
    private String fechaDevolucion;

    public Libros() {
    }

    public Libros(int id, String nombreLibro, String Autor, int ISB, String fechaPublicacion, int nºEdicion, String editorial, String tipoDeLibro, String colegio, boolean prestado, String prestadoA, String FechaPrestamo, String fechaDevolucion) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.Autor = Autor;
        this.ISB = ISB;
        this.fechaPublicacion = fechaPublicacion;
        this.nºEdicion = nºEdicion;
        this.editorial = editorial;
        this.tipoDeLibro = tipoDeLibro;
        this.colegio = colegio;
        this.prestado = prestado;
        this.prestadoA = prestadoA;
        this.FechaPrestamo = FechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getISB() {
        return ISB;
    }

    public void setISB(int ISB) {
        this.ISB = ISB;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNºEdicion() {
        return nºEdicion;
    }

    public void setNºEdicion(int nºEdicion) {
        this.nºEdicion = nºEdicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTipoDeLibro() {
        return tipoDeLibro;
    }

    public void setTipoDeLibro(String tipoDeLibro) {
        this.tipoDeLibro = tipoDeLibro;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public String getPrestadoA() {
        return prestadoA;
    }

    public void setPrestadoA(String prestadoA) {
        this.prestadoA = prestadoA;
    }

    public String getFechaPrestamo() {
        return FechaPrestamo;
    }

    public void setFechaPrestamo(String FechaPrestamo) {
        this.FechaPrestamo = FechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
