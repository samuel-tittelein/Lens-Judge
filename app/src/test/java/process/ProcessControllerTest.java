package process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProcessControllerTest {

    private IProcess processController;

    @BeforeEach
    void setUp() {
        processController = new ProcessController();
    }

    @Test
    void testStartProcess() throws IOException {
        List<String> command = List.of("echo", "Hello, World!");
        processController.startProcess(command);

        assertNotNull(processController.getStandardOutput());
        assertNotNull(processController.getErrorOutput());
        assertFalse(processController.isProcessFinished());
    }

    @Test
    void testGetStandardOutput() throws IOException, InterruptedException {
        List<String> command = List.of("echo", "test output");
        processController.startProcess(command);
        processController.waitForCompletion();

        String output = processController.getStandardOutput();
        assertEquals("test output\n", output);
    }

    @Test
    void testGetErrorOutput() throws IOException, InterruptedException {
        // This command creates an error output
        List<String> command = List.of("bash", "-c", ">&2 echo 'error'");
        processController.startProcess(command);
        processController.waitForCompletion();

        String errorOutput = processController.getErrorOutput();
        assertEquals("error\n", errorOutput);
    }

    @Test
    void testIsProcessFinished() throws IOException, InterruptedException {
        List<String> command = List.of("sleep", "5");
        processController.startProcess(command);

        assertFalse(processController.isProcessFinished());

        processController.waitForCompletion();

        assertTrue(processController.isProcessFinished());
    }
}
