/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Cliente;
import com.app.inventario.model.Producto;
import com.app.inventario.model.Usuario;
import com.app.inventario.service.int_Cliente_service;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
@RequestMapping("/cliente")
public class clienteController {

    @Autowired
    private int_Cliente_service clienteService;

    @GetMapping("/editar/{iDCliente}")
    public String editarCliente(@PathVariable Integer iDCliente, Model model) {
        Cliente cliente = clienteService.obtenerCliente(iDCliente).get();
        model.addAttribute("cliente", cliente);
        return "editar_cliente";

    }

    @PostMapping("/guardarModificacion")
    private String guardarModificacion(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/reporte/clientes";
    }

}
