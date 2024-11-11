package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Menu.ItemListarMenusDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CalcularPrecioReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.DetalleReserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Model.Documents.Reserva;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.DetalleReservaServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.MenuServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.ReservaServicio;
import co.edu.uniquindio.Subdidiis.Repositorio.DetalleReservaRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.MenuRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.ReservaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservaServicioImpl implements ReservaServicio {
    private final ReservaRepo reservaRepo;
    private final EmailServicio emailServicio;
    private final MenuRepo menuRepo;
    private final DetalleReservaRepo detalleReservaRepo;
    private final DetalleReservaServicio detalleReservaServicio;

    public ReservaServicioImpl(ReservaRepo reservaRepo, EmailServicio emailServicio, MenuRepo menuRepo, DetalleReservaRepo detalleReservaRepo, DetalleReservaServicio detalleReservaServicio) {
        this.reservaRepo = reservaRepo;
        this.emailServicio = emailServicio;
        this.menuRepo = menuRepo;
        this.detalleReservaRepo = detalleReservaRepo;
        this.detalleReservaServicio = detalleReservaServicio;
    }

    @Override
    public String crearReserva(CrearReservaDTO crearReservaDTO) throws Exception {
        Reserva reserva = new Reserva();
        reserva.setUsuario(crearReservaDTO.usuario());
        reserva.setFechaReserva(crearReservaDTO.fechaReserva());
        reserva.setHoraReserva(crearReservaDTO.horaReserva());
        reserva.setEstado(crearReservaDTO.estado());

        double precioTotal = 0.0;
        List<String> idsDetalleReserva = new ArrayList<>();

        for (AgregarDetalleReservaDTO detalleDTO : crearReservaDTO.detalles()) {
            String idDetalle = detalleReservaServicio.agregarDetalleReserva(detalleDTO);
            DetalleReserva detalleReserva = detalleReservaRepo.findById(idDetalle)
                    .orElseThrow(() -> new Exception("Detalle de reserva no encontrado"));
            precioTotal += detalleReserva.getSubtotal();
            idsDetalleReserva.add(idDetalle);
        }

        reserva.setDetalleReserva(idsDetalleReserva);
        reserva.setCantidad(idsDetalleReserva.size());
        reserva.setPrecioTotal(precioTotal);

        Reserva reservaGuardada = reservaRepo.save(reserva);
        return reservaGuardada.getIdReserva();
    }


    @Override
    public void actualizarReserva(ActualizarReservaDTO actualizarReservaDTO) throws Exception {
        Optional<Reserva> optionalReserva = reservaRepo.findById(actualizarReservaDTO.idReserva());
        if (optionalReserva.isEmpty()) {
            throw new Exception("Reserva no encontrada");
        }

        Reserva reserva = optionalReserva.get();

        reserva.setDetalleReserva(actualizarReservaDTO.detalleReserva());
        reserva.setHoraReserva(actualizarReservaDTO.horaReserva());
        reserva.setPrecioTotal(actualizarReservaDTO.precioTotal());
        reserva.setCantidad(actualizarReservaDTO.cantidad());
        reserva.setEstado(actualizarReservaDTO.estado());

        reservaRepo.save(reserva);
    }


    @Override
    public void eliminarReserva(String idReserva) throws Exception {
        Optional<Reserva> optionalReserva = reservaRepo.findById(idReserva);
        if (optionalReserva.isEmpty()) {
            throw new Exception("Reserva no encontrada");
        }
        reservaRepo.deleteById(idReserva);
    }


    @Override
    public ReservaDTO obtenerReservaPorId(String idReserva) throws Exception {
        Optional<Reserva> optionalReserva = reservaRepo.findById(idReserva);
        if (optionalReserva.isEmpty()) {
            throw new Exception("Reserva no encontrada");
        }

        Reserva reserva = optionalReserva.get();
        return new ReservaDTO(
                reserva.getIdReserva(),
                reserva.getUsuario(),
                reserva.getFechaReserva(),
                reserva.getDetalleReserva(),
                reserva.getHoraReserva(),
                reserva.getPrecioTotal(),
                reserva.getCantidad(),
                reserva.getEstado()
        );
    }


    @Override
    public List<ReservaDTO> obtenerReservasPorUsuario(String idUsuario) throws Exception {
        List<Reserva> reservas = reservaRepo.findReservasByUsuario(idUsuario);
        if (reservas.isEmpty()) {
            throw new Exception("No se encontraron reservas para el usuario especificado");
        }

        return reservas.stream()
                .map(reserva -> new ReservaDTO(
                        reserva.getIdReserva(),
                        reserva.getUsuario(),
                        reserva.getFechaReserva(),
                        reserva.getDetalleReserva(),
                        reserva.getHoraReserva(),
                        reserva.getPrecioTotal(),
                        reserva.getCantidad(),
                        reserva.getEstado()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<ReservaDTO> obtenerReservasPorFecha(LocalDate fechaReserva) throws Exception {
        List<Reserva> reservasEncontradas = reservaRepo.findByFechaReserva(fechaReserva);

        List<ReservaDTO> reservasDTO = new ArrayList<>();
        for (Reserva r : reservasEncontradas) {
            reservasDTO.add(new ReservaDTO(
                    r.getIdReserva(),
                    r.getUsuario(),
                    r.getFechaReserva(),
                    r.getDetalleReserva(),
                    r.getHoraReserva(),
                    r.getPrecioTotal(),
                    r.getCantidad(),
                    r.getEstado()));
        }
        return reservasDTO;
    }

    @Override
    public void cambiarEstadoReserva(String idReserva, Estado nuevoEstado) throws Exception {
        Optional<Reserva> optionalReserva = reservaRepo.findById(idReserva);
        if (optionalReserva.isEmpty()) {
            throw new Exception("Reserva no encontrada");
        }

        Reserva reserva = optionalReserva.get();
        reserva.setEstado(nuevoEstado);

        reservaRepo.save(reserva);
    }

}
