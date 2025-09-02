package ar.edu.unq.compra_tu_auto.controller.DTO;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String> {
    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
