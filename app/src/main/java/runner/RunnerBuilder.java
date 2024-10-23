package runner;

import problem.TestCase;
import verifier.IVerifier;

import java.io.File;

public class RunnerBuilder {
    File sourceFile;
    File expectedOutputFile;
    File inputFile;
    Long timeInMs = 1000L;
    TestCase testCase;
    IVerifier verifier;


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

    public RunnerBuilder withTimeInMs(Long timeInMs){
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
