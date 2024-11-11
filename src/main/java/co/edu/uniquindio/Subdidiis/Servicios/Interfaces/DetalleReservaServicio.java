package co.edu.uniquindio.Subdidiis.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.DetalleReservaDTO;

public interface DetalleReservaServicio {
    String agregarDetalleReserva(AgregarDetalleReservaDTO agregarDetalleReservaDTO) throws Exception;

    void actualizarDetalleReserva(ActualizarDetalleReservaDTO actualizarDetalleReservaDTO) throws Exception;

    void eliminarDetalleReserva(String idDetalle) throws Exception;

    DetalleReservaDTO obtenerDetalleReservaPorId(String idDetalle) throws Exception;

}
