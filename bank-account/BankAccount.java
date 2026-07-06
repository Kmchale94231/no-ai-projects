public class BankAccount {
    private String userName;
    private String password;
    private String accountName;
    private String accountType;
    private double balance;

    public BankAccount(String userName, String password, String accountName, String accountType, double balance) {
        this.userName = userName;
        this.password = password;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public static BankAccount createAccount(String userName, String password, String accountName, String accountType, double balance) {
        return new BankAccount(userName, password, accountName, accountType, balance);
    }

    public boolean checkPassword(String enteredPassword) {
        return enteredPassword.equals(password);
    }
    
    public String getPassword() {
        return password;
    }
}

