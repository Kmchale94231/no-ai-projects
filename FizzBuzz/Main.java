import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number: ");
        int userInput = scanner.nextInt();
        
        if (userInput % 5 == 0 && userInput % 3 == 0) {
            System.out.print("FizzBuzz");
        }
        else if (userInput % 5 == 0) {
            System.out.println("Fizz");
        } 
        else if (userInput % 3 == 0) {
            System.out.println("Buzz");
        }
        else {
            System.err.println(userInput);
        } 
    }
}