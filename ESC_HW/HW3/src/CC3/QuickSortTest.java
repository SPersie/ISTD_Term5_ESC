package CC3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class QuickSortTest {
    public int[] actual;
    public int[] expected;

    public QuickSortTest(int[] expected, int[] actual) {
        this.expected =expected;
        this.actual =actual;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {new int[] {1,2,4,5,6,7,8}, new int[]{4,1,2,5,7,8,6}},
                {new int[] {1,2,3,6,7}, new int[]{3,2,1,6,7}}
        });
    }

    @Test
    public void test() {
        QuickSort quicksort =new QuickSort();
        quicksort.sort(actual);
        assertTrue(Arrays.equals(expected, actual));
    }

}