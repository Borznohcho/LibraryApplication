package entities;

public class NotAvailableException extends RuntimeException {
    
    public NotAvailableException() {
	super();
    }
    
    public NotAvailableException(String message) {
	super(message);
	
    }

}
