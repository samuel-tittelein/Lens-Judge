package exception;

public class RuntimeErrorException extends Exception{
    public RuntimeErrorException(String errorMessage){
        super(errorMessage);
    }
}
