package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.DetallerReserva.DetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.DetalleReserva;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.DetalleReservaServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.MenuServicio;
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
    private final MenuServicio menuServicio;

    public DetalleReservaServicioImpl(DetalleReservaRepo detalleReservaRepo, MenuRepo menuRepo, MenuServicio menuServicio) {
        this.detalleReservaRepo = detalleReservaRepo;
        this.menuRepo = menuRepo;
        this.menuServicio = menuServicio;
    }

    @Override
    public String agregarDetalleReserva(AgregarDetalleReservaDTO agregarDetalleReservaDTO) throws Exception {
        Optional<Menu> menuOptional = menuRepo.findById(agregarDetalleReservaDTO.menu());
        if (menuOptional.isEmpty()) {
            throw new Exception("Menú no encontrado");
        }

        Menu menu = menuOptional.get();
        DetalleReserva detalleReserva = new DetalleReserva();
        detalleReserva.setMenu(menu.getIdMenu());
        detalleReserva.setComentarios(agregarDetalleReservaDTO.comentarios());

        double subtotal = menu.getPrecio();
        double precioProteina = menuServicio.obtenerPrecioAdicion("proteina");
        double precioGuarnicion = menuServicio.obtenerPrecioAdicion("guarnicion");
        double precioEntrada = menuServicio.obtenerPrecioAdicion("entrada");

        List<String> adicionesSeleccionadas = agregarDetalleReservaDTO.adiciones();
        for (String adicion : adicionesSeleccionadas) {
            switch (adicion) {
                case "proteina" -> subtotal += precioProteina;
                case "guarnicion" -> subtotal += precioGuarnicion;
                case "entrada" -> subtotal += precioEntrada;
                default -> throw new Exception("Tipo de adición no válida: " + adicion);
            }
        }

        detalleReserva.setSubtotal(subtotal);
        detalleReserva.setAdiciones(adicionesSeleccionadas);

        DetalleReserva detalleGuardado = detalleReservaRepo.save(detalleReserva);
        return detalleGuardado.getIdDetalle();
    }




    @Override
    public void actualizarDetalleReserva(ActualizarDetalleReservaDTO actualizarDetalleReservaDTO) throws Exception {
        // Buscar el DetalleReserva existente
        Optional<DetalleReserva> optionalDetalleReserva = detalleReservaRepo.findById(actualizarDetalleReservaDTO.idDetalle());
        if (optionalDetalleReserva.isEmpty()) {
            throw new Exception("Detalle de reserva no encontrado");
        }

        DetalleReserva detalleReserva = optionalDetalleReserva.get();

        // Buscar el menú correspondiente
        Optional<Menu> menuOptional = menuRepo.findById(actualizarDetalleReservaDTO.menu());
        if (menuOptional.isEmpty()) {
            throw new Exception("Menú no encontrado");
        }

        Menu menu = menuOptional.get();

        detalleReserva.setMenu(menu.getIdMenu());
        detalleReserva.setComentarios(actualizarDetalleReservaDTO.comentarios());

        double subtotal = menu.getPrecio();
        double precioProteina = menuServicio.obtenerPrecioAdicion("proteina");
        double precioGuarnicion = menuServicio.obtenerPrecioAdicion("guarnicion");
        double precioEntrada = menuServicio.obtenerPrecioAdicion("entrada");

        for (String adicion : actualizarDetalleReservaDTO.adiciones()) {
            switch (adicion) {
                case "proteina" -> subtotal += precioProteina;
                case "guarnicion" -> subtotal += precioGuarnicion;
                case "entrada" -> subtotal += precioEntrada;
                default -> throw new Exception("Tipo de adición no válida: " + adicion);
            }
        }

        detalleReserva.setSubtotal(subtotal);
        detalleReserva.setAdiciones(actualizarDetalleReservaDTO.adiciones());

        detalleReservaRepo.save(detalleReserva);
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

        DetalleReserva detalle = optionalDetalleReserva.get();
        Optional<Menu> optionalMenu = menuRepo.findById(detalle.getMenu());
        if (optionalMenu.isEmpty()) {
            throw new Exception("No se ha encontrado el Menu");
        }

        Menu menu = optionalMenu.get();
        DetalleReservaDTO detalleReservaDTO = new DetalleReservaDTO(
                detalle.getIdDetalle(),
                menu.getIdMenu(),
                detalle.getSubtotal(),
                detalle.getComentarios(),
                detalle.getAdiciones()
        );

        return detalleReservaDTO;
    }


}
