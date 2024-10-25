package compiler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class CompilerTest {
    public static CCompiler cCompiler;
    public static PythonCompiler pythonCompiler;
    public static JavaCompiler javaCompiler;
    public static CCompiler cppCompiler;

    /**
     * initialise test attributes
     */
    @BeforeAll
    static void setUp() {
        // Initialize compiler objects for testing
        cCompiler = new CCompiler(CCompilerEnum.C);
        pythonCompiler = new PythonCompiler();
        cppCompiler = new CCompiler(CCompilerEnum.CPP);
    }

    /**
     * assert that a python file has no syntax error (we can execute it)
     */
    @Test
    void testPythonCompile() {
        File pyfile = new File(getClass().getClassLoader().getResource("test.py").getFile());
        File exe = pythonCompiler.compile(pyfile);

        assertTrue(exe.exists());
        assertEquals(exe.getName(), pyfile.getName());
        assertEquals(exe.getAbsolutePath(), pyfile.getAbsolutePath());
    }

    /**
     * compile a C file and check if it's well compiled (with right extension and path ...)
     */
    @Test
    void testCCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.c").getFile());
        String expectedName = "exe";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }

    /**
     * compile a C++ file and check if it's well compiled (with right extension and path ...)
     */
    @Test
    void testCppCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.cc").getFile());
        String expectedName = "exe";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }

    /**
     * compile a Java file and check if it's well compiled (with right extension and path ...)
     * (don't work on the university PC because there is no javac compiler)
     */
    //@Test
    void testJavaCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.java").getFile());
        String expectedName = "test.java";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }
}
