import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifierTest {
@Test
    public void verifyTest() throws IOException {
    Verifier verifier = new Verifier();
    File expectedFile = new File("expected.txt");
    File actualCorrectFile = new File("actual_correct.txt");
    File actualIncorrectFile = new File("actual_incorrect.txt");

    assertTrue(verifier.verify(expectedFile, actualCorrectFile));
    assertFalse(verifier.verify(expectedFile, actualIncorrectFile));

}
}