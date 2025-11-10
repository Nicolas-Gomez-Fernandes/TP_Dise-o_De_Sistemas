package ar.edu.frba.ddsi.servicio_fuente_dinamica.controllers;


import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.MultimediaOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IGestionMultimediaService;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IMultimediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dinamica/multimedia")
public class MultimediaController {
  private IMultimediaService multimediaService;

  public MultimediaController(IMultimediaService multimediaService) {
    this.multimediaService = multimediaService;
  }

  @PostMapping
  public ResponseEntity<List<MultimediaOutputDTO>> uploadMultimedia(@RequestPart("file") List<MultipartFile> archivos) {
    return ResponseEntity.ok(this.multimediaService.guardarArchivos(archivos));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMultimedia(@PathVariable Long id) {
    this.multimediaService.eliminarArchivo(id);
    return ResponseEntity.noContent().build();
  }
}
