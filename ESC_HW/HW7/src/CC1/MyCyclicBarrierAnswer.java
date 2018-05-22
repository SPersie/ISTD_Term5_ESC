package CC1;

public class MyCyclicBarrierAnswer {
    private int count = 0;
    private int currentcount =0;
    private Runnable torun;

    public MyCyclicBarrierAnswer (int count, Runnable torun) {
        this.count = count;
        this.torun = torun;
    }

    public MyCyclicBarrierAnswer (int count) {
        this.count = count;
    }

    //complete the implementation below.
    //hint: use wait(), notifyAll()
    public synchronized void await () {
        currentcount ++;
        if (currentcount ==count) {
            torun.run();
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
