package compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaCompiler extends AbstractCompiler implements ICompiler {
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {

        String sourceDirectory = sourceFile.getParentFile().getAbsolutePath();
        ArrayList<String> cmd = new ArrayList<>(
                List.of("javac", "-d", sourceDirectory, sourceFile.getName())
        );
        System.out.println(cmd);
        return null;
    }
}
