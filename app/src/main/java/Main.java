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
import util.InputReader;

public class Main {

    // Codes ANSI pour les couleurs
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

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
        File compiledFile = getCompiledFile(sourceFile);

        //--------------------------------- Problem creation -------------------------------------
        Problem problem = getTestCases(sourceFile, args);
        if (problem == null)
            return;

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
                System.out.println(testCase.getInputProgramFile().getName() + " " +testCase.getOutputFile().getName());
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

    private static File getCompiledFile(File sourceFile) {
        System.out.println("======== Compiling : ========");
        System.out.println(sourceFile.getAbsolutePath());
        ICompiler compiler = new AbstractCompiler();
        return compiler.compile(sourceFile);
    }

    private static Problem getTestCases(File sourceFile, String[] args) {

        File testDirectory = new File(args[1]);
        if (!testDirectory.isDirectory()) {
            System.out.println(ANSI_RED + "Error: The specified directory does not exist." + ANSI_RESET);
            return null;
        }

        File[] files = testDirectory.listFiles();
        if (files == null) {
            System.out.println(ANSI_RED + "Error: No test cases found in the specified directory." + ANSI_RESET);
            return null;
        }

        ProblemBuilder pbb = new ProblemBuilder();
        IVerifier verifier = InputReader.readVerifier();
        pbb.withVerifier(verifier);

        //------------------- TestCase list creation for problem creation --------------------
        List<TestCase> testCases = new ArrayList<>();
        long timeLimitEachTestCase = InputReader.readTimeLimit();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".in")) {

                File outFile = new File(
                        file.getAbsolutePath().replace(".in", ".ans")
                );
                testCases.add(new TestCase(sourceFile, file, outFile, timeLimitEachTestCase, verifier));
            } else if (file.isFile() && !file.getName().endsWith(".ans")){
                System.out.println(
                        ANSI_RED + "Warning: Ignoring non-input file: "
                        + file.getName() + ANSI_RESET);
            }
        }
        pbb.withTestCasesList(testCases);
        return pbb.build();
    }

    public static void verifyOneTestCase(File sourceFile, String [] args) {
        File inFile = new File(args[1]);
        File outFile = new File(args[2]);

        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(outFile)
                .withInputFile(inFile)
                .withSourceFile(sourceFile)
                .withTimeInMs(1000)//readTimeLimit())
                .withVerifier(InputReader.readVerifier())// Time limit in ms, 1000ms = 1s
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
}
