package skypay.bank;

// Main.java
import java.util.Date; // For setting dates in transactions if needed, though printStatement uses current date

public class Main {
    public static void main(String[] args) {

        AccountService account = new Account(); // Instantiate your Account class

        System.out.println("--- Running Banking Service Test Case ---");
        // we will test use cases as mentioned in the test :
        // Given a client makes a deposit of 1000 on 10-01-2012
        // Note: The Account class currently uses new Date() for transactions.
        // For strict date adherence, you'd need to pass Date to deposit/withdraw,
        // or modify Transaction to accept and store the provided date.
        // For now, let's assume the spirit of the test is sequence and values.
        // If you need exact dates, we'd need to modify Account/Transaction to allow specifying date.

        try {
            System.out.println("\nDepositing 1000...");
            account.deposit(1000);
        } catch (Exception e) {
            System.err.println("Operation failed: " + e.getMessage());
        }

        // And a deposit of 2000 on 13-01-2012
        try {
            System.out.println("Depositing 2000...");
            account.deposit(2000);
        } catch (Exception e) {
            System.err.println("Operation failed: " + e.getMessage());
        }

        // And a withdrawal of 500 on 14-01-2012
        try {
            System.out.println("Withdrawing 500...");
            account.withdraw(500);
        } catch (Exception e) {
            System.err.println("Operation failed: " + e.getMessage());
        }

        System.out.println("\n--- Printing Bank Statement ---");
        account.printStatement();

        // Example of an invalid operation (insufficient funds)
        try {
            System.out.println("\nAttempting to withdraw 3000 (should fail)...");
            account.withdraw(3000);
        } catch (Exception e) {
            System.err.println("Operation failed (expected): " + e.getMessage());
        }
        System.out.println("\n--- Printing Bank Statement After Failed Withdrawal ---");
        account.printStatement();

        // Optional: Clean up Jansi
        // org.fusesource.jansi.AnsiConsole.systemUninstall();
    }
}