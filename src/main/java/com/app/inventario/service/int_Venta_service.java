/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Venta;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface int_Venta_service {

    public Venta guardar(Venta venta);

    public void eliminar(Integer id);

    public Optional<Venta> obtenerVenta(Integer id);

    public List<Venta> listarVenta();

    public List<Venta> listarVentaDesc();

    public List<Venta> listarFechaBETWEEN(Timestamp fechaInicio, Timestamp fechaFin);
}
