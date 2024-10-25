package problem;

import verifier.IVerifier;

import java.util.List;

public class ProblemBuilder {

    private List<TestCase> testCasesList;
    private IVerifier verifier;

    /**
     * Creates an instance of ProblemBuilder
     */
    public ProblemBuilder(){
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
    public static ProblemBuilder newInstance(){
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
