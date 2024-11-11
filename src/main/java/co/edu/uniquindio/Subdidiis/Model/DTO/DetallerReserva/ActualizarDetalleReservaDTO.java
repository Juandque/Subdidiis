package co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva;

import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ActualizarDetalleReservaDTO(
         @NotBlank String idDetalle,
         @NotNull String menu,
         @NotBlank double subtotal,
         @NotBlank String comentarios,
         List<String> adiciones
) {
}
