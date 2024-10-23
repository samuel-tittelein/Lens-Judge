package compiler;

import process.ProcessController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author samuel_tittelein
 *
 * This class implements ICompiler for C and C++ compilers
 */
public class CCompiler extends AbstractCompiler implements ICompiler {
    protected List<String> args;

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

    /**
     * run the compiler command
     * @param sourceFile The source file
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        //cmd is the list of string that represent the command that will be executed
        List<String> cmd = new ArrayList<>(args);
        String bin = binName(sourceFile);

        cmd.add(bin);
        cmd.add(sourceFile.getAbsolutePath());

        try {
            processController.startProcess(cmd);
            processController.waitForCompletion();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new File(bin);
    }

}
