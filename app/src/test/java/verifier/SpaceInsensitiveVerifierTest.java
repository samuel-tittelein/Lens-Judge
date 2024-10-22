package verifier;
import java.io.File;
import java.net.URL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceInsensitiveVerifierTest {
    @Test
    void spaceInsensitiveVerifyTestTrue() throws Exception {
        IVerifier verifier = new SpaceInsensitiveVerifier(new Verifier());

        File expectedFile = getFileFromResources("expected_space_insensitive.txt");
        File actualCorrectFile = getFileFromResources("actual_space_insensitive_correct.txt");

        assertTrue(verifier.verify(expectedFile, actualCorrectFile));
    }

    @Test
    void spaceInsensitiveVerifyTestFalse() throws Exception {
        IVerifier verifier = new SpaceInsensitiveVerifier(new Verifier());

        File expectedFile = getFileFromResources("expected_space_insensitive.txt");
        File actualIncorrectFile = getFileFromResources("actual_space_insensitive_incorrect.txt");

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
