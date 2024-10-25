package verifier;

import exception.RuntimeErrorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Verifier implements IVerifier {
    public boolean verify(File expectedFile, File actualFile) throws RuntimeErrorException {
        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFile));
             BufferedReader actualReader = new BufferedReader(new FileReader(actualFile))) {

            String expectedLine;
            String actualLine;

            while ((expectedLine = expectedReader.readLine()) != null) {
                actualLine = actualReader.readLine();

                if (!expectedLine.equals(actualLine)) {
                    return false;
                }
            }
            String forSonarLint = actualReader.readLine(); //SonarLint doesn't like returning it directly so I have to do that ¯\_(ツ)_/¯
            return forSonarLint == null;
        }
        catch (IOException e) {
            throw new RuntimeErrorException("Error reading file: " + e.getMessage());
        }
    }
}
