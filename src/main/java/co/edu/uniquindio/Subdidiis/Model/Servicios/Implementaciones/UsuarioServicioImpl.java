package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.EmailDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.SesionDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import co.edu.uniquindio.Subdidiis.Model.Enum.ROL;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.UsuarioServicio;
import co.edu.uniquindio.Subdidiis.Repositorio.UsuarioRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdk.incubator.vector.VectorOperators.ROL;
@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {
    private final EmailServicio emailServicio;
    private final UsuarioRepo   usuarioRepo;
    LocalTime hora = LocalTime.now();

    public UsuarioServicioImpl(EmailServicio emailServicio, UsuarioRepo usuarioRepo) {
        this.emailServicio = emailServicio;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {
        if(existeEmail(registroClienteDTO.email())){
            throw new Exception("Usted ya se encuentra registrado en nuestra plataforma");
        }else {
            Usuario usuario = new Usuario();
            usuario.setNombre(registroClienteDTO.nombre());
            usuario.setEmail(registroClienteDTO.email());
            usuario.setPassword(registroClienteDTO.password());
            usuario.setApellido(registroClienteDTO.apellido());
            usuario.setDireccion(registroClienteDTO.direccion());
            usuario.setTelefono(registroClienteDTO.telefono());
            usuario.setRol(co.edu.uniquindio.Subdidiis.Model.Enum.ROL.ESTUDIANTE);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.password() );
            usuario.setPassword( passwordEncriptada );
            Usuario usuarioGuardado = usuarioRepo.save(usuario);
            String asunto = "Registro exitoso";
            String cuerpo = "Le damos la bienvenida a nuestra página web de reserva de almuerzos";
            String correo = usuario.getEmail();
            emailServicio.enviarCorreo(new EmailDTO(asunto,cuerpo,correo));
            return usuarioGuardado.getCodigo();
        }
    }

    @Override
    public void actualizarPassword(CambiarPasswordDTO cambioPasswordDto) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(cambioPasswordDto.id());
        if(usuarioOptional.isEmpty()){
            throw new Exception("Usted no se encuentra registrado");
        }
        Usuario usuario = usuarioOptional.get();
        if(cambioPasswordDto.password().length()<8){
            throw new Exception("El tamaño de la contraseña no cumple con los requeremientos");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(cambioPasswordDto.password()));
        usuarioRepo.save(usuario);
        emailServicio.enviarCorreo(new EmailDTO("Modificación de contraseña","Usted ha modificado su contraseña hoy a las" + hora, usuario.getEmail() ));

    }

    @Override
    public String iniciarSesionUsuario(SesionDTO sesionDto) throws Exception {
        if(sesionDto.email().isEmpty()||sesionDto.password().isEmpty()){
            throw new Exception("El email y la contraseña no pueden estar vacíos");
        }
        Optional<Usuario> usuarioOptional = usuarioRepo.findByEmail(sesionDto.email());
        if (usuarioOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usuario = usuarioOptional.get();
        if(!passwordEncoder.matches(sesionDto.password(), usuario.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        return usuario.getCodigo();
    }
    private boolean existeEmail(String email) {
        return usuarioRepo.findByEmail(email).isPresent();
    }
}
