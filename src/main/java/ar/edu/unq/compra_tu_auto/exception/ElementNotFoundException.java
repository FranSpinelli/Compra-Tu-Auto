package ar.edu.unq.compra_tu_auto.exception;

public class ElementNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "%s with Id: %s not found";

    public ElementNotFoundException(String elementNotFoundName, String elementNotFoundId) {
        super(String.format(ERROR_MESSAGE, elementNotFoundName, elementNotFoundId));
    }
}
