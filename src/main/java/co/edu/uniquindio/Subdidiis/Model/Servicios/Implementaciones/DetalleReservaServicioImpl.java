package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.DetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.DetalleReserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.DetalleReservaServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Repositorio.DetalleReservaRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.MenuRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleReservaServicioImpl implements DetalleReservaServicio {
    private final DetalleReservaRepo detalleReservaRepo;
    private final MenuRepo menuRepo;
    private final EmailServicio emailServicio;

    public DetalleReservaServicioImpl(DetalleReservaRepo detalleReservaRepo, MenuRepo menuRepo, EmailServicio emailServicio) {
        this.detalleReservaRepo = detalleReservaRepo;
        this.menuRepo = menuRepo;
        this.emailServicio = emailServicio;
    }

    @Override
    public String agregarDetalleReserva(AgregarDetalleReservaDTO agregarDetalleReservaDTO) throws Exception {
        if (agregarDetalleReservaDTO.menu().isEmpty()) {
            throw new Exception("Por favor Seleccione un menú");
        }
        DetalleReserva detalleReserva = new DetalleReserva();
        Optional<Menu> menuOptional = menuRepo.findById(agregarDetalleReservaDTO.menu());
        if (menuOptional.isEmpty()) {
            throw new Exception("Menú no encontrado");
        } else {

            Menu menu = menuOptional.get();
            detalleReserva.setMenu(agregarDetalleReservaDTO.menu());
            detalleReserva.setSubtotal(menu.getPrecio());
            detalleReserva.setComentarios(agregarDetalleReservaDTO.comentarios());
            DetalleReserva detalleGuardado = detalleReservaRepo.save(detalleReserva);

            return detalleGuardado.getIdDetalle();
        }
    }

    @Override
    public void actualizarDetalleReserva(ActualizarDetalleReservaDTO actualizarDetalleReservaDTO) throws Exception {

    }

    @Override
    public void eliminarDetalleReserva(String idDetalle) throws Exception {

    }

    @Override
    public DetalleReservaDTO obtenerDetalleReservaPorId(String idDetalle) throws Exception {
        Optional<DetalleReserva> optionalDetalleReserva = detalleReservaRepo.findById(idDetalle);
        if (optionalDetalleReserva.isEmpty()) {
            throw new Exception("No se ha podido encontrar el Detalle de la reserva");
        }
        Optional<Menu> optionalMenu = menuRepo.findById(idDetalle);
        if (optionalMenu.isPresent()) {
            DetalleReserva detalle = optionalDetalleReserva.get();
            Menu menu = optionalMenu.get();
            DetalleReservaDTO detalleReservaDTO = new DetalleReservaDTO(
                    detalle.getIdDetalle(),
                    menu.getIdMenu(),
                    detalle.getSubtotal(),
                    detalle.getComentarios());
            return detalleReservaDTO;
        } else {
            throw new Exception("No se ha encontrado el Menu");
        }
    }
}
