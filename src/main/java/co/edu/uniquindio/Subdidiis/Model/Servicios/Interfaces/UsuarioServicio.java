package co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.SesionDTO;

public interface UsuarioServicio {
    String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception;

    void actualizarPassword(CambiarPasswordDTO cambioPasswordDto) throws Exception;

    String iniciarSesionUsuario(SesionDTO sesionDto)throws Exception;
}
