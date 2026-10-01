package com.hotel.Hotel.mapper;

import com.hotel.Hotel.domain.Habitacion;
import com.hotel.Hotel.domain.HabitacionEstandar;
import com.hotel.Hotel.domain.SuitePresidencial;
import com.hotel.Hotel.dto.request.CrearHabitacionEstandarRequest;
import com.hotel.Hotel.dto.request.CrearSuitePresidencialRequest;
import com.hotel.Hotel.dto.response.HabitacionEstandarResponse;
import com.hotel.Hotel.dto.response.HabitacionResponse;
import com.hotel.Hotel.dto.response.SuitePresidencialResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-01T14:34:31-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class HabitacionMapperImpl implements HabitacionMapper {

    @Override
    public HabitacionEstandarResponse toEstandarResponse(HabitacionEstandar habitacion) {
        if ( habitacion == null ) {
            return null;
        }

        UUID id = null;
        String numero = null;
        int capacidadMaxima = 0;
        double precioPorNoche = 0.0d;
        String estado = null;
        int camasIndividuales = 0;

        id = habitacion.getId();
        numero = habitacion.getNumero();
        capacidadMaxima = habitacion.getCapacidadMaxima();
        precioPorNoche = habitacion.getPrecioPorNoche();
        if ( habitacion.getEstado() != null ) {
            estado = habitacion.getEstado().name();
        }
        camasIndividuales = habitacion.getCamasIndividuales();

        HabitacionEstandarResponse habitacionEstandarResponse = new HabitacionEstandarResponse( id, numero, capacidadMaxima, precioPorNoche, estado, camasIndividuales );

        return habitacionEstandarResponse;
    }

    @Override
    public SuitePresidencialResponse toSuiteResponse(SuitePresidencial suite) {
        if ( suite == null ) {
            return null;
        }

        UUID id = null;
        String numero = null;
        int capacidadMaxima = 0;
        double precioPorNoche = 0.0d;
        String estado = null;
        boolean incluyeMayordomo = false;
        boolean jacuzziPrivado = false;

        id = suite.getId();
        numero = suite.getNumero();
        capacidadMaxima = suite.getCapacidadMaxima();
        precioPorNoche = suite.getPrecioPorNoche();
        if ( suite.getEstado() != null ) {
            estado = suite.getEstado().name();
        }
        incluyeMayordomo = suite.isIncluyeMayordomo();
        jacuzziPrivado = suite.isJacuzziPrivado();

        SuitePresidencialResponse suitePresidencialResponse = new SuitePresidencialResponse( id, numero, capacidadMaxima, precioPorNoche, estado, incluyeMayordomo, jacuzziPrivado );

        return suitePresidencialResponse;
    }

    @Override
    public List<HabitacionResponse> toResponseList(List<Habitacion> habitaciones) {
        if ( habitaciones == null ) {
            return null;
        }

        List<HabitacionResponse> list = new ArrayList<HabitacionResponse>( habitaciones.size() );
        for ( Habitacion habitacion : habitaciones ) {
            list.add( toResponse( habitacion ) );
        }

        return list;
    }

    @Override
    public HabitacionEstandar toEntity(CrearHabitacionEstandarRequest request) {
        if ( request == null ) {
            return null;
        }

        String numero = null;
        int capacidadMaxima = 0;
        double precioPorNoche = 0.0d;
        int camasIndividuales = 0;

        numero = request.numero();
        capacidadMaxima = request.capacidadMaxima();
        precioPorNoche = request.precioPorNoche();
        camasIndividuales = request.camasIndividuales();

        HabitacionEstandar habitacionEstandar = new HabitacionEstandar( numero, capacidadMaxima, precioPorNoche, camasIndividuales );

        return habitacionEstandar;
    }

    @Override
    public SuitePresidencial toEntity(CrearSuitePresidencialRequest request) {
        if ( request == null ) {
            return null;
        }

        boolean mayordomo = false;
        boolean jacuzzi = false;
        String numero = null;
        int capacidadMaxima = 0;
        double precioPorNoche = 0.0d;

        mayordomo = request.incluyeMayordomo();
        jacuzzi = request.jacuzziPrivado();
        numero = request.numero();
        capacidadMaxima = request.capacidadMaxima();
        precioPorNoche = request.precioPorNoche();

        SuitePresidencial suitePresidencial = new SuitePresidencial( numero, capacidadMaxima, precioPorNoche, mayordomo, jacuzzi );

        return suitePresidencial;
    }
}
