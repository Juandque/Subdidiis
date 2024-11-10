package co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.DetalleReservaDTO;

import java.util.List;

public interface DetalleReservaServicio {
    String agregarDetalleReserva(AgregarDetalleReservaDTO agregarDetalleReservaDTO) throws Exception;

    void actualizarDetalleReserva(ActualizarDetalleReservaDTO actualizarDetalleReservaDTO) throws Exception;

    void eliminarDetalleReserva(String idDetalle) throws Exception;

    DetalleReservaDTO obtenerDetalleReservaPorId(String idDetalle) throws Exception;

}
