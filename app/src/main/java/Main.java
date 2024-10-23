import runner.Runner;
import runner.RunnerBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        if (args.length != 3) {
            return;
        }
        File sourceFile = new File(args[0]);
        File inFile = new File(args[1]);
        File outFile = new File(args[2]);

        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(outFile)
                .withInputFile(inFile)
                .withSourceFile(sourceFile)
                .withTimeInMs(1000L)  // Time limit in ms, 1000ms = 1s
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
                System.out.println(ANSI_GREEN + "Test passed in " + (end - start) + " miliseconds !" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "Test failed" + ANSI_RESET);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
