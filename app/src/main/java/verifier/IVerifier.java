package verifier;

import exception.RuntimeErrorException;

import java.io.File;
import java.io.IOException;

public interface IVerifier {

    /**
     * Verify if the expected file and the actual file are the same
     * @param expectedFile the expected file
     * @param actualFile the actual file
     * @return a boolean that indicates if the files are the same
     * @throws IOException if an error occurs while reading the files
     */
    boolean verify(File expectedFile, File actualFile) throws IOException, RuntimeErrorException;
}
