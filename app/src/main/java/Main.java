import compiler.AbstractCompiler;
import compiler.ICompiler;
import problem.Problem;
import problem.ProblemBuilder;
import problem.TestCase;
import runner.Runner;
import runner.RunnerBuilder;
import verifier.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Codes ANSI pour les couleurs
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final long DEFAULT_TIMEOUT = 1000;
    public static final IVerifier DEFAULT_VERIFIER = new Verifier();
    public static final double DEFAULT_PRECISION = 0.1;

    public static void printFile(File file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File sourceFile = new File(args[0]);
        if (!sourceFile.exists()) {
            System.out.println(ANSI_RED + "Error: The specified source file does not exist." + ANSI_RESET);
            return;
        }

        if (args.length == 2) {
            verifyDirectoryTestCase(sourceFile, args);
        } else if (args.length == 3) {
            verifyOneTestCase(sourceFile, args);
        }

    }

    private static void verifyDirectoryTestCase(File sourceFile,String[] args) {

//---------------------------------- Compilation ----------------------------------------
        System.out.println("======== Compiling : ========");
        System.out.println(sourceFile.getAbsolutePath());
        ICompiler compiler = new AbstractCompiler();
        File compiledFile = compiler.compile(sourceFile);

//--------------------------------- Problem creation -------------------------------------
        File testDirectory = new File(args[1]);
        if (!testDirectory.isDirectory()) {
            System.out.println(ANSI_RED + "Error: The specified directory does not exist." + ANSI_RESET);
            return;
        }

        File[] files = testDirectory.listFiles();
        if (files == null) {
            System.out.println(ANSI_RED + "Error: No test cases found in the specified directory." + ANSI_RESET);
            return;
        }

        ProblemBuilder pbb = new ProblemBuilder();
        pbb.withVerifier(readVerifier());

    //------------------- TestCase list creation for problem creation --------------------
        List<TestCase> testCases = new ArrayList<>();
        long timeLimitEachTestCase = readTimeLimit();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".in")) {
                File outFile = new File(
                        file.getAbsolutePath().replace(".in", ".ans")
                );
                testCases.add(new TestCase(sourceFile, file, outFile, timeLimitEachTestCase, readVerifier()));
            } else {
                System.out.println(
                        ANSI_RED + "Warning: Ignoring non-input file: "
                        + file.getName() + ANSI_RESET);
            }
        }
        pbb.withTestCasesList(testCases);
        Problem problem = pbb.build();

//------------------------------------- Running ------------------------------------------
        long totalTime = 0;
        boolean success = true;

        for (TestCase testCase : problem) {
            try {
                // runner creation for each test case
                RunnerBuilder rnb = new RunnerBuilder();
                rnb.withVerifier(testCase.getVerifier())
                    .withInputFile(testCase.getInputFile())
                    .withSourceFile(testCase.getInputProgramFile())
                    .withExpectedOutputFile(testCase.getOutputFile())
                    .withTimeInMs(testCase.getTimeInMs());
                Runner runner = rnb.build();
                runner.setCompiledFile(compiledFile);

                System.out.println("======== Running : ========");
                System.out.println(testCase.getInputProgramFile().getName());
                long start = System.currentTimeMillis();
                runner.runFile();
                long end = System.currentTimeMillis();
                totalTime += end - start;

                System.out.println("Expected : ");
                printFile(testCase.getOutputFile());
                System.out.println("Actual : ");
                printFile(runner.getOutputFile());

    //-------------------------------- Verifying ----------------------------------------
                System.out.println("======== Verifying ========");
                boolean result = runner.verifyProgram();
                success = result && success;
                if (result) {
                    System.out.println(
                            ANSI_GREEN + "Test passed in " + (end - start)
                            + " milliseconds !" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Test failed" + ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println("Error running test case: " + e.getMessage());
            }
        }

        if (success) {
            System.out.println(ANSI_GREEN + "All test cases passed!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Some test cases failed!" + ANSI_RESET);
        }
        System.out.println("======== Total time : " + totalTime + " milliseconds ========");
    }

    public static void verifyOneTestCase(File sourceFile, String [] args) {
        File inFile = new File(args[1]);
        File outFile = new File(args[2]);

        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(outFile)
                .withInputFile(inFile)
                .withSourceFile(sourceFile)
                .withTimeInMs(readTimeLimit())
                .withVerifier(readVerifier())// Time limit in ms, 1000ms = 1s
                .build();

        try {
            System.out.println("======== Compiling : ========");
            System.out.println(args[0]);
            runner.compileFile();
            System.out.println("======== Running ========");
            long start = System.currentTimeMillis();
            runner.runFile();
            long end = System.currentTimeMillis();
            System.out.println("Expected : ");
            printFile(outFile);
            System.out.println("Actual : ");
            printFile(runner.getOutputFile());

            System.out.println("======== Verifying ========");
            boolean result = runner.verifyProgram();
            if (result) {
                System.out.println(ANSI_GREEN + "Test passed in " + (end - start) + " milliseconds !" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Test failed" + ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static long readTimeLimit() {
        long timeLimit = DEFAULT_TIMEOUT;
        System.out.print("Enter the time limit in milliseconds (default "+ timeLimit + ") : ");
        do {
            timeLimit = readInt();
        } while ( timeLimit < 0 );
        return timeLimit;
    }


    private static IVerifier readVerifier() {
        System.out.println(
                "1. Strict verification\n2. Space insensitive verification\n3. Order tolerant verification\n4. Precision tolerant verification\n5. Case sensitive verification"
        );
        System.out.print("Enter the verification mode (default : 1. Strict) : ");
        IVerifier verifier = DEFAULT_VERIFIER;
        int verifierIndex;
        do {
            verifierIndex = readInt();
        } while (0 > verifierIndex || verifierIndex > 5);


        switch (verifierIndex) {
            case 0, 1:
                break;
            case 2:
                verifier = new SpaceInsensitiveVerifier(verifier);
                break;
            case 3:
                verifier = new OrderTolerantVerifier(verifier);
                break;
            case 4:
                verifier = new PrecisionVerifier(readPrecision());
                break;
            case 5:
                verifier = new CaseInsensitiveVerifier(verifier);
                break;
            default:
                System.out.println(ANSI_RED + "Invalid verification mode. Using default strict verification." + ANSI_RESET);
        }
        return verifier;
    }

    private static double readPrecision() {
        double precision = DEFAULT_PRECISION;
        System.out.print("Enter the precision (default "+ DEFAULT_PRECISION +" : ");
        double ans = readDouble();
        precision = ans!=0 ? ans : precision;
        return precision;
    }

    private static double readDouble() {
        Scanner sc;
        double result = -1;
        do {
            sc = new Scanner(System.in);
            if (sc.hasNextDouble()) {
                result = sc.nextDouble();
            }
            if (sc.next().isEmpty()) {
                result = 0;
            }
            if (result < 0) System.out.println(ANSI_RED + "Invalid, retry : " + ANSI_RESET);
        } while (result < 0);
        return result;
    }

    private static int readInt() {
        Scanner sc;
        int result = -1;
        do {
            sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                result = sc.nextInt();
            }
            if (sc.next().isEmpty()) {
                result = 0;
            }
            if (result < 0) System.out.println(ANSI_RED + "Invalid, retry : " + ANSI_RESET);
        } while (result < 0);
        return result;
    }

}
