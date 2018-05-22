package HW1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serverSite {
    public volatile static boolean finish;
    static final int numofThreads =4;

    public static void main(String[] args) throws Exception{
        Thread[] mythreads =new Thread[numofThreads];
        int initValue =2;

        System.out.println("Please enter a number");
        BufferedReader input =new BufferedReader(new InputStreamReader(System.in));
        String n =input.readLine();

        System.out.println("(... expecting connection ...)");
        ServerSocket serverSocket =new ServerSocket(8000);


        for (int i =0; i <numofThreads; i ++) {
            Socket clientSocket =serverSocket.accept();
            mythreads[i] =new server(clientSocket, n, Integer.toString(initValue ++),
                    Integer.toString(numofThreads));
            mythreads[i].start();
            System.out.println("(... connection established ...)");
        }


    }
}

class server extends Thread {
    Socket clientSocket;
    String n;
    String init;
    String stepSize;
    String result;

    public server(Socket clientSocket, String n, String init, String stepSize) {
        this.clientSocket =clientSocket;
        this.n =n;
        this.init =init;
        this.stepSize =stepSize;
    }

    @Override
    public void run() {
        PrintWriter output =null;
        BufferedReader input =null;

        try {
            output =new PrintWriter(clientSocket.getOutputStream(), true);
            input =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (Exception e) {
            e.getMessage();
        }

        output.println(n +"," +init +"," +stepSize);
        output.flush();

        try {
            while (true) {
                if (serverSite.finish) {
                    output.println("stop");
                    output.flush();
                    break;
                }
                if (input.ready()) {
                    result =input.readLine();
                    System.out.println("The result is: " +result);
                    serverSite.finish =true;
                    break;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}