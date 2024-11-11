package co.edu.uniquindio.Subdidiis.Model.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("detalleReservas")
public class DetalleReserva {
    @Id
    private String idDetalle;
    private String menu;
    private double subtotal;
    private String comentarios;
    private List<String> adiciones;

}
