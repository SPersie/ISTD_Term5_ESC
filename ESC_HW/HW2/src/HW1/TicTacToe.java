package HW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {

        char[][] table =new char[3][3];
        Scanner input =new Scanner(System.in);
        System.out.println("---------------Tic Tac Toe---------------");
        System.out.println("Player 1, please enter coordinate.");

        while (true) {
            Integer[] inputs ={11, 12, 13, 21, 22, 23, 31, 32, 33};
            ArrayList<Integer> input_cooo =new ArrayList<>();
            input_cooo.addAll(Arrays.asList(inputs));

            //Player 1 turn
            System.out.println("Player 1 enter coordinate: ");
            int coo_A =Integer.valueOf(input.next());

            while (!input_cooo.contains(coo_A)
                    ||table[coo_A /10 -1][coo_A %10 -1] !='\u0000') {
                System.out.println("This is not a valid input.");
                coo_A =Integer.valueOf(input.next());
            }

            table[coo_A /10 -1][coo_A %10 -1] ='X';
            //TODO: print the table
            printTable(table);
            //TODO: check whether player 1 wins
            if (win(table)) {
                System.out.println("Player 1 wins.");
                break;
            }

            //Player 2 turn
            System.out.println("Player 2, please enter coordinate.");
            int coo_B =Integer.valueOf(input.next());

            while (!input_cooo.contains(coo_B)
                    ||table[coo_B /10 -1][coo_B %10 -1] !='\u0000') {
                System.out.println("This is not a valid input.");
                coo_B =Integer.valueOf(input.next());
            }

            table[coo_B /10 -1][coo_B %10 -1] ='\u25EF';
            //TODO: print the table
            printTable(table);
            //TODO: check whether player 2 wins
            if (win(table)) {
                System.out.println("Player 2 wins.");
                break;
            }
        }
    }

    public static boolean win(char[][] table) {
        if (table[0][0] ==table[0][1] &&table[0][0] ==table[0][2]) {
            return true;
        } else if (table[1][0] ==table[1][1] &&table[1][0] ==table[1][2]
                &&table[1][0] !='\u0000') {
            return true;
        } else if (table[2][0] ==table[2][1] &&table[2][0] ==table[2][2]
                &&table[2][0] !='\u0000') {
            return true;
        } else if (table[0][0] ==table[1][0] &&table[0][0] ==table[2][0]
                &&table[0][0] !='\u0000') {
            return true;
        } else if (table[1][0] ==table[1][1] &&table[1][0] ==table[1][2]
                &&table[1][0] !='\u0000') {
            return true;
        } else if (table[2][0] ==table[2][1] &&table[2][0] ==table[2][2]
                &&table[2][0] !='\u0000') {
            return true;
        } else if (table[0][0] ==table[1][1] &&table[0][0] ==table[2][2]
                &&table[0][0] !='\u0000') {
            return true;
        } else if (table[0][2] ==table[1][1] &&table[0][2] ==table[2][0]
                &&table[0][2] !='\u0000') {
            return true;
        } else {
            return false;
        }
    }

    public static void printTable(char[][] table) {
        System.out.println(table[0][0] + " " + table[0][1] + " " + table[0][2]);
        System.out.println(table[1][0] + " " + table[1][1] + " " + table[1][2]);
        System.out.println(table[2][0] + " " + table[2][1] + " " + table[2][2]);
    }
}
