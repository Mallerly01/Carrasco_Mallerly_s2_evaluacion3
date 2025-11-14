package ingsoftware.evaluacion2.Repositorio;
import ingsoftware.evaluacion2.Modelo.Variante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianteRepository extends JpaRepository<Variante, Long> {
}
