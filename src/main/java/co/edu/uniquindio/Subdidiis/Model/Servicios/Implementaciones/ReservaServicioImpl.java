package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CalcularPrecioReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.ReservaServicio;

import java.time.LocalDate;
import java.util.List;

public class ReservaServicioImpl implements ReservaServicio {

    @Override
    public String crearReserva(CrearReservaDTO crearReservaDTO) throws Exception {
        return "";
    }

    @Override
    public void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {

    }

    @Override
    public void eliminarReserva(String idReserva) throws Exception {

    }

    @Override
    public ReservaDTO obtenerReservaPorId(String idReserva) throws Exception {
        return null;
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorUsuario(String idUsuario) throws Exception {
        return List.of();
    }

    @Override
    public List<ReservaDTO> obtenerReservasPorFecha(LocalDate fechaReserva) throws Exception {
        return List.of();
    }

    @Override
    public void cambiarEstadoReserva(String idReserva, Estado nuevoEstado) throws Exception {

    }

    @Override
    public double calcularPrecioReserva(CalcularPrecioReservaDTO calcularPrecioDTO) throws Exception {
        return 0;
    }
}
