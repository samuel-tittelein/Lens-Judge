package compiler;

import java.io.File;
import java.util.List;

public class JavaCompiler extends AbstractCompiler implements ICompiler {
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {

        String sourceDirectory = sourceFile.getParentFile().getAbsolutePath();
        List<String> cmd = List.of("javac", "-d", sourceDirectory, sourceFile.getName());

        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new File(binName(sourceFile));
    }
}
