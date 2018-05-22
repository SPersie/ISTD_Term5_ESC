package CC12;
import CC8.Disk;
import org.junit.Test;

public class DIskBugReveal {

    @Test
    public void diskBug() {
        new Disk(1001, 1).manipulate();
    }
}
