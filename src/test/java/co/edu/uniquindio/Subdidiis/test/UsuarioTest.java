package co.edu.uniquindio.Subdidiis.test;

import co.edu.uniquindio.Subdidiis.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.SesionDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import co.edu.uniquindio.Subdidiis.Repositorio.UsuarioRepo;
import co.edu.uniquindio.Subdidiis.Servicios.Implementaciones.UsuarioServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class UsuarioTest {
    @Autowired
    private UsuarioServicioImpl usuarioServicio;
    @Autowired
    private UsuarioRepo usuarioRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void registrarClienteFailTest() {
        RegistroClienteDTO registroClienteDto = new RegistroClienteDTO(
                "correo@example.com", "password123", "Juan", "Pérez", "Calle 123", "123456789");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.registrarse(registroClienteDto);
        });

        Assertions.assertEquals("Usted ya se encuentra registrado en nuestra plataforma", exception.getMessage());
    }

    @Test
    public void actualizarPasswordExitoTest() throws Exception {
        CambiarPasswordDTO cambioPasswordDto = new CambiarPasswordDTO("U001", "newpassword123");

        usuarioServicio.actualizarPassword(cambioPasswordDto);

        Optional<Usuario> usuarioOptional = usuarioRepo.findById(cambioPasswordDto.id());
        Usuario usuarioActualizado = usuarioOptional.get();
        Assertions.assertTrue(passwordEncoder.matches(cambioPasswordDto.password(), usuarioActualizado.getPassword()));
    }

    @Test
    public void actualizarPasswordUsuarioNoRegistradoTest() {
        CambiarPasswordDTO cambioPasswordDto = new CambiarPasswordDTO("U999", "newpassword123");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.actualizarPassword(cambioPasswordDto);
        });

        Assertions.assertEquals("Usted no se encuentra registrado", exception.getMessage());
    }

    @Test
    public void actualizarPasswordContraseñaCortaTest() {
        CambiarPasswordDTO cambioPasswordDto = new CambiarPasswordDTO("U001", "short");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.actualizarPassword(cambioPasswordDto);
        });

        Assertions.assertEquals("El tamaño de la contraseña no cumple con los requeremientos", exception.getMessage());
    }

    @Test
    public void iniciarSesionUsuarioExitoTest() throws Exception {
        SesionDTO sesionDto = new SesionDTO("correo@example.com", "password123");

        String codigo = usuarioServicio.iniciarSesionUsuario(sesionDto);

        Assertions.assertNotNull(codigo);
    }

    @Test
    public void iniciarSesionUsuarioNoRegistradoTest() {
        SesionDTO sesionDto = new SesionDTO("correoNoRegistrado@example.com", "password123");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.iniciarSesionUsuario(sesionDto);
        });

        Assertions.assertEquals("El correo no se encuentra registrado", exception.getMessage());
    }

    @Test
    public void iniciarSesionUsuarioContraseñaIncorrectaTest() {
        SesionDTO sesionDto = new SesionDTO("correo@example.com", "wrongpassword");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            usuarioServicio.iniciarSesionUsuario(sesionDto);
        });

        Assertions.assertEquals("La contraseña es incorrecta", exception.getMessage());
    }
}
