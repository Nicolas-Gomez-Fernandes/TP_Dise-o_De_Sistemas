package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Hecho;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IHechosRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class HechosRepository{
  private final List<Hecho> hechos;
  private Long nextId = 1L;

  public HechosRepository() {
    this.hechos = new ArrayList<Hecho>();
  }



  public Hecho buscarPorId(Long id) {
    return this.hechos.stream()
        .filter(h -> h.getId().equals(id))
        .findFirst()
        .orElse(null);
  }


  public void guardar(Hecho hecho) {
    hecho.setId(nextId++);
    this.hechos.add(hecho);
  }


  public void borrar(Hecho hecho) {
    this.hechos.remove(hecho);
  }


  public List<Hecho> buscarTodos() {
    return this.hechos;
  }
}
