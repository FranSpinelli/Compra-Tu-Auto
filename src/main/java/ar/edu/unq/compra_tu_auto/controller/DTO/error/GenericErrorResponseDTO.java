package ar.edu.unq.compra_tu_auto.controller.DTO.error;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String> {
    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
