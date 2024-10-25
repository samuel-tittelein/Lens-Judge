package verifier;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTolerantVerifierTest {
    @Test
    void orderTolerantVerifyTestTrue() throws Exception {
        IVerifier verifier = new OrderTolerantVerifier(new Verifier());

        File expectedFile = getFileFromResources("expected_order_tolerant.txt");
        File actualCorrectFile = getFileFromResources("actual_order_tolerant_correct.txt");

        assertTrue(verifier.verify(expectedFile, actualCorrectFile));
    }

    @Test
    void orderTolerantVerifyTestFalse() throws Exception {
        IVerifier verifier = new OrderTolerantVerifier(new Verifier());

        File expectedFile = getFileFromResources("expected_order_tolerant.txt");
        File actualIncorrectFile = getFileFromResources("actual_order_tolerant_incorrect.txt");

        assertFalse(verifier.verify(expectedFile, actualIncorrectFile));
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
