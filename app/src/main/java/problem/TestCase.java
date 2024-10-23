package problem;

import verifier.IVerifier;

import java.io.File;
import java.util.Iterator;

public class TestCase {

    private File inputProgramFile;
    private File inputFile;
    private File outputFile;
    private long timeInMs;
    private IVerifier verifier;

    public TestCase(File inputProgramFile, File inputFile, File outputFile, long timeInMs, IVerifier verifier) {

        this.inputProgramFile = inputProgramFile;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.timeInMs = timeInMs;
        this.verifier = verifier;
    }

    public File getInputProgramFile() {

        return inputProgramFile;
    }

    public void setInputProgramFile(File inputProgramFile) {

        this.inputProgramFile = inputProgramFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public long getTimeInMs() {

        return timeInMs;
    }

    public IVerifier getVerifier() {

        return verifier;
    }

}
