package problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verifier.IVerifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProblemTest {

    private IVerifier verifier;
    private Problem problem;
    private List<TestCase> testCasesList;

    @BeforeEach
    void setUp() {
        testCasesList = new ArrayList<>();
        testCasesList.add(mock(TestCase.class));
        testCasesList.add(mock(TestCase.class));
        testCasesList.add(mock(TestCase.class));
        verifier = mock(IVerifier.class);

        ProblemBuilder builder = ProblemBuilder.newInstance()
                .withTestCasesList(testCasesList)
                .withTimeLimit(5)
                .withMemoryLimit(12)
                .withVerifier(verifier);

        problem = builder.build();
    }

    @Test
    void problemTest(){
        assertEquals(Problem.class, problem.getClass());
    }

    @Test
    void getTestCasesListClassTest(){
        assertEquals(problem.getTestCasesList().getClass(), testCasesList.getClass());
    }

    @Test
    void getTestCasesListValueTest(){
        assertEquals(problem.getTestCasesList(), testCasesList);
    }

    @Test
    void getTimeLimitTest(){
        assertEquals(5, problem.getTimeLimit());
        assertNotEquals(150, problem.getTimeLimit());
    }

    @Test
    void getMemoryLimitTest(){
        assertEquals(12, problem.getMemoryLimit());
        assertNotEquals(150, problem.getMemoryLimit());
    }

    @Test
    void getVerifierValueTest(){
        assertEquals(problem.getVerifier(), verifier);
    }

    @Test
    void testIterator() {
        Iterator<TestCase> iterator = problem.iterator();

        for (TestCase testCase : testCasesList) {
            assertTrue(iterator.hasNext(), "Iterator should have next element.");
            assertEquals(testCase, iterator.next(), "Iterator should return the correct TestCase object.");
        }

        assertFalse(iterator.hasNext(), "Iterator should not have more elements.");
    }

    @Test
    void testForEach() {
        int[] counter = {0};

        problem.forEach(testCase -> counter[0]++);

        assertEquals(testCasesList.size(), counter[0], "forEach should iterate over all elements.");
    }

    @Test
    void testSpliterator() {
        Spliterator<TestCase> spliterator = problem.spliterator();

        int[] counter = {0};

        spliterator.forEachRemaining(testCase -> counter[0]++);

        assertEquals(testCasesList.size(), counter[0], "Spliterator should iterate over all elements.");
    }
}
