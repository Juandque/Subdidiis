package co.edu.uniquindio.Subdidiis.Model.DTO.Reserva;

import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservaDTO(
        @NotBlank String idReserva,
        @NotBlank String usuario,
        LocalDate fechaReserva,
        @NotEmpty List <String>detalleReserva,
        LocalTime horaReserva,
        double precioTotal,
        int cantidad,
        Estado estado
) {
}
