package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.CategoriaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.ICategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class CategoriaRepository{
private final List<Categoria> categorias;
private Long nextId = 1L;

public CategoriaRepository() {
    this.categorias = new ArrayList<Categoria>();
  }

  public Categoria buscarPorId(Long id) {
   return this.categorias.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .orElse(null);
  }
  public void guardar(Categoria categoria) {
    categoria.setId(nextId++);
    this.categorias.add(categoria);
  }
  public void borrar(Categoria categoria) {
    this.categorias.remove(categoria);
  }
  public List<Categoria> buscarTodos() {
    return this.categorias;
  }
}
