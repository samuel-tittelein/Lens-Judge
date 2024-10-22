package problem;

public class ProblemBuilder {

    private List<TestCase> testCasesList;
    private int timeLimit;
    private int memoryLimit;
    private IVerifier verifier;

    private ProblemBuilder(){
        super();
    }

    public ProblemBuilder withTestCasesList(List<TestCase> testCasesList){
        this.testCasesList = testCasesList;
        return this;
    }

    protected List<TestCase> getTestCasesList(){
        return testCasesList;
    }

    public ProblemBuilder withTimeLimit(int timeLimit){
        this.timeLimit = timeLimit;
        return this;
    }

    protected int getTimeLimit(){
        return timeLimit;
    }

    public ProblemBuilder withMemoryLimit(int memoryLimit){
        this.memoryLimit = memoryLimit;
        return this;
    }

    protected int getMemoryLimit(){
        return memoryLimit;
    }

    public ProblemBuilder withVerifier(IVerifier verifier){
        this.verifier = verifier;
        return this;
    }

    protected IVerifier getVerifier(){
        return verifier;
    }

    public ProblemBuilder newInstance(){
        return new ProblemBuilder();
    }

    public Problem build(){
        return new Problem(this);
    }
}
