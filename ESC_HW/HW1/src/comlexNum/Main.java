package comlexNum;

/**
 * Created by study on 11/2/18.
 */
public class Main {
    public static void main(String[] args) {
        complexNum num1 =new complexNum(1,2);
        complexNum num2 =new complexNum(1,1);

        operations plus =new operations(num1, num2, '+');
        operations minus =new operations(num1, num2, '-');
        operations multiply =new operations(num1, num2, '*');
        operations divide =new operations(num1, num2, '/');

        System.out.println("Add: " +plus.getResult());
        System.out.println("Subtraction: " +minus.getResult());
        System.out.println("Multiply: " +multiply.getResult());
        System.out.println("Divide: " +divide.getResult());

    }
}
