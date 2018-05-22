package CC2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchExercise {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch =new CountDownLatch(7);
        ArrayList<String> grade1 =new ArrayList<>(Arrays.asList("F", "F", "F", "F", "A", "B", "F", "F", "F", "A", "F"));
        ArrayList<String> grade2 =new ArrayList<>(Arrays.asList("A", "B", "F", "F", "F", "A", "C", "B"));
        ArrayList<String> grade3 =new ArrayList<>(Arrays.asList("F", "F", "F", "A", "B", "C", "A", "F", "B"));
        ArrayList<Thread> threads =new ArrayList<>();

        threads.add(new Thread(new Service(grade1, latch)));
        threads.add(new Thread(new Service(grade2, latch)));
        threads.add(new Thread(new Service(grade3, latch)));


        for (int i =0; i <threads.size(); i ++) {
            threads.get(i).start();
        }

        try {
            latch.await();
//            Thread.sleep(1000);
            for (int i =0; i <threads.size(); i ++) {
                threads.get(i).interrupt();
            }

            System.out.println("Seven F are founded");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service implements Runnable {
    private final CountDownLatch latch;
    private ArrayList<String> grade;

    public Service(ArrayList<String> grade, CountDownLatch latch) {
        this.latch =latch;
        this.grade =grade;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i =0; i <grade.size(); i ++) {
            if (Thread.interrupted()) {
                break;
            } else {
                System.out.println("The grade is " +grade.get(i));
                if (grade.get(i).equals("F")) {
//            System.out.println("The grade is " +grade);
                    latch.countDown();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
//                e.printStackTrace();
                }
            }

        }
    }
}






