import java.util.ArrayList;

abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    protected double balance;

    // Constructor
    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Encapsulation: Getters
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(holderName + " deposited " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(holderName + " withdrew " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // Abstract Method for Interest Calculation
    public abstract double calculateInterest();

    // Display Account Details
    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

// Interface for Loanable Accounts
interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

// Savings Account (Earns Interest)
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }
}

// Current Account (No Interest, Loan Eligible)
class CurrentAccount extends BankAccount implements Loanable {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double calculateInterest() {
        return 0; // No interest on current accounts
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println(getHolderName() + " applied for a loan of " + amount);
        } else {
            System.out.println(getHolderName() + " is not eligible for a loan.");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        return getBalance() > 500; // Simple eligibility condition
    }
}


public class BankingSystem {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // Creating Different Bank Accounts
        BankAccount savings = new SavingsAccount("SAV123", "Riya", 5000, 3.5);
        BankAccount current = new CurrentAccount("CUR456", "Piya", 2000, 1000);

        // Adding to Account List
        accounts.add(savings);
        accounts.add(current);

        // Processing Accounts Dynamically
        for (BankAccount account : accounts) {
            account.displayDetails();
            double interest = account.calculateInterest();
            System.out.println("Calculated Interest: " + interest);

            // Checking if the account is loanable
            if (account instanceof Loanable) {
                ((Loanable) account).applyForLoan(5000);
            }

            System.out.println();
        }

        // Demonstrating Deposit and Withdraw
        savings.deposit(1000);
        current.withdraw(500);
    }
}
