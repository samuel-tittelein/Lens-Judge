package problem;

import java.io.File;
import java.util.Iterator;

public class TestCase {

    private File inputProgramFile;
    private File inputFile;
    private File outputFile;

    public TestCase(File inputProgramFile, File inputFile, File outputFile) {

        this.inputProgramFile = inputProgramFile;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
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
}
