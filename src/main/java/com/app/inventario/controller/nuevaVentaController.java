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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
@RequestMapping("/nuevaVenta")
public class nuevaVentaController {

    List<Producto> lista_productos = new ArrayList<>();

    @Autowired
    private int_Producto_service productoService;

    @Autowired
    private int_Venta_service ventaService;

    @Autowired
    private int_Venta_Detalle_service ventaDetalleService;

    @Autowired
    private int_Cliente_service clienteService;
    Venta venta = new Venta();

    VentaDetalle ventaDetalle = new VentaDetalle();

    float total = 0;

    String mensajeAlerta = null;

    Cliente cliente = new Cliente();

    Date fechaHoy = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("dd ' de ' MMMMM ',' yyyy");

    @GetMapping("")
    public String nuevaVenta(Model model) {
        fechaHoy = new Date();
        model.addAttribute("producto_encontrado", new Producto());
        model.addAttribute("lista", lista_productos);
        model.addAttribute("total", total);
        model.addAttribute("alerta", mensajeAlerta);
        model.addAttribute("cliente", cliente);
        model.addAttribute("fechaHoy", sf.format(fechaHoy));
        return "nueva_venta";
    }

    @GetMapping("/buscarPorCodido")
    public String listarPorCodigo(@RequestParam("codigo") String codigo, Model model) {
        List<Producto> productos = productoService.listarProducto();
        Producto p_encontrado = new Producto();
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                System.out.println("buecando__-->" + p.getCodigo());
                System.out.println("dasdad:_ " + codigo);
                p_encontrado = p;
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("producto_encontrado", p_encontrado);
        model.addAttribute("lista", lista_productos);
        model.addAttribute("cliente", cliente);
        model.addAttribute("fechaHoy", sf.format(fechaHoy));
        return "nueva_venta";
    }

    @PostMapping("/agregarProducto")
    public String agregarProducto(@ModelAttribute("producto_encontrado") Producto producto, Model model, @RequestParam("cantidad") Integer cantidad) {
        System.out.println("Entra a agregar");
        Optional<Producto> prodEncontrado = productoService.obtenerProducto(producto.getIDProducto());

        prodEncontrado.get().setCantidad(cantidad);
        productoService.guardar(prodEncontrado.get());
        total += (Float.parseFloat(prodEncontrado.get().getPrecio().toString()) * cantidad);
        lista_productos.add(prodEncontrado.get());
        System.out.println("Producto: " + prodEncontrado.get().getCategoria());
        System.out.println("Cod: " + prodEncontrado.get().getCodigo());
        model.addAttribute("lista", lista_productos);
        model.addAttribute("total", total);
        System.out.println("Total_ " + total);
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";
    }

    @GetMapping("/eliminar/{iDProducto}")
    public String eliminarProducto(@PathVariable Integer iDProducto, Model model) {
        //Optional<Producto> prod = productoService.obtenerProducto(iDProducto);
        //System.out.println("cantidad-------------->" + prod.getCantidad());
        for (Producto p : lista_productos) {
            if (p.getIDProducto().equals(iDProducto)) {
                total -= Float.parseFloat(p.getPrecio().toString()) * p.getCantidad();
                lista_productos.remove(p);
                break;
            }
        }

        //total = total - (Float.parseFloat(prod.getPrecio().toString()) * prod.getCantidad());
        //lista_productos.remove(prod);
        model.addAttribute("producto_encontrado", new Producto());
        model.addAttribute("lista", lista_productos);
        model.addAttribute("total", total);
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";
    }

    @GetMapping("/nuevaVenta")
    public String cancelarVenta(Model model) {
        lista_productos = new ArrayList<>();
        total = 0;

        model.addAttribute("lista", lista_productos);
        model.addAttribute("total", total);
        mensajeAlerta = "";
        model.addAttribute("alerta", mensajeAlerta);
        cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";
    }

    @GetMapping("/realizarVenta")
    public String realizarVenta(Model model, HttpSession sesion) {
        venta = new Venta();
        //Optional<Cliente> c = clienteService.obtenerCliente(1);

        venta.setFechaVenta(new Date());
        venta.setIDCliente(cliente);
        venta.setTotal(BigDecimal.valueOf(total));
        venta.setIDUsuario((Usuario) sesion.getAttribute("usuario"));
        venta = ventaService.guardar(venta);

        for (Producto p : lista_productos) {
            ventaDetalle.setIDVenta(venta);
            ventaDetalle.setIDProducto(p);
            ventaDetalle.setPrecioUnitario(p.getPrecio());
            ventaDetalle.setCantidad(p.getCantidad());
            float subTotal = Float.valueOf(p.getPrecio().toString()) * p.getCantidad();
            ventaDetalle.setSubtotal(BigDecimal.valueOf(subTotal));
            ventaDetalleService.guardar(ventaDetalle);
            ventaDetalle = new VentaDetalle();
        }
        model.addAttribute("lista", lista_productos);
        model.addAttribute("total", total);
        mensajeAlerta = "Venta realizada!";
        model.addAttribute("alerta", mensajeAlerta);
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";
    }

    @PostMapping("/filtrarCliente")
    public String filtrarCliente(@ModelAttribute("cliente") Cliente c, Model model) {
        System.out.println("Cliente c: " + c.getNombre());
        List<Cliente> lista = clienteService.listarCliente();
        boolean f = false;
        for (Cliente lisC : lista) {
            if (lisC.getNombre().equalsIgnoreCase(c.getNombre())) {
                cliente = lisC;
                f = true;
                break;
            }
        }
        if (!f) {
            cliente=clienteService.guardar(c);
        }
        mensajeAlerta = null;
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";
    }

    @PostMapping("/guardarModificacion")
    public String guardarModificacion(@ModelAttribute("cliente") Cliente c, Model model) {
        cliente.setFirma(c.getFirma());
        cliente.setCuit(c.getCuit());
        cliente = clienteService.guardar(cliente);
        mensajeAlerta = null;
        model.addAttribute("cliente", cliente);
        return "redirect:/nuevaVenta";

    }

}
