package java.process;

import java.io.IOException;
import java.util.List;

public interface IProcess {

    /**
     * Start the process with the given command.
     *
     * @param command The command to execute.
     */
    void startProcess(List<String> command) throws IOException;

    /**
     * Stop the current process
     */
    void stopProcess();

    /**
     * Wait for the end of the process.
     *
     * @throws InterruptedException If the process is interrupted.
     */
    void waitForCompletion() throws InterruptedException;

    /**
     * get the standard Output of the process as a string.
     *
     * @return The standard output.
     * @throws IOException If an error occurs while reading the standard output.
     */
    String getStandardOutput() throws IOException;

    /**
     * get the error Output of the process as a string
     *
     * @return The error output.
     * @throws IOException If an error occurs while reading the error output.
     */
    String getErrorOutput() throws IOException;

    /**
     * Returns if the process is finished.
     *
     * @return returns true if the process is finished else false.
     */
    boolean isProcessFinished();

}
