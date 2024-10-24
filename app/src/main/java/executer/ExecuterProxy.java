package executer;

import java.io.File;
import java.io.IOException;

import compiler.AbstractCompiler;

public class ExecuterProxy implements IExecuter {
    private IExecuter executer;


    /**
     * Put the output of the execution in a file name {filename}.out
     * @param file A python, java or c file compiled
     * @param input The input file to pass to the script (can be null).
     * @throws IOException If an error occurs while writing the output to the file.
     * @throws InterruptedException If the process is interrupted.
     */
    @Override
    public void execute(File file, File input, long timeInMs) throws IOException, InterruptedException {
        if (file == null)
            throw new RuntimeException("The file is null.");
        try{
        String extension = AbstractCompiler.getExtension(file);
        switch (extension) {
            case "class" -> executer = new JavaExecuter();
            case "py" -> executer = new PythonExecuter();
            default -> throw new IllegalArgumentException("The source file has an unsupported extension.");
        }}
        catch (IllegalArgumentException e){
            executer = new CExecuter();
        }
        executer.execute(file, input, timeInMs);
    }



}
