package compiler;

import process.ProcessController;

import java.io.File;
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
     * @throws IllegalArgumentException
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {

        String sourceDirectory = sourceFile.getParentFile().getAbsolutePath();
        List<String> cmd = List.of("javac", "-d", sourceDirectory, sourceFile.getName());

        processController = new ProcessController();
        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return new File(binName(sourceFile));
    }
}
