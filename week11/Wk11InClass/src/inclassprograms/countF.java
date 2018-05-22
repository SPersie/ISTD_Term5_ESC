package inclassprograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class countF {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch =new CountDownLatch(7);
        ArrayList<String> grades =new ArrayList<>(Arrays.asList("F", "F", "F", "F", "A", "B", "F", "F", "F", "A", "F"));
        ArrayList<Thread> threads =new ArrayList<>();
        for (int i =0; i <grades.size(); i ++) {
            threads.add(new Thread(new Service(grades.get(i), latch)));
        }

        for (int i =0; i <threads.size(); i ++) {
            threads.get(i).start();
//            Thread.sleep(1000);
        }

        try {
            latch.await();
//            Thread.sleep(1000);
            System.out.println("Seven F are founded");
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service implements Runnable {
    private final CountDownLatch latch;
    private final String grade;

    public Service(String grade, CountDownLatch latch) {
        this.latch =latch;
        this.grade =grade;
    }

    @Override
    public void run() {
//            try {
////                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("The grade is " +grade);
            if (grade.equals("F")) {
//            System.out.println("The grade is " +grade);
                latch.countDown();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
