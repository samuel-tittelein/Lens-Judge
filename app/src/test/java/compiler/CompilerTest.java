package compiler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CompilerTest {
    public static CCompiler cCompiler;
    public static PythonCompiler pythonCompiler;
    public static JavaCompiler javaCompiler;
    public static CCompiler cppCompiler;

    @BeforeAll
    static void setUp() {
        // Initialize compiler objects for testing
        cCompiler = new CCompiler(CCompilerEnum.C);
        pythonCompiler = new PythonCompiler();
        //javaCompiler = new JavaCompiler();
        cppCompiler = new CCompiler(CCompilerEnum.CPP);
    }

    @Test
    void testPythonCompile() {
        File pyfile = new File(getClass().getClassLoader().getResource("test.py").getFile());
        File exe = pythonCompiler.compile(pyfile);

        assertTrue(exe.exists());
        assertEquals(exe.getName(), pyfile.getName());
        assertEquals(exe.getAbsolutePath(), pyfile.getAbsolutePath());
    }

    @Test
    void testCCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.c").getFile());
        String expectedName = "exe";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }

    void testCppCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.cc").getFile());
        String expectedName = "exe";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }

    void testJavaCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.java").getFile());
        String expectedName = "test.java";

        File exe = cCompiler.compile(cfile);

        assertTrue(exe.exists());
        assertEquals(expectedName, exe.getName());
        assertEquals(exe.getAbsolutePath(), exe.getAbsolutePath());
    }
}
