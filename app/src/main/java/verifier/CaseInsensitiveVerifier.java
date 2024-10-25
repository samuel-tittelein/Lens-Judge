package verifier;

import exception.RuntimeErrorException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CaseInsensitiveVerifier implements IVerifier{

    private final IVerifier decoratedVerifier;

    public CaseInsensitiveVerifier(IVerifier decoratedVerifier){
        this.decoratedVerifier = decoratedVerifier;
    }

    @Override
    public boolean verify(File expectedFile, File actualFile) throws IOException {
        File lowerCaseExpectedFile = createLowerCaseCopy(expectedFile);
        File lowerCaseActualFile = createLowerCaseCopy(actualFile);

        try {
            return decoratedVerifier.verify(lowerCaseExpectedFile, lowerCaseActualFile);
        } catch (RuntimeErrorException e) {
            throw new RuntimeException(e);
        } finally {
            //Need to put a finally to delete the temporary files, Yeah it's weird
            // Deleting files using NIO Files.delete for better error handling
            try {
                Files.delete(lowerCaseExpectedFile.toPath());
            } catch (IOException e) {
                System.err.println("Failed to delete lowerCaseExpectedFile: " + e.getMessage());
            }

            try {
                Files.delete(lowerCaseActualFile.toPath());
            } catch (IOException e) {
                System.err.println("Failed to delete lowerCaseActualFile: " + e.getMessage());
            }
        }
    }

    private File createLowerCaseCopy(File originalFile) throws IOException {
        Path originalPath = originalFile.toPath();
        Path lowerCasePath = Files.createTempFile("lowercase_", "_" + originalFile.getName());

        String content = Files.readString(originalPath);

        String lowerCaseContent = content.toLowerCase();

        Files.writeString(lowerCasePath, lowerCaseContent);

        return lowerCasePath.toFile();
    }


}
