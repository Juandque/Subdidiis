package co.edu.uniquindio.Subdidiis.Model.Servicios.Implementaciones;

import co.edu.uniquindio.Subdidiis.Model.DTO.EmailDTO;
import co.edu.uniquindio.Subdidiis.Model.DTO.Menu.*;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Model.Documents.Usuario;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.EmailServicio;
import co.edu.uniquindio.Subdidiis.Model.Servicios.Interfaces.MenuServicio;
import co.edu.uniquindio.Subdidiis.Repositorio.MenuRepo;
import co.edu.uniquindio.Subdidiis.Repositorio.UsuarioRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MenuServicioImpl implements MenuServicio {
    private final MenuRepo menuRepo;
    private static final double adicionEntrada = 4000;
    private static final double adicionProteina = 5000;
    private static final double adicionGuarnicion = 3000;


    public MenuServicioImpl(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    @Override
    public String crearMenu(CrearMenuDTO crearMenuDTO) throws Exception {
        if (crearMenuDTO.entrada().isEmpty() || crearMenuDTO.guarnicion().isEmpty() || crearMenuDTO.postre().isEmpty()
                || crearMenuDTO.precio() <= 0 || crearMenuDTO.proteina().isEmpty() || crearMenuDTO.fechaDisponibilidad() == null) {
            throw new Exception("Por favor complete todos los campos");
        } else {
            Menu menu = new Menu();
            menu.setEntrada(crearMenuDTO.entrada());
            menu.setGuarnicion(crearMenuDTO.guarnicion());
            menu.setPostre(crearMenuDTO.postre());
            menu.setPrecio(crearMenuDTO.precio());
            menu.setProteina(crearMenuDTO.proteina());
            menu.setFechaDisponibilidad(crearMenuDTO.fechaDisponibilidad());
            Menu menuGuardado = menuRepo.save(menu);
            return menuGuardado.getIdMenu();
        }
    }

    @Override
    public void actualizarMenu(ActualizarMenuDTO actualizarMenuDTO) throws Exception {
        Optional<Menu> menuOptional = menuRepo.findById(actualizarMenuDTO.id());
        if (menuOptional.isEmpty()) {
            throw new Exception("El menú no se encuentra registrado");
        }
        if (actualizarMenuDTO.entrada().isEmpty() || actualizarMenuDTO.guarnicion().isEmpty() || actualizarMenuDTO.postre().isEmpty()
                || actualizarMenuDTO.precio() <= 0 || actualizarMenuDTO.proteina().isEmpty() || actualizarMenuDTO.fechaDisponibilidad() == null) {
            throw new Exception("Por favor complete todos los campos");
        } else {
            Menu menu = menuOptional.get();
            menu.setEntrada(actualizarMenuDTO.entrada());
            menu.setGuarnicion(actualizarMenuDTO.guarnicion());
            menu.setPostre(actualizarMenuDTO.postre());
            menu.setPrecio(actualizarMenuDTO.precio());
            menu.setProteina(actualizarMenuDTO.proteina());
            menu.setFechaDisponibilidad(actualizarMenuDTO.fechaDisponibilidad());
            menuRepo.save(menu);
        }
    }

    @Override
    public void eliminarMenu(String codigoMenu) throws Exception {
        Optional<Menu> optionalMenu = menuRepo.findById(codigoMenu);

        if (optionalMenu.isEmpty()) {
            throw new Exception("El negocioo a eliminar no ha sido encontrado");
        }
        Menu menu = optionalMenu.get();
        menuRepo.delete(menu);
    }

    @Override
    public List<ItemListarMenusDTO> buscarMenusPorFecha(LocalDate busqueda) {
        List<Menu> menusEncontrados = menuRepo.findByFechaDisponibilidadIsLike(busqueda);

        List<ItemListarMenusDTO> detalleMenusEncontraadosDTOS = new ArrayList<>();
        for (Menu m : menusEncontrados) {
            detalleMenusEncontraadosDTOS.add(new ItemListarMenusDTO(
                    m.getIdMenu(),
                    m.getEntrada(),
                    m.getGuarnicion(),
                    m.getProteina(),
                    m.getPostre(),
                    m.getPrecio()));
        }
        return detalleMenusEncontraadosDTOS;
    }

    @Override
    public ItemMenuInfoDTO obtenerInformacionMenu(String codigoMenu) throws Exception {
        Optional<Menu> optionalMenu = menuRepo.findById(codigoMenu);
        if (optionalMenu.isEmpty()) {
            throw new Exception("No se ha podido encontrar el negocio");
        }
        Menu menu = optionalMenu.get();
        ItemMenuInfoDTO detalleMenuDTO = new ItemMenuInfoDTO(
                menu.getEntrada(),
                menu.getProteina(),
                menu.getGuarnicion(),
                menu.getGuarnicion(),
                menu.getPrecio(),
                menu.getFechaDisponibilidad());
        return detalleMenuDTO;
    }

    @Override
    public double obtenerCostoAdicion(String adicion) throws Exception {
        switch (adicion.toLowerCase()) {
            case "proteina":
                return adicionProteina;
            case "guarnicion":
                return adicionGuarnicion;
            case "entrada":
                return adicionEntrada;
            default:
                throw new Exception("No se ha podido encontrar el adicion");
        }
    }
}
