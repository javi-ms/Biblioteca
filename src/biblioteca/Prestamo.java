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
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByIDPrestamo", query = "SELECT p FROM Prestamo p WHERE p.iDPrestamo = :iDPrestamo"),
    @NamedQuery(name = "Prestamo.findByPrestadoA", query = "SELECT p FROM Prestamo p WHERE p.prestadoA = :prestadoA"),
    @NamedQuery(name = "Prestamo.findByFechaPrestamo", query = "SELECT p FROM Prestamo p WHERE p.fechaPrestamo = :fechaPrestamo"),
    @NamedQuery(name = "Prestamo.findByFechaDevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechaDevolucion = :fechaDevolucion")})
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Prestamo")
    private Integer iDPrestamo;
    @Basic(optional = false)
    @Column(name = "PrestadoA")
    private String prestadoA;
    @Column(name = "FechaPrestamo")
    @Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
    @Column(name = "FechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @JoinColumn(name = "ID_libro", referencedColumnName = "ID_libro")
    @ManyToOne
    private Libro iDlibro;

    public Prestamo() {
    }

    public Prestamo(Integer iDPrestamo) {
        this.iDPrestamo = iDPrestamo;
    }

    public Prestamo(Integer iDPrestamo, String prestadoA) {
        this.iDPrestamo = iDPrestamo;
        this.prestadoA = prestadoA;
    }

    public Integer getIDPrestamo() {
        return iDPrestamo;
    }

    public void setIDPrestamo(Integer iDPrestamo) {
        this.iDPrestamo = iDPrestamo;
    }

    public String getPrestadoA() {
        return prestadoA;
    }

    public void setPrestadoA(String prestadoA) {
        this.prestadoA = prestadoA;
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

    public Libro getIDlibro() {
        return iDlibro;
    }

    public void setIDlibro(Libro iDlibro) {
        this.iDlibro = iDlibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPrestamo != null ? iDPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.iDPrestamo == null && other.iDPrestamo != null) || (this.iDPrestamo != null && !this.iDPrestamo.equals(other.iDPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "biblioteca.Prestamo[ iDPrestamo=" + iDPrestamo + " ]";
    }
    
}
