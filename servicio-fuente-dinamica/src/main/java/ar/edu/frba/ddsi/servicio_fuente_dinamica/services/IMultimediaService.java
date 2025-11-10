package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.MultimediaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMultimediaService {
  List<MultimediaOutputDTO> guardarArchivos(List<MultipartFile> multimedia);

  void eliminarArchivo(Long id);
}
