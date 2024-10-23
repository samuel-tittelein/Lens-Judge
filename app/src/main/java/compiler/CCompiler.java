package compiler;

import process.IProcess;
import process.ProcessController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author samuel_tittelein
 *
 * This class implements ICompiler for C and C++ compilers
 */
public class CCompiler extends AbstractCompiler implements ICompiler {
    protected List<String> args;
    public static final String OUTPUT_FILENAME = "exe";

    /**
     * Construct
     *
     * @param compiler CCompilerEnum instance, may be C or CPP.
     *
     * Creates a new instance of CCompiler but with different args if it's C or C++
     */
    public CCompiler(CCompilerEnum compiler) {
        switch (compiler) {
            case C:
                args = List.of("gcc", "-x", "c", "-Wall", "-O2", "-pipe", "-lm", "-o");
                break;
            case CPP:
                args = List.of("g++", "-x", "c++", "-Wall", "-O2", "-pipe", "-o");
                break;
            default:
                throw new IllegalArgumentException("Unsupported compiler");
        }
        processController = new ProcessController();
    }

    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        List<String> cmd = new ArrayList<>(args);

        cmd.add(OUTPUT_FILENAME);
        cmd.add(sourceFile.getAbsolutePath());

        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new File(getOutputDirectory(sourceFile) + "/" + OUTPUT_FILENAME);
    }

}
