package ar.edu.unq.compra_tu_auto.exception;

public class InsufficientStockException extends RuntimeException {

    private static final String ERROR_MESSAGE = "%s with Id: %s is out of stock.";

    public InsufficientStockException(String elementWithoutStock, Integer elementWithoutStockId) {
        super(String.format(ERROR_MESSAGE, elementWithoutStock, elementWithoutStockId));
    }
}
