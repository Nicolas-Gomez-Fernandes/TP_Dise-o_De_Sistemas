package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.CategoriaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
