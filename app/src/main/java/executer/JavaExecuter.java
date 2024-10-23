package executer;

import process.IProcess;
import process.ProcessController;
import process.TimedProcessController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class JavaExecuter implements IExecuter {

    private IProcess process;

    @Override
    public void execute(File file, File input, long timeInMs) throws IOException, InterruptedException {
        process = new TimedProcessController(new ProcessController(), timeInMs);
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
