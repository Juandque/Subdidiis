package co.edu.uniquindio.Subdidiis.test;

import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Documents.Reserva;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.ReservaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@SpringBootTest
public class ReservaTest {
    @Autowired
    private ReservaServicio reservaServicio;

    @Test
    public void crearReservaExitoTest() throws Exception {
        CrearReservaDTO crearReservaDTO = new CrearReservaDTO(
                "U002",
                LocalDate.now(),
                LocalTime.now(),
                15000.0,
                1,
                Estado.PENDIENTE,
                List.of(new AgregarDetalleReservaDTO("6734c3593c88f13f9eebf300", 17000, "comentarios", List.of("entrada")))
        );

        String idReserva = reservaServicio.crearReserva(crearReservaDTO);

        Assertions.assertNotNull(idReserva);
    }


    @Test
    public void crearReservaDetalleNoEncontradoTest() {
        CrearReservaDTO crearReservaDTO = new CrearReservaDTO(
                "U001",
                LocalDate.now(),
                LocalTime.now(),
                15000.0,
                1,
                Estado.PENDIENTE,
                List.of(new AgregarDetalleReservaDTO("menuInvalido", 15000, "comentarios", List.of("adicion1")))
        );

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.crearReserva(crearReservaDTO);
        });

        Assertions.assertEquals("Detalle de reserva no encontrado", exception.getMessage());
    }


    @Test
    public void actualizarReservaExitoTest() throws Exception {
        ActualizarReservaDTO actualizarReservaDTO = new ActualizarReservaDTO(
                "6734cae05f1b4d7480b7bb56",
                List.of("6734c3593c88f13f9eebf300", "6734c3593c88f13f9eebf300"),
                LocalTime.now(),
                100.0,
                2,
                Estado.CONFIRMADA
        );

        reservaServicio.actualizarReserva(actualizarReservaDTO);

        ReservaDTO reservaActualizada = reservaServicio.obtenerReservaPorId("6734cae05f1b4d7480b7bb56");
        Assertions.assertEquals(Estado.CONFIRMADA, reservaActualizada.estado());
        Assertions.assertEquals(2, reservaActualizada.cantidad());
    }


    @Test
    public void actualizarReservaNoEncontradaTest() {
        ActualizarReservaDTO actualizarReservaDTO = new ActualizarReservaDTO(
                "R999",
                List.of("detalle1", "detalle2"),
                LocalTime.now(),
                100.0,
                2,
                Estado.CONFIRMADA
        );

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.actualizarReserva(actualizarReservaDTO);
        });

        Assertions.assertEquals("Reserva no encontrada", exception.getMessage());
    }

    @Test
    public void eliminarReservaExitoTest() throws Exception {
        reservaServicio.eliminarReserva("6734cc2732c70e06dcf9801a");

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.obtenerReservaPorId("6734cc2732c70e06dcf9801a");
        });

        Assertions.assertEquals("Reserva no encontrada", exception.getMessage());
    }

    @Test
    public void obtenerReservaPorIdExitoTest() throws Exception {
        ReservaDTO reservaDTO = reservaServicio.obtenerReservaPorId("6734cae05f1b4d7480b7bb56");

        Assertions.assertNotNull(reservaDTO);
        Assertions.assertEquals("6734cae05f1b4d7480b7bb56", reservaDTO.idReserva());
    }

    @Test
    public void obtenerReservaPorIdNoEncontradaTest() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.obtenerReservaPorId("R999");
        });

        Assertions.assertEquals("Reserva no encontrada", exception.getMessage());
    }

    @Test
    public void obtenerReservasPorUsuarioExitoTest() throws Exception {
        List<ReservaDTO> reservas = reservaServicio.obtenerReservasPorUsuario("6734bc6bd0e0c70895dd73dd");

        Assertions.assertFalse(reservas.isEmpty());
    }

    @Test
    public void obtenerReservasPorUsuarioNoEncontradoTest() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.obtenerReservasPorUsuario("U999");
        });

        Assertions.assertEquals("No se encontraron reservas para el usuario especificado", exception.getMessage());
    }

    @Test
    public void obtenerReservasPorFechaExitoTest() throws Exception {
        List<ReservaDTO> reservas = reservaServicio.obtenerReservasPorFecha(LocalDate.now());

        Assertions.assertFalse(reservas.isEmpty());
    }

    @Test
    public void cambiarEstadoReservaExitoTest() throws Exception {
        reservaServicio.cambiarEstadoReserva("6734cae05f1b4d7480b7bb56", Estado.CANCELADA);

        ReservaDTO reserva = reservaServicio.obtenerReservaPorId("6734cae05f1b4d7480b7bb56");
        Assertions.assertEquals(Estado.CANCELADA, reserva.estado());
    }


    @Test
    public void cambiarEstadoReservaNoEncontradaTest() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            reservaServicio.cambiarEstadoReserva("R999", Estado.CANCELADA);
        });

        Assertions.assertEquals("Reserva no encontrada", exception.getMessage());
    }
}
