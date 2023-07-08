/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Producto;
import com.app.inventario.model.Usuario;
import com.app.inventario.service.int_Usuario_service;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private int_Usuario_service usuarioService;

    private Producto producto = new Producto();


    @PostMapping("/auth")
    public String auth(@ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
        System.out.println("Usuario:---->" + usuario.getUser());
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        String alerta = "";
        boolean siExiste = false;
        for (Usuario u : usuarios) {
            if (u.getUser().equals(usuario.getUser()) && u.getPassword().equals(usuario.getPassword())) {
                usuario = u;
                siExiste = true;
                session.setAttribute("usuario", usuario);
                break;
            } else {
                siExiste = false;
            }
        }
        if (siExiste) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("producto", producto);
            return "productos";

        } else {
            alerta = "Usuario o Contraseña incorrecta!";
            model.addAttribute("alerta", alerta);
            return "login";
        }

    }

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("producto", producto);
        return "/productos";
    }

    @GetMapping("/login")
    public String login(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        String alerta = "";
        model.addAttribute("alerta", alerta);
        return "login";
    }

    @GetMapping("/nuevaVenta")
    public String nuevaVenta() {
        return "nueva_venta";
    }

    @GetMapping("/productos/editar")
    public String editarProducto() {
        return "editar_producto";
    }

}
