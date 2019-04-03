import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class SavingsAccount extends BankAccount {
    
	private double annualInterestRate = 0.05;
    private double minimumBalance;

    /** Default constructor. */
    public SavingsAccount() {}

    /** Constructor. 
     * @param interest This is the interest rate of the account.*/
    public SavingsAccount(double interest) {
        setAnnualInterestRate(interest);
    }
    
    /** Constructor. 
     * @param customer This is the icustomer information of the account.
     * @param balance This is the balance of the account.*/
    public SavingsAccount(Customer customer, double balance) {
        super(customer, balance);
    }
    
    /** Constructor. 
     * @param customer This is the icustomer information of the account.
     * @param balance This is the balance of the account.
     * @param interest This is the interest rate of the account.*/
    public SavingsAccount(Customer customer, double balance, double interest) {
        this(customer, balance);
        setAnnualInterestRate(interest);
    }

    /** Constructor. 
     * @param balance This is the balance of the account.
     * @param interest This is the interest rate of the account.*/
    public SavingsAccount(double balance, double annualInterestRate ) {
        super(balance);
        setAnnualInterestRate(annualInterestRate);   
    }

    /** Constructor.
     * Allows instantiation of SavingsAccount to be able to obtain the values of 
     * instance variables from a specified text file.
     * @param reader Takes a BufferedReader as a parameter.
     * @throws IOException */  
    public SavingsAccount(BufferedReader reader) throws IOException{
        super(reader);
        double readAIR = Double.parseDouble(reader.readLine()); //
        double readMB = Double.parseDouble(reader.readLine()); //
        setAnnualInterestRate(readAIR);
        setMinimumBalance(readMB);
    }
    
    /**Getter.
	 * @return annualInterestRate*/
    public double getAnnualInterestRate() {
        return new Double(annualInterestRate);
    }
    
    /**Getter.
	 * @return minimumBalance*/
    public double getMinimumBalance() {
        return new Double(minimumBalance);
    }
    
    /**Setter.
	 * @param air sets the instance variable <annualInterestRate>*/
    public void setAnnualInterestRate(double air) {
        if (air >= 0 && air <= 1) {
            this.annualInterestRate = new Double(air);
        }
    }
    
    /**Setter.
	 * @param mb sets the instance variable <minimumBalance>*/
    public void setMinimumBalance(double mb) {
        this.minimumBalance = new Double(mb);
    }
    
    /** Sets the monthly interest rate based on the annual interest rate and deposits it into the account.*/
    public void depositMonthlyInterest() {
        double toDeposit = (annualInterestRate*super.getBalance())/12.0;
        super.deposit(new Double(toDeposit));
    }

    /** Ensures that any withdraws doesn't result in the balance being lower than the lowest permitted balance.
     * @param withdrawNum This is the amount to withdraw.*/
    public void withdraw(double withdrawNum) {
        double afterW = super.getBalance() - withdrawNum;
        if (afterW >= this.minimumBalance) {
            super.withdraw(withdrawNum);
        }
    }
    
    /** Gets the amount to deposit based on the annual interest rate.
     * @param toDeposit This is the amount to deposit.*/
    public double getMonthlyFeesAndInterest() {
        double toDeposit = (annualInterestRate*super.getBalance())/12.0;
        return toDeposit;
    }

    /** Allows the instance variables to be written into a text file.
     * @param fileName
     * @throws IOException */
    public void saveToTextFile(String fileName) throws IOException {
        super.saveToTextFile(fileName);
        FileWriter fWriter = new FileWriter(fileName, true);
        PrintWriter writer = new PrintWriter(fWriter);
        writer.write(getAnnualInterestRate() + "" + System.getProperty("line.separator"));
        writer.write(getMinimumBalance() + "" + System.getProperty("line.separator"));
        writer.close();
    }
}