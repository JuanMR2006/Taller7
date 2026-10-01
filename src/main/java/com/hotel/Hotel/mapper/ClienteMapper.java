package com.hotel.Hotel.mapper;

import com.hotel.Hotel.domain.Cliente;
import com.hotel.Hotel.domain.EstadoReserva;
import com.hotel.Hotel.domain.Reserva;
import com.hotel.Hotel.dto.request.ActualizarClienteRequest;
import com.hotel.Hotel.dto.request.CrearClienteRequest;
import com.hotel.Hotel.dto.response.ClienteResponse;
import com.hotel.Hotel.dto.response.ClienteResumenResponse;
import com.hotel.Hotel.dto.response.ReservaItemResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "penalizaciones", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente toEntity(CrearClienteRequest request);

    // ---------- TAREA 2: resumen con proyección anidada ----------

    @Mapping(target = "totalReservasRealizadas", expression = "java(cliente.getReservas().size())")
    @Mapping(target = "montoTotalGastado", source = "reservas", qualifiedByName = "calcularMontoTotal")
    @Mapping(target = "reservasRecientes", source = "reservas", qualifiedByName = "mapearReservasRecientes")
    ClienteResumenResponse toResumen(Cliente cliente);

    @Mapping(target = "idReserva", source = "id")
    @Mapping(target = "numeroHabitacion", source = "habitacion.numero")
    @Mapping(target = "fechaInicio", source = "periodo.fechaInicio")
    @Mapping(target = "fechaFin", source = "periodo.fechaFin")
    ReservaItemResponse toReservaItem(Reserva reserva);

    /** Suma con Stream; las reservas canceladas no cuentan como dinero gastado. */
    @Named("calcularMontoTotal")
    default double calcularMontoTotal(List<Reserva> reservas) {
        if (reservas == null) {
            return 0.0;
        }
        return reservas.stream()
                .filter(r -> r.getEstado() != EstadoReserva.CANCELADA)
                .mapToDouble(Reserva::getCostoTotal)
                .sum();
    }

    /** Historial completo, de la reserva más reciente a la más antigua. */
    @Named("mapearReservasRecientes")
    default List<ReservaItemResponse> mapearReservasRecientes(List<Reserva> reservas) {
        if (reservas == null) {
            return List.of();
        }
        return reservas.stream()
                .sorted(Comparator.comparing((Reserva r) -> r.getPeriodo().fechaInicio()).reversed())
                .map(this::toReservaItem)
                .toList();
    }

    // ---------- TAREA 3: actualización parcial segura ----------

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "penalizaciones", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    void updateClienteFromDto(ActualizarClienteRequest dto, @MappingTarget Cliente entity);
}
