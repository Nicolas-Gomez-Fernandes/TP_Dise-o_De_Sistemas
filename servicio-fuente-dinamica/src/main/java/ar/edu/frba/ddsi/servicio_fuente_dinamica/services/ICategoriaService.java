package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.CategoriaOutputDTO;
import java.util.List;

public interface ICategoriaService {
  CategoriaOutputDTO buscarPorId(Long id);
  List<CategoriaOutputDTO> buscarTodos();
  Long crear(String nombre);
  void eliminar(Long id);
}