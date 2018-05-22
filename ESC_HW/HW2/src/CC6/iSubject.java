package CC6;

import java.util.ArrayList;

public interface iSubject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver(ArrayList<Observer> list, String alert);
}
