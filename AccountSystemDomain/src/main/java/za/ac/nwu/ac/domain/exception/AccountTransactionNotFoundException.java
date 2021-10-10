package za.ac.nwu.ac.domain.exception;

public class AccountTransactionNotFoundException extends RuntimeException{

    public AccountTransactionNotFoundException(String message) {
        super(message);
    }
}
