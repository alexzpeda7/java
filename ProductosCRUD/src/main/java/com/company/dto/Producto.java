package com.company.dto;

/**
 * DTO para productos.
 */
public class Producto {

    /** Identificador del producto. */
    private String id;
    
    /** Nombre del producto. */
    private String nombre;

    /**
     * Constructor de defecto.
     */
    public Producto() {
    }

    /**
     * @param id
     * @param nombre
     * @param precio
     */
    public Producto(String id, String nombre, Double precio) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    /** Precio del producto. */
    private Double precio;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
}
