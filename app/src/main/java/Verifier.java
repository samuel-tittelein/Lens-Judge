import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Verifier {
    public boolean verify(File expectedFile, File actualFile) throws IOException {
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
            return actualReader.readLine() == null;
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }
}
