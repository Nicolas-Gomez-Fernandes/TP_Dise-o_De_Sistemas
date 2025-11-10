package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.SolicitudEliminacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolicitudEliminacionRepository extends JpaRepository<SolicitudEliminacion, Long> {
}
