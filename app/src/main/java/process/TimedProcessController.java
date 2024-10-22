package process;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimedProcessController implements IProcess {
    private final IProcess decoratedProcess;
    private final long timeoutInMillis;
    private Timer timer;

    public TimedProcessController(IProcess decoratedProcess, long timeoutInMillis) {
        this.decoratedProcess = decoratedProcess;
        this.timeoutInMillis = timeoutInMillis;
    }

    @Override
    public void startProcess(List<String> command) throws IOException {
        // Start the timer to limit execution time
        timer = new Timer(true);
        //This timer will stop the process after the timeout
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Stop the process when the timer triggers
                System.out.println("Time limit exceeded. Stopping the process.");
                stopProcess();
            }
        }, timeoutInMillis);

        // Start the actual process
        decoratedProcess.startProcess(command);
    }

    @Override
    public void stopProcess() {
        decoratedProcess.stopProcess();

        // Cancel the timer to avoid unnecessary triggering
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void waitForCompletion() throws InterruptedException {
        decoratedProcess.waitForCompletion();

        // If the process finishes normally, cancel the timer
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public String getStandardOutput() {
        return decoratedProcess.getStandardOutput();
    }

    @Override
    public void writeStandardOutputToFile(String fileOutputName) throws IOException {
        decoratedProcess.writeStandardOutputToFile(fileOutputName);
    }

    @Override
    public String getErrorOutput() {
        return decoratedProcess.getErrorOutput();
    }

    @Override
    public boolean isProcessFinished() {
        return decoratedProcess.isProcessFinished();
    }
}
