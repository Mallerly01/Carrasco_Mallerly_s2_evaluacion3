package ingsoftware.evaluacion2.Controlador;

import ingsoftware.evaluacion2.Modelo.Mueble;
import ingsoftware.evaluacion2.Servicio.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muebles") 
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @PostMapping
    public Mueble crearMueble(@RequestBody Mueble mueble) {
        return muebleService.crearMueble(mueble);
    }

    @GetMapping
    public List<Mueble> listarMuebles() {
        return muebleService.listarMueblesActivos(); 
    }

    @GetMapping("/todos")
    public List<Mueble> listarTodosLosMuebles() {
        return muebleService.listarTodosLosMuebles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mueble> obtenerMueblePorId(@PathVariable Long id) {
        return muebleService.obtenerMueblePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> actualizarMueble(@PathVariable Long id, @RequestBody Mueble muebleDetalles) {
        try {
            return ResponseEntity.ok(muebleService.actualizarMueble(id, muebleDetalles));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // usa delete pero desactiva, no borra
    @DeleteMapping("/{id}")
    public ResponseEntity<Mueble> desactivarMueble(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(muebleService.desactivarMueble(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
