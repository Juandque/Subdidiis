package co.edu.uniquindio.Subdidiis.Model.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CambiarPasswordDTO(
        @NotBlank @Size(min = 8) String password
) {
}
