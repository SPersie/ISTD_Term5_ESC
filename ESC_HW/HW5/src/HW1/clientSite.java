package HW1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class clientSite {
    public static void main(String[] args) {
        client[] myclients =new client[serverSite.numofThreads];
        for (int i =0; i <serverSite.numofThreads; i ++) {
            myclients[i] =new client();
            myclients[i].start();
        }

//        while (!serverSite.finish) {
//
//        }
//
//        for (Thread thread :myclients) {
//            thread.interrupt();
//        }
    }
}

class client extends Thread {
    BigInteger n;
    BigInteger init;
    BigInteger stepSize;
    public client() {

    }

    @Override
    public void run() {
//        try {
//            if (this.isInterrupted()) {
//                throw new InterruptedException();
//            }
//        } catch (InterruptedException e) {
//            System.exit(0);
//        }

        try {
            int portNumber =8000;
            String hostName ="localhost";

            Socket echoSocket =new Socket();
            SocketAddress sockaddr =new InetSocketAddress(hostName, portNumber);
            echoSocket.connect(sockaddr, 100);
            PrintWriter output =new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader input =new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            String msg =input.readLine();

//            System.out.println(msg);
            String[] msgs =msg.trim().split(",");
            n =new BigInteger(msgs[0]);
            init =new BigInteger(msgs[1]);
            stepSize =new BigInteger(msgs[2]);

            BigInteger zero =new BigInteger("0");
            while (init.compareTo(n) <0) {
                if (input.ready()) {
                    String testInput =input.readLine();
                    if (testInput.equals("stop")) {
                        echoSocket.close();
                        break;
                    }
                }

                if (n.remainder(init).compareTo(zero) ==0) {
                    System.out.println("Got it: " +init);
                    output.println(init);
                    output.flush();
                    break;
                }
                init =init.add(stepSize);
            }
        } catch (Exception e) {
            e.getMessage();

        }
    }
}
