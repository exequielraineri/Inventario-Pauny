/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Usuario;
import com.app.inventario.model.VentaDetalle;
import com.app.inventario.repository.int_Usuario_repo;
import com.app.inventario.repository.int_Venta_Detalle_repo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VentaDetalleService implements int_Venta_Detalle_service{
    
    @Autowired
    private int_Venta_Detalle_repo venta_Detalle_repo;

    @Override
    public VentaDetalle guardar(VentaDetalle ventaDetalle) {
        return venta_Detalle_repo.save(ventaDetalle);
    }

    @Override
    public void eliminar(Integer id) {
        venta_Detalle_repo.deleteById(id);
    }

    @Override
    public Optional<VentaDetalle> obtenerVentaDetalle(Integer id) {
        return venta_Detalle_repo.findById(id);
    }

    @Override
    public List<VentaDetalle> listarVentaDetalle() {
        return venta_Detalle_repo.findAll();
    }
    
    
}
