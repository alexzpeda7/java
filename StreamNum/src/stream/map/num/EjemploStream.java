package stream.map.num;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjemploStream {
    public static void main(String[] args) {
        // Lista de 5 números
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
            
            stream.filter(i -> i % 2 == 0).map(i -> i * 2).forEach (System.out::println);

    }
}


