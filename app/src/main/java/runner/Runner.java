package runner;

import problem.TestCase;

import java.io.File;

public class Runner {
    File sourceFile;
    File expectedOutputFile;
    File inputFile;
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

}
