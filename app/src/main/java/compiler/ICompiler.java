package compiler;

import java.io.File;

public interface ICompiler {
    /**
     * check the compatibility of the source file extension
     * @param sourceFile The source file
     * @return boolean indicating if the extention is supported
     */
    boolean isCompatible(File sourceFile) throws IllegalArgumentException;

    /**
     * Deduce from the name of the source file the name of the compiled binary.
     * @param sourceFile The source file
     * @return String the name of the binary file
     */
    String binName(File sourceFile) throws IllegalArgumentException;

    /**
     * run the compiler command
     * @param sourceFile The source file
     * @return File the compiled binary
     */
    File compile(File sourceFile) throws IllegalArgumentException;
}
