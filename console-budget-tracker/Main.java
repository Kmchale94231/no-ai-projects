import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        double totalIncome = 0;
        double totalExpenses = 0;
        double savingsGoal = 0;
        int choice;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n==============================");
            System.out.println("        BUDGET TRACKER");
            System.out.println("==============================");
            System.out.println("1. View Current Balance");
            System.out.println("2. Add Income");
            System.out.println("3. Add Expense");
            System.out.println("4. View Spending Summary");
            System.out.println("5. Set Savings Goal");
            System.out.println("6. Exit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> viewBalance(totalIncome, totalExpenses);

                case 2 -> {
                    totalIncome += addIncome(scanner);
                }

                case 3 -> {
                    totalExpenses += addExpense(scanner);
                }

                case 4 -> savingsGoal = setSavingsGoal(scanner);

                case 5 -> spendingSummary(totalIncome, totalExpenses, savingsGoal);

                case 6 -> isRunning = false;

                default -> System.out.println("Invalid Choice");
            }

            if (isRunning) {
                System.out.println("\nPress Enter to return to main menu");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    static void viewBalance(double totalIncome, double totalExpenses) {
        double balance = totalIncome - totalExpenses;
        System.out.printf("Current Balance: $%.2f\n", balance);
    }

    static double addIncome(Scanner scanner) {

        double amount;

        System.out.print("Enter added income: ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        while (amount < 0) {
            System.out.println("Amount must be greater than 0");
            return 0;
        }
        return amount;
    }

    static double addExpense(Scanner scanner) {
        double expense;

        System.out.println("Enter added expense: ");
        expense = scanner.nextDouble();
        scanner.nextLine();

        return expense;
    }

    static double setSavingsGoal(Scanner scanner) {

        System.out.println("Enter savings goal: $");
        double savingsGoal = scanner.nextDouble();
        scanner.nextLine();
        
        while (savingsGoal < 0) {
            System.out.println("Amount must be greater than 0.");
            return 0;
        }
        System.out.printf("Savings goal set to: $%.2f\n", savingsGoal);
        return savingsGoal;
    }

    static void spendingSummary(double totalIncome, double totalExpenses, double savingsGoal) {
        double balance = totalIncome - totalExpenses;
        System.out.printf("Total income:    $%.2f\n", totalIncome);
        System.out.printf("Total expenses:  $%.2f\n", totalExpenses);
        System.out.printf("Current balance: $%.2f\n", balance);
        System.out.printf("Savings goal: $%.2f\n", savingsGoal);
    }
}