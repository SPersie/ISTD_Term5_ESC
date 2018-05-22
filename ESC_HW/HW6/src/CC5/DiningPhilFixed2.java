package CC5;//package CC5;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilFixed2 {
    private static int N = 5;

    public static void main (String[] args) throws Exception {
        Philosopher2[] phils = new Philosopher2[N];
        Fork[] forks = new Fork[N];

        for (int i = 0; i < N; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < N; i++) {
            phils[i] = new Philosopher2 (i, forks[i], forks[(i+N-1)%N]);
            phils[i].start();
        }
    }
}

class Philosopher2 extends Thread {
    private final int index;
    private final Fork left;
    private final Fork right;

    public Philosopher2 (int index, Fork left, Fork right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public void run() {

        final ReentrantLock reentrantLock = new ReentrantLock();
        Random randomGenerator = new Random();

        try {
            while (true) {
                Thread.sleep(randomGenerator.nextInt(100)); //not sleeping but thinking
                System.out.println("Phil " + index + " finishes thinking.");
                if (reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        left.pickup();
                        System.out.println("Phil " + index + " picks up left fork.");
                        right.pickup();
                        System.out.println("Phil " + index + " picks up right fork.");
                        Thread.sleep(randomGenerator.nextInt(100)); //eating
                        System.out.println("Phil " + index + " finishes eating.");
                        left.putdown();
                        System.out.println("Phil " + index + " puts down left fork.");
                        right.putdown();
                        System.out.println("Phil " + index + " puts down right fork.");
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
