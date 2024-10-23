package executer;

import process.IProcess;
import process.ProcessController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class CExecuter implements IExecuter {
    private IProcess process;

    public CExecuter() {
        process = new ProcessController();
    }

    @Override
    public void execute(File file, File input) throws IOException, InterruptedException {
        process.startProcess(List.of(file.getAbsolutePath()));
        if (input != null && input.exists()) {
            try (FileInputStream fis = new FileInputStream(input)) {
                process.provideInput(fis);
            }
        }
        process.waitForCompletion();
        process.writeStandardOutputToFile(file.getName() + ".out");
    }

}
