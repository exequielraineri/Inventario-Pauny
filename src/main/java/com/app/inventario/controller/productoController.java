/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Producto;
import com.app.inventario.service.int_Producto_service;
import jakarta.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/productos")
public class productoController {
    
    @Autowired
    private int_Producto_service productoService;
    
    @GetMapping("")
    public String productos(Model model) {
        List<Producto> productos = productoService.listarProducto();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        return "productos";
    }
    
    @PostMapping("/nuevoProducto")
    public String guardarProducto(@ModelAttribute("producto") Producto p, Model model) {
        p.setFechaRegistro(new Date());
        productoService.guardar(p);
        return "redirect:/productos";
    }
    
    @GetMapping("/eliminar/{iDProducto}")
    public String eliminarProducto(@PathVariable Integer iDProducto) {
        productoService.eliminar(iDProducto);
        return "redirect:/productos";
    }
    
    @GetMapping("/editar/{iDProducto}")
    public String editarProducto(@PathVariable Integer iDProducto, Model model) {
        Optional<Producto> producto = productoService.obtenerProducto(iDProducto);
        model.addAttribute("producto", producto.get());
        return "editar_producto";
    }
    
    @PostMapping("/guardarModificacion")
    public String guardarModificacion(@ModelAttribute("producto") Producto producto, @RequestParam("add_stock") Integer add_stock) {
        //Optional<Producto> p=productoService.obtenerProducto(producto.getIDProducto());
        System.out.println("add_ "+ add_stock);
        if (add_stock != 0 || add_stock != null) {
            producto.setStock(producto.getStock()+add_stock);
        }
       
        
        producto.setFechaRegistro(new Date());
        productoService.guardar(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/filtrarCat")
    public String filtrarCat(@RequestParam("categoria") String categoria, Model model) {
        System.out.println("Categoria-->" + categoria);
        List<Producto> productos = productoService.listarProducto();
        List<Producto> filtrado = new ArrayList<>();
        if (categoria.equals("Todos")) {
            return "redirect:/productos";
        } else {
            for (Producto p : productos) {
                if (p.getCategoria().equals(categoria)) {
                    filtrado.add(p);
                }
            }
            model.addAttribute("productos", filtrado);
            model.addAttribute("producto", new Producto());
            return "productos";
        }
    }
    
}
