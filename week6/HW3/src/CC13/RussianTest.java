package CC13;

import org.junit.Test;
import static org.junit.Assert.*;

public class RussianTest {

    @Test
    public void blackBox() {
        assertTrue(Russian.multiply(12, 11) ==132);
    }

    @Test
    public void whiteBox1() {
        assertTrue(Russian.multiply(100, 0) ==0);
    }

    @Test void whiteBox2() {
        assertTrue(Russian.multiply(100, 3) ==300);
    }

    @Test
    public void faultBase1() {
        assertTrue(Russian.multiply(-2, -3) ==12);
    }

    @Test void faultBase2() {
        assertTrue(Russian.multiply(Integer.MAX_VALUE, 10) >0);
    }
}
