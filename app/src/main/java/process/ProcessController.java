package process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessController implements IProcess {

    private Process process;
    private StringBuilder standardOutput;
    private StringBuilder errorOutput;

    @Override
    public void startProcess(List<String> command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        process = processBuilder.start();
        standardOutput = new StringBuilder();
        errorOutput = new StringBuilder();
    }

    @Override
    public void stopProcess() {
        if (process != null && process.isAlive()) {
            process.destroy();
        }
    }

    @Override
    public void waitForCompletion() throws InterruptedException {
        if (process != null) {
            // Wait for the process to complete
            process.waitFor();

            // After the process finishes, capture the outputs
            try{
                captureOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void captureOutput() throws IOException {
        // Capture standard output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                standardOutput.append(line).append(System.lineSeparator());
            }
        }

        // Capture error output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                errorOutput.append(line).append(System.lineSeparator());
            }
        }
    }

    @Override
    public String getStandardOutput() {
        return standardOutput.toString();
    }

    @Override
    public void writeStandardOutputToFile(String fileOutputName) throws IOException {
        File file = new File(fileOutputName);
        if (!file.exists()) {
            boolean err = file.createNewFile();
            if (!err) {
                throw new IOException("Failed to create the file");
            }
        }
        // Write the standard output to the file
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(file)) {
            fileWriter.write(standardOutput.toString());
        }
    }

    @Override
    public String getErrorOutput() {
        return errorOutput.toString();
    }

    @Override
    public boolean isProcessFinished() {
        return process != null && !process.isAlive();
    }
}
