package itq.edu.tap.tools;

import java.util.Random;

public class WordGenerator {
    private static final double MAX_NUMERO = 15;
    public static char[] consonants = new char[]{'b', 'c', 'd', 'f'};
    char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    /**
     * Genera una palabra aleatoria.
     * @return
     */
    public static String generateWord() {
        Random random = new Random();
        random.nextDouble(); // [0, 1)
        double numero = Math.random(); // [0, 1)

        int longitudPalabra = 5 + (int)(numero * MAX_NUMERO);
        boolean vocal = random.nextBoolean();
        String palablar = "";
        for (int i = 0; i < longitudPalabra; i++) {
            if (vocal) {
                
                vocal = false;
            } else {
                vocal = true;
            }
        }
        
        
        return "";
    }
    
    /**
     * Método que escribe un mensaje en pantalla.
     */
    public void escribirMensaje() {
        System.out.println("hola");
    }
    
    public static void main(String[] args) {
        WordGenerator objeto = new WordGenerator();
        WordGenerator objeto2 = new WordGenerator();
        System.out.println(objeto.generateWord());
        System.out.println(objeto2.generateWord());
//        objeto.escribirMensaje();
        WordGenerator.generateWord();
        char firstCons = WordGenerator.consonants[0];
        char firstVowel = objeto.vowels[0];
    }
}
