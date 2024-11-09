package co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.Model.DTO.EmailDTO;

public interface EmailServicio {
    void enviarCorreo(EmailDTO emailDTO) throws Exception;
}
