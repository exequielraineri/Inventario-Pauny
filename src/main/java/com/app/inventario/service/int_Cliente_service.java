/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface int_Cliente_service {

    public Cliente guardar(Cliente cliente);

    public void eliminar(Integer id);

    public Optional<Cliente> obtenerCliente(Integer id);

    public List<Cliente> listarCliente();
}
