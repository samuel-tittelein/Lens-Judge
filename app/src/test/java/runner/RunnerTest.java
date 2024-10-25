package runner;

import exception.RuntimeErrorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import verifier.Verifier;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RunnerTest {

    @ParameterizedTest
    @ValueSource(strings = {"test.py", "test.c", "test.cc"})
    void testRunners(String arg) throws IOException, InterruptedException, RuntimeErrorException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources(arg))
                .withVerifier(new Verifier())
                .build();
        assertTrue(runner.verifyProgram());
    }

    //@Test
    //This test doesn't work on our computers because the javac command doesn't exist
    void testRunnerJava() throws IOException, InterruptedException, RuntimeErrorException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources("test.java"))
                .withVerifier(new Verifier())
                .build();
        assertTrue(runner.verifyProgram());
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
