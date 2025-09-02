package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.GenericErrorResponseDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import ar.edu.unq.compra_tu_auto.exception.ValidationErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleElementNotFoundException(ElementNotFoundException elementNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(elementNotFoundException.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponseDTO(errors));
    }
}
