package co.edu.uniquindio.Subdidiis.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.DTO.Menu.ActualizarMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.CrearMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemListarMenusDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemMenuInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface MenuServicio {
    String crearMenu(CrearMenuDTO crearMenuDTO) throws Exception;

    void actualizarMenu(ActualizarMenuDTO actualizarMenuDTO) throws Exception;

    void eliminarMenu(String codigoMenu)throws Exception;

    List<ItemListarMenusDTO> buscarMenusPorFecha(LocalDate busqueda);

    ItemMenuInfoDTO obtenerInformacionMenu(String codigoMenu) throws Exception;

    double obtenerPrecioAdicion(String adicion) throws Exception;
}
