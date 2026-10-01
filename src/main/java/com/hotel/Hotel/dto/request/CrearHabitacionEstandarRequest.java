package com.hotel.Hotel.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CrearHabitacionEstandarRequest(
        @NotBlank @Size(max = 20) String numero,
        @Positive double precioPorNoche,
        @Min(1) int capacidadMaxima,
        @Min(1) int camasIndividuales) implements CrearHabitacionRequest {
}
