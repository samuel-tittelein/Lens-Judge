package verifier;

import exception.RuntimeErrorException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VerifierTest {

    @Test
    void verifyTest() throws IOException, RuntimeErrorException {
        IVerifier verifier = new Verifier();

        File expectedFile = getFileFromResources("expected.txt");
        File actualCorrectFile = getFileFromResources("actual_correct.txt");
        File actualIncorrectFile = getFileFromResources("actual_incorrect.txt");
        File actualMissingLineFile = getFileFromResources("actual_missing_line.txt");
        File actualMoreLine = getFileFromResources("actual_more_line.txt");

        assertTrue(verifier.verify(expectedFile, actualCorrectFile));
        assertFalse(verifier.verify(expectedFile, actualIncorrectFile));
        assertFalse(verifier.verify(expectedFile, actualMissingLineFile));
        assertFalse(verifier.verify(expectedFile, actualMoreLine));
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
