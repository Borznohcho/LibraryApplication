package entities;


/**
 * Exception levée lorsqu’un livre ne peut pas être emprunté.
 */
public class NotAvailableException extends RuntimeException {
    public NotAvailableException() {
        super();
    }

    public NotAvailableException(String message) {
        super(message);
    }
}
