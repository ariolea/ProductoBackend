package com.example.demo.Producto;

import lombok.Data;

import java.util.Date;

@Data
public class producto {

    private Long sku;
    private String articulo;
    private String marca;
    private String modelo;
    private int departamento;
    private int clase;
    private int familia;
    private Date fechaAlta;
    private int stock;
    private int cantidad;
    private int descontinuado;
    private Date fechaBaja;
}
