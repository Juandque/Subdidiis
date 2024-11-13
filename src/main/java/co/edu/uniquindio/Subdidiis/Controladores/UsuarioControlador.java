package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.SesionDTO;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
   private final UsuarioServicio usuarioServicio;

    @PutMapping("/registrarse")
    public ResponseEntity<MensajeDTO<String>> registrarse(@Valid @RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        usuarioServicio.registrarse(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente") );
    }
    @PutMapping("/editar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@RequestBody @Valid CambiarPasswordDTO cambioPasswordDto)throws Exception{
        usuarioServicio.actualizarPassword(cambioPasswordDto);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Contraseña actualizada correctamente") );
    }

    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<String>> iniciarSesionCliente(@Valid @RequestBody SesionDTO loginDTO) throws Exception {
        usuarioServicio.iniciarSesionUsuario(loginDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Sesion iniciada con éxito") );
    }
}
