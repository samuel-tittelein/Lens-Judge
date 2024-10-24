package verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpaceInsensitiveVerifier implements IVerifier {

    private final IVerifier decoratedVerifier;

    public SpaceInsensitiveVerifier(IVerifier decoratedVerifier){
        this.decoratedVerifier = decoratedVerifier;
    }

    @Override
    public boolean verify(File expectedFile, File actualFile) throws IOException {
        File spaceRemovedExpectedFile = createSpaceRemovedCopy(expectedFile);
        File spaceRemovedActualFile = createSpaceRemovedCopy(actualFile);

        try {
            return decoratedVerifier.verify(spaceRemovedExpectedFile, spaceRemovedActualFile);
        } finally {
            //Need to put a finally to delete the temporary files, Yeah it's weird
            spaceRemovedExpectedFile.delete();
            spaceRemovedActualFile.delete();
        }
    }

    private File createSpaceRemovedCopy(File originalFile) throws IOException {
        Path originalPath = originalFile.toPath();
        Path spaceRemovedPath = Files.createTempFile("spaceRemoved_", "_" + originalFile.getName());

        String content = Files.readString(originalPath);

        String spaceRemovedContent = content.replaceAll("\\s", "");

        Files.writeString(spaceRemovedPath, spaceRemovedContent);

        return spaceRemovedPath.toFile();
    }

}
