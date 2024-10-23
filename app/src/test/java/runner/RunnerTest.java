package runner;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RunnerTest {
    @Test
    void testRunnerPython() throws IOException, InterruptedException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources("test.py"))
                .build();
        assertTrue(runner.verifyProgram());
    }

    @Test
    void testRunnerC() throws IOException, InterruptedException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources("test.c"))
                .build();
        assertTrue(runner.verifyProgram());
    }

    @Test
    void testRunnerCPP() throws IOException, InterruptedException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources("test.cc"))
                .build();
        assertTrue(runner.verifyProgram());
    }

    //@Test
    void testRunnerJava() throws IOException, InterruptedException {
        RunnerBuilder builder = new RunnerBuilder();
        Runner runner = builder.withExpectedOutputFile(getFileFromResources("test.ans"))
                .withInputFile(getFileFromResources("test.in"))
                .withSourceFile(getFileFromResources("test.java"))
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
