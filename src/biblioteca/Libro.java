/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByIDlibro", query = "SELECT l FROM Libro l WHERE l.iDlibro = :iDlibro"),
    @NamedQuery(name = "Libro.findByNombreLibro", query = "SELECT l FROM Libro l WHERE l.nombreLibro = :nombreLibro"),
    @NamedQuery(name = "Libro.findByAutor", query = "SELECT l FROM Libro l WHERE l.autor = :autor"),
    @NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Libro.findByNuEdicion", query = "SELECT l FROM Libro l WHERE l.nuEdicion = :nuEdicion"),
    @NamedQuery(name = "Libro.findByEditorial", query = "SELECT l FROM Libro l WHERE l.editorial = :editorial"),
    @NamedQuery(name = "Libro.findByGenero", query = "SELECT l FROM Libro l WHERE l.genero = :genero"),
    @NamedQuery(name = "Libro.findByPublicacion", query = "SELECT l FROM Libro l WHERE l.publicacion = :publicacion"),
    @NamedQuery(name = "Libro.findByPrestado", query = "SELECT l FROM Libro l WHERE l.prestado = :prestado"),
    @NamedQuery(name = "Libro.findByPrestadoA", query = "SELECT l FROM Libro l WHERE l.prestadoA = :prestadoA"),
    @NamedQuery(name = "Libro.findBySinopsis", query = "SELECT l FROM Libro l WHERE l.sinopsis = :sinopsis")})
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_libro")
    private Integer iDlibro;
    @Column(name = "Nombre_Libro")
    private String nombreLibro;
    @Column(name = "Autor")
    private String autor;
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "NuEdicion")
    private int nuEdicion;
    @Basic(optional = false)
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "Genero")
    private String genero;
    @Column(name = "Publicacion")
    @Temporal(TemporalType.DATE)
    private Date publicacion;
    @Basic(optional = false)
    @Column(name = "Prestado")
    private boolean prestado;
    @Column(name = "PrestadoA")
    private String prestadoA;
    @Column(name = "Sinopsis")
    private String sinopsis;

    public Libro() {
    }

    public Libro(Integer iDlibro) {
        this.iDlibro = iDlibro;
    }

    public Libro(Integer iDlibro, int nuEdicion, String editorial, boolean prestado) {
        this.iDlibro = iDlibro;
        this.nuEdicion = nuEdicion;
        this.editorial = editorial;
        this.prestado = prestado;
    }

    public Integer getIDlibro() {
        return iDlibro;
    }

    public void setIDlibro(Integer iDlibro) {
        this.iDlibro = iDlibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNuEdicion() {
        return nuEdicion;
    }

    public void setNuEdicion(int nuEdicion) {
        this.nuEdicion = nuEdicion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public boolean getPrestado() {
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

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDlibro != null ? iDlibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.iDlibro == null && other.iDlibro != null) || (this.iDlibro != null && !this.iDlibro.equals(other.iDlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Libro[ iDlibro=" + iDlibro + " ]";
    }
    
}
