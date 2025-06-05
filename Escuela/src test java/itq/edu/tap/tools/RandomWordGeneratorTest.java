package itq.edu.tap.tools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomWordGeneratorTest {

    @Test
    void test() {
        String noControl = RandomWordGenerator.generateNoControl();
        System.out.println(noControl);
        assertEquals(8, noControl.length());
    }

}
