/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import igu.screen;

/**
 *
 * @author zeped
 */
public class ArtC {
    
    

    /**
     * @param args the command line arguments
     */
    
    String título; 
String autor; 
String[] palabrasClaves = new String[3];
String publicación; 
int año; 
String resumen; 

public ArtC(String título, String autor) {
this.título = título;
this.autor = autor;
}

public ArtC(String título, String autor, String[] palabrasClaves, String publicación, int año) {
this(título, autor);
this.palabrasClaves = palabrasClaves;
this.publicación = publicación;
this.año = año;
}

public ArtC(String título, String autor, String[] palabrasClaves, String publicación, int año, String resumen) {
this(título, autor, palabrasClaves, publicación, año);
this.resumen = resumen;
}

public void imprimir() {
System.out.println("Título del artículo = " + título);
System.out.println("Autor del artículo = " + autor);
System.out.println("Palabras clave = ");
// Recorre el array para imprimir cada palabra clave
for (int i = 0; i < palabrasClaves.length; i++) {
System.out.println(palabrasClaves[i]);
}
System.out.println("Publicación = " + publicación);
System.out.println("Año = " + año);
System.out.println("Resumen = " + resumen);
}

public static void main (String args[]) {
    
    screen panta = new screen();
        panta.setVisible(true);
        panta.setLocationRelativeTo(null);
        
        
String[] palabras = {"Física","Espacio","Tiempo"};
ArtC artículo = new ArtC("La teoría especial de la relatividad", "Albert Einstein",palabras, "Anales de Física", 1913, "Las leyes de la física son las mismas en todos los sistemas de referencia inerciales.");
artículo.imprimir();
}
}
