package HW2;

import java.util.Scanner;

/*
    In this code, I use factory pattern to check the vote and add to the counters.
 */

public class SequentialElection {
    public static void main(String[] args) {

        int counterA =0;
        int counterB =0;
        Scanner input =new Scanner(System.in);

        for (int i =0; i <5; i ++) {
            System.out.println("Electorate " +(i +1) +":");
            System.out.println("Please vote for A or B.");
            String vote =input.next();

            while (!vote.equals("A") &&!vote.equals("B")) {
                System.out.println("You can only vote for A or B.");
                vote =input.next();
            }

            voteFactory theFactory =new voteFactory();
            int[] result =theFactory.countForThisVote(vote);
            counterA +=result[0];
            counterB +=result[1];
        }

        if (counterA >=counterB) {
            System.out.println("A wins.");
        } else {
            System.out.println("B wins.");
        }
    }

}

class voteFactory {
    public int[] countForThisVote(String vote) {
        int[] result =new int[2];
        if (vote.equals("A")) {
            result[0] =1;
            result[1] =0;
            return result;
        } else {
            result[0] =0;
            result[1] =1;
            return result;
        }
    }
}

