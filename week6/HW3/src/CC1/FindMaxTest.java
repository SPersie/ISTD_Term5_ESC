package CC1;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindMaxTest {

    @Test
    public void testFailure() {
        int[] list = new int[5];
        list[0] = 3;
        list[1] = 5;
        list[2] = 4;
        list[3] = 6;
        list[4] = 7;
        int list_max = FindMax.max(list);
        assertTrue(list_max == 7);
    }

    @Test
    public void testError() {
        FindMax.max(null);
        assertTrue(true);
    }

    @Test
    public void testPass() {
        int[] list = new int[5];
        list[0] = 3;
        list[1] = 5;
        list[2] = 4;
        list[3] = 6;
        list[4] = 2;
        int list_max = FindMax.max(list);
        assertTrue(list_max == 6);
    }
}