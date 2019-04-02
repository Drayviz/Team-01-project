import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public abstract class BankAccount {

    /** Instance variables for the class that are set to default values, note that one is of
     * type Customer. */ 
    private double balance = 0.0;
    private String accountNumber = "1111";
	private Customer accountHolder = null; 

    public BankAccount() {}

    /** Copy constructor.
     * @param copy Takes a BankAccount object as an argument and creates a copy of it*/
    public BankAccount(BankAccount copy) {
        this(copy.balance,copy.accountNumber);
		setAccountHolder(copy.accountHolder);
    }

    /** Constructor.
     * @param currentBalance Takes the balance of the account as a parameter. */
    public BankAccount(double currentBalance){
        this.balance = new Double(currentBalance);
    }

    /** Constructor.
     * @param currentBalance Takes the balance of the account as a parameter. 
     * @param currentAccountNumber Takes the account number of the account as a parameter.*/
    public BankAccount(double currentBalance, String currentAccountNumber) {
        this(currentBalance);
        this.accountNumber = new String(currentAccountNumber);
    }
    
    /** Constructor.
     * @param currentAccountHolder Takes the account holder of the account as a parameter. 
     * @param currentBalance Takes the balance of the account as a parameter.*/
    public BankAccount(Customer currentAccountHolder, double currentBalance) {
        this(currentBalance);
        setAccountHolder(currentAccountHolder);
    }
    
    /** Constructor.
     * @param currentAccountHolder Takes the account holder of the account as a parameter. 
     * @param currentBalance Takes the balance of the account as a parameter.
     * @param currentAccountNumber Takes the account number of the account as a parameter.*/
	public BankAccount(Customer currentAccountHolder, double currentBalance, String currentAccountNumber) {
        this(currentBalance,currentAccountNumber);
        setAccountHolder(currentAccountHolder);
    }
    
    /** Constructor.
     * Allows instantiation of subclasses of BankAccount to be able to obtain the values of 
     * instance variables from a specified text file.
     * @param reader Takes a BufferedReader as a parameter.
     * @throws IOException */    
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

    /** Getter. 
     * @return balance. Used to return the instance variable <balance>.*/
    public double getBalance(){
        return new Double(this.balance);
    }
    
    /** Getter. 
     * @return accountNumber. Used to return the instance variable <accountNumber>.*/
    public String getAccountNumber() {
        return new String(this.accountNumber);
    }
    
    /** Allows the instance variables to be printed in an organized manner, 
     * now including the account holder information. */
    public String toString() {
        String forString = balance + "";
        return ("(" + accountHolder.toString() + ") " + getAccountNumber() + ": " + forString);
    }
    
    
    /** Getter. 
     * @return accountHolder. Used to return the instance variable <accountHolder>.*/
	public Customer getAccountHolder() {
        /* return accountHolder; */
        return this.accountHolder;
	}
	
    /** Setter.
     * if-SM is necessary to ensure that one doesn't deposit a negative number (which would be
     * technically be a withdrawal).
     * @param depositNum This is how much they desire to deposit.*/
    public void deposit(double depositNum) {
        if(depositNum>0) {
            balance = balance + depositNum;
        }
    }
    
    /**Setter.
     * if-SM #1 is necessary to ensure that they don't withdraw over past their overdraft amount.
     * if-SM #2 ensures they don't withdraw a negative number (which would technically be a deposit).
     * @param amount This is how much they desire to withdraw.*/
    public void withdraw(double amount) {
        if (sufficientFunds(amount)) {
            if(amount>0) {
                this.balance = new Double(this.balance - amount);
            }
        }
    }
    
    /**Setter.
     * Used to set the value for the instance variable <accountHolder>.
     * @param newAccountHolder This is new account holder they'd like to set.*/
    public void setAccountHolder(Customer newAccountHolder) {
        this.accountHolder = newAccountHolder;
    }

    /** Used to transfer the amount specified and which account to transfer to.
     * if-SM is necessary to ensure that they don't withdraw over past their overdraft amount.
     * @param amount This is how much to transfer.
     * @param account This is the account to transfer to. */
    public void transfer(double amount, BankAccount account) {
        if (sufficientFunds(amount)){
            withdraw(amount);
            account.deposit(amount);
        }
    }

    /** This is an abstract method set to ensure subclasses can use this method by overriding. */
    protected abstract double getMonthlyFeesAndInterest();

    /** Calls getMonthlyFeesAndInterest() to see how much the instance variable <balance>
     * should change. A negative amount return will result in a reduction in balance (fee) 
     * and a positive amount return will result in an increase in balance (interest).*/
    public void monthEndUpdate() {
        double amount = getMonthlyFeesAndInterest();
        this.balance = new Double(balance + amount);
    } 

    /** Allows the instance variables to be written into a text file.
     * @param fileName
     * @throws IOException */
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

    /** Used to check if amount to withdraw would result in a negative balance.
     * @param amount This is how much one would like to withdraw.*/
    public boolean sufficientFunds(double amount) {
        boolean check = false;
        if ((this.balance - amount) >= 0.0) {
            check = true;
        }
        return check;
    }

}
