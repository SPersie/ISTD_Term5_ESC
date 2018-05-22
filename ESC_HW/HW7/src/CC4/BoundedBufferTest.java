package CC4;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class BoundedBufferTest {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;

    @Test
    public void testIsEmptyWhenConstructued () {
        BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        assertTrue(bb.isEmpty());
        assertFalse(bb.isFull());
    }

    @Test
    public void testIsFullAfterPuts () throws InterruptedException {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Runnable task = new Runnable () {
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread (task);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty () {
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
        Thread taker = new Thread() {
            public void run() {
                try {
                    int unused = bb.take();
                    assertTrue(false);
                } catch (InterruptedException success) {} //if interrupted, the exception is caught here
            }
        };

        try {
            taker.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(taker.isAlive()); //the taker should not be alive for some time
        } catch (Exception unexpected) {
            assertTrue(false);
        }
    }

	@Test
    public void testPutsFour() {
	    final BoundedBuffer<Integer> bb =new BoundedBuffer<Integer>(10);
	    Runnable task =new Runnable() {
            @Override
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
	    };
	    Thread[] threads =new Thread[10];
    }


    //in this test, the buffer was empty at first, then i make it full and take one out of it.
    @Test
    public void testPutbeforeTake() throws InterruptedException{
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Runnable task = new Runnable () {
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread (task);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        bb.take();

        assertFalse(bb.isFull());
        assertFalse(bb.isEmpty());
    }

    //in this test, the buffer is added five items first and taken 4 items.
    @Test
    public void testTakeBeforePut() throws InterruptedException{
        final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);

        Runnable task = new Runnable () {
            public void run() {
                try {
                    bb.put((new Random()).nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable taker =new Runnable() {
            @Override
            public void run() {
                try {
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread[] threads = new Thread[5];
        Thread[] takes =new Thread[4];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread (task);
            threads[i].start();
        }

        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }

        for (int i =0; i <4; i ++) {
            takes[i] =new Thread(taker);
            takes[i].start();
        }

        for (int i =0; i <4; i ++) {
            takes[i].join();
        }


        assertFalse(bb.isFull());
        assertFalse(bb.isEmpty());

    }

}
