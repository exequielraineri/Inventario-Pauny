/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.VentaDetalle;
import java.util.List;
import java.util.Optional;

public interface int_Venta_Detalle_service {

    public VentaDetalle guardar(VentaDetalle ventaDetalle);

    public void eliminar(Integer id);

    public Optional<VentaDetalle> obtenerVentaDetalle(Integer id);

    public List<VentaDetalle> listarVentaDetalle();
}
