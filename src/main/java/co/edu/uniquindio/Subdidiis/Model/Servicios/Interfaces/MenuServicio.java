package co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.Model.DTO.Menu.*;

import java.time.LocalDate;
import java.util.List;

public interface MenuServicio {
    String crearMenu(CrearMenuDTO crearMenuDTO) throws Exception;

    void actualizarMenu(ActualizarMenuDTO actualizarMenuDTO) throws Exception;

    void eliminarMenu(String codigoMenu)throws Exception;

    List<ItemListarNegociosDTO> buscarMenusPorFecha(LocalDate busqueda);

    ItemMenuInfoDTO obtenerInformacionMenu(String codigoMenu) throws Exception;

    ObtenerMenuDTO obtenerMenu(String codigoMenu) throws  Exception;
    double obtenerCostoAdicion(String adicion) throws Exception;
}
