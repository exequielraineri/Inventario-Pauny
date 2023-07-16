/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Cliente;
import com.app.inventario.model.Usuario;
import com.app.inventario.model.Venta;
import com.app.inventario.service.int_Cliente_service;
import com.app.inventario.service.int_Venta_service;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
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
@RequestMapping("/reporte")
public class reporteController {

    @Autowired
    private int_Venta_service ventaService;

    @Autowired
    private int_Cliente_service clienteService;

    @GetMapping("/ventas")
    public String ventas(Model model, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        List<Venta> ventas = ventaService.listarVentaDesc();
        List<Venta> aux = new ArrayList<>();
        for (Venta v : ventas) {
            if (v.getIDUsuario().equals(u)) {
                aux.add(v);
            }
        }
        model.addAttribute("ventas", aux);
        return "reporte_ventas";
    }

    @GetMapping("/clientes")
    public String clientes(Model model) {
        List<Cliente> clientes = clienteService.listarCliente();
        model.addAttribute("clientes", clientes);
        return "reporte_clientes";
    }

}
