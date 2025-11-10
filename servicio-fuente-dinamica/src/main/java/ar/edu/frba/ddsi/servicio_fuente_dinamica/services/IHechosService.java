package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.HechoInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.HechoOutputDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHechosService {
  List<HechoOutputDTO> buscarTodos();
  HechoOutputDTO buscarPorId(Long id);
  HechoOutputDTO crear(HechoInputDTO hechoInputDTO);
  void eliminar(Long id);
}
