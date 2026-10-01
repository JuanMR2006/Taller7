package com.hotel.Hotel.mapper;

import com.hotel.Hotel.domain.Cliente;
import com.hotel.Hotel.domain.Habitacion;
import com.hotel.Hotel.domain.RangoFechas;
import com.hotel.Hotel.domain.Reserva;
import com.hotel.Hotel.dto.request.ActualizarClienteRequest;
import com.hotel.Hotel.dto.request.CrearClienteRequest;
import com.hotel.Hotel.dto.response.ClienteResponse;
import com.hotel.Hotel.dto.response.ClienteResumenResponse;
import com.hotel.Hotel.dto.response.ReservaItemResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-01T13:30:14-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteResponse toResponse(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        UUID id = null;
        String nombre = null;
        String email = null;
        boolean activo = false;
        int penalizaciones = 0;

        id = cliente.getId();
        nombre = cliente.getNombre();
        email = cliente.getEmail();
        activo = cliente.isActivo();
        penalizaciones = cliente.getPenalizaciones();

        ClienteResponse clienteResponse = new ClienteResponse( id, nombre, email, activo, penalizaciones );

        return clienteResponse;
    }

    @Override
    public List<ClienteResponse> toResponseList(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteResponse> list = new ArrayList<ClienteResponse>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( toResponse( cliente ) );
        }

        return list;
    }

    @Override
    public Cliente toEntity(CrearClienteRequest request) {
        if ( request == null ) {
            return null;
        }

        String nombre = null;
        String email = null;

        nombre = request.nombre();
        email = request.email();

        Cliente cliente = new Cliente( nombre, email );

        return cliente;
    }

    @Override
    public ClienteResumenResponse toResumen(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        double montoTotalGastado = 0.0d;
        List<ReservaItemResponse> reservasRecientes = null;
        UUID id = null;
        String nombre = null;
        String email = null;
        boolean activo = false;
        int penalizaciones = 0;

        montoTotalGastado = calcularMontoTotal( cliente.getReservas() );
        reservasRecientes = mapearReservasRecientes( cliente.getReservas() );
        id = cliente.getId();
        nombre = cliente.getNombre();
        email = cliente.getEmail();
        activo = cliente.isActivo();
        penalizaciones = cliente.getPenalizaciones();

        int totalReservasRealizadas = cliente.getReservas().size();

        ClienteResumenResponse clienteResumenResponse = new ClienteResumenResponse( id, nombre, email, activo, penalizaciones, totalReservasRealizadas, montoTotalGastado, reservasRecientes );

        return clienteResumenResponse;
    }

    @Override
    public ReservaItemResponse toReservaItem(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        UUID idReserva = null;
        String numeroHabitacion = null;
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        String estado = null;
        double costoTotal = 0.0d;

        idReserva = reserva.getId();
        numeroHabitacion = reservaHabitacionNumero( reserva );
        fechaInicio = reservaPeriodoFechaInicio( reserva );
        fechaFin = reservaPeriodoFechaFin( reserva );
        if ( reserva.getEstado() != null ) {
            estado = reserva.getEstado().name();
        }
        costoTotal = reserva.getCostoTotal();

        ReservaItemResponse reservaItemResponse = new ReservaItemResponse( idReserva, numeroHabitacion, fechaInicio, fechaFin, estado, costoTotal );

        return reservaItemResponse;
    }

    @Override
    public void updateClienteFromDto(ActualizarClienteRequest dto, Cliente entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.nombre() != null ) {
            entity.setNombre( dto.nombre() );
        }
        if ( dto.email() != null ) {
            entity.setEmail( dto.email() );
        }
    }

    private String reservaHabitacionNumero(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        Habitacion habitacion = reserva.getHabitacion();
        if ( habitacion == null ) {
            return null;
        }
        String numero = habitacion.getNumero();
        if ( numero == null ) {
            return null;
        }
        return numero;
    }

    private LocalDateTime reservaPeriodoFechaInicio(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        RangoFechas periodo = reserva.getPeriodo();
        if ( periodo == null ) {
            return null;
        }
        LocalDateTime fechaInicio = periodo.fechaInicio();
        if ( fechaInicio == null ) {
            return null;
        }
        return fechaInicio;
    }

    private LocalDateTime reservaPeriodoFechaFin(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }
        RangoFechas periodo = reserva.getPeriodo();
        if ( periodo == null ) {
            return null;
        }
        LocalDateTime fechaFin = periodo.fechaFin();
        if ( fechaFin == null ) {
            return null;
        }
        return fechaFin;
    }
}
