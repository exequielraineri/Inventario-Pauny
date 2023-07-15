/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.inventario.controller;

import com.app.inventario.model.Cliente;
import com.app.inventario.model.Producto;
import com.app.inventario.model.Usuario;
import com.app.inventario.model.Venta;
import com.app.inventario.model.VentaDetalle;
import com.app.inventario.service.int_Cliente_service;
import com.app.inventario.service.int_Producto_service;
import com.app.inventario.service.int_Venta_Detalle_service;
import com.app.inventario.service.int_Venta_service;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private int_Venta_Detalle_service ventaDetalleService;

    private int cant_productos;

    private Map<String, Integer> mapeoVentas;
    private Map<String, Integer> mapeoProductos;

    private Usuario usuarioSession;

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        //cant_productos = cant_Productos();

        usuarioSession = (Usuario) session.getAttribute("usuario");

        mapeoVentas = obtenerMapeo(this.mapeoVentas);
        mapeoProductos = obtenerMapeoProductos(this.mapeoProductos);
        model.addAttribute("cant_productos", cant_Productos());
        model.addAttribute("cant_clientes", cant_Clientes());
        model.addAttribute("cant_ventas", cant_Ventas());
        model.addAttribute("mapeoVentas", mapeoVentas);
        model.addAttribute("mapeoProductos", mapeoProductos);

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
        int cant = 0;
        for (Venta venta : ventas) {
            if (venta.getIDUsuario().equals(usuarioSession)) {
                cant++;
            }
        }
        return cant;
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
            if (venta.getIDUsuario().equals(usuarioSession)) {
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

    private Map<String, Integer> obtenerMapeoProductos(Map<String, Integer> mapeoProductos) {
        mapeoProductos = new LinkedHashMap<>();
        List<VentaDetalle> detalles = ventaDetalleService.listarVentaDetalle();

        List<Venta> venta = ventaService.listarVenta();
        for (Venta v : venta) {
            if (v.getIDUsuario().equals(usuarioSession)) {
                for (VentaDetalle vDetalle : v.getVentaDetalleList()) {
                    Integer idProducto = vDetalle.getIDProducto().getIDProducto();
                    int cant = 0;
                    for (int i = 0; i < detalles.size(); i++) {
                        if (detalles.get(i).getIDProducto().getiDProducto().equals(idProducto)) {
                            cant += detalles.get(i).getCantidad();
                        }
                    }
                    mapeoProductos.put(vDetalle.getIDProducto().getDscripcion(), cant);

                }
            }
        }

        /*for (VentaDetalle d : detalles) {
            Integer idProducto = d.getIDProducto().getIDProducto();
            int cant = 0;
            for (int i = 0; i < detalles.size(); i++) {
                if (detalles.get(i).getIDProducto().getiDProducto().equals(idProducto)) {
                    cant += detalles.get(i).getCantidad();
                }
            }
        mapeoProductos.put(d.getIDProducto().getDscripcion(), cant);*/
        return mapeoProductos;

    }

}
