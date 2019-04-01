public class ChequingAccount extends BankAccount {
	
	private double overdraftFee;
    private double overdraftAmount;
    
    /** Constructor.
     * @param accountHolder Takes the account holder of the account as a parameter.
     * @param startBalance Takes the start balance of the account as a parameter.
     * @param overdraftFee Takes the overdraft fee of the account as a parameter. */
    public ChequingAccount(Customer accountHolder, double startBalance, double overdraftFee) {
        super(accountHolder, startBalance);
        setOverdraftFee(overdraftFee);
    }
    
    /**Getter. 
     * @return overdraftFee. Used to return the instance variable <overdraftFee>.*/
    public double getOverdraftFee() {
        return new Double(this.overdraftFee);
    }
    
    
    /**Getter. 
     * @return overdraftAmount. Used to return the instance variable <overdraftAmount>.*/
    public double getOverdraftAmount() {
        return new Double(this.overdraftAmount);
    }
    
    /** Setter. Ensures that the overdraft fee can't be less than or equal to 0. 
     * If the desired overdraft fee is not viable, then it is set to a default value of 1.
     * @param overdraftFee This is the desired overdraft fee for the account. */
    public void setOverdraftFee(double overdraftFee) {
        if (overdraftFee > 0.0) {
            this.overdraftFee = new Double(overdraftFee);
        }
        else {
            this.overdraftFee = 1.0;
        }
    }
    
    /** Setter. Ensures that the overdraft amount can't be less than or equal to 0. 
     * @param overdraftAmount This is the desired overdraft amount for the account. */
    public void setOverdraftAmount(double overdraftAmount) {
        if (overdraftAmount > 0.0) {
            this.overdraftAmount = new Double(overdraftAmount);
        }
    }    
    
    /**If the withdraw results in a negative balance, it updates the amount to withdraw to add the overdraft fee.
     * @param amount is the value to withdraw.*/
    public void withdraw(double amount) {
        if ((super.getBalance()- amount) < 0.0 && sufficientFunds(amount)) {
            super.withdraw(amount + new Double(this.overdraftFee));
        }
        else if (super.getBalance() - amount >= 0.0) {
            super.withdraw(amount);
        }
    }

    /**
     * @Override
     * Overrides the method in the base class. 
     * It checks to see if the withdraw amount results in a balance greater than or equal to the overdraft amount.
     * @return true if the above requirement is fulfilled.*/
    @Override
    public boolean sufficientFunds(double amount) {
        boolean check = false;
        if ((super.getBalance() - amount) >= (0.0 - this.overdraftAmount)) {
            check = true;
        }
        return check;
    }
    
    /** If the balance is greater than or equal to zero, @return 0.
     * If not, @return 20% of the balance.*/
    public double getMonthlyFeesAndInterest() {
        if(super.getBalance() >= 0) {
            return 0.0;
        }
        else {
            return (-(this.overdraftAmount*0.2));
        }
    }
}