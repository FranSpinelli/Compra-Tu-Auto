package ar.edu.unq.compra_tu_auto.controller.DTO.error;

import java.util.Map;

public class ValidationErrorResponseDTO extends ErrorResponseDTO<Map<String, String>> {
    public ValidationErrorResponseDTO(Map<String, String> errors) {
        super(errors);
    }
}
