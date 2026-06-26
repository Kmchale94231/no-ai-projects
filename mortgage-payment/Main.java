import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Principal: ");
        int principal = scanner.nextInt();

        System.out.println("Anual Interest Rate: ");
        float anualInterest = scanner.nextFloat();
        float monthlyInterest = anualInterest / PERCENT / MONTHS_IN_YEAR;


        System.out.println("Period (Years): ");
        int periodYears = scanner.nextInt();

        int totalPayments = periodYears * MONTHS_IN_YEAR;


        double mortgagePayment = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, totalPayments)) / (Math.pow(1 + monthlyInterest, totalPayments) - 1);

        System.out.printf("Mortgage Payment: %.2f%n", mortgagePayment);
    }
}
