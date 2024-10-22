package process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimedProcessControllerTest {
    private IProcess processController;

    @BeforeEach
    void setUp() {
        processController = new TimedProcessController(new ProcessController(), 1000);  // 1 second timeout
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
    void testTimedOutProcess() throws IOException {
        List<String> command = List.of("sleep", "5");
        processController.startProcess(command);

        assertThrows(RuntimeException.class, () -> processController.waitForCompletion());
    }
}
