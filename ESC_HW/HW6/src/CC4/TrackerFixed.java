package CC4;

import java.util.Map;

class test extends Thread {
    TrackerFixed tracker;

    public test (TrackerFixed tra) {
        this.tracker = tra;
    }

    public void run () {
        CC4.TrackerFixed.MutablePoint loc = tracker.getLocation("somestring");
        loc.x = -1212000;
    }
}

//is this class thread-safe?
public class TrackerFixed {
    //@guarded by ???
    private Map<String, MutablePoint> locations;

    //the reference locations, is it going to be an escape?
    public TrackerFixed(Map<String, MutablePoint> locations) {
        Map<String, MutablePoint> deep_copy =null;
        for (Map.Entry<String, MutablePoint> entry :locations.entrySet()) {
            String key =entry.getKey();
            MutablePoint value =entry.getValue();

            deep_copy.put(key, value);
        }

        this.locations =deep_copy;
    }

    //is this an escape?
    public Map<String, MutablePoint> getLocations () {
        Map<String, MutablePoint> deep_copy =null;
        for (Map.Entry<String, MutablePoint> entry :locations.entrySet()) {
            String key =entry.getKey();
            MutablePoint value =entry.getValue();

            deep_copy.put(key, value);
        }

        return deep_copy;
    }

    //is this an escape?
    public MutablePoint getLocation (String id) {
        Map<String, MutablePoint> deep_copy =null;
        for (Map.Entry<String, MutablePoint> entry :locations.entrySet()) {
            String key =entry.getKey();
            MutablePoint value =entry.getValue();

            deep_copy.put(key, value);
        }

        MutablePoint loc = deep_copy.get(id);
        return loc;
    }

    public void setLocation (String id, int x, int y) {
        Map<String, MutablePoint> deep_copy =null;
        for (Map.Entry<String, MutablePoint> entry :locations.entrySet()) {
            String key =entry.getKey();
            MutablePoint value =entry.getValue();

            deep_copy.put(key, value);
        }

        MutablePoint loc = deep_copy.get(id);

        if (loc == null) {
            throw new IllegalArgumentException ("No such ID: " + id);
        }

        loc.x = x;
        loc.y = y;
    }

    //this class is not thread-safe (why?) and keep it unmodified.
    //Because the second constructor takes in a MutablePoint which is also a reference.
    //We need to make a deep copy of it.
    class MutablePoint {
        public int x, y;

        public MutablePoint (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MutablePoint (MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}
