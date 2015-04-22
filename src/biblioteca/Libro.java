/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.util.Date;

/**
 *
 * @author Javier
 */
public class Libro {

    private int id;
    private String nombreLibro;
    private String Autor;
    private int ISBN;
    private Date fechaPublicacion;
    private int nuEdicion;
    //objetos
    private Editorial editorial;
    private Genero genero;
    //private Colegio colegio;
    private boolean prestado;
    private String prestadoA;
    private Date FechaPrestamo;
    private Date fechaDevolucion;
    private String sinopsis;

    public Libro() {

    }
//Constructor y getter y setter
//    public Libro(int id, String nombreLibro, String Autor, int ISBN, Date fechaPublicacion, int nuEdicion, Editorial editorial, Genero genero, boolean prestado, String prestadoA, Date FechaPrestamo, Date fechaDevolucion) {
//        this.id = id;
//        this.nombreLibro = nombreLibro;
//        this.Autor = Autor;
//        this.ISBN = ISBN;
//        this.fechaPublicacion = fechaPublicacion;
//        this.nuEdicion = nuEdicion;
//        this.editorial = editorial;
//        this.genero = genero;
//        this.prestado = prestado;
//    }

    public Libro(int id, String nombreLibro, String Autor, int ISBN, Date fechaPublicacion, int nuEdicion, Editorial editorial, Genero genero, boolean prestado, String sinopsis) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.Autor = Autor;
        this.ISBN = ISBN;
        this.fechaPublicacion = fechaPublicacion;
        this.nuEdicion = nuEdicion;
        this.genero = genero;
        this.editorial = editorial;
        this.prestado = prestado;
        this.sinopsis = sinopsis;

    }

   
//libro prestado y ...

    public Libro(int id, String nombreLibro, String Autor, boolean prestado, String prestadoA, Date FechaPrestamo, Date fechaDevolucion) {
        this.id = id;
        this.nombreLibro = nombreLibro;
        this.Autor = Autor;
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getNuEdicion() {
        return nuEdicion;
    }

    public void setNuEdicion(int numEdicion) {
        this.nuEdicion = numEdicion;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero){
        this.genero = genero;
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

    public Date getFechaPrestamo() {
        return FechaPrestamo;
    }

    public void setFechaPrestamo(Date FechaPrestamo) {
        this.FechaPrestamo = FechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

}
