/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Usuario;
import com.app.inventario.service.int_Usuario_service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/perfil")
public class perfilController {

    @Autowired
    private int_Usuario_service usuarioService;

    private String alerta;

    @GetMapping("")
    public String perfil(Model model) {
        alerta=null;
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("alerta", alerta);
        return "perfil";
    }

    @PostMapping("/guardarContraseña")
    public String guardarContraseña(Model model, HttpSession session, @RequestParam("passActual") String passActual, @RequestParam("passNueva") String passNueva, @RequestParam("passRepetida") String passRepetida) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario.getPassword().equals(passActual)) {
            if (passNueva.equals(passRepetida)) {
                usuario.setPassword(passNueva);
                usuario = usuarioService.guardar(usuario);
                session.setAttribute("usuario", usuario);
                alerta = "Contraseña modificada!";
            } else {
                alerta = "Las contraseñas no coiniceden!";
            }
        } else {
            alerta = "Contraseña actual invalida!";
        }

        model.addAttribute("alerta", alerta);
        model.addAttribute("usuario", new Usuario());
        return "perfil";
    }

}
