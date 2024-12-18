package co.edu.uniquindio.Subdidiis.Model.Documents;

import co.edu.uniquindio.Subdidiis.Model.Enum.ROL;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("usuarios")
public class Usuario implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private ROL rol;
}
