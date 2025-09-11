package ar.edu.unq.compra_tu_auto.controller.DTO.error;

import lombok.Getter;

@Getter
public class ErrorResponseDTO<T> {

    private final T message;

    public ErrorResponseDTO(T message) {
        this.message = message;
    }
}
