import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, BankAccount> accounts = new HashMap<>();
        boolean programRunning = true;
        
        while (programRunning) {
            displayMenu();

            int menuChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (menuChoice == 1) {
                createAccount(scanner, accounts);
            }

            else if (menuChoice == 2) {
                accountLogin(scanner, accounts);
            }
        }
    }

    public static void displayMenu() {
        System.out.println("===================================");
        System.out.println("       Welcome to Auburn Bank");
        System.out.println("===================================");
        System.out.println("1. Create Account");
        System.out.println("2. Log In");
        System.out.println("3. Exit");
        System.out.println("===================================");
        System.out.print("Choose an option: ");
    }

    public static void createAccount(Scanner scanner, HashMap<String, BankAccount> accounts) {
        System.out.print("Create username: ");
        String userName = scanner.nextLine();

        System.out.print("Create password: ");
        String password = scanner.nextLine();

        System.out.print("Create account-holder name: ");
        String accountName = scanner.nextLine();

        String accountType;

        while (true) {

            System.out.println("Select account type: ");
            System.out.println("1. Checking");
            System.out.println("2. Savings");
            int accountChoice = 0;
            accountChoice = scanner.nextInt();

            switch (accountChoice) {
                case 1:
                    accountType = "Checking";
                    break;
                case 2:
                    accountType = "Savings";
                    break;
                default:
                    System.out.println("Invalid account type choice");
                    continue;                   
            }
            break;
        }

        System.out.print("Enter starting balance: ");
        double balance = scanner.nextDouble();
        System.out.println();

        BankAccount newAccount = BankAccount.createAccount(userName, password, accountName, accountType, balance);
        accounts.put(userName, newAccount);
    }

    public static void accountLogin(Scanner scanner, HashMap<String, BankAccount> accounts) {
        boolean usernameFound = false;
        String inputUsername;
        BankAccount currentAccount = null;
        int loginAttempts = 3;
        boolean correctPass = false;

        while (!usernameFound) {

            System.out.print("Enter username: ");
            inputUsername = scanner.nextLine();

            if (accounts.containsKey(inputUsername)) {
                currentAccount = accounts.get(inputUsername);
                usernameFound = true;
            } else {
                System.out.println("Username does not exist.");
                
                loginAttempts -= 1;

                if (loginAttempts == 0) {
                    break;
                }
            }
        }

        if (!usernameFound) {
                return;
        }

        while (!correctPass) {
            loginAttempts = 3;
            String enteredPassword;
            System.out.print("Enter password: ");
            enteredPassword = scanner.nextLine();

            if (currentAccount.checkPassword(enteredPassword)) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Incorrect password.");
                loginAttempts -= 1;

                if (loginAttempts == 0) {
                    break;
                }
            }
        }
        scanner.close();
    }
}











/* 
                System.out.println("       Account Details");
                System.out.println("===============================");
                System.out.println("Username:           " + userName);
                System.out.println("Account-Holder:     " + accountName);
                System.out.println("Account Type:       " + accountType);
                System.out.println("Balance:            " + balance);
*/