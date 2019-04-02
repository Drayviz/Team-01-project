import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public abstract class BankAccount {

    private double balance = 0.0;
    private String accountNumber = "1111";
	private Customer accountHolder = null; 

    public BankAccount() {}
    public BankAccount(BankAccount copy) {
        this.balance = copy.balance;
        this.accountNumber = copy.accountNumber;
		this.accountHolder = copy.accountHolder;
    }
    public BankAccount(double currentBalance){
        balance = currentBalance;
    }
    public BankAccount(double currentBalance, String currentAccountNumber) {
        balance = currentBalance;
        accountNumber = currentAccountNumber;
    }
    public BankAccount(Customer currentAccountHolder, double currentBalance) {
        balance = currentBalance;
        accountHolder = currentAccountHolder;
    }
	public BankAccount(Customer currentAccountHolder, double currentBalance, String currentAccountNumber) {
        balance = currentBalance;
        accountNumber = currentAccountNumber;
		accountHolder = currentAccountHolder;
    }
    
    public BankAccount(BufferedReader reader) throws IOException {
        double readBalance = Double.parseDouble(reader.readLine()); //
        String readNumber = reader.readLine(); //
        this.balance = readBalance;
        this.accountNumber = readNumber;
        try {
            Customer c = new Customer(reader);
            this.accountHolder = c;
        }
        catch (Exception e) {
            this.accountHolder = null;
        }
    }

    public double getBalance(){
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String toString() {
        String forString = balance + "";
        return ("(" + accountHolder + ") " + accountNumber + ": " + forString);
    }
	public Customer getAccountHolder() {
        return this.accountHolder;
	}

    public void deposit(double depositNum) {
        //boolean success = false;
        if(depositNum>0) {
            balance = balance + depositNum;
            //success = true;
        }
        //return success;
    }
    public void withdraw(double withdrawNum) {
        //boolean success = false;
        if(withdrawNum>0 && withdrawNum<=balance) {
            balance = balance - withdrawNum;
            //success = true;
        }
        //return success;
    }
    public void setAccountHolder(Customer newAccountHolder) {
        accountHolder = newAccountHolder;
    }
    public void transfer(double amount, BankAccount account) {
        if (amount <= balance) {
            balance = balance - amount;
            account.balance = account.balance + amount;
        }
    }
    
    public void printBalance(BankAccount account) {
        System.out.println("original: " + balance);
        System.out.println("other acc: " + account.balance);
    }

    protected abstract double getMonthlyFeesAndInterest();

    public void monthEndUpdate() {
        double amount = getMonthlyFeesAndInterest();
        this.balance = new Double(balance + amount);
    } 

    public void saveToTextFile(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName);
        writer.write(getBalance() + "" + System.getProperty("line.separator"));
        writer.write(getAccountNumber() + System.getProperty("line.separator"));
        if (getAccountHolder() == null) {
            writer.write("null" + System.getProperty("line.separator"));
        }
        else {
            accountHolder.save(writer);
        }
        writer.close();
    }

}
