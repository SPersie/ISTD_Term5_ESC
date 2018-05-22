package CC6;

//The Observers update method is called when the Subject changes

import java.util.ArrayList;

public interface Observer {
    public void update(String alert);
    public ArrayList<Integer> getInterest_post_index();
}
