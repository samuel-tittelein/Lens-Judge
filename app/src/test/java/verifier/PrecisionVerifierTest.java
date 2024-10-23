package verifier;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrecisionVerifierTest {
    @Test
    void precisionVerifyTestTrue() throws Exception {
        IVerifier verifier = new PrecisionVerifier(0.1);

        File expectedFile = getFileFromResources("expected_precision.txt");
        File actualCorrectFile = getFileFromResources("actual_precision_correct.txt");

        assertTrue(verifier.verify(expectedFile, actualCorrectFile));
    }

    @Test
    void precisionVerifyTestFalse() throws Exception{
        IVerifier verifier = new PrecisionVerifier(0.1);

        File expectedFile = getFileFromResources("expected_precision.txt");
        File actualIncorrectFile = getFileFromResources("actual_precision_incorrect.txt");

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
