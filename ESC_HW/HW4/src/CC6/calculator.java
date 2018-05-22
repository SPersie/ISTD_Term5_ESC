package CC6;

import org.omg.PortableInterceptor.DISCARDING;

import java.util.concurrent.ThreadLocalRandom;

public class calculator {
    public static int len =0;
    public static final int max_length =1000;

    public static void main(String[] args) {

        Expr();
    }

    public static void Expr() {
        int choose = ThreadLocalRandom.current().nextInt(0, 3);
        if (len >max_length) {
            choose =2;
        }
        if (choose ==0) {
            Expr();
            System.out.print("+");
            len ++;
            Term();
        } else if (choose ==1) {
            Expr();
            System.out.print("-");
            len ++;
            Term();
        } else {
            Term();
        }
    }

    public static void Term() {
        int choose =ThreadLocalRandom.current().nextInt(0, 3);
        if (len >max_length) {
            choose =2;
        }
        if (choose ==0) {
            Term();
            System.out.print("*");
            len ++;
            Factor();
        } else if (choose ==1) {
            Term();
            System.out.print("/");
            len ++;
            Factor();
        } else {
            Factor();
        }
    }

    public static void Factor() {
        int choose =ThreadLocalRandom.current().nextInt(0, 4);
        if (len >max_length) {
            choose =3;
        }
        if (choose ==0) {
            System.out.print("-");
            len ++;
            Integer();
        } else if (choose ==1) {
            System.out.print("(");
            len ++;
            Expr();
            System.out.print(")");
        } else if (choose ==2) {
            Integer();
        } else {
            Integer();
            System.out.print(".");
            len ++;
            Integer();
        }
    }

    public static void Integer() {
        int choose =ThreadLocalRandom.current().nextInt(0, 2);
        if (len >max_length) {
            choose =0;
        }
        if (choose ==0) {
            Digit();
        } else {
            Integer();
            Digit();
        }
    }

    public static void Digit() {
        int choose =ThreadLocalRandom.current().nextInt(0, 10);
        System.out.print(choose);
    }
}
