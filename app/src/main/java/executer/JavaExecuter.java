package executer;

import process.IProcess;
import process.ProcessController;
import process.TimedProcessController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class JavaExecuter implements IExecuter {


    /**
     * Put the output of the execution in a file name {filename}.out
     * @param file A python, java or c file compiled
     * @param input The input file to pass to the script (can be null).
     * @throws IOException If an error occurs while writing the output to the file.
     * @throws InterruptedException If the process is interrupted.
     */
    @Override
    public void execute(File file, File input, long timeInMs) throws IOException, InterruptedException {
        IProcess process = new TimedProcessController(new ProcessController(), timeInMs);
        process.startProcess(List.of("java", file.getAbsolutePath()));
        if (input != null && input.exists()) {
            try (FileInputStream fis = new FileInputStream(input)) {
                process.provideInput(fis);
            }
        }
        process.waitForCompletion();
        process.writeStandardOutputToFile(file.getName() + ".out");
    }

}
