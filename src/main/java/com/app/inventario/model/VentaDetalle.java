/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "venta_detalle")
@NamedQueries({
    @NamedQuery(name = "VentaDetalle.findAll", query = "SELECT v FROM VentaDetalle v"),
    @NamedQuery(name = "VentaDetalle.findByIDVentaDetalle", query = "SELECT v FROM VentaDetalle v WHERE v.iDVentaDetalle = :iDVentaDetalle"),
    @NamedQuery(name = "VentaDetalle.findByCantidad", query = "SELECT v FROM VentaDetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentaDetalle.findByPrecioUnitario", query = "SELECT v FROM VentaDetalle v WHERE v.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "VentaDetalle.findBySubtotal", query = "SELECT v FROM VentaDetalle v WHERE v.subtotal = :subtotal")})
public class VentaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Venta_Detalle")
    private Integer iDVentaDetalle;
    @Column(name = "Cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Precio_Unitario")
    private BigDecimal precioUnitario;
    @Column(name = "Subtotal")
    private BigDecimal subtotal;
    @JoinColumn(name = "ID_Venta", referencedColumnName = "ID_Venta")
    @ManyToOne
    private Venta iDVenta;
    @JoinColumn(name = "ID_Producto", referencedColumnName = "ID_Producto")
    @ManyToOne
    private Producto iDProducto;

    public VentaDetalle() {
    }

    public VentaDetalle(Integer iDVentaDetalle) {
        this.iDVentaDetalle = iDVentaDetalle;
    }

    public Integer getIDVentaDetalle() {
        return iDVentaDetalle;
    }

    public void setIDVentaDetalle(Integer iDVentaDetalle) {
        this.iDVentaDetalle = iDVentaDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getIDVenta() {
        return iDVenta;
    }

    public void setIDVenta(Venta iDVenta) {
        this.iDVenta = iDVenta;
    }

    public Producto getIDProducto() {
        return iDProducto;
    }

    public void setIDProducto(Producto iDProducto) {
        this.iDProducto = iDProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDVentaDetalle != null ? iDVentaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaDetalle)) {
            return false;
        }
        VentaDetalle other = (VentaDetalle) object;
        if ((this.iDVentaDetalle == null && other.iDVentaDetalle != null) || (this.iDVentaDetalle != null && !this.iDVentaDetalle.equals(other.iDVentaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.inventario.model.VentaDetalle[ iDVentaDetalle=" + iDVentaDetalle + " ]";
    }
    
}
