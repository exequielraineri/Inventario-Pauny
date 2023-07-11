/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Cliente;
import com.app.inventario.repository.int_Cliente_repo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements int_Cliente_service {

    @Autowired
    private int_Cliente_repo clienteRepo;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public Optional<Cliente> obtenerCliente(Integer id) {
        return clienteRepo.findById(id);
    }

    @Override
    public List<Cliente> listarCliente() {
        return clienteRepo.findAll();
    }

}
