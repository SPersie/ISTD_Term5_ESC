package HW1;

public class SynchronizedAccount {

    private double balance;

    public SynchronizedAccount(double balance) {
        this.balance =balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance +=amount;
        System.out.println("You have successfully deposit" +amount);
    }

    public synchronized void withdraw(double amount) {
        if (amount >balance) {
            System.out.println("No sufficient balance.");
        } else {
            balance -=amount;
            System.out.println("You have successfully withdraw " +amount);
        }
    }
}
