/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "venta")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByIDVenta", query = "SELECT v FROM Venta v WHERE v.iDVenta = :iDVenta"),
    @NamedQuery(name = "Venta.findByFechaVenta", query = "SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta"),
    @NamedQuery(name = "Venta.findByTotal", query = "SELECT v FROM Venta v WHERE v.total = :total")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Venta")
    private Integer iDVenta;
    @Column(name = "Fecha_Venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente")
    @ManyToOne
    private Cliente iDCliente;
    @JoinColumn(name = "ID_Usuario", referencedColumnName = "ID")
    @ManyToOne
    private Usuario iDUsuario;
    @OneToMany(mappedBy = "iDVenta")
    private List<VentaDetalle> ventaDetalleList;

    public Venta() {
    }

    public Venta(Integer iDVenta) {
        this.iDVenta = iDVenta;
    }

    public Integer getIDVenta() {
        return iDVenta;
    }

    public void setIDVenta(Integer iDVenta) {
        this.iDVenta = iDVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Cliente iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Usuario getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Usuario iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public List<VentaDetalle> getVentaDetalleList() {
        return ventaDetalleList;
    }

    public void setVentaDetalleList(List<VentaDetalle> ventaDetalleList) {
        this.ventaDetalleList = ventaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVenta != null ? iDVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.iDVenta == null && other.iDVenta != null) || (this.iDVenta != null && !this.iDVenta.equals(other.iDVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.inventario.model.Venta[ iDVenta=" + iDVenta + " ]";
    }
    
}
