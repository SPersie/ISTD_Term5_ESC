package CC4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.ThreadLocalRandom;

public class generalized_fuzzer {
    public static void main(String[] args) {
        generalized_fuzzer fuzzer =new generalized_fuzzer();

        //setting filename and read line
        String inputfile ="mytext.txt";
        String outputfile ="output.txt";
        String input =null;

        try{
            //initialize FileReader and BufferedReader
            FileReader filereader =new FileReader(inputfile);
            BufferedReader bufferedReader =new BufferedReader(filereader);

            //initialize FileWriter and BufferWriter
            FileWriter fileWriter =new FileWriter(outputfile);
            BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);

            while ((input =bufferedReader.readLine()) !=null) {
//                System.out.println(input);
                int operation =ThreadLocalRandom.current().nextInt(0, 3);
//                System.out.println(operation);
                if (operation ==0) {
                    input =fuzzer.my_swap(input);
//                    System.out.println(input);
                    bufferedWriter.write(input);
                    bufferedWriter.newLine();
                } else if (operation ==1) {
                    input =fuzzer.my_trim(input);
//                    System.out.println(input);
                    bufferedWriter.write(input);
                    bufferedWriter.newLine();
                } else {
                    input =fuzzer.my_flip(input);
//                    System.out.println(input);
                    bufferedWriter.write(input);
                    bufferedWriter.newLine();
                }

            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {

        }
    }

    public String my_swap(String s) {
        int position =ThreadLocalRandom.current().nextInt(0, s.length() -1);
        StringBuilder sb =new StringBuilder(s);
        char temp =s.charAt(position +1);
        sb.setCharAt(position +1, s.charAt(position));
        sb.setCharAt(position, temp);
        return sb.toString();
    }

    public String my_trim(String s) {
        int position =ThreadLocalRandom.current().nextInt(0, s.length());
        StringBuilder sb =new StringBuilder(s);
        sb.deleteCharAt(position);
        return sb.toString();
    }

    public String my_flip(String s) {
        StringBuilder sb =new StringBuilder(s);
        byte[] bytes;
        bytes =sb.toString().getBytes();
        int byte_position =ThreadLocalRandom.current().nextInt(0, bytes.length -1);
        int bit_position =ThreadLocalRandom.current().nextInt(0, 8);

        byte fliped =bytes[byte_position];
        if ((fliped ^bit_position) ==1) {
            fliped =(byte) (fliped ^0 <<bit_position);
        } else {
            fliped =(byte) (fliped ^1 <<bit_position);
        }

        bytes[byte_position] =fliped;
        return new String(bytes);
    }
}
