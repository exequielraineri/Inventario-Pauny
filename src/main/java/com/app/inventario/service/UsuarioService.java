/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Usuario;
import com.app.inventario.repository.int_Usuario_repo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements int_Usuario_service {

    @Autowired
    private int_Usuario_repo usuarioRepo;

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public Optional<Usuario> obtenerUsuario(Integer id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuario(String user) {
        return usuarioRepo.findByUser(user);
    }

}
