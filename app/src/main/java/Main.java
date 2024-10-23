import compiler.ICompiler;
import executer.IExecuter;
import problem.Problem;
import runner.Runner;
import runner.RunnerBuilder;

import java.io.File;
import java.io.IOException;

public class Main {

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
                .build();

        try {
            runner.runFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
