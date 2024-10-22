package compiler;

import process.IProcess;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CCompiler extends AbstractCompiler implements ICompiler {
    protected List<String> args;

    public CCompiler(CCompilerEnum compiler) {
        switch (compiler) {
            case C:
                args = List.of("gcc", "-x", "c", "-Wall", "-O2", "-pipe", "-lm", "-o", "exe");
                break;
            case CPP:
                args = List.of("g++", "-x", "c++", "-Wall", "-O2", "-pipe", "-o", "exe");
                break;
            default:
                throw new IllegalArgumentException("Unsupported compiler");
        }
    }

    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        List<String> cmd = new ArrayList<>(args);
        cmd.add(sourceFile.getAbsolutePath());
        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new File(this.binName(sourceFile));
    }

}
