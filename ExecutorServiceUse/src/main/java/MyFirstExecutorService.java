import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyFirstExecutorService {

    public static void main(String[] args) {
        // Create a thread pool with 100 threads
        ExecutorService executorService 
            = Executors.newFixedThreadPool(100);
        executorService.execute(() -> {
            System.out.println("Hello from thread " + Thread.currentThread().getName());
        });
        Future<?> future = executorService.submit(() -> {
            System.out.println("Hello from thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        Thread tr = new Thread(() -> {
//            System.out.println("Hello from thread " + Thread.currentThread().getName());
//        });

    }

}
