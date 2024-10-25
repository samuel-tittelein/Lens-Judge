package runner;

import compiler.AbstractCompiler;
import compiler.ICompiler;
import exception.RuntimeErrorException;
import executer.ExecuterProxy;
import executer.IExecuter;
import problem.TestCase;
import verifier.IVerifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    public boolean verifyProgram() throws IOException, InterruptedException, RuntimeErrorException {
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
            try{
                Files.delete(outputFile.toPath());
            }catch(IOException e){
                System.err.println("Failed to delete outputFile "+e.getMessage());
            }
        }
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
    public void runFile() throws IOException, InterruptedException, RuntimeErrorException {
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

