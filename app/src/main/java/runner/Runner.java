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

    /**
     *
     * @return a boolean that indicates if the output file is correct
     * @throws IOException if an error occurs while writing the output to the file.
     * @throws InterruptedException if the process is interrupted.
     */
    public boolean verifyProgram() throws IOException, InterruptedException {
        if (compiledFile == null) {
            compileFile();
        }
        if (outputFile == null) {
            runFile();
        }
        IVerifier verifier = testCase.getVerifier();
        return verifier.verify(testCase.getOutputFile(), outputFile);
    }

    /**
     * Compile the file
     */
    public void compileFile(){

        ICompiler compiler = new AbstractCompiler();
        compiledFile = compiler.compile(testCase.getInputProgramFile());
    }

    /**
     * Run the file
     * @throws IOException if an error occurs while writing the output to the file.
     * @throws InterruptedException if the process is interrupted.
     */
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
