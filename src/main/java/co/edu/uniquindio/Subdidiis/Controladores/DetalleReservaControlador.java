package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.ActualizarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.AgregarDetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.DetallerReserva.DetalleReservaDTO;
import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.DetalleReservaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/detallereservas")
public class DetalleReservaControlador {
    private final DetalleReservaServicio detalleReservaServicio;

    @PostMapping("/crear-detalleReserva")
    public ResponseEntity<MensajeDTO<String>> agregarDetalleReserva(@Valid @RequestBody AgregarDetalleReservaDTO agregarDetalleReservaDTO)throws Exception{
        detalleReservaServicio.agregarDetalleReserva(agregarDetalleReservaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "DetalleReserva creado correctamente") );
    }
    @PutMapping("/editar-DetalleReserva")
    public ResponseEntity<MensajeDTO<String>> actualizarDetalleReserva(@RequestBody @Valid ActualizarDetalleReservaDTO actualizarDetalleReservaDTO)throws Exception{
        detalleReservaServicio.actualizarDetalleReserva(actualizarDetalleReservaDTO);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"DetalleReserva actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarDetalleReserva(@PathVariable String codigo)throws
            Exception{
        detalleReservaServicio.eliminarDetalleReserva(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "DetalleReserva eliminado correctamente")
        );
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleReservaDTO>> obtenerDetalleReservaPorId(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                detalleReservaServicio.obtenerDetalleReservaPorId(codigo) ) );
    }

}
