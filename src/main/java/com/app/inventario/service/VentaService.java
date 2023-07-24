/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Venta;
import com.app.inventario.repository.int_Venta_repo;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements int_Venta_service {

    @Autowired
    private int_Venta_repo ventaRepo;

    @Override
    public Venta guardar(Venta venta) {
        return ventaRepo.save(venta);
    }

    @Override
    public void eliminar(Integer id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public Optional<Venta> obtenerVenta(Integer id) {
        return ventaRepo.findById(id);
    }

    @Override
    public List<Venta> listarVenta() {
        return ventaRepo.findAll();
    }

    @Override
    public List<Venta> listarVentaDesc() {
        return ventaRepo.findAllDesc();
    }

    @Override
    public List<Venta> listarFechaBETWEEN(Timestamp fechaInicio, Timestamp fechaFin) {
        return ventaRepo.findByFechaVenta(fechaInicio, fechaFin);
    }

}
