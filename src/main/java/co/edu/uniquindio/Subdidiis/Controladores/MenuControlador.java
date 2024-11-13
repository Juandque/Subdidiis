package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ActualizarMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.CrearMenuDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemListarMenusDTO;
import co.edu.uniquindio.Subdidiis.DTO.Menu.ItemMenuInfoDTO;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.MenuServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class MenuControlador {
    private final MenuServicio menuServicio;

    @PostMapping("/crear-menu")
    public ResponseEntity<MensajeDTO<String>> crearMenu(@Valid @RequestBody CrearMenuDTO crearMenuDTO)throws Exception{
        menuServicio.crearMenu(crearMenuDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Menú creado correctamente") );
    }
    @PutMapping("/editar-menu")
    public ResponseEntity<MensajeDTO<String>> actualizarmenu(@RequestBody @Valid ActualizarMenuDTO actualizarMenuDTO)throws Exception{
        menuServicio.actualizarMenu(actualizarMenuDTO);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Menú actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarMenu(@PathVariable String codigo)throws
            Exception{
        menuServicio.eliminarMenu(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Menú eliminado correctamente")
        );
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<ItemMenuInfoDTO>> obtenerInformacionMenu(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                menuServicio.obtenerInformacionMenu(codigo) ) );
    }

    @GetMapping("/buscar/{fecha}")
    public ResponseEntity<MensajeDTO<List<ItemListarMenusDTO>>> buscarMenusPorFecha(@PathVariable LocalDate fecha) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, menuServicio.buscarMenusPorFecha(fecha) )
        );
    }
    @PostMapping("/obtener/{adicion}")
    public ResponseEntity<MensajeDTO<String>> agregarFavorito(@PathVariable String adicion)throws  Exception{
        menuServicio.obtenerPrecioAdicion(adicion);
        return  ResponseEntity.ok().body(new MensajeDTO<>(false,"Adicion obtenida correctamente") );
    }
}
