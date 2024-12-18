package co.edu.uniquindio.Subdidiis.DTO.Reserva;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CalcularPrecioReservaDTO(
        @NotEmpty List<String> detalleReserva,
        double precioTotal,
        int cantidad
) {
}
