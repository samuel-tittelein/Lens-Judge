package problem;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import verifier.IVerifier;

public class Problem implements Iterable<TestCase>{

    private final List<TestCase> testCasesList;
    private final IVerifier verifier;

    /**
     * Creates an instance of Problem using the builder and its attributes
     */
    public Problem(ProblemBuilder b){
        this.testCasesList = b.getTestCasesList();
        this.verifier = b.getVerifier();
    }

    /**
     *
     * @return the list of test cases from the Problem
     */
    public List<TestCase> getTestCasesList() {
        return testCasesList;
    }

    /**
     *
     * @return the problem's verifier
     */
    public IVerifier getVerifier() {
        return verifier;
    }

    /**
     *
     * @return the iterator associated with the list of test cases
     */
    @Override
    public Iterator<TestCase> iterator() {
        return testCasesList.iterator();
    }

    /**
     *
     * Implementation of the method forEach of Iterable
     */
    @Override
    public void forEach(Consumer<? super TestCase> action) {
        Iterable.super.forEach(action);
    }

    /**
     *
     * @return the problem as spliterator
     */
    @Override
    public Spliterator<TestCase> spliterator() {
        return Iterable.super.spliterator();
    }
}
