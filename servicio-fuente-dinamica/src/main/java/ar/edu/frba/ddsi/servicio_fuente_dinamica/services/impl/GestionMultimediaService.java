package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IMultimediaRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IGestionMultimediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Service
public class GestionMultimediaService implements IGestionMultimediaService {
  @Autowired
  private IMultimediaRepository multimediaRepository;

  @Value("${multimedia.upload.dir}")
  private String dir_multimedia;

  private static final String RUTA_BASE = "";


  //private static final long TAMANIO_MAXIMO = 10_000_000; // 10MB

  @Override
  public Multimedia guardarArchivo(MultipartFile archivo) { // Pensamos que la multimedia va a ser particular por cada hecho
    Multimedia multimedia = new Multimedia();

    // TODO JOACO guardar el archivo en el package
    if (archivo == null || archivo.isEmpty()) {
      throw new IllegalArgumentException("El archivo está vacío o es nulo");
    }

    if (!validarArchivo(archivo)) {
      throw new IllegalArgumentException("El archivo no cumple con las validaciones necesarias");
    }

    try {
      Path uploadPath = Paths.get(RUTA_BASE);
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      Path filePath = uploadPath.resolve(archivo.getOriginalFilename());
      Files.copy(archivo.getInputStream(), filePath);



      //TODO DEVOVLER UN MULTIMEDIA NO LA RUTA
      multimedia.setRutaArchivo(RUTA_BASE + archivo.getOriginalFilename());
      multimedia.setTipoArchivo(archivo.getContentType());

      multimediaRepository.save(multimedia);
      return multimedia;

    } catch (IOException e){
      throw new RuntimeException("Error al guardar el archivo: " + e.getMessage(), e);
    }
  }

  @Override
  public void eliminarArchivo(String nombreArchivo) {
    if (nombreArchivo == null || nombreArchivo.isEmpty()) {
      throw new IllegalArgumentException("El nombre del archivo es inválido");
    }

    try {
      Path rutaCompleta = obtenerRutaCompleta(nombreArchivo);
      if (!Files.exists(rutaCompleta)) {
        throw new FileNotFoundException("El archivo no existe: " + nombreArchivo);
      }
      Files.delete(rutaCompleta);
    } catch (IOException e) {
      throw new RuntimeException("Error al eliminar el archivo: " + e.getMessage(), e);
    }
  }

  @Override
  public boolean validarArchivo(MultipartFile archivo) {
    if (archivo == null) {
      return false;
    }


//    // Validar tamaño
//    if (archivo.getSize() > TAMANIO_MAXIMO) {
//      return false;
//    }

    // Validar nombre de archivo
    String nombreOriginal = archivo.getOriginalFilename();
    if (nombreOriginal == null || nombreOriginal.contains("..")) {
      return false;
    }

    return true;
  }

  @Override
  public boolean existeArchivo(String nombreArchivo) {
    if (nombreArchivo == null || nombreArchivo.isEmpty()) {
      return false;
    }
    return Files.exists(obtenerRutaCompleta(nombreArchivo));
  }

  @Override
  public List<Multimedia> guardarArchivos(List<MultipartFile> archivos) {
    return archivos.stream().map(this:: guardarArchivo).toList();
  }

  private Path obtenerRutaCompleta(String nombreArchivo) {
    return Paths.get(RUTA_BASE).resolve(nombreArchivo).normalize();
  }


  private String obtenerExtension(String nombreArchivo) {
    if (nombreArchivo == null) {
      return "";
    }
    int ultimoPunto = nombreArchivo.lastIndexOf(".");
    return (ultimoPunto > 0) ? nombreArchivo.substring(ultimoPunto) : "";
  }

  public String getDir_multimedia() {
    return dir_multimedia;
  }

  public void setDir_multimedia(String dir_multimedia) {
    this.dir_multimedia = dir_multimedia;
  }
}

