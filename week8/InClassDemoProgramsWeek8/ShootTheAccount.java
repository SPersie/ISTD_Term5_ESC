
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShootTheAccount {
  private int balance = 0;
  private String accountType;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  
  public ShootTheAccount () {
  }

  public ShootTheAccount (int balance, String accountType) {
    this.balance = balance;
    this.accountType =accountType;
  }
  
  public void deposite (int amount) {
    balance += amount;
  }
    
  public void setBalance (int amount) {
    balance = amount;
  }

  public void setAccountType(String type) {
      accountType =type;
  }
  
  public int getBalance () {
    return balance;
  }

  public String getAccountType() {
      return accountType;
  }
  
  // this method has a long method smell
  public void CreditTransaction(int amount) {
	  
	//increase the amount
	balance = balance + amount;
	  
	//add to the transaction list
	ListOfAllTransactions.add(new Transaction("debit", amount));
	  	  
  }

  
  // this method has a long method smell
  public void DebitTransaction(int amount) {
	  
	  if (balance >= 500) {
		  //reduce the amount
		  balance = balance - amount;
	  
		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));
	  
		  //update the last debit date
		  Calendar cal = Calendar.getInstance();
	  
		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);  
	  }	  
  }
  
  // this method has a long method smell
  public void Transfer(int amount, ShootTheAccount Benf) {
	  
	  if (balance >= 500) {
		  //reduce the amount
		  balance = balance - amount;
	  
		  //add to the transaction list
		  ListOfAllTransactions.add(new Transaction("debit", amount));
	  
		  //update the last debit date
		  Calendar cal = Calendar.getInstance();
	  
		  lastDebitTime = cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);  
		  
		  Benf.CreditTransaction(amount);
	  }	  
  }
  
  public void sendWarning() {
	  if (balance < 500 &&accountType.equals("personal"))
		  System.out.println("Balance must be more than 500, please deposit");
  }	  
}                                                                                                                                                                                                       
