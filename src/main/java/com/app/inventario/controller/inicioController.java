/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Cliente;
import com.app.inventario.model.Producto;
import com.app.inventario.model.Venta;
import com.app.inventario.service.int_Cliente_service;
import com.app.inventario.service.int_Producto_service;
import com.app.inventario.service.int_Venta_service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/inicio")
public class inicioController {

    @Autowired
    private int_Producto_service productoService;

    @Autowired
    private int_Cliente_service clienteService;

    @Autowired
    private int_Venta_service ventaService;

    private int cant_productos;

    private Map<String, Integer> mapeo;

    @GetMapping("")
    public String home(Model model) {
        //cant_productos = cant_Productos();
        model.addAttribute("cant_productos", cant_Productos());
        model.addAttribute("cant_clientes", cant_Clientes());
        model.addAttribute("cant_ventas", cant_Ventas());

        mapeo = obtenerMapeo(this.mapeo);
       // obtenerMapeo(mapeo);
        //mapeo = new LinkedHashMap<>();
        /*mapeo.put("Enero", 50);
        mapeo.put("Febrero", 93);
        mapeo.put("Marzo", 69);
        mapeo.put("Abril", 23);
        mapeo.put("Mayo", 25);
        mapeo.put("Junio", 18);
        mapeo.put("Julio", 50);
        mapeo.put("Agosto", 93);
        mapeo.put("Septiembre", 69);
        mapeo.put("Octubre", 23);
        mapeo.put("Noviembre", 25);
        mapeo.put("Diciembre", 18);*/
        model.addAttribute("mapeo", mapeo);

        return "index";
    }

    private int cant_Productos() {
        List<Producto> productos = productoService.listarProducto();
        return productos.size();
    }

    private int cant_Clientes() {
        List<Cliente> clientes = clienteService.listarCliente();
        return clientes.size();
    }

    private int cant_Ventas() {
        List<Venta> ventas = ventaService.listarVenta();
        return ventas.size();
    }

    private Map<String, Integer> obtenerMapeo(Map<String, Integer> mapeo) {
        mapeo = new LinkedHashMap<>();
        int enero = 0;
        int febrero = 0;
        int marzo = 0;
        int abril = 0;
        int mayo = 0;
        int junio = 0;
        int julio = 0;
        int agosto = 0;
        int septimbre = 0;
        int octubre = 0;
        int novimbre = 0;
        int diciembre = 0;

        List<Venta> ventas = ventaService.listarVenta();
        for (Venta venta : ventas) {
            System.out.println("mes-->" + venta.getFechaVenta().getMonth());
            int mes = venta.getFechaVenta().getMonth() + 1;
            switch (mes) {
                case 1: {
                    enero++;
                    break;
                }
                case 2: {
                    febrero++;
                    break;
                }
                case 3: {
                    marzo++;
                    break;
                }
                case 4: {
                    abril++;
                    break;
                }
                case 5: {
                    mayo++;
                    break;
                }
                case 6: {
                    junio++;
                    break;
                }
                case 7: {
                    julio++;
                    break;
                }
                case 8: {
                    agosto++;
                    break;
                }
                case 9: {
                    septimbre++;
                    break;
                }
                case 10: {
                    octubre++;
                    break;
                }
                case 11: {
                    novimbre++;
                    break;
                }
                case 12: {
                    diciembre++;
                    break;
                }
                default: {
                    break;
                }
            }

        }

        mapeo.put("Enero", enero);
        mapeo.put("Febrero", febrero);
        mapeo.put("Marzo", marzo);
        mapeo.put("Abril", abril);
        mapeo.put("Mayo", mayo);
        mapeo.put("Junio", junio);
        mapeo.put("Julio", julio);
        mapeo.put("Agosto", agosto);
        mapeo.put("Septiembre", septimbre);
        mapeo.put("Octubre", octubre);
        mapeo.put("Noviembre", novimbre);
        mapeo.put("Diciembre", diciembre);

        return mapeo;
    }

}
