package ingsoftware.evaluacion2.Repositorio;

import ingsoftware.evaluacion2.Modelo.EstadoMueble;
import ingsoftware.evaluacion2.Modelo.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Long> {
    // listar solo los muebles que tengan ese estado
    List<Mueble> findByEstado(EstadoMueble estado);
}
