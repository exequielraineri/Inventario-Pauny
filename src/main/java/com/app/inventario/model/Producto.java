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
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIDProducto", query = "SELECT p FROM Producto p WHERE p.iDProducto = :iDProducto"),
    @NamedQuery(name = "Producto.findByDscripcion", query = "SELECT p FROM Producto p WHERE p.dscripcion = :dscripcion"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByStock", query = "SELECT p FROM Producto p WHERE p.stock = :stock"),
    @NamedQuery(name = "Producto.findByFechaRegistro", query = "SELECT p FROM Producto p WHERE p.fechaRegistro = :fechaRegistro")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Producto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iDProducto;
    @Column(name = "dscripcion")
    private String dscripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "Stock")
    private Integer stock;
    @Column(name = "Fecha_Registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "iDProducto")
    private List<VentaDetalle> ventaDetalleList;

    public Producto() {
    }

    public Producto(Integer iDProducto) {
        this.iDProducto = iDProducto;
    }

    public Integer getIDProducto() {
        return iDProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getiDProducto() {
        return iDProducto;
    }

    public void setiDProducto(Integer iDProducto) {
        this.iDProducto = iDProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIDProducto(Integer iDProducto) {
        this.iDProducto = iDProducto;
    }

    public String getDscripcion() {
        return dscripcion;
    }

    public void setDscripcion(String dscripcion) {
        this.dscripcion = dscripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
        hash += (iDProducto != null ? iDProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.iDProducto == null && other.iDProducto != null) || (this.iDProducto != null && !this.iDProducto.equals(other.iDProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.inventario.model.Producto[ iDProducto=" + iDProducto + " ]";
    }

}
