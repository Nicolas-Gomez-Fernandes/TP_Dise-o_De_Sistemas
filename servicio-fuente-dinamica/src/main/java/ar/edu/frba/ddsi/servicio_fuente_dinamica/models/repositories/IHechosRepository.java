package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Hecho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IHechosRepository extends JpaRepository<Hecho, Long> {
  Boolean existsByTitulo(String titulo);
  Hecho findByTitulo(String titulo);
}
