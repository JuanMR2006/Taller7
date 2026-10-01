package com.hotel.Hotel.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/** Ambos campos son opcionales: null = "no cambiar". */
public record ActualizarClienteRequest(
        @Size(max = 100) String nombre,
        @Email @Size(max = 120) String email) {
}
