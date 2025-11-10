package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.CategoriaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.ICategoriaRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {
  private ICategoriaRepository categoriaRepository;

  CategoriaService(ICategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public CategoriaOutputDTO buscarPorId(Long id) {
    Categoria categoria = this.categoriaRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada con id: " + id));
    return this.categoriaOutputDTO(categoria);
  }

  @Override
  public List<CategoriaOutputDTO> buscarTodos() {
    return this.categoriaRepository
        .findAll()
        .stream()
        .map(this :: categoriaOutputDTO)
        .toList();
  }

  @Override
  public Long crear(String inputDTO) {
    if (inputDTO == null) {
      throw new IllegalArgumentException("El nombre de la categoría no puede ser null");
    }

    Categoria categoria = new Categoria(inputDTO);
    this.categoriaRepository.save(categoria);

    return categoria.getId();
  }

  @Override
  public void eliminar(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("El id no puede ser null");
    }

    boolean existe = this.categoriaRepository.existsById(id);
    if (!existe) {
      throw new IllegalArgumentException("No existe una categoría con id: " + id);
    }

    this.categoriaRepository.deleteById(id);
  }

  private CategoriaOutputDTO categoriaOutputDTO(Categoria categoria) {
    return new CategoriaOutputDTO(
        categoria.getId(),
        categoria.getNombre());
  }
}
