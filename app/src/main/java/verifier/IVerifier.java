package verifier;

import java.io.File;
import java.io.IOException;

public interface IVerifier {
    boolean verify(File expectedFile, File actualFile) throws IOException;
}
