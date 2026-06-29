import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        int principal = 0;
        float anualInterest = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Principal: ");
            principal = scanner.nextInt();

            if (principal >= 1000 && principal <= 1000000)
                break;
            System.out.println("Enter a number between 1,000 and 1,000,000.");
        }

        while (true) { 
            System.out.println("Anual Interest Rate: ");
            anualInterest = scanner.nextFloat();

            if (anualInterest > 0 || anualInterest <= 30)
                break;
            System.out.println("Enter a value greater than 0 and less than or equal to 30");
        }


        System.out.println("Period (Years): ");
        int periodYears = scanner.nextInt();

        // Math
        
        float monthlyInterest = anualInterest / PERCENT / MONTHS_IN_YEAR;

        int totalPayments = periodYears * MONTHS_IN_YEAR;

        double mortgagePayment = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, totalPayments)) / (Math.pow(1 + monthlyInterest, totalPayments) - 1);

        System.out.printf("Mortgage Payment: %.2f%n", mortgagePayment);
    }
}
