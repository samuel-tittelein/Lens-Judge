package compiler;

import java.io.File;

public class PythonCompiler extends AbstractCompiler implements ICompiler{
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        return sourceFile;
    }
}
