package problem;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Problem implements Iterable<TestCase>{

    public Problem(ProblemBuilder b){
        //TODO
    }

    @Override
    public Iterator<TestCase> iterator() {
        return null;
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
