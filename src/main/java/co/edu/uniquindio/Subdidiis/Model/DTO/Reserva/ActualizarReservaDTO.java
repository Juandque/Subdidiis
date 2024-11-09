package co.edu.uniquindio.Subdidiis.Model.DTO.Reserva;

import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ActualizarReservaDTO(
        @NotBlank String idReserva,
        @NotEmpty List<String> detalleReserva,
        LocalTime horaReserva,
        double precioTotal,
        int cantidad,
        Estado estado
) {
}
