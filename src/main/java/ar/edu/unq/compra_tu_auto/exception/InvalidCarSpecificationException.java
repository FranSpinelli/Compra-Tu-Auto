package ar.edu.unq.compra_tu_auto.exception;

public class InvalidCarSpecificationException extends RuntimeException {

    private static final String ERROR_MESSAGE = "%s: %s is not valid for car model with Id: %s";

    public InvalidCarSpecificationException(String fieldName, String fieldValue, String carModelId) {
        super(String.format(ERROR_MESSAGE, fieldName, fieldValue, carModelId));
    }
}
