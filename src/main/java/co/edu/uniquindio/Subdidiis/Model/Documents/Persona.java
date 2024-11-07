package co.edu.uniquindio.Subdidiis.Model.Documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("personas")
public class Persona implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String usuario;
}
