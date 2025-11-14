package ingsoftware.evaluacion2.Controlador;

import ingsoftware.evaluacion2.Modelo.Cotizacion;
import ingsoftware.evaluacion2.Servicio.VentaService;
import ingsoftware.evaluacion2.dto.CotizacionRequestDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/cotizar")
    public ResponseEntity<Cotizacion> crearCotizacion(@RequestBody CotizacionRequestDTO requestDTO) {
        Cotizacion cotizacion = ventaService.crearCotizacion(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotizacion);
    }

    @PostMapping("/confirmar/{idCotizacion}")
    public ResponseEntity<Cotizacion> confirmarVenta(@PathVariable Long idCotizacion) {
        Cotizacion cotizacion = ventaService.confirmarVenta(idCotizacion);
        return ResponseEntity.ok(cotizacion);
    }

    @GetMapping("/{idCotizacion}")
    public ResponseEntity<Cotizacion> obtenerCotizacionPorId(@PathVariable Long idCotizacion) {
        // Asumiendo que VentaService tiene un método para buscar por ID
        return ventaService.obtenerCotizacionPorId(idCotizacion)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cotizacion>> listarTodasLasCotizaciones() {
        // Asumiendo que VentaService tiene un método para listar todas
        List<Cotizacion> cotizaciones = ventaService.listarTodasLasCotizaciones();
        return ResponseEntity.ok(cotizaciones);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Filtramos por el mensaje de error específico de stock
        if (ex.getMessage().startsWith("stock insuficiente")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        // Otros errores
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
