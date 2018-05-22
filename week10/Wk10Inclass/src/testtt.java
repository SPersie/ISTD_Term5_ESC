
class testtt {
    public static volatile int count =0;

    public static void main(String[] args) {
        myThread test =new myThread();
        test.start();

        while (true) {
            if (count ==500) {
                test.interrupt();
                break;
            }
        }

    }
}

class myThread extends Thread {

    public myThread() {
    }

    @Override
    public void run() {
        for (int i =0; i <1000; i ++) {
            try {
                if (this.isInterrupted()) {
                    throw new InterruptedException();
                }
            } catch (Exception e) {
                break;
            }
            synchronized (testtt.class) {
                testtt.count ++;
                System.out.println(testtt.count);
            }
        }
    }
}