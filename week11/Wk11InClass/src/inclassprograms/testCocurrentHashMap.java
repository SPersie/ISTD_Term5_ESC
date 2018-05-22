gpackage inclassprograms;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class testCocurrentHashMap {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;
    protected final int nTrials, nPairs;
    protected final Map synchronizedMap;
    protected AtomicInteger putSum =new AtomicInteger(0);
    protected AtomicInteger takeSume =new AtomicInteger(0);

    public static void main(String[] args) {
        Map<Integer, Integer> map =new HashMap();
        new testCocurrentHashMap(10000, 10, map).test();
        pool.shutdown();

    }

    public testCocurrentHashMap(int nTrials, int nPairs, Map<Integer, Integer> synchronizedMap) {
        this.nTrials =nTrials;
        this.synchronizedMap =Collections.synchronizedMap(synchronizedMap);
        this.nPairs =nPairs;
        this.barrier =new CyclicBarrier(nPairs *2 +1);
    }

    void test() {
        try {
            for (int i =0; i <nPairs; i ++) {
                pool.execute(new Put());
                pool.execute(new Delete());
            }
            barrier.await();
            barrier.await();
            assert(putSum.get() ==takeSume.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShoft(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Put implements Runnable {
        public void run() {
            try {
                int seed =(this.hashCode() ^(int) System.nanoTime());
                int sum =0;
                barrier.await();
                for (int i =0; i <nTrials; i ++) {
                    synchronizedMap.put(nTrials, seed);
                    sum +=seed;
                    seed =xorShoft(seed);
                }

                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Delete implements Runnable {
        public void run() {
            try {
                barrier.await();
                int sum =0;
                for (int i =0; i <nTrials; i ++) {
                    sum += (Integer)synchronizedMap.get(nTrials);
                }
                takeSume.getAndAdd(sum);
                barrier.wait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
