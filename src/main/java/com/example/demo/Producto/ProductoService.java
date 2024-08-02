package com.example.demo.Producto;

import org.springframework.stereotype.Service;

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
        producto.setFechaAlta(productoDetails.getFechaAlta());
        producto.setStock(productoDetails.getStock());
        producto.setCantidad(productoDetails.getCantidad());
        producto.setDescontinuado(productoDetails.getDescontinuado());
        producto.setFechaBaja(productoDetails.getFechaBaja());

        productoRepository.update(producto);
        return producto;
    }

    public void deleteProducto(Long sku) {
        productoRepository.deleteById(sku);
    }
}
