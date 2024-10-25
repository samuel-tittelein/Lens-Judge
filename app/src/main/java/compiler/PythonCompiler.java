package compiler;

import process.ProcessController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * implements a python compiler
 * @author samuel.tittelein
 */
public class PythonCompiler extends AbstractCompiler implements ICompiler{

    /**
     * Check if the given file has no syntax errors
     * @param sourceFile The source file
     * @return the source file if it has syntax errors
     * @throws IllegalArgumentException else
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        processController = new ProcessController();
        try {
            processController.startProcess(
                    List.of("python3", "-m", "py_compile", sourceFile.getAbsolutePath())
            );
            processController.waitForCompletion();
        } catch (InterruptedException e) {
            // Restore the interrupt status to ensure it can be handled further up the stack if needed
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        }
        return sourceFile;
    }
}
