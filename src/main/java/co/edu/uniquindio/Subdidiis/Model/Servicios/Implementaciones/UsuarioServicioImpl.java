package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.CambiarPasswordDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.RegistroClienteDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Usuario.SesionDTO;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.UsuarioServicio;

public class UsuarioServicioImpl implements UsuarioServicio {
    @Override
    public String registrarse(RegistroClienteDTO registroClienteDTO) throws Exception {
        return "";
    }

    @Override
    public void actualizarPassword(CambiarPasswordDTO cambioPasswordDto) throws Exception {

    }

    @Override
    public String iniciarSesionUsuario(SesionDTO sesionDto) throws Exception {
        return "";
    }
}
