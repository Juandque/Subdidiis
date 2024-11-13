package co.edu.uniquindio.Subdidiis.test;

import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.DetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.Servicios.Implementaciones.DetalleReservaServicioImpl;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.DetalleReservaServicio;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.ReservaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DetalleReservaTest {
    @Autowired
    private DetalleReservaServicioImpl detalleReservaServicio;
    @Autowired
    private ReservaServicio reservaServicio;


    @Test
    public void agregarDetalleReservaExitoTest() throws Exception {
        AgregarDetalleReservaDTO agregarDetalleReservaDTO = new AgregarDetalleReservaDTO("6734c3593c88f13f9eebf300", 120000,"comentarios", List.of("proteina", "guarnicion"));

        String idDetalle = detalleReservaServicio.agregarDetalleReserva(agregarDetalleReservaDTO);

        Assertions.assertNotNull(idDetalle);
    }

    @Test
    public void agregarDetalleReservaMenuNoEncontradoTest() {
        AgregarDetalleReservaDTO agregarDetalleReservaDTO = new AgregarDetalleReservaDTO("menuInvalido", 30000,"comentarios", List.of("proteina", "guarnicion"));

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.agregarDetalleReserva(agregarDetalleReservaDTO);
        });

        Assertions.assertEquals("Menú no encontrado", exception.getMessage());
    }

    @Test
    public void agregarDetalleReservaAdicionNoValidaTest() {
        AgregarDetalleReservaDTO agregarDetalleReservaDTO = new AgregarDetalleReservaDTO("menu1", 20000, "comentarios", List.of("adicionInvalida"));

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.agregarDetalleReserva(agregarDetalleReservaDTO);
        });

        Assertions.assertTrue(exception.getMessage().contains("Tipo de adición no válida: adicionInvalida"),
                "El mensaje de la excepción no es el esperado");
    }


    @Test
    public void actualizarDetalleReservaExitoTest() throws Exception {
        ActualizarDetalleReservaDTO actualizarDetalleReservaDTO = new ActualizarDetalleReservaDTO("6734c7915d3ec72761d776c4", "6734c3593c88f13f9eebf300",10000, "nuevos comentarios", List.of("proteina", "entrada"));

        detalleReservaServicio.actualizarDetalleReserva(actualizarDetalleReservaDTO);

        DetalleReservaDTO detalleActualizado = detalleReservaServicio.obtenerDetalleReservaPorId("6734c7915d3ec72761d776c4");
        Assertions.assertEquals("nuevos comentarios", detalleActualizado.comentarios());
        Assertions.assertEquals(List.of("proteina", "entrada"), detalleActualizado.adiciones());
    }

    @Test
    public void actualizarDetalleReservaNoEncontradoTest() {
        ActualizarDetalleReservaDTO actualizarDetalleReservaDTO = new ActualizarDetalleReservaDTO("detalleInvalido", "menu1",120000, "comentarios", List.of("proteina"));

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.actualizarDetalleReserva(actualizarDetalleReservaDTO);
        });

        Assertions.assertEquals("Detalle de reserva no encontrado", exception.getMessage());
    }

    @Test
    public void actualizarDetalleReservaMenuNoEncontradoTest() {
        ActualizarDetalleReservaDTO actualizarDetalleReservaDTO = new ActualizarDetalleReservaDTO("detalle1", "menuInvalido", 15000,"comentarios", List.of("proteina"));

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.actualizarDetalleReserva(actualizarDetalleReservaDTO);
        });

        Assertions.assertEquals("Menú no encontrado", exception.getMessage());
    }
    @Test
    public void eliminarDetalleReservaExitoTest() throws Exception {
        String idDetalle = "6734c92f0d588271bb25a5f7";

        detalleReservaServicio.eliminarDetalleReserva(idDetalle);

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.obtenerDetalleReservaPorId(idDetalle);
        });
        Assertions.assertEquals("No se ha podido encontrar el Detalle de la reserva", exception.getMessage());
    }

    @Test
    public void eliminarDetalleReservaNoEncontradoTest() {
        String idDetalle = "Menu1";

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.eliminarDetalleReserva(idDetalle);
        });

        Assertions.assertEquals("Detalle de reserva no encontrado", exception.getMessage());
    }
    @Test
    public void obtenerDetalleReservaPorIdExitoTest() throws Exception {
        String idDetalle = "6734c7915d3ec72761d776c4";

        DetalleReservaDTO detalleReservaDTO = detalleReservaServicio.obtenerDetalleReservaPorId(idDetalle);

        Assertions.assertNotNull(detalleReservaDTO);
        Assertions.assertEquals("6734c3593c88f13f9eebf300", detalleReservaDTO.menu());
    }

    @Test
    public void obtenerDetalleReservaPorIdNoEncontradoTest() {
        String idDetalle = "detalleInvalido";

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.obtenerDetalleReservaPorId(idDetalle);
        });

        Assertions.assertEquals("No se ha podido encontrar el Detalle de la reserva", exception.getMessage());
    }

    @Test
    public void obtenerDetalleReservaPorIdMenuNoEncontradoTest() {
        String idDetalle = "detalleConMenuInvalido";

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            detalleReservaServicio.obtenerDetalleReservaPorId(idDetalle);
        });

        Assertions.assertEquals("No se ha encontrado el Menu", exception.getMessage());
    }


}
