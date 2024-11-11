package co.edu.uniquindio.Subdidiis.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Enum.ROL;

import java.util.List;

public interface EnumServicio {
    List<Estado> obtenerTiposDeEstado() throws Exception;
    List<ROL> obtenerRoles() throws Exception ;
}
