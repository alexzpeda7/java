/**
 * ITQ. Topicos Avanzados de Programacion.
 * Todos los derechos reservados 2025.
 */
package itq.tap.mensajes;

/**
 * Clase que se encarga de enviar mensajes.
 */
public class Mensajero {

    /** Numero maximo que se utilizara para contar. */
    private static final int MAXIMO_NUMERO = 100;

    /**
     * Método inicial.
     * @param args Argumentos del programa.
     */
    public static void main(String[] args) {
        int contador = 1;
        // Valida que el contador este en el rango a contar.
        while (contador <= MAXIMO_NUMERO) {
            System.out.println(contador);
            /*
             * Se incrementa el contador despues de que se imprimio. 
             */
            contador++;
        }
    }

}
