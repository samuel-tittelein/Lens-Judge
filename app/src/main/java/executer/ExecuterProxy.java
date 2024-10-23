package executer;

import java.io.File;
import java.io.IOException;

import compiler.AbstractCompiler;

public class ExecuterProxy implements IExecuter {
    private IExecuter executer;

    @Override
    public void execute(File file) throws IOException, InterruptedException {
        if (file == null)
            throw new RuntimeException("The file is null.");
        String extension = AbstractCompiler.getExtension(file);
        switch (extension) {
            case "class" -> executer = new JavaExecuter();
            case "" -> executer = new CExecuter();
            case "py" -> executer = new PythonExecuter();
            default -> throw new IllegalArgumentException("The source file has an unsupported extension.");
        }
        executer.execute(file);
    }



}
