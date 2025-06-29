package skypay.bank;

// Transaction.java
import java.util.Date;

public class Transaction {
    private Date date;
    private int amount; // Positive for deposit, negative for withdrawal
    private int balanceAfterTransaction; // Balance *after* this specific transaction

    public Transaction(Date date, int amount, int balanceAfterTransaction) {
        this.date = date;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                '}';
    }
}
