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
    File outputFile;
    File compiledFile;
    TestCase testCase;


    public Runner(RunnerBuilder b){
        this.testCase = b.getTestCase();
    }

    public TestCase getTestCase() {

        return testCase;
    }

    public boolean verifyProgram() throws IOException, InterruptedException {
        compileFile();
        runFile();
        IVerifier verifier = new Verifier();
        return verifier.verify(testCase.getOutputFile(), outputFile);
    }

    public void compileFile(){

        ICompiler compiler = new AbstractCompiler();
        compiledFile = compiler.compile(testCase.getInputProgramFile());
    }

    public void runFile() throws IOException, InterruptedException {
        IExecuter executer = new ExecuterProxy();
        executer.execute(compiledFile, testCase.getInputFile(),
                testCase.getTimeInMs());
        outputFile = new File(compiledFile.getName() + ".out");
    }
}
