/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro_1.findAll", query = "SELECT l FROM Libro_1 l"),
    @NamedQuery(name = "Libro_1.findByIDlibro", query = "SELECT l FROM Libro_1 l WHERE l.iDlibro = :iDlibro"),
    @NamedQuery(name = "Libro_1.findByNombreLibro", query = "SELECT l FROM Libro_1 l WHERE l.nombreLibro = :nombreLibro"),
    @NamedQuery(name = "Libro_1.findByAutor", query = "SELECT l FROM Libro_1 l WHERE l.autor = :autor"),
    @NamedQuery(name = "Libro_1.findByIsbn", query = "SELECT l FROM Libro_1 l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Libro_1.findByNuEdicion", query = "SELECT l FROM Libro_1 l WHERE l.nuEdicion = :nuEdicion"),
    @NamedQuery(name = "Libro_1.findByEditorial", query = "SELECT l FROM Libro_1 l WHERE l.editorial = :editorial"),
    @NamedQuery(name = "Libro_1.findByGenero", query = "SELECT l FROM Libro_1 l WHERE l.genero = :genero"),
    @NamedQuery(name = "Libro_1.findByPrestado", query = "SELECT l FROM Libro_1 l WHERE l.prestado = :prestado"),
    @NamedQuery(name = "Libro_1.findByPrestadoA", query = "SELECT l FROM Libro_1 l WHERE l.prestadoA = :prestadoA")})
public class Libro_1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_libro")
    private String iDlibro;
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
    @Basic(optional = false)
    @Column(name = "Prestado")
    private boolean prestado;
    @Column(name = "PrestadoA")
    private String prestadoA;
    @OneToMany(mappedBy = "iDLibro")
    private Collection<Prestamo> prestamoCollection;
    @OneToMany(mappedBy = "iDPrestamo")
    private Collection<Historicolibro> historicolibroCollection;

    public Libro_1() {
    }

    public Libro_1(String iDlibro) {
        this.iDlibro = iDlibro;
    }

    public Libro_1(String iDlibro, int nuEdicion, String editorial, boolean prestado) {
        this.iDlibro = iDlibro;
        this.nuEdicion = nuEdicion;
        this.editorial = editorial;
        this.prestado = prestado;
    }

    public String getIDlibro() {
        return iDlibro;
    }

    public void setIDlibro(String iDlibro) {
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

    @XmlTransient
    public Collection<Prestamo> getPrestamoCollection() {
        return prestamoCollection;
    }

    public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
        this.prestamoCollection = prestamoCollection;
    }

    @XmlTransient
    public Collection<Historicolibro> getHistoricolibroCollection() {
        return historicolibroCollection;
    }

    public void setHistoricolibroCollection(Collection<Historicolibro> historicolibroCollection) {
        this.historicolibroCollection = historicolibroCollection;
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
        if (!(object instanceof Libro_1)) {
            return false;
        }
        Libro_1 other = (Libro_1) object;
        if ((this.iDlibro == null && other.iDlibro != null) || (this.iDlibro != null && !this.iDlibro.equals(other.iDlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Libro_1[ iDlibro=" + iDlibro + " ]";
    }
    
}
