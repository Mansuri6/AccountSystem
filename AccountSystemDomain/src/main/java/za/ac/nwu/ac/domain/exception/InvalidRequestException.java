package za.ac.nwu.ac.domain.exception;

public class InvalidRequestException extends RuntimeException{
    private String msg;

    public InvalidRequestException(String message) {
        super(message);
    }

}
