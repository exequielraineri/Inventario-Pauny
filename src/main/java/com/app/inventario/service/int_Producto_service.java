/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Producto;
import java.util.List;
import java.util.Optional;

public interface int_Producto_service {

    public Producto guardar(Producto producto);

    public void eliminar(Integer id);

    public Optional<Producto> obtenerProducto(Integer id);

    public List<Producto> listarProducto();
}
