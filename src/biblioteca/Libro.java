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
public class Libro {

    private int id ;
    private String nombreLibro ;
    private String Autor ;
    private int ISBN ;
    private String fechaPublicacion;
    private int nuEdicion ;
    //objetos
    private Editorial editorial ;
    private Genero genero ;
    //private Colegio colegio;
    private boolean prestado;
    private String prestadoA;
    private String FechaPrestamo;
    private String fechaDevolucion;

    public Libro() {
        
    }
//Constructor y getter y setter
    public Libro(int id, String nombreLibro, String Autor, int ISBN, String fechaPublicacion, int nuEdicion, Editorial editorial, Genero tipoDeLibro, boolean prestado, String prestadoA, String FechaPrestamo, String fechaDevolucion) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.Autor = Autor;
        this.ISBN = ISBN;
        this.fechaPublicacion = fechaPublicacion;
        this.nuEdicion = nuEdicion;
        this.editorial = editorial;
        this.genero = tipoDeLibro;
        this.prestado = prestado;
//        this.prestadoA = prestadoA;
//        this.FechaPrestamo = FechaPrestamo;
//        this.fechaDevolucion = fechaDevolucion;
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNºEdicion() {
        return nuEdicion;
    }

    public void setNºEdicion(int nºEdicion) {
        this.nuEdicion = nºEdicion;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Genero getTipoDeLibro() {
        return genero;
    }

    public void setTipoDeLibro(Genero tipoDeLibro) {
        this.genero = tipoDeLibro;
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
