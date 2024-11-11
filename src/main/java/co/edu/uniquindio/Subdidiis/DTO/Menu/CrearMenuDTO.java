package co.edu.uniquindio.Subdidiis.DTO.Menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record CrearMenuDTO(
        @NotEmpty List<String>entrada,
        @NotEmpty List<String> proteina,
        @NotEmpty List<String> guarnicion,
        @NotEmpty List<String> postre,
        double precio,
        LocalDate fechaDisponibilidad
) {
}