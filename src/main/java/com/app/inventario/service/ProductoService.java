/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Producto;
import com.app.inventario.repository.int_Producto_repo;
import com.app.inventario.repository.int_Usuario_repo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoService implements int_Producto_service{
    
    @Autowired
    private int_Producto_repo productoRepo;

    @Override
    public Producto guardar(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepo.deleteById(id);
    }

    @Override
    public Optional<Producto> obtenerProducto(Integer id) {
        return productoRepo.findById(id);
    }

    @Override
    public List<Producto> listarProducto() {
        return productoRepo.findAll();
    }
}
