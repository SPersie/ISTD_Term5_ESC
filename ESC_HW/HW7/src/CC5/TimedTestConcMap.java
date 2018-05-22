package CC5;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import junit.framework.TestCase;

public class TimedTestConcMap {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;
    protected final ConcurrentHashMap bb;
    protected final int nTrials, nPairs;
    protected final AtomicInteger putSum = new AtomicInteger(0); //for testing
    protected final AtomicInteger takeSum = new AtomicInteger(0);//for testing

    public static void main(String[] args) throws Exception {
        long start =System.nanoTime();
        new TimedTestConcMap(10, 10, 100000).test(); // sample parameters
        pool.shutdown();
        long end =System.nanoTime();
        System.out.println(end -start);
    }

    public TimedTestConcMap(int capacity, int npairs, int ntrials) {
        this.bb = new ConcurrentHashMap(capacity);
        this.nTrials = ntrials;
        this.nPairs = npairs;
        this.barrier = new CyclicBarrier(npairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer());
                pool.execute(new Consumer());
            }
            barrier.await(); // wait for all threads to be ready
            barrier.await(); // wait for all threads to finish
            assert(putSum.get() == takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Producer implements Runnable {
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.put(i, seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {
        public void run() {
            try {
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    bb.get(i);
//                    System.out.println(sum);
                    bb.remove(i);
                }
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
