package entities;


/**
 * Exception levée lorsqu’un choix utilisateur est invalide dans un menu.
 */
public class RangeException extends RuntimeException {
    public RangeException() {
        super();
    }

    public RangeException(String message) {
        super(message);
    }
}
