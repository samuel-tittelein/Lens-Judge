package compiler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(exe.getName().equals("test.py"));
    }

    @Test
    void testCCompile() {
        File cfile = new File(getClass().getClassLoader().getResource("test.c").getFile());
        File exe = cCompiler.compile(cfile);
        assertTrue(exe.getName().equals("exe"));
        System.out.println(exe.getAbsolutePath());


    }
}
