package CC7;

public class LockStaticVariablesFixed {
    public static int saving = 5000;
    public static int cash = 0;

    public static void main(String args[]){
        int numberofThreads = 10000;
        WD[] threads = new WD[numberofThreads];

        for (int i = 0; i < numberofThreads; i++) {
            threads[i] = new WD();
            threads[i].start();
        }

        try {
            for (int i = 0; i < numberofThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("some thread is not finished");
        }

        System.out.print("The result is: " + LockStaticVariablesFixed.cash);
    }
}

class WD extends Thread {
    public void run () {
        if (LockStaticVariablesFixed.saving >= 1000) {
            System.out.println("I am doing something.");
            synchronized (LockStaticVariablesFixed.class) {
                LockStaticVariablesFixed.saving = LockStaticVariablesFixed.saving - 1000;
                LockStaticVariablesFixed.cash = LockStaticVariablesFixed.cash + 1000;
            }
        }
    }
}

