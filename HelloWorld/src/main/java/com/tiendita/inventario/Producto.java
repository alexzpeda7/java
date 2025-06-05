package com.tiendita.inventario;

public class Producto {
    private String nombre;
    private int pieza;
    private double precio;
    

    public Producto(String nombre, int pieza , double precio) {
        this.nombre = nombre;
        this.pieza = pieza;
        this.precio = precio;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ": Pieza: " + pieza + ", Precio: $" + precio);
    }

    public static void main(String[] args) {
        Producto[] productos = new Producto[5];
        productos[0] = new Producto("Manzana", 4, 18.50);
        productos[1] = new Producto("Pan", 1, 2.50);
        productos[2] = new Producto("Gelatina", 2, 10.60);
        productos[3] = new Producto("1 kg de Huevos", 1, 2.00);
        productos[4] = new Producto("Caja de jugos", 1, 30.50);

        for (Producto producto : productos) {
            producto.mostrarInformacion();
        }
    }
}
