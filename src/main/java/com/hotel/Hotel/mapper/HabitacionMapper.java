package com.hotel.Hotel.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hotel.Hotel.domain.Habitacion;
import com.hotel.Hotel.domain.HabitacionEstandar;
import com.hotel.Hotel.domain.SuitePresidencial;
import com.hotel.Hotel.dto.request.CrearHabitacionEstandarRequest;
import com.hotel.Hotel.dto.request.CrearHabitacionRequest;
import com.hotel.Hotel.dto.request.CrearSuitePresidencialRequest;
import com.hotel.Hotel.dto.response.HabitacionEstandarResponse;
import com.hotel.Hotel.dto.response.HabitacionResponse;
import com.hotel.Hotel.dto.response.SuitePresidencialResponse;

@Mapper(componentModel = "spring")
public interface HabitacionMapper {

    // ---------- Entidad -> Response (polimórfico en tiempo de ejecución) ----------

    default HabitacionResponse toResponse(Habitacion habitacion) {
        if (habitacion instanceof HabitacionEstandar estandar) {
            return toEstandarResponse(estandar);
        }
        if (habitacion instanceof SuitePresidencial suite) {
            return toSuiteResponse(suite);
        }
        throw new IllegalArgumentException("Tipo de habitación no soportado: " + habitacion);
    }

    HabitacionEstandarResponse toEstandarResponse(HabitacionEstandar habitacion);

    SuitePresidencialResponse toSuiteResponse(SuitePresidencial suite);

    List<HabitacionResponse> toResponseList(List<Habitacion> habitaciones);

    // ---------- Request -> Entidad (polimórfico según el tipo de request) ----------

    default Habitacion toEntity(CrearHabitacionRequest request) {
        if (request instanceof CrearHabitacionEstandarRequest estandar) {
            return toEntity(estandar);
        }
        if (request instanceof CrearSuitePresidencialRequest suite) {
            return toEntity(suite);
        }
        throw new IllegalArgumentException("Tipo de request no soportado: " + request);
    }

    HabitacionEstandar toEntity(CrearHabitacionEstandarRequest request);

    @Mapping(target = "mayordomo", source = "incluyeMayordomo")
    @Mapping(target = "jacuzzi", source = "jacuzziPrivado")
    SuitePresidencial toEntity(CrearSuitePresidencialRequest request);
}