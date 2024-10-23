package executer;

import java.io.File;
import java.io.IOException;

interface IExecuter {

    /**
     * Put the output of the execution in a file name {filename}.out
     * @param file A python, java or c file compiled
     * @throws IOException
     * @throws InterruptedException
     */
    void execute(File file) throws IOException, InterruptedException;
}
