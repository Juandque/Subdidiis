package co.edu.uniquindio.Subdidiis.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailDTO(
        @NotBlank String asunto,
        @NotBlank String cuerpo,
        @NotBlank @Length(max = 200) String destinatario
) {
}
