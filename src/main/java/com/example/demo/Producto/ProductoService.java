package com.example.demo.Producto;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductoService {

    private final productoRepository productoRepository;

    public ProductoService(productoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public producto getProductoById(Long sku) {
        return productoRepository.findById(sku);
    }

    public producto createProducto(producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    public producto updateProducto(Long sku, producto productoDetails) {
        producto producto = productoRepository.findById(sku);

        producto.setArticulo(productoDetails.getArticulo());
        producto.setMarca(productoDetails.getMarca());
        producto.setModelo(productoDetails.getModelo());
        producto.setDepartamento(productoDetails.getDepartamento());
        producto.setClase(productoDetails.getClase());
        producto.setFamilia(productoDetails.getFamilia());
        producto.setFechaAlta(convertToTimestamp(productoDetails.getFechaAlta()));
        producto.setStock(productoDetails.getStock());
        producto.setCantidad(productoDetails.getCantidad());
        producto.setDescontinuado(productoDetails.getDescontinuado());
        producto.setFechaBaja(convertToTimestamp(productoDetails.getFechaBaja()));

        productoRepository.update(producto);
        return producto;
    }

    public void deleteProducto(Long sku) {
        productoRepository.deleteById(sku);
    }

    private Timestamp convertToTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, +1);
        return new Timestamp(cal.getTimeInMillis());
    }
}
