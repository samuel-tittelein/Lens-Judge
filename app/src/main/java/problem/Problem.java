package problem;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Problem implements Iterable<TestCase>{

    private final List<TestCase> testCasesList;
    private final int timeLimit;
    private final int memoryLimit;
    private final IVerifier verifier;

    public Problem(ProblemBuilder b){
        this.testCasesList = b.getTestCasesList();
        this.timeLimit = b.getTimeLimit();
        this.memoryLimit = b.getMemoryLimit();
        this.verifier = b.getVerifier();
    }

    public List<TestCase> getTestCasesList() {
        return testCasesList;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public IVerifier getVerifier() {
        return verifier;
    }

    @Override
    public Iterator<TestCase> iterator() {
        return testCasesList.iterator();
    }

    @Override
    public void forEach(Consumer<? super TestCase> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<TestCase> spliterator() {
        return Iterable.super.spliterator();
    }
}