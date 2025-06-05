package com.company.dao;

import java.util.ArrayList;
import java.util.List;

import com.company.dto.Producto;

/**
 * DAO. Clase especializada para manejo de datos de
 * productos.
 */
public class ProductoDao {

    /** Repositorio de productos. */
    private List<Producto> productos;
    
    public ProductoDao() {
        productos = new ArrayList<>();
    }

    /**
     * Crear un nuevo producto.
     * @param producto
     */
    public void create(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su nombre.
     * @param nombreABuscar Nombre del producto a buscar.
     * @return Lista de productos coincidentes.
     */
    public List<Producto> read(String nombreABuscar) {
        List<Producto> productosCoincidentes = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreABuscar)) {
                productosCoincidentes.add(producto);
            }
        }
        return productosCoincidentes;
    }
    
    public void borrar(String nombreProducto) {
        List<Producto> productosABorrar = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                productosABorrar.add(producto);
            }
        }
        productos.removeAll(productosABorrar);
    }
    
}
