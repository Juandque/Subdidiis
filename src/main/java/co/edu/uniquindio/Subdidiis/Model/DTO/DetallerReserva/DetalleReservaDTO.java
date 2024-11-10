package co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva;

import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleReservaDTO(
        @NotBlank String idDetalle,
        @NotNull String menu,
        @NotBlank double subtotal,
        @NotBlank String comentarios
) {
}
