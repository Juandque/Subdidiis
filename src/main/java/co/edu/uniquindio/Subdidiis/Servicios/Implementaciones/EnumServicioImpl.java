package co.edu.uniquindio.Subdidiis.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Enum.ROL;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.EnumServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EnumServicioImpl implements EnumServicio {
    @Override
    public List<Estado> obtenerTiposDeEstado() throws Exception {
        return Arrays.asList(Estado.values());
    }

    @Override
    public List<ROL> obtenerRoles() throws Exception {
        return Arrays.asList(ROL.values());    }
}
