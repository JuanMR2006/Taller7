package com.hotel.Hotel.dto.request;

/**
 * Contrato común de alta de habitaciones. Al ser una interfaz sellada,
 * el compilador conoce TODOS los tipos concretos permitidos.
 */
public sealed interface CrearHabitacionRequest
        permits CrearHabitacionEstandarRequest, CrearSuitePresidencialRequest {

    String numero();

    double precioPorNoche();

    int capacidadMaxima();
}
