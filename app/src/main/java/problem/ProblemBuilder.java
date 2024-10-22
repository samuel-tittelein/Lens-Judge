package problem;

import java.util.List;

public class ProblemBuilder {

    private List<TestCase> testCasesList;
    private int timeLimit;
    private int memoryLimit;
    private IVerifier verifier;

    /**
     * Creates an instance of ProblemBuilder
     */
    private ProblemBuilder(){
        super();
    }

    /**
     *
     * @return the instance of ProblemBuilder, with its testCasesList initialized
     */
    public ProblemBuilder withTestCasesList(List<TestCase> testCasesList){
        this.testCasesList = testCasesList;
        return this;
    }

    /**
     *
     * @return the list of test cases from the ProblemBuilder
     */
    protected List<TestCase> getTestCasesList(){
        return testCasesList;
    }

    /**
     *
     * @return the instance of ProblemBuilder, with its timeLimit initialized
     */
    public ProblemBuilder withTimeLimit(int timeLimit){
        this.timeLimit = timeLimit;
        return this;
    }

    /**
     *
     * @return the time limit of the problem's execution in seconds
     */
    protected int getTimeLimit(){
        return timeLimit;
    }

    /**
     *
     * @return the instance of ProblemBuilder, with its memoryLimit initialized
     */
    public ProblemBuilder withMemoryLimit(int memoryLimit){
        this.memoryLimit = memoryLimit;
        return this;
    }

    /**
     *
     * @return the memory limit of the problem's execution in bytes
     */
    protected int getMemoryLimit(){
        return memoryLimit;
    }

    /**
     *
     * @return the instance of ProblemBuilder, with its verifier initialized
     */
    public ProblemBuilder withVerifier(IVerifier verifier){
        this.verifier = verifier;
        return this;
    }

    /**
     *
     * @return the problem's verifier
     */
    protected IVerifier getVerifier(){
        return verifier;
    }

    /**
     *
     * @return a new instance of ProblemBuilder
     */
    public ProblemBuilder newInstance(){
        return new ProblemBuilder();
    }

    /**
     *
     * @return a new instance of a problem
     */
    public Problem build(){
        return new Problem(this);
    }
}
