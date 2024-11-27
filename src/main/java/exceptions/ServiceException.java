package exceptions;

public class ServiceException extends Error{
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
