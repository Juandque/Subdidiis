package co.edu.uniquindio.Subdidiis.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.DTO.Usuario.SesionDTO;

public interface UsuarioServicio {
    String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarPassword(CambiarPasswordDTO cambioPasswordDto) throws Exception;

    String iniciarSesionUsuario(SesionDTO sesionDto)throws Exception;
}
