package verifier;

import exception.RuntimeErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class OrderTolerantVerifier implements IVerifier {

    private final IVerifier decoratedVerifier;

    public OrderTolerantVerifier(IVerifier decoratedVerifier){
        this.decoratedVerifier = decoratedVerifier;
    }

    @Override
    public boolean verify(File expectedFile, File actualFile) throws
            IOException {
        File sortedExpectedFile = createSortedCopy(expectedFile);
        File sortedActualFile = createSortedCopy(actualFile);

        try {
            return decoratedVerifier.verify(sortedExpectedFile, sortedActualFile);
        } catch (RuntimeErrorException e) {
            throw new RuntimeException(e);
        } finally {
            //Need to put a finally to delete the temporary files, Yeah it's weird
            try {
                Files.delete(sortedExpectedFile.toPath());
            } catch (IOException e) {
                System.err.println("Failed to delete sortedExpectedFile: " + e.getMessage());
            }

            try {
                Files.delete(sortedActualFile.toPath());
            } catch (IOException e) {
                System.err.println("Failed to delete sortedActualFile: " + e.getMessage());
            }
        }
    }

    private File createSortedCopy(File originalFile) throws IOException {
        Path originalPath = originalFile.toPath();
        Path sortedPath = Files.createTempFile("sorted_", "_" + originalFile.getName());

        List<String> lines = Files.readAllLines(originalPath);
        Collections.sort(lines);

        Files.write(sortedPath, lines);

        return sortedPath.toFile();
    }

}
