package runner;

import problem.TestCase;
import verifier.IVerifier;
import verifier.Verifier;

import java.io.File;

public class RunnerBuilder {
    File sourceFile;
    File expectedOutputFile;
    File inputFile;
    long timeInMs = 1000; // Default time limit is 1 second
    TestCase testCase;
    IVerifier verifier = new Verifier(); // Default verifier is the strict one


    public RunnerBuilder(){
        super();
    }

    public RunnerBuilder withSourceFile(File sourceFile){
        this.sourceFile = sourceFile;
        return this;
    }

    public RunnerBuilder withExpectedOutputFile(File expectedOutputFile){
        this.expectedOutputFile = expectedOutputFile;
        return this;
    }

    public RunnerBuilder withInputFile(File inputFile){
        this.inputFile = inputFile;
        return this;
    }

    public RunnerBuilder withTimeInMs(long timeInMs){
        this.timeInMs = timeInMs;
        return this;
    }

    public static RunnerBuilder newInstance(){
        return new RunnerBuilder();
    }

    public TestCase getTestCase() {

        return testCase;
    }

    public RunnerBuilder withVerifier(IVerifier verifier){
        this.verifier = verifier;
        return this;
    }

    public Runner build(){
        testCase = new TestCase(sourceFile, inputFile, expectedOutputFile, timeInMs, verifier);
        return new Runner(this);
    }



}
