package ar.edu.unq.compra_tu_auto.controller;

import ar.edu.unq.compra_tu_auto.controller.DTO.GenericErrorResponseDTO;
import ar.edu.unq.compra_tu_auto.exception.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleElementNotFoundException(ElementNotFoundException elementNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(elementNotFoundException.getMessage()));
    }

}
