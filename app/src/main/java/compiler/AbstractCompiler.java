package compiler;

import process.IProcess;

import java.io.File;
import java.util.List;

/**
 * This class implements the interface ICompiler
 *
 * I apologize to 3rd grade student for the bad code quality
 */
public class AbstractCompiler implements ICompiler {
    private final IllegalArgumentException wrongExtension = new IllegalArgumentException(
            "The source file has an unsupported extension.");
    protected IProcess processController;

    protected String getExtension(File sourceFile) throws IllegalArgumentException {
        String fileName = sourceFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new IllegalArgumentException("The source file has no extension");
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    /**
     * check the compatibility of the source file extension
     *
     * @param sourceFile The source file
     *
     * @return boolean indicating if the extension is supported
     */
    @Override
    public boolean isCompatible(File sourceFile) throws IllegalArgumentException {
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("The source file does not exist.");
        }
        String extension = getExtension(sourceFile);
        return switch (extension) {
            case "java", "c", "cxx", "cc", "cpp", "py" -> true;
            default -> false;
        };
    }

    /**
     * Deduce from the name of the source file the name of the compiled binary.
     *
     * @param sourceFile The source file
     *
     * @return String the name of the binary file
     */
    @Override
    public String binName(File sourceFile) throws IllegalArgumentException {
        String fileName = sourceFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new IllegalArgumentException("The source file has no extension");
        }
        String binName = fileName.substring(0, dotIndex-1).toLowerCase();
        String extension = fileName.substring(dotIndex+1).toLowerCase();
        return switch (extension) {
            case "java" -> binName + ".class";
            case "c", "cxx", "cc", "cpp" -> binName + ".o";
            case "py" -> binName + ".py";
            default -> throw wrongExtension;
        };
    }

    /**
     * run the compiler command
     *
     * @param sourceFile The source file
     *
     * @return File the compiled binary
     */
    @Override
    public File compile(File sourceFile) throws IllegalArgumentException {
        if (!isCompatible(sourceFile)) {
            throw wrongExtension;
        }
        String extension = getExtension(sourceFile);
        ICompiler compiler = switch (extension) {
            case "java" -> new JavaCompiler();
            case "c" -> new CCompiler(CCompilerEnum.C);
            case "cxx", "cc", "cpp" -> new CCompiler(CCompilerEnum.CPP);
            case "py" -> new PythonCompiler();
            default -> throw wrongExtension;
        };
        return compiler.compile(sourceFile);
    }

}
