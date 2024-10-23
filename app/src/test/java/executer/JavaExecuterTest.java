package executer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaExecuterTest {

    @Test
    void testJava() throws IOException, InterruptedException {
        IExecuter pythonExecuter = new ExecuterProxy();
        pythonExecuter.execute(getFileFromResources("test.class"), getFileFromResources("test.in"));
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
