/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import GUI.Pantalla;

/**
 *
 * @author zeped
 */
public class Avion {
    private String fabricante;
private int numeroMotores;

private Avion(String fabricante, int numeroMotores) {
this.fabricante = fabricante;
this.numeroMotores = numeroMotores;
}

public String getFabricante() {
return fabricante;
}

private void setFabricante(String fabricante) {
this.fabricante = fabricante;
}

private void cambiarFabricante(Avion a) {
a.setFabricante("Lockheed");
}

public int getNumeroMotores() {
return numeroMotores;
}

private void setNumeroMotores(int numeroMotores) {
this.numeroMotores = numeroMotores;
}

public void imprimirFabricante() {
System.out.println("El fabricante del avión es:"  + fabricante);
}

public static void main(String args[]) {

Pantalla panta = new Pantalla();
panta.setVisible(true);
panta.setLocationRelativeTo(null);
    
Avion a1 = new Avion("Airbus",4);
Avion a2;
Avion a3 = new Avion("Boeing",4);
a2 = a1;
a1.imprimirFabricante();
a2.imprimirFabricante();
a1.setFabricante("Douglas");
a1.imprimirFabricante();
a2.imprimirFabricante();
a1.cambiarFabricante(a2);
a2.imprimirFabricante();
}

    /**
     * @param args the command line arguments
     */    
}
