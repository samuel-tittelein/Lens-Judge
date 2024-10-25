package executer;

import exception.RuntimeErrorException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CExecuterTest {

    @Test
    void testC() throws IOException, InterruptedException, RuntimeErrorException {
        IExecuter pythonExecuter = new ExecuterProxy();
        pythonExecuter.execute(getFileFromResources("exe"), getFileFromResources("test.in"), 1000);
        File file = new File("exe.out");
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
