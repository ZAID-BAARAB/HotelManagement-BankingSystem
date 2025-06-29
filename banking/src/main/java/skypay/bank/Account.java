package skypay.bank;

// Account.java
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.text.SimpleDateFormat; // For formatting dates in printStatement

public class Account implements AccountService {
    private int currentBalance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.currentBalance = 0;
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive."); // Handle Exceptions
        }
        this.currentBalance += amount;
        // Record the transaction
        transactions.add(new Transaction(new Date(), amount, this.currentBalance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive."); // Handled Exceptions
        }
        if (this.currentBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal. Available: " + this.currentBalance + ", Requested: " + amount); //  Exceptions
        }
        this.currentBalance -= amount;
        // Recording the transaction (negative amount for withdrawal)
        transactions.add(new Transaction(new Date(), -amount, this.currentBalance));
    }

    @Override
    public void printStatement() {
        // Desired Behaviour specified in the document: Date || Amount || Balance (from latest to oldest)

        // Make a copy and reverse for printing from latest to oldest
        ArrayList<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format as per example 10-01-2012

        // Use System.out.println for now. If you want pretty tables, you'd integrate the libraries here.
        System.out.println("Date       || Amount || Balance");
        System.out.println("----------------------------------"); // Simple separator

        for (Transaction transaction : reversedTransactions) {
            String amountStr = (transaction.getAmount() > 0 ? "+" : "") + transaction.getAmount(); // Show + for deposit
            System.out.printf("%-10s || %-7s || %s%n",
                    sdf.format(transaction.getDate()),
                    amountStr,
                    transaction.getBalanceAfterTransaction());
        }
    }
}