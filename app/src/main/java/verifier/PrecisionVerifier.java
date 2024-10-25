package verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PrecisionVerifier implements IVerifier {

    /**
     * Solution heavily inspired by copilot since the last implementation didn't work
     */
    private final double tolerance;

    public PrecisionVerifier(double tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public boolean verify(File expectedFile, File actualFile) throws IOException {
        try (BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFile));
             BufferedReader actualReader = new BufferedReader(new FileReader(actualFile))) {

            String expectedLine;
            String actualLine;

            while ((expectedLine = expectedReader.readLine()) != null) {
                actualLine = actualReader.readLine();

                if (actualLine == null) {
                    return false;  // Different outputs, actual file is shorter
                }

                // Each token is a string separated by space
                String[] expectedTokens = expectedLine.trim().split("\\s+");
                String[] actualTokens = actualLine.trim().split("\\s+");

                if (expectedTokens.length != actualTokens.length) {
                    return false;  // Different number of elements in lines
                }

                for (int i = 0; i < expectedTokens.length; i++) {
                    if (extracted(expectedTokens, i, actualTokens))
                        return false;  // Difference exceeds tolerance
                }
            }

            String forSonarLint = actualReader.readLine();
            return forSonarLint == null;
        }
    }

    private boolean extracted(String[] expectedTokens, int i,
            String[] actualTokens) {

        try {
            double expectedValue = Double.parseDouble(expectedTokens[i]);
            double actualValue = Double.parseDouble(actualTokens[i]);

            if (Math.abs(expectedValue - actualValue) > tolerance) {
                return true;
            }

        } catch (NumberFormatException ignored) {
            // Handle non-numeric tokens by direct comparison
            if (!expectedTokens[i].equals(actualTokens[i])) {
                return true;
            }
        }
        return false;
    }

}
