package executer;

import exception.RuntimeErrorException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaExecuterTest {

    //@Test
    //Cannot be executed since the javac command doesn't exist on our computers
    void testJava() throws IOException, InterruptedException, RuntimeErrorException {
        IExecuter pythonExecuter = new ExecuterProxy();
        pythonExecuter.execute(getFileFromResources("test.class"), getFileFromResources("test.in"),1000 );
        File file = new File("test.class.out");
        assertTrue(file.exists());
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        return new File(resource.getFile());
    }
}
