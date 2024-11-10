package co.edu.uniquindio.Subdidiis.Model.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("DetalleReservas")
public class DetalleReserva {
    @Id
    private String idDetalle;
    private String menu;
    private double subtotal;
    private String comentarios;
}
