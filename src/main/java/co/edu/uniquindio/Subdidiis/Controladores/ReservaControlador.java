package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.ActualizarReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.CrearReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.Reserva.ReservaDTO;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.ReservaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservas")
public class ReservaControlador {
    private final ReservaServicio reservaServicio;

    @PostMapping("/crear-Reserva")
    public ResponseEntity<MensajeDTO<String>> crearReserva(@Valid @RequestBody CrearReservaDTO crearReservaDTO)throws Exception{
        reservaServicio.crearReserva(crearReservaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Reserva creado correctamente") );
    }
    @PutMapping("/editar-Reserva")
    public ResponseEntity<MensajeDTO<String>> actualizarReserva(@RequestBody @Valid ActualizarReservaDTO actualizarReservaDTO)throws Exception{
        reservaServicio.actualizarReserva(actualizarReservaDTO);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Reserva actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarReserva(@PathVariable String codigo)throws
            Exception{
        reservaServicio.eliminarReserva(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Reserva eliminado correctamente")
        );
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<ReservaDTO>> obtenerReservaPorId(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                reservaServicio.obtenerReservaPorId(codigo) ) );
    }
    @GetMapping("/buscar/{usuario}")
    public ResponseEntity<MensajeDTO<List<ReservaDTO>>> obtenerReservasPorUsuario(@PathVariable String usuario) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, reservaServicio.obtenerReservasPorUsuario(usuario) )
        );
    }
    @GetMapping("/buscar/{fecha}")
    public ResponseEntity<MensajeDTO<List<ReservaDTO>>> obtenerReservasPorFecha(@PathVariable LocalDate fecha) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, reservaServicio.obtenerReservasPorFecha(fecha) )
        );
    }
    @PutMapping("/editar-Estado/{idReserva}")
    public ResponseEntity<MensajeDTO<String>> cambiarEstadoReserva(@PathVariable String idReserva, @RequestBody Estado estado) throws Exception {
        reservaServicio.cambiarEstadoReserva(idReserva, estado);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Reserva actualizado correctamente"));
    }

}
