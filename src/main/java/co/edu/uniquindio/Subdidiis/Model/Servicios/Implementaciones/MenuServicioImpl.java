package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.Menu.*;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.MenuServicio;

import java.time.LocalDate;
import java.util.List;

public class MenuServicioImpl implements MenuServicio {


    @Override
    public String crearMenu(CrearMenuDTO crearMenuDTO) throws Exception {
        return "";
    }

    @Override
    public void actualizarMenu(ActualizarMenuDTO actualizarMenuDTO) throws Exception {

    }

    @Override
    public void eliminarMenu(String codigoMenu) throws Exception {

    }

    @Override
    public List<ItemListarMenusDTO> buscarMenusPorFecha(LocalDate busqueda) {
        return List.of();
    }

    @Override
    public ItemMenuInfoDTO obtenerInformacionMenu(String codigoMenu) throws Exception {
        return null;
    }

    @Override
    public double obtenerCostoAdicion(String adicion) throws Exception {
        return 0;
    }
}
