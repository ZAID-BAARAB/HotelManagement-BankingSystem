package skypay.hotel;

// User.java
public class User {
    private int id; // Corresponds to userId in setUser
    private int balance;

    public User(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", Balance=" + balance +
                '}';
    }
}
