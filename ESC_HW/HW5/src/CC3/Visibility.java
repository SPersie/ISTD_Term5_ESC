package CC3;

public class Visibility {
    private static volatile boolean ready;
//    private static boolean ready;
    private static volatile int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }

            System.out.println(number);
            //Question: what will be printed here?
        }
    }

    public static void main (String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
