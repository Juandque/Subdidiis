package co.edu.uniquindio.Subdidiis.test;

import co.edu.uniquindio.Subdidiis.DTO.Menu.ActualizarMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.CrearMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemListarMenusDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemMenuInfoDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.Menu;
import co.edu.uniquindio.Subdidiis.Repositorio.MenuRepo;
import co.edu.uniquindio.Subdidiis.Servicios.Implementaciones.MenuServicioImpl;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.MenuServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class MenuTest {
    @Autowired
    private MenuServicioImpl menuServicio;
    @Autowired
    private MenuRepo menuRepo;

    @Test
    public void crearMenuExitoTest() throws Exception {
        CrearMenuDTO crearMenuDTO = new CrearMenuDTO(
                List.of("entrada3", "entrada3"),
                List.of("proteina13", "proteina23"),
                List.of("guarnicion3"),
                List.of("postre3"),
                12000,
                LocalDate.now().plusDays(1)
        );

        String idMenu = menuServicio.crearMenu(crearMenuDTO);

        Assertions.assertNotNull(idMenu);
    }

    @Test
    public void crearMenuCamposIncompletosTest() {
        CrearMenuDTO crearMenuDTO = new CrearMenuDTO(
                List.of(),
                List.of("proteina1"),
                List.of("guarnicion1"),
                List.of("postre1"),
                15000,
                LocalDate.now().plusDays(1)
        );

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.crearMenu(crearMenuDTO);
        });

        Assertions.assertEquals("Por favor complete todos los campos", exception.getMessage());
    }
    @Test
    public void actualizarMenuExitoTest() throws Exception {
        ActualizarMenuDTO actualizarMenuDTO = new ActualizarMenuDTO(
                "6734c1dc4b029f16ce107007",
                List.of("nuevaEntrada1", "nuevaEntrada2"),
                List.of("nuevaProteina1"),
                List.of("nuevaGuarnicion1"),
                List.of("nuevoPostre1"),
                20000,
                LocalDate.now().plusDays(5)
        );

        Assertions.assertDoesNotThrow(() -> menuServicio.actualizarMenu(actualizarMenuDTO));

        Menu menuActualizado = menuRepo.findById("6734c1dc4b029f16ce107007").orElseThrow();
        Assertions.assertEquals(20000, menuActualizado.getPrecio());
        Assertions.assertEquals(List.of("nuevaEntrada1", "nuevaEntrada2"), menuActualizado.getEntrada());
    }

    @Test
    public void actualizarMenuCamposIncompletosTest() {
        ActualizarMenuDTO actualizarMenuDTO = new ActualizarMenuDTO(
                "M001",
                List.of(),
                List.of("proteina1"),
                List.of("guarnicion1"),
                List.of("postre1"),
                15000,
                LocalDate.now().plusDays(1)
        );

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.actualizarMenu(actualizarMenuDTO);
        });

        Assertions.assertEquals("Por favor complete todos los campos", exception.getMessage());
    }

    @Test
    public void eliminarMenuExitoTest() throws Exception {
        String codigoMenu = "6734c1dc4b029f16ce107007";

        menuServicio.eliminarMenu(codigoMenu);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.obtenerInformacionMenu(codigoMenu);
        });
        Assertions.assertEquals("No se ha podido encontrar el negocio", exception.getMessage());
    }

    @Test
    public void eliminarMenuNoEncontradoTest() {
        String codigoMenu = "menuInvalido";

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.eliminarMenu(codigoMenu);
        });

        Assertions.assertEquals("El menú a eliminar no ha sido encontrado", exception.getMessage());
    }
    @Test
    public void buscarMenusPorFechaExitoTest() {
        LocalDate fecha = LocalDate.now().plusDays(1);

        List<ItemListarMenusDTO> menus = menuServicio.buscarMenusPorFecha(fecha);

        Assertions.assertFalse(menus.isEmpty());
    }

    @Test
    public void buscarMenusPorFechaSinResultadosTest() {
        LocalDate fecha = LocalDate.of(2050, 1, 1);

        List<ItemListarMenusDTO> menus = menuServicio.buscarMenusPorFecha(fecha);

        Assertions.assertTrue(menus.isEmpty());
    }
    @Test
    public void obtenerInformacionMenuExitoTest() throws Exception {
        String codigoMenu = "6734c4899e64a37ff9f1ad94";

        ItemMenuInfoDTO menuInfo = menuServicio.obtenerInformacionMenu(codigoMenu);

        Assertions.assertNotNull(menuInfo);
        Assertions.assertTrue(menuInfo.entrada().contains("entrada2"));
    }


    @Test
    public void obtenerInformacionMenuNoEncontradoTest() {
        String codigoMenu = "menuInvalido";

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.obtenerInformacionMenu(codigoMenu);
        });

        Assertions.assertEquals("No se ha podido encontrar el negocio", exception.getMessage());
    }
    @Test
    public void obtenerPrecioAdicionProteinaTest() throws Exception {
        double precio = menuServicio.obtenerPrecioAdicion("proteina");

        Assertions.assertTrue(precio > 0);
    }

    @Test
    public void obtenerPrecioAdicionInvalidoTest() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            menuServicio.obtenerPrecioAdicion("adicionInvalida");
        });

        Assertions.assertTrue(exception.getMessage().startsWith("Tipo de adición no válida"));
    }

}
