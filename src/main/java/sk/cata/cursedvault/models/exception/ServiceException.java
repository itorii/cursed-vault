package sk.cata.cursedvault.models.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

    private HttpStatus httpStatus  = HttpStatus.INTERNAL_SERVER_ERROR;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message,cause);
    }

}
