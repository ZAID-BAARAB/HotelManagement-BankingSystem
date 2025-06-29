package skypay.bank;

// AccountService.java
public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}
