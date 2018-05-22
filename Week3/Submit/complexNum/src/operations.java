/**
 * Created by study on 11/2/18.
 */
public class operations {
    private complexNum a;
    private complexNum b;
    private char operation;

    public operations(complexNum a, complexNum b, char operation) {
        this.a =a;
        this.b =b;
        this.operation =operation;
    }

    public String getResult() {
        float aa =result(a, b, operation).getA();
        float bb =result(a, b, operation).getB();
        return aa +"+" +bb +"i";
    }

    public static complexNum add(complexNum a, complexNum b) {
        return new complexNum(a.getA() +b.getA(), a.getB() +b.getB());
    }

    public static complexNum subtraction(complexNum a, complexNum b) {
        return new complexNum(a.getA() -b.getA(), a.getB() -b.getB());
    }

    public static complexNum multiply(complexNum a, complexNum b) {
        return new complexNum(a.getA() *b.getA() -a.getB() *b.getB(), a.getA() *b.getB() +a.getB() *b.getA());
    }

    public static complexNum division(complexNum a, complexNum b) {
        return new complexNum(
                (a.getA() *b.getA() +a.getB() *b.getB()) /(a.getA() *b.getA() +b.getA() *b.getA()),
                (a.getB() *b.getA() -a.getA() *b.getB()) /(a.getA() *a.getA() +b.getA() *b.getA())
        );
    }

    public static complexNum result(complexNum a, complexNum b, char operation) {
        complexNum result;
        switch (operation) {
            case '+':
                result =add(a, b);
                break;
            case '-':
                result =subtraction(a, b);
                break;
            case '*':
                result =multiply(a, b);
                break;
            case '/':
                result =division(a, b);
                break;
            default:
                result =null;
        }
        return result;
    }

}
