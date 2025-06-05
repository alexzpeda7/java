import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreacionThreads {

    static int variableCompartida = 0;
    private static int variableSalida;
    public static void main(String[] args) {
        while (true) {
            // Create a new thread
            Thread thread = new Thread(() -> {
                // Simulate some work
                try {
                    variableCompartida++;
                    variableSalida = 5+5;
                    System.out.println("Thread " 
                            + Thread.currentThread().getName() 
                            + " is running.");
                    List<Integer> lista = new ArrayList<>();
                    for (int i = 0; i < 1000000; i++) {
                        lista.add(new Random().nextInt(Integer.MAX_VALUE));
                    }
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // Start the thread
            thread.start();
        }
    }

}
