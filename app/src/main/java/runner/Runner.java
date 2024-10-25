package runner;

import compiler.AbstractCompiler;
import compiler.ICompiler;
import executer.ExecuterProxy;
import executer.IExecuter;
import problem.TestCase;
import verifier.IVerifier;

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
        if (compiledFile == null) {
            compileFile();
        }
        if (outputFile == null) {
            runFile();
        }
        IVerifier verifier = testCase.getVerifier();
        try {
            return verifier.verify(testCase.getOutputFile(), outputFile);
        } finally {
            outputFile.delete();
            compiledFile.delete();
        }

    }

    public void compileFile(){

        ICompiler compiler = new AbstractCompiler();
        compiledFile = compiler.compile(testCase.getInputProgramFile());
    }

    public void runFile() throws IOException, InterruptedException {
        if (compiledFile == null) {
            compileFile();
        }
        IExecuter executer = new ExecuterProxy();
        executer.execute(compiledFile, testCase.getInputFile(),
                testCase.getTimeInMs());
        outputFile = new File(compiledFile.getName() + ".out");
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setCompiledFile(File compiledFile) {
        this.compiledFile = compiledFile;
    }

}
