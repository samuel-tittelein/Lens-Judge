package compiler;

import process.ProcessController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * implements a java compiler
 * @author samuel.tittelein
 */
public class JavaCompiler extends AbstractCompiler implements ICompiler {

    /**
     * compile the given file with the command : "javac -c [sourceDirectory] [sourceFileName]"
     * @param sourceFile The source file
     * @return the compiled file
     * @throws IllegalArgumentException if the source file is not compatible
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {

        String sourceDirectory = sourceFile.getParentFile().getAbsolutePath();
        List<String> cmd = List.of("javac", "-d", sourceDirectory, sourceFile.getName());

        processController = new ProcessController();
        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (InterruptedException e) {
            // Restore the interrupt status to ensure it can be handled further up the stack if needed
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        }

        return new File(binName(sourceFile));
    }
}
