package comlexNum;

/**
 * Created by study on 11/2/18.
 */
public class complexNum {
    private float a;
    private float b;

    public complexNum(float a, float b) {
        this.a =a;
        this.b =b;
    }

    public complexNum(int a, int b) {
        this.a =(float) a;
        this.b =(float) b;
    }

    public complexNum() {
        this.a =0;
        this.b =0;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

}
