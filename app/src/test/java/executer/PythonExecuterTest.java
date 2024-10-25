package executer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PythonExecuterTest {

    @Test
    void testPython() throws IOException, InterruptedException {
        IExecuter pythonExecuter = new ExecuterProxy();
        pythonExecuter.execute(getFileFromResources("test.py"), getFileFromResources("test.in"), 1000);
        File file = new File("test.py.out");
        assertTrue(file.exists());
    }

    @Test
    void testPythonTimedOut() throws IOException {
        IExecuter pythonExecuter = new ExecuterProxy();

        try {
            pythonExecuter.execute(getFileFromResources("test.py"), getFileFromResources("test.in"), 1000);
        } catch (InterruptedException e) {
            assertTrue(true);
        }

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
