package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGestionMultimediaService {
  Multimedia guardarArchivo(MultipartFile archivo);
  void eliminarArchivo(String nombreArchivo);
  boolean validarArchivo(MultipartFile archivo);
  boolean existeArchivo(String nombreArchivo);
  List<Multimedia> guardarArchivos(List<MultipartFile> archivos);
}

