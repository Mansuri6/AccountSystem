package za.ac.nwu.ac.domain.exception;

public class AccountTypeCreationException extends RuntimeException{
    public AccountTypeCreationException() {
    }

    public AccountTypeCreationException(String message) {
        super(message);
    }

    public AccountTypeCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountTypeCreationException(Throwable cause) {
        super(cause);
    }

    public AccountTypeCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
