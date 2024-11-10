package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CalcularPrecioReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Model.Documents.Reserva;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.MenuServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.ReservaServicio;
import co.edu.uniquindio.Subdidiis.Repositorio.DetalleReservaRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.MenuRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.ReservaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
public class ReservaServicioImpl implements ReservaServicio {
    private final ReservaRepo reservaRepo;
    private final EmailServicio emailServicio;
    private final MenuRepo menuRepo;
    private final DetalleReservaRepo detalleReservaRepo;

    public ReservaServicioImpl(ReservaRepo reservaRepo, EmailServicio emailServicio, MenuRepo menuRepo, DetalleReservaRepo detalleReservaRepo) {
        this.reservaRepo = reservaRepo;
        this.emailServicio = emailServicio;
        this.menuRepo = menuRepo;
        this.detalleReservaRepo = detalleReservaRepo;
    }

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
