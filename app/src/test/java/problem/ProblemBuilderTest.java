package problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verifier.IVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProblemBuilderTest {
    private IVerifier verifier;
    private List<TestCase> testCasesList;
    ProblemBuilder builder;

    @BeforeEach
    void setUp() {
        testCasesList = new ArrayList<>();
        testCasesList.add(mock(TestCase.class));
        testCasesList.add(mock(TestCase.class));
        testCasesList.add(mock(TestCase.class));
        verifier = mock(IVerifier.class);

        builder = ProblemBuilder.newInstance();
    }

    @Test
    void problemTest(){
        assertEquals(ProblemBuilder.class, builder.getClass());
    }

    @Test
    void withTestCasesListTest(){
        builder.withTestCasesList(testCasesList);
        assertNotNull(builder.getTestCasesList());
    }

    @Test
    void getTestCasesListClassTest(){
        builder.withTestCasesList(testCasesList);
        assertEquals(testCasesList.getClass(), builder.getTestCasesList().getClass());
    }

    @Test
    void getTestCasesListValueTest(){
        builder.withTestCasesList(testCasesList);
        assertEquals(testCasesList, builder.getTestCasesList());
    }

    @Test
    void withTimeLimitTest(){
        int timeLimit = 5;
        builder.withTimeLimit(timeLimit);
        assertEquals(timeLimit, builder.getTimeLimit());
    }

    @Test
    void getTimeLimitClassTest(){
        int timeLimit = 12;
        builder.withTimeLimit(timeLimit);
        assertEquals(timeLimit, builder.getTimeLimit());
    }

    @Test
    void withMemoryLimitTest(){
        int memoryLimit = 120;
        builder.withMemoryLimit(memoryLimit);
        assertEquals(memoryLimit, builder.getMemoryLimit());
    }

    @Test
    void getMemoryLimitValueTest(){
        int memoryLimit = 150;
        builder.withMemoryLimit(memoryLimit);
        assertEquals(memoryLimit, builder.getMemoryLimit());
    }

    @Test
    void withVerifierTest(){
        verifier = mock(IVerifier.class);
        builder.withVerifier(verifier);
        assertNotNull(builder.getVerifier().getClass());
    }

    @Test
    void getVerifierValueTest(){
        verifier = mock(IVerifier.class);
        builder.withVerifier(verifier);
        assertEquals(verifier, builder.getVerifier());
    }

    @Test
    void buildTest(){
        Problem problem = builder.build();
        assertNotNull(problem);
    }

    @Test
    void buildClassTest(){
        Problem problem = builder.build();
        assertEquals(Problem.class, problem.getClass());
    }
}
