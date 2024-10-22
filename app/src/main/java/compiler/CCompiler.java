package compiler;

import process.IProcess;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CCompiler extends AbstractCompiler implements ICompiler {

    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        ArrayList<String> cmd = new ArrayList<>(
                List.of("gcc", "-x", "c", "-Wall", "-O2", "-static", "-pipe", "-lm", "-o", "exe", sourceFile.getAbsolutePath())
        );
        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new File(this.binName(sourceFile));
    }

}
