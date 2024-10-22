package compiler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        javaCompiler = new JavaCompiler();
        cppCompiler = new CCompiler(CCompilerEnum.CPP);
    }

    @Test
    void testCompile() {
        // TODO: Write test cases for Compiler classes
    }
}
