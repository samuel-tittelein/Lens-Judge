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

public class RunnerBuilder {
    File sourceFile;
    File expectedOutputFile;
    File inputFile;
    TestCase testCase;

    private RunnerBuilder(){
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

    public RunnerBuilder withTestCase(TestCase testCase){
        this.testCase = testCase;
        return this;
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

    public static RunnerBuilder newInstance(){
        return new RunnerBuilder();
    }

    public Runner build(){
        return new Runner(this);
    }



}
