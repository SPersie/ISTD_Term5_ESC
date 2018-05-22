package HW1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamExample {

    public static void main(String args[]) {
        final CyclicBarrier cb =new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All of the students are ready, start the exam.");
            }
        });
        final CountDownLatch latch = new CountDownLatch(3);
        Thread sam = new Thread(new Student("Sam", 1000, latch, cb));
        Thread dean = new Thread(new Student("Dean", 1000, latch, cb));
        Thread alex = new Thread(new Student("Alex", 1000, latch, cb));

        sam.start(); //separate thread will initialize CacheService
        dean.start(); //another thread for AlertService initialization
        alex.start();

        try{
            latch.await();  //main thread is waiting on CountDownLatch to finish
            Thread.sleep(1000);

            sam.join();
            dean.join();
            alex.join();
            System.out.println("All students have finished. Examiner can leave.");
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}

class Student implements Runnable{
    private final String name;
    private final int timeToStart;
    private final CountDownLatch latch;
    private CyclicBarrier cyclicBarrier;

    public Student(String name, int timeToStart, CountDownLatch latch, CyclicBarrier cyclicBarrier){
        this.name = name;
        this.timeToStart = timeToStart;
        this.latch = latch;
        this.cyclicBarrier =cyclicBarrier;
    }

    public void run() {
        try {
            Thread.sleep(timeToStart);
        } catch (InterruptedException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( name + " is Ready");
        try {
            cyclicBarrier.await();
        } catch (Exception e) {

        }

        System.out.println(name +" starts doing the exam.");
        try {
            Thread.sleep(10000);
            System.out.println(name +" has finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown(); //reduce count of CountDownLatch by 1
    }
}
