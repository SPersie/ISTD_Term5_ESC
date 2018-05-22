package CC8;

/**
 * I write two test cases to cover all the statements.
 * Minimum test cases needed are two.
 * **/

import org.junit.Test;

public class DiskTest {

    @Test
    public void test1() {
        new Disk(1, 0).manipulate();
    }

    @Test
    public void test2() {
        new Disk(1002, -1).manipulate();
    }

}