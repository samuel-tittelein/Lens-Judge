package executer;

import java.io.File;
import java.io.IOException;

public interface IExecuter {

    /**
     * Put the output of the execution in a file name {filename}.out
     * @param file A python, java or c file compiled
     * @param input The input file to pass to the script (can be null).
     * @throws IOException
     * @throws InterruptedException
     */
    void execute(File file, File input) throws IOException, InterruptedException;
}
