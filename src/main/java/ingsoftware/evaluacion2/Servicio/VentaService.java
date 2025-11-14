package ingsoftware.evaluacion2.Servicio;

import ingsoftware.evaluacion2.Modelo.*;
import ingsoftware.evaluacion2.Repositorio.CotizacionRepository;
import ingsoftware.evaluacion2.Repositorio.ItemCotizacionRepository;
import ingsoftware.evaluacion2.Repositorio.MuebleRepository;
import ingsoftware.evaluacion2.Repositorio.VarianteRepository;
import ingsoftware.evaluacion2.dto.CotizacionRequestDTO;
import ingsoftware.evaluacion2.dto.ItemCotizacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private ItemCotizacionRepository itemCotizacionRepository;

    @Autowired
    private MuebleRepository muebleRepository;

    @Autowired
    private VarianteRepository varianteRepository;

    @Transactional 
    public Cotizacion crearCotizacion(CotizacionRequestDTO requestDTO) {

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setFecha(LocalDate.now());
        cotizacion.setEstado(EstadoCotizacion.PENDIENTE);

        Cotizacion cotizacionGuardada = cotizacionRepository.save(cotizacion);
        List<ItemCotizacion> items = new ArrayList<>();

        for (ItemCotizacionDTO itemDTO : requestDTO.getItems()) {
            Mueble mueble = muebleRepository.findById(itemDTO.getMuebleId())
                    .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            Variante variante = varianteRepository.findById(itemDTO.getVarianteId())
                    .orElseThrow(() -> new RuntimeException("Variante no encontrada"));

            Calculable muebleConPrecio = mueble; 
            muebleConPrecio = new VarianteDinamicaDecorator(muebleConPrecio, variante);
            double precioFinal = muebleConPrecio.calcularPrecio();
   
            ItemCotizacion item = new ItemCotizacion();
            item.setMueble(mueble);
            item.setVariante(variante);
            item.setCantidad(itemDTO.getCantidad());
            item.setCotizacion(cotizacionGuardada);
            item.setPrecioUnitarioFinal(precioFinal);

            items.add(itemCotizacionRepository.save(item));
        }

        cotizacionGuardada.setItems(items);
        return cotizacionGuardada;
    }

    @Transactional
    public Cotizacion confirmarVenta(Long idCotizacion) {

        Cotizacion cotizacion = cotizacionRepository.findById(idCotizacion)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        if (cotizacion.getEstado() == EstadoCotizacion.VENDIDA) {
            throw new RuntimeException("Esta cotización ya fue confirmada como venta.");
        }

        for (ItemCotizacion item : cotizacion.getItems()) {
            Mueble mueble = item.getMueble();
            if (mueble.getStock() < item.getCantidad()) {
                throw new RuntimeException("stock insuficiente para " + mueble.getNombreMueble());
            }
        }

        for (ItemCotizacion item : cotizacion.getItems()) {
            Mueble mueble = item.getMueble();
            int nuevoStock = mueble.getStock() - item.getCantidad();
            mueble.setStock(nuevoStock);
        }

        cotizacion.setEstado(EstadoCotizacion.VENDIDA);
        return cotizacionRepository.save(cotizacion);
    }
    
    public Optional<Cotizacion> obtenerCotizacionPorId(Long id) {
        return cotizacionRepository.findById(id);
    }

    public List<Cotizacion> listarTodasLasCotizaciones() {
        return cotizacionRepository.findAll();
    }
}
