package co.edu.uniquindio.Subdidiis.Model.Documents;

import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("Reservas")
public class Reserva implements Serializable {
    @Id
    private String idReserva;
    private String usuario;
    private LocalDate fechaReserva;
    private List<String> detalleReserva;
    private LocalTime horaReserva;
    private double precioTotal;
    private int cantidad;
    private Estado estado;
}
