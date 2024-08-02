package com.example.demo.Producto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<producto> getProductoById(@PathVariable Long sku) {
        producto producto = productoService.getProductoById(sku);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public producto createProducto(@RequestBody producto producto) {
        return productoService.createProducto(producto);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<producto> updateProducto(@PathVariable Long sku, @RequestBody producto productoDetails) {
        producto updatedProducto = productoService.updateProducto(sku, productoDetails);
        if (updatedProducto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long sku) {
        producto producto = productoService.getProductoById(sku);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.deleteProducto(sku);
        return ResponseEntity.noContent().build();
    }
}
