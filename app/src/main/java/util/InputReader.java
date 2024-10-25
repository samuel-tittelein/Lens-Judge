package util;

import verifier.*;

import java.util.Scanner;

public class InputReader {

    private InputReader(){
        super();
    }

    // Constants for ANSI colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    // Default values
    public static final long DEFAULT_TIMEOUT = 1000;
    public static final IVerifier DEFAULT_VERIFIER = new Verifier();
    public static final double DEFAULT_PRECISION = 0.1;

    /**
     * Reads the time limit from the standard input
     * @return a long, the time limit in milliseconds OR the DEFAULT_TIMEOUT if the standard input is empty
     */
    public static long readTimeLimit() {
        long timeLimit = DEFAULT_TIMEOUT;
        System.out.print("Enter the time limit in milliseconds (default " + timeLimit + "): ");
        String input = readLine();
        if (!input.isEmpty()) {
            try {
                timeLimit = Long.parseLong(input);
                if (timeLimit < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Using default time limit." + ANSI_RESET);
                timeLimit = DEFAULT_TIMEOUT;
            }
        }
        return timeLimit;
    }

    /**
     * Reads the verifier from the standard input
     * @return the chosen verifier OR the DEFAULT_VERIFIER if the standard input is empty
     */
    public static IVerifier readVerifier() {
        System.out.println(
                "1. Strict verification\n2. Space insensitive verification\n3. Order tolerant verification\n4. Precision tolerant verification\n5. Case sensitive verification"
        );
        System.out.print("Enter the verification mode (default: 1. Strict): ");
        IVerifier verifier = DEFAULT_VERIFIER;
        int verifierIndex = readInt(1);  // Default to 1 if empty

        switch (verifierIndex) {
            case 0, 1 -> {
                return verifier;
            }
            case 2 -> verifier = new SpaceInsensitiveVerifier(verifier);
            case 3 -> verifier = new OrderTolerantVerifier(verifier);
            case 4 -> verifier = new PrecisionVerifier(readPrecision());
            case 5 -> verifier = new CaseInsensitiveVerifier(verifier);
            default -> System.out.println(ANSI_RED + "Invalid verification mode. Using default strict verification." + ANSI_RESET);
        }
        return verifier;
    }

    /**
     * Reads the precision from the standard input
     * @return the chosen precision OR the DEFAULT_PRECISION if the standard input is empty
     */
    public static double readPrecision() {
        System.out.print("Enter the precision (default " + DEFAULT_PRECISION + "): ");
        double precision = DEFAULT_PRECISION;
        String input = readLine();
        if (!input.isEmpty()) {
            try {
                precision = Double.parseDouble(input);
                if (precision <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Using default precision." + ANSI_RESET);
            }
        }
        return precision;
    }

    /**
     * Reads an int from the standard input, default if empty
     * @return a positive int or the default if input is empty or invalid
     */
    public static int readInt(int defaultValue) {
        int result = defaultValue;
        String input = readLine();
        if (!input.isEmpty()) {
            try {
                result = Integer.parseInt(input);
                if (result < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Invalid input. Using default: " + defaultValue + ANSI_RESET);
                result = defaultValue;
            }
        }
        return result;
    }

    /**
     * Reads a line from the standard input
     * @return the input line as a String or empty if no input
     */
    public static String readLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
}
