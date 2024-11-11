package co.edu.uniquindio.Subdidiis.Model.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("menus")
public class Menu {
        @Id
        private String idMenu;
        private List<String> entrada;
        private List<String> proteina;
        private List<String> guarnicion;
        private List<String> postre;
        private double precio;
        private LocalDate fechaDisponibilidad;
}
