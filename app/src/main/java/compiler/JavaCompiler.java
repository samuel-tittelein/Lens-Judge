package compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaCompiler extends AbstractCompiler implements ICompiler {
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {

        String sourceDirectory = sourceFile.getAbsolutePath();
        ArrayList<String> cmd = new ArrayList<>(
                List.of("javac", "-d", sourceDirectory, sourceFile.getName())
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
