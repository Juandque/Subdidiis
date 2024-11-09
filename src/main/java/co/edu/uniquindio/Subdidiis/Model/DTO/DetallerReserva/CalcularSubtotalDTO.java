package co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva;

import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import jakarta.validation.constraints.NotNull;

public record CalcularSubtotalDTO(
        @NotNull Menu menu,
        @NotNull double subtotal
) {
}
