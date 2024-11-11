package co.edu.uniquindio.Subdidiis.Servicios.Interfaces;

import co.edu.uniquindio.Subdidiis.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;

import java.time.LocalDate;
import java.util.List;

public interface ReservaServicio {
    String crearReserva(CrearReservaDTO crearReservaDTO) throws Exception;

    void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception;

    void eliminarReserva(String idReserva) throws Exception;

    ReservaDTO obtenerReservaPorId(String idReserva) throws Exception;

    List<ReservaDTO> obtenerReservasPorUsuario(String idUsuario) throws Exception;

    List<ReservaDTO> obtenerReservasPorFecha(LocalDate fechaReserva) throws Exception;

    void cambiarEstadoReserva(String idReserva, Estado nuevoEstado) throws Exception;

}
