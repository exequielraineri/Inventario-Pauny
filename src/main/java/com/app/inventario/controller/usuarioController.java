/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Usuario;
import com.app.inventario.service.UsuarioService;
import com.app.inventario.service.int_Usuario_service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/admin")
public class usuarioController {
    
    @Autowired
    private int_Usuario_service usuarioService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        List<Usuario> lista = usuarioService.listarUsuarios();
        model.addAttribute("user1", lista.get(0));
        
        return "index";
    }
    
    
    
    @GetMapping("/inicio")
    public String inicio(){
        return "productos";
    }
    
    
    @GetMapping("/productos")
    public String productos(){
        return "productos";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    
    @GetMapping("/nuevaVenta")
    public String nuevaVenta(){
        return "nueva_venta";
    }
 
    @GetMapping("/productos/editar")
    public String editarProducto(){
        return "editar_producto";
    }
    
    
    
}
