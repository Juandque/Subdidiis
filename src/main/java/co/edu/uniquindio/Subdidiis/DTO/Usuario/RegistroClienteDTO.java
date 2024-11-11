package co.edu.uniquindio.Subdidiis.DTO.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroClienteDTO(
         @Email @NotBlank String email,
         @NotBlank String password,
         @NotBlank String nombre,
         @NotBlank String apellido,
         @NotBlank String direccion,
         @NotBlank String telefono
) {
}
