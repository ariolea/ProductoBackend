package com.example.demo.Producto;

import com.example.demo.Producto.producto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class productoRepository {

    private final JdbcTemplate jdbcTemplate;

    public productoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ProductoRowMapper implements RowMapper<producto> {
        @Override
        public producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            producto producto = new producto();
            producto.setSku(rs.getLong("Sku"));
            producto.setArticulo(rs.getString("Articulo"));
            producto.setMarca(rs.getString("Marca"));
            producto.setModelo(rs.getString("Modelo"));
            producto.setDepartamento(rs.getInt("Departamento"));
            producto.setClase(rs.getInt("Clase"));
            producto.setFamilia(rs.getInt("Familia"));
            producto.setFechaAlta(rs.getDate("FechaAlta"));
            producto.setStock(rs.getInt("Stock"));
            producto.setCantidad(rs.getInt("Cantidad"));
            producto.setDescontinuado(rs.getInt("Descontinuado"));
            producto.setFechaBaja(rs.getDate("FechaBaja"));
            return producto;
        }
    }

    public List<producto> findAll() {
        String sql = "EXEC UP_IME_Productos 'SELECTALL'";
        return jdbcTemplate.query(sql, new ProductoRowMapper());
    }

    public producto findById(Long sku) {
        String sql = "EXEC UP_IME_Productos 'SELECT', ?";
        return jdbcTemplate.queryForObject(sql, new ProductoRowMapper(), sku);
    }

    public int save(producto producto) {
        String sql = "EXEC UP_IME_Productos 'INSERT', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        return jdbcTemplate.update(sql, producto.getSku(), producto.getArticulo(), producto.getMarca(),
                producto.getModelo(),
                producto.getDepartamento(), producto.getClase(), producto.getFamilia(), producto.getFechaAlta(),
                producto.getStock(), producto.getCantidad(), producto.getDescontinuado(), producto.getFechaBaja());
    }

    public int update(producto producto) {
        String sql = "EXEC UP_IME_Productos 'UPDATE', ?,  ?,  ?,  ?, ?,  ?,  ?,  ?,  ?,  ?, ?,  ?";
        return jdbcTemplate.update(sql, producto.getSku(), producto.getArticulo(), producto.getMarca(),
                producto.getModelo(),
                producto.getDepartamento(), producto.getClase(), producto.getFamilia(), producto.getFechaAlta(),
                producto.getStock(), producto.getCantidad(), producto.getDescontinuado(), producto.getFechaBaja());
    }

    public int deleteById(Long sku) {
        String sql = "EXEC UP_IME_Productos 'DELETE', ?";
        return jdbcTemplate.update(sql, sku);
    }
}
