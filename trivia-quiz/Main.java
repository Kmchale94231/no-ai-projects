import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        boolean isRunning = true;
        int choice;
        int highScore = 0;


        while (isRunning) {

            System.out.println("==============================");
            System.out.println("          QUIZ GAME");
            System.out.println("==============================");
            System.out.println("1. Start Quiz");
            System.out.println("2. View High Score");
            System.out.println("3. View Instructions");       
            System.out.println("4. Exit");       
            System.out.println("Choose an option: ");       

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {
                    int score = startQuiz();

                    if (score > highScore){
                        highScore = score;
                    }
                }

                case 2 -> highScore(highScore);

                case 3 -> instructions();

                case 4 -> {
                    System.out.println("Exit");
                    isRunning = false;
                }

                default -> System.out.println("Invalid Choice");
            }

            if (isRunning) {
                System.out.println("\nPress enter to return to main menu");
                scanner.nextLine();
            }
        }
    }

    public static int startQuiz() {
        String[] questions = {
                "What does JVM stand for?",
                "Which keyword is used to create a constant variable in Java?",
                "Which data type stores true or false values?",
                "Which symbol is used to end most Java statements?",
                "Which method is the starting point of a Java program?",
                "Which loop runs at least once before checking its condition?",
                "Which operator checks if two values are equal?",
                "Which keyword is used to return a value from a method?",
                "Which Java type is used to store decimal numbers?",
                "Which collection-like structure stores multiple values of the same type?"
        };

        String[][] choices = {
            {
                "1. Java Variable Method",
                "2. Java Virtual Machine",
                "3. Java Verified Mode",
                "4. Joint Value Manager"
            },
            {
                "1. static",
                "2. final",
                "3. constant",
                "4. fixed"
            },
            {
                "1. int",
                "2. String",
                "3. boolean",
                "4. double"
            },
            {
                "1. :",
                "2. ;",
                "3. ,",
                "4. ."
            },
            {
                "1. start()",
                "2. run()",
                "3. main()",
                "4. execute()"
            },
            {
                "1. for loop",
                "2. while loop",
                "3. do-while loop",
                "4. switch loop"
            },
            {
                "1. =",
                "2. ==",
                "3. !=",
                "4. >="
            },
            {
                "1. break",
                "2. return",
                "3. continue",
                "4. output"
            },
            {
                "1. int",
                "2. boolean",
                "3. double",
                "4. char"
            },
            {
                "1. method",
                "2. array",
                "3. scanner",
                "4. switch"
            }
        };

        int[] answers = {
            2,
            2,
            3,
            2,
            3,
            3,
            2,
            2,
            3,
            2
        };

        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            printQuestion(questions[i], choices[i]);

            System.out.println("Your answer: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == answers[i]) {
                score++;
                System.out.println("\nCorrect!");
            } else {
                System.out.println("\nIncorrect!");
            }
        }
        System.out.printf("\nGame Over! Score: " + score);

        return score;
    }

    static void printQuestion(String currentQuestion, String[] currentChoices) {
        System.out.println(currentQuestion);

        for (String choice : currentChoices) {
            System.out.println(choice);
        }
    }

    public static void highScore(int highScore) {
        System.out.printf("Current High Score: " + highScore);
    }

    public static void instructions() {
        System.out.println("\n==============================");
        System.out.println("        INSTRUCTIONS");
        System.out.println("==============================");
        System.out.println("You will be given multiple-choice questions.");
        System.out.println("Each question has 4 answer choices.");
        System.out.println("Type the number of your answer and press Enter.");
        System.out.println("Each correct answer gives you 1 point.");
        System.out.println("Your final score will be shown at the end.");
        System.out.println("Try to beat your high score!");
        System.out.println("------------------------------");
    }
}