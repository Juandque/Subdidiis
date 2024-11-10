package co.edu.uniquindio.Subdidiis.Model.DTO.Menu;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

public record ItemListarMenusDTO(
        @Id String id,
        @NotEmpty List<String> entrada,
        @NotEmpty List<String> proteina,
        @NotEmpty List<String> guarnicion,
        @NotEmpty List<String> postre,
        double precio) {
}
