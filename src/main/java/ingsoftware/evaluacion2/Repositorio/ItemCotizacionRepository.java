package ingsoftware.evaluacion2.Repositorio;

import ingsoftware.evaluacion2.Modelo.ItemCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCotizacionRepository extends JpaRepository<ItemCotizacion, Long> {
}
