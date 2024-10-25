package executer;

import exception.RuntimeErrorException;

import java.io.File;
import java.io.IOException;

public interface IExecuter {

    /**
     * Put the output of the execution in a file name {filename}.out
     * @param file A python, java or c file compiled
     * @param input The input file to pass to the script (can be null).
     * @throws IOException If an error occurs while writing the output to the file.
     * @throws InterruptedException If the process is interrupted.
     */
    void execute(File file, File input, long timeInMs) throws IOException, InterruptedException, RuntimeErrorException;
}
