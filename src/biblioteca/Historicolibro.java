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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "historicolibro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicolibro.findAll", query = "SELECT h FROM Historicolibro h"),
    @NamedQuery(name = "Historicolibro.findByIDlibro", query = "SELECT h FROM Historicolibro h WHERE h.iDlibro = :iDlibro"),
    @NamedQuery(name = "Historicolibro.findByNombreLibro", query = "SELECT h FROM Historicolibro h WHERE h.nombreLibro = :nombreLibro"),
    @NamedQuery(name = "Historicolibro.findByAutor", query = "SELECT h FROM Historicolibro h WHERE h.autor = :autor"),
    @NamedQuery(name = "Historicolibro.findByPrestado", query = "SELECT h FROM Historicolibro h WHERE h.prestado = :prestado"),
    @NamedQuery(name = "Historicolibro.findByFechaPrestamo", query = "SELECT h FROM Historicolibro h WHERE h.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "Historicolibro.findByFechaDevolucion", query = "SELECT h FROM Historicolibro h WHERE h.fechaDevolucion = :fechaDevolucion")})
public class Historicolibro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_libro")
    private Integer iDlibro;
    @Basic(optional = false)
    @Column(name = "NombreLibro")
    private String nombreLibro;
    @Column(name = "Autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "Prestado")
    private String prestado;
    @Column(name = "FechaPrestamo")
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
    @Column(name = "FechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @JoinColumn(name = "ID_Prestamo", referencedColumnName = "ID_libro")
    @ManyToOne
    private Libro_1 iDPrestamo;

    public Historicolibro() {
    }

    public Historicolibro(Integer iDlibro) {
        this.iDlibro = iDlibro;
    }

    public Historicolibro(Integer iDlibro, String nombreLibro, String prestado) {
        this.iDlibro = iDlibro;
        this.nombreLibro = nombreLibro;
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

    public String getPrestado() {
        return prestado;
    }

    public void setPrestado(String prestado) {
        this.prestado = prestado;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro_1 getIDPrestamo() {
        return iDPrestamo;
    }

    public void setIDPrestamo(Libro_1 iDPrestamo) {
        this.iDPrestamo = iDPrestamo;
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
        if (!(object instanceof Historicolibro)) {
            return false;
        }
        Historicolibro other = (Historicolibro) object;
        if ((this.iDlibro == null && other.iDlibro != null) || (this.iDlibro != null && !this.iDlibro.equals(other.iDlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Historicolibro[ iDlibro=" + iDlibro + " ]";
    }
    
}
