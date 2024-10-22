package compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CppCompiler extends AbstractCompiler implements ICompiler {

    /**
     * Compiles the given file
     *
     * @param sourceFile The source file
     * @return File the compiled file from the source file
     * @throws IllegalArgumentException
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        ArrayList<String> cmd = new ArrayList<>(
                List.of("g++", "-x", "c++", "-Wall", "-O2", "-static", "-pipe", "-o", "exe", sourceFile.getAbsolutePath())
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
