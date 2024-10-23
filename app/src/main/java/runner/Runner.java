package runner;

import compiler.AbstractCompiler;
import compiler.ICompiler;
import executer.ExecuterProxy;
import executer.IExecuter;
import problem.TestCase;
import verifier.IVerifier;
import verifier.Verifier;

import java.io.File;
import java.io.IOException;

public class Runner {
    File sourceFile;
    File expectedOutputFile;
    File inputFile;
    File outputFile;
    File compiledFile;
    TestCase testCase;

    public Runner(RunnerBuilder b){
        this.sourceFile = b.getSourceFile();
        this.expectedOutputFile = b.getExpectedOutputFile();
        this.inputFile = b.getInputFile();
        this.testCase = b.getTestCase();
    }

    public File getSourceFile() {

        return sourceFile;
    }

    public File getExpectedOutputFile() {

        return expectedOutputFile;
    }

    public File getInputFile() {

        return inputFile;
    }

    public TestCase getTestCase() {

        return testCase;
    }

    public boolean verifyProgram() throws IOException, InterruptedException {
        compileFile();
        runFile();
        IVerifier verifier = new Verifier();
        return verifier.verify(expectedOutputFile, outputFile);
    }

    public void compileFile(){

        ICompiler compiler = new AbstractCompiler();
        compiledFile = compiler.compile(sourceFile);
    }

    public void runFile() throws IOException, InterruptedException {
        IExecuter executer = new ExecuterProxy();
        executer.execute(compiledFile, inputFile);
        outputFile = new File(compiledFile.getName() + ".out");
    }
}
