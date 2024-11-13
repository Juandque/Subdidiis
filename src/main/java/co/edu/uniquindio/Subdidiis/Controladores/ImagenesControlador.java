package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.ImagenDTO;
import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.ImagenesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenesControlador {
    private final ImagenesServicio imagenesServicio;
    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<Map>> subir(@RequestParam("file") MultipartFile imagen) throws Exception{
        Map respuesta = imagenesServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta ));
    }
    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<Map>> eliminar(@RequestBody ImagenDTO imagenDTO) throws
            Exception{
        Map respuesta = imagenesServicio.eliminarImagen( imagenDTO.URL() );
        return ResponseEntity.ok().body(new MensajeDTO<>(false, respuesta ));
    }
}
