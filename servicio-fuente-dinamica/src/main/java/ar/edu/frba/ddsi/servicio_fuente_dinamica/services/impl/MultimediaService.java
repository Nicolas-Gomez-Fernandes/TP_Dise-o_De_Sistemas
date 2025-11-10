package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.exceptions.multimedia.MultimediaNotFoundException;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.exceptions.multimedia.MultimediaOperationException;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.MultimediaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IMultimediaRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IMultimediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@Service
public class MultimediaService implements IMultimediaService {
  @Value("${multimedia.ruta-base}")
  private String rutaBase;

  private IMultimediaRepository multimediaRepository;

  public MultimediaService(IMultimediaRepository multimediaRepository) {
    this.multimediaRepository = multimediaRepository;
  }

  @Override
  public List<MultimediaOutputDTO> guardarArchivos(List<MultipartFile> multimedia) {
    return multimedia.stream().map(this::guardarArchivo).toList();
  }

  public MultimediaOutputDTO guardarArchivo(MultipartFile archivo) {
    if (archivo == null || archivo.isEmpty()) {
      throw new IllegalArgumentException("El archivo no puede ser nulo o vacío");
    }

    try {
      // Generar nombre único para el archivo
      String nombreOriginal = archivo.getOriginalFilename();
      String extension = "";
      if (nombreOriginal != null && nombreOriginal.lastIndexOf(".") > 0) {
        extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
      }
      String nombreUnico = java.util.UUID.randomUUID() + extension;

      // Crear directorio si no existe
      Path uploadPath = Path.of(rutaBase);
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      // Guardar archivo con nombre único
      Path filePath = uploadPath.resolve(nombreUnico);
      Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

      // Crear y guardar entidad
      Multimedia multimedia = new Multimedia();
      multimedia.setRutaArchivo(filePath.toString());
      String tipoArchivo = archivo.getContentType();
      multimedia.setTipoArchivo(tipoArchivo != null ? tipoArchivo : "application/octet-stream");

      log.info("Archivo guardado en: {}", filePath);
      return mapToDTO(multimediaRepository.save(multimedia));
    } catch (IOException e) {
      throw new RuntimeException("Error al guardar el archivo: " + e.getMessage(), e);
    }
  }

  @Override
  public void eliminarArchivo(Long id) {
    Multimedia multimedia = multimediaRepository.findById(id)
        .orElseThrow(() -> new MultimediaNotFoundException("No se encontró el archivo multimedia con id: " + id));

    try {
      Path filePath = Path.of(multimedia.getRutaArchivo());
      Files.deleteIfExists(filePath);
      multimediaRepository.delete(multimedia);
    } catch (IOException e) {
      throw new MultimediaOperationException("Error al eliminar el archivo: " + e.getMessage(), e);
    }
  }

  private MultimediaOutputDTO mapToDTO(Multimedia multimedia) {
    return new MultimediaOutputDTO(
        multimedia.getId(),
        multimedia.getRutaArchivo(),
        multimedia.getTipoArchivo()
    );
  }


}
