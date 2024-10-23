package compiler;

import java.io.File;
import java.util.List;

public class PythonCompiler extends AbstractCompiler implements ICompiler{
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        try {
            processController.startProcess(
                    List.of("python3", "-m", "py_compile", sourceFile.getAbsolutePath())
            );
            processController.waitForCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sourceFile;
    }
}
