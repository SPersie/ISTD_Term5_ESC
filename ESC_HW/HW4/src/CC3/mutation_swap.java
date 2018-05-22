package CC3;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class mutation_swap {
    public static void main(String[] args) {
//        System.out.println(ThreadLocalRandom.current().nextInt(0, 1));
        Scanner input =new Scanner(System.in);
        System.out.println("Please enter a string.");
        String s =input.next();

        int len =s.length();
        int rdm = ThreadLocalRandom.current().nextInt(0, len -1);

        mutation_swap haha =new mutation_swap();
        System.out.println(haha.simple_swap(s, rdm));
    }

    public String simple_swap(String s, int position) {
        StringBuilder sb =new StringBuilder(s);
        char temp =s.charAt(position +1);
        sb.setCharAt(position +1, s.charAt(position));
        sb.setCharAt(position, temp);
        return sb.toString();
    }
}
