import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            String inputText = validateInput(scanner, "Enter text: ", String.class);

            StringBuilder text = new StringBuilder(inputText);

            int wordLength = validateInput(scanner, "Enter the length of the word to replace: ", Integer.class);

            String replacement = validateInput(scanner, "Enter the string to replace the words with: ", String.class);

            StringBuilder resultText = StringReplacer.replaceWords(text, wordLength, replacement);

            System.out.println("The result of the replacement: " + resultText);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.exit(1);
        }
    }

    public static <T> T validateInput(Scanner scanner, String prompt, Class<T> type) {
        T input = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            try {
                switch (type.getSimpleName()) {
                    case "Integer":
                        int intValue = scanner.nextInt();
                        if (intValue <= 0) {
                            System.out.println("Invalid input. Number must be greater than zero.");
                        } else {
                            input = type.cast(intValue);
                            validInput = true;
                            scanner.nextLine();
                        }
                        break;

                    case "String":
                        String stringValue = scanner.nextLine();
                        if (stringValue.isBlank()) {
                            System.out.println("Invalid input. String cannot be blank.");
                        } else {
                            input = type.cast(stringValue);
                            validInput = true;
                        }
                        break;

                    default:
                        System.out.println("Unsupported type for numerical input.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a value in the correct format.");
                scanner.next();
            }
        }
        return input;
    }
}