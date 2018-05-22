package CC5;

import java.util.HashSet;
import java.util.Set;


class Taxi {
    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        this.location = location;
        if (location.equals(destination))
            dispatcher.notifyAvailable(this);
    }

    public synchronized Point getDestination() {
        return destination;
    }

    public synchronized void setDestination(Point location) {
        this.destination =location;
    }
}

class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher() {
        taxis = new HashSet<Taxi>();
        availableTaxis = new HashSet<Taxi>();
    }

    public synchronized void addTaxi(Taxi taxi) {
        taxis.add(taxi);
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public synchronized Image getImage() {
        Image image = new Image();
        for (Taxi t : taxis)
            image.drawMarker(t.getLocation());
        return image;
    }
}

class Image {
    public void drawMarker(Point p) {
    }
}

class Point {

}


class DLExampleTest {
    public static void main(String[] args) {
        Dispatcher dispatcher =new Dispatcher();
        Taxi taxi =new Taxi(dispatcher);
        Point sutd =new Point();

        dispatcher.addTaxi(taxi);
        taxi.setDestination(sutd);


        myThread1 thread1 =new myThread1(dispatcher);
        myThread2 thread2 =new myThread2(dispatcher, taxi);

        thread1.start();
        thread2.start();
    }
}

class myThread1 extends Thread {
    Dispatcher dis;
    public myThread1(Dispatcher dispatcher) {
        this.dis =dispatcher;
    }

    @Override
    public void run() {
        dis.getImage();
    }
}

class myThread2 extends Thread {
    Dispatcher dispatcher;
    Taxi taxi;
    public myThread2(Dispatcher dis, Taxi taxi) {
        this.dispatcher =dis;
        this.taxi =taxi;
    }

    @Override
    public void run() {
        taxi.setLocation(taxi.getDestination());
    }
}
