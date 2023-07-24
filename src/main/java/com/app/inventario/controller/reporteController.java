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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    float total;

    @GetMapping("/ventas")
    public String ventas(Model model, HttpSession session) {
        Usuario u = (Usuario) session.getAttribute("usuario");
        total = 0;
        List<Venta> ventas = ventaService.listarVentaDesc();
        List<Venta> aux = new ArrayList<>();
        for (Venta v : ventas) {
            if (v.getIDUsuario().equals(u)) {
                aux.add(v);
                total += v.getTotal().floatValue();
            }
        }
        model.addAttribute("ventas", aux);
        model.addAttribute("total", total);
        return "reporte_ventas";
    }

    @GetMapping("/ventas/filtrarFecha")
    public String filtrarPorFecha(@RequestParam("fecha") String fecha, Model model) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        fecha = fecha.replace("-", "/");

        Date fInicio = sf.parse(fecha);
        Date fFin = sf.parse(fecha);

        Calendar fFinCalendar = Calendar.getInstance();
        fFinCalendar.setTime(fFin);
        fFinCalendar.add(Calendar.DAY_OF_MONTH, 1);

        fFin = fFinCalendar.getTime();
        total = 0;
        List<Venta> ventas = ventaService.listarFechaBETWEEN(new Timestamp(fInicio.getTime()), new Timestamp(fInicio.getTime()));
        for (Venta v : ventas) {
            total += v.getTotal().floatValue();
        }

        model.addAttribute("total", total);
        model.addAttribute("fecha", fecha.replace("/", "-"));
        model.addAttribute("ventas", ventas);
        return "reporte_ventas";
    }

    @GetMapping("/clientes")
    public String clientes(Model model) {
        List<Cliente> clientes = clienteService.listarCliente();
        model.addAttribute("clientes", clientes);
        return "reporte_clientes";
    }

}
