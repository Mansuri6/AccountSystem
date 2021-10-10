package za.ac.nwu.ac.domain.exception;

public class AccountTransactionCreationException extends RuntimeException{

    public AccountTransactionCreationException() {
    }

    public AccountTransactionCreationException(String message) {
        super(message);
    }

    public AccountTransactionCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountTransactionCreationException(Throwable cause) {
        super(cause);
    }

    public AccountTransactionCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
