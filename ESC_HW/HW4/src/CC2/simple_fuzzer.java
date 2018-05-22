package CC2;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class simple_fuzzer {
    static final String source ="0123456789QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm";
    static SecureRandom rnd =new SecureRandom();

    public static void main(String[] args) {
        simple_fuzzer test =new simple_fuzzer();
        System.out.println(test.randomStirng());
        System.out.println(test.randomStirng());

    }

    public String randomStirng() {
        int rdm = ThreadLocalRandom.current().nextInt(0, 1025);
        StringBuilder sb =new StringBuilder(rdm);

        for (int i =0; i <rdm; i ++) {
            sb.append(source.charAt(rnd.nextInt(source.length())));
        }
        return sb.toString();
    }
}
