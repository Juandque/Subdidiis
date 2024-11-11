package co.edu.uniquindio.Subdidiis.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

public record CambiarPasswordDTO(
        @Id String id,
        @NotBlank @Size(min = 8) String password
) {
}
