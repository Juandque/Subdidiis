package co.edu.uniquindio.Subdidiis.DTO.Reserva;

import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record CrearReservaDTO(
        @NotBlank String usuario,
        LocalDate fechaReserva,
        LocalTime horaReserva,
        double precioTotal,
        int cantidad,
        Estado estado,
        List<AgregarDetalleReservaDTO> detalles
) {
}
