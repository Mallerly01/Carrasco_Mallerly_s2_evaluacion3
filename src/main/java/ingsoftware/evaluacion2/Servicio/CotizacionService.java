package ingsoftware.evaluacion2.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ingsoftware.evaluacion2.Modelo.Calculable;
import ingsoftware.evaluacion2.Modelo.Variante;
import ingsoftware.evaluacion2.Modelo.VarianteDinamicaDecorator;
import ingsoftware.evaluacion2.Repositorio.MuebleRepository;
import ingsoftware.evaluacion2.Repositorio.VarianteRepository;

@Service
public class CotizacionService {

    @Autowired
    private MuebleRepository muebleRepository;
    
    @Autowired
    private VarianteRepository varianteRepository;

    public double crearCotizacion(Long muebleId, List<Long> variantesIds) {
        
        Calculable muebleCotizado = muebleRepository.findById(muebleId)
                            .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));
        
        for (Long varianteId : variantesIds) {
            
            Variante varianteData = varianteRepository.findById(varianteId)
                                    .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
            
            muebleCotizado = new VarianteDinamicaDecorator(muebleCotizado, varianteData);
        }

        System.out.println("Descripción: " + muebleCotizado.getDescripcion());
        return muebleCotizado.calcularPrecio();
    }
}