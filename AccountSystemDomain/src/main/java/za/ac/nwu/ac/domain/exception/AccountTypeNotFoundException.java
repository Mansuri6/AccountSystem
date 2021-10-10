package za.ac.nwu.ac.domain.exception;

public class AccountTypeNotFoundException extends RuntimeException{

    public AccountTypeNotFoundException(String message) {
        super(message);
    }
}
