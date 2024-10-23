package executer;

import process.IProcess;
import process.ProcessController;
import process.TimedProcessController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PythonExecuter implements IExecuter {

    private IProcess process;


    /**
     * Executes a Python script and optionally passes an input file.
     *
     * @param file  The Python script to execute.
     * @param input The input file to pass to the Python script (can be null).
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void execute(File file, File input, long timeInMs) throws IOException, InterruptedException {
        process = new TimedProcessController(new ProcessController(), timeInMs);

        process.startProcess(List.of("python3", file.getAbsolutePath()));
        if (input != null && input.exists()) {
           try (FileInputStream fis = new FileInputStream(input)) {
                process.provideInput(fis);
           }
        }
        process.waitForCompletion();
        process.writeStandardOutputToFile(file.getName() + ".out");
    }
}
