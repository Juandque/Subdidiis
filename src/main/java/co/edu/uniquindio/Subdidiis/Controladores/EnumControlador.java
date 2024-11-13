package co.edu.uniquindio.Subdidiis.Controladores;

import co.edu.uniquindio.Subdidiis.DTO.MensajeDTO;
import co.edu.uniquindio.Subdidiis.Model.Enum.Estado;
import co.edu.uniquindio.Subdidiis.Model.Enum.ROL;
import co.edu.uniquindio.Subdidiis.Servicios.Implementaciones.EnumServicioImpl;
import co.edu.uniquindio.Subdidiis.Servicios.Interfaces.EnumServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publico")
@RequiredArgsConstructor
public class EnumControlador {
    private final EnumServicio enumServicio;

    @GetMapping("/tipos-de-negocio")
    public ResponseEntity<MensajeDTO<List<Estado>>> obtenerTiposDeEstado() throws Exception {
        List<Estado> tiposDeNegocio = enumServicio.obtenerTiposDeEstado();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,tiposDeNegocio));
    }

    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<ROL>>> obtenerRoles() throws Exception {
        List<ROL> ciudades = enumServicio.obtenerRoles();
        return ResponseEntity.ok().body(new MensajeDTO<>(false,ciudades));
    }
}