package ingsoftware.evaluacion2.Controlador;

import ingsoftware.evaluacion2.Modelo.Variante;
import ingsoftware.evaluacion2.Repositorio.VarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/variantes")
public class VarianteController {

    @Autowired
    private VarianteRepository varianteRepository;

    @PostMapping
    public ResponseEntity<Variante> crearVariante(@RequestBody Variante variante) {
        Variante nuevaVariante = varianteRepository.save(variante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVariante);
    }

    @GetMapping
    public List<Variante> listarVariantes() {
        return varianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variante> obtenerVariantePorId(@PathVariable Long id) {
        return varianteRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variante> actualizarVariante(@PathVariable Long id, @RequestBody Variante detallesVariante) {
        return varianteRepository.findById(id)
            .map(variante -> {
                variante.setNombre(detallesVariante.getNombre());
                variante.setAumentoPrecio(detallesVariante.getAumentoPrecio());
                Variante varianteActualizada = varianteRepository.save(variante);
                return ResponseEntity.ok(varianteActualizada);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVariante(@PathVariable Long id) {
        if (varianteRepository.existsById(id)) {
            varianteRepository.deleteById(id);
            //se eliminó con exito
            return ResponseEntity.noContent().build();
        } else {
            //no se encuentro
            return ResponseEntity.notFound().build();
        }
    }
}
