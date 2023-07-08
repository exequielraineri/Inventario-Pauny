/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.inventario.service;

import com.app.inventario.model.Usuario;
import java.util.List;
import java.util.Optional;


public interface int_Usuario_service {

    public Usuario guardar(Usuario usuario);

    public void eliminar(Integer id);

    public Optional<Usuario> obtenerUsuario(Integer id);

    public List<Usuario> listarUsuarios();

    public Optional<Usuario> obtenerUsuario(String user);
}
