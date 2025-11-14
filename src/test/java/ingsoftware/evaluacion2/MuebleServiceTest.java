package ingsoftware.evaluacion2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ingsoftware.evaluacion2.Modelo.EstadoMueble;
import ingsoftware.evaluacion2.Modelo.Mueble;
import ingsoftware.evaluacion2.Repositorio.MuebleRepository;
import ingsoftware.evaluacion2.Servicio.MuebleService;

@ExtendWith(MockitoExtension.class)
public class MuebleServiceTest {

   @InjectMocks
    private MuebleService muebleService;

    @Mock
    private MuebleRepository muebleRepository;

    @Test
    void testCrearMueble_EstableceEstadoActivo() {
        Mueble nuevoMueble = new Mueble();
        nuevoMueble.setNombreMueble("Mesa de Centro");
        
        when(muebleRepository.save(any(Mueble.class))).thenReturn(nuevoMueble);

        Mueble resultado = muebleService.crearMueble(nuevoMueble);

        assertEquals(EstadoMueble.ACTIVO, resultado.getEstado(), 
                    "El estado del mueble debe ser ACTIVO por defecto al crearse.");
        verify(muebleRepository, times(1)).save(nuevoMueble);
    }
    @Test
    void testListarMueblesActivos_FiltraCorrectamente() {
        Mueble activo1 = new Mueble();
        activo1.setEstado(EstadoMueble.ACTIVO);
        
        Mueble inactivo1 = new Mueble();
        inactivo1.setEstado(EstadoMueble.INACTIVO);
        
        List<Mueble> mueblesActivos = List.of(activo1);

        when(muebleRepository.findByEstado(EstadoMueble.ACTIVO)).thenReturn(mueblesActivos);

        List<Mueble> resultado = muebleService.listarMueblesActivos();

        assertNotNull(resultado, "La lista no debe ser nula.");
        assertEquals(1, resultado.size(), "Solo debe haber 1 mueble activo.");
        assertEquals(EstadoMueble.ACTIVO, resultado.get(0).getEstado());
        
        verify(muebleRepository, times(1)).findByEstado(EstadoMueble.ACTIVO);
        verify(muebleRepository, never()).findAll();
    }

    @Test
    void testDesactivarMueble_CambiaEstadoAInactivo() {
        Long id = 5L;
        Mueble muebleActivo = new Mueble();
        muebleActivo.setIdMueble(id);
        muebleActivo.setEstado(EstadoMueble.ACTIVO);

        when(muebleRepository.findById(id)).thenReturn(Optional.of(muebleActivo));
        when(muebleRepository.save(any(Mueble.class))).thenReturn(muebleActivo);

        Mueble resultado = muebleService.desactivarMueble(id);

        assertEquals(EstadoMueble.INACTIVO, resultado.getEstado(), 
                    "El estado debe cambiar a INACTIVO.");
        verify(muebleRepository, times(1)).save(muebleActivo);
    }

    @Test
    void testDesactivarMueble_LanzaExcepcionSiNoExiste() {
        Long idInexistente = 99L;
        when(muebleRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            muebleService.desactivarMueble(idInexistente);
        }, "Debe lanzar una RuntimeException si el mueble no se encuentra.");
        
        verify(muebleRepository, never()).save(any(Mueble.class));
    }

}
