package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMultimediaRepository extends JpaRepository<Multimedia, Long>{
}
