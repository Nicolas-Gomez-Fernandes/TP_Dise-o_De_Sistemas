package ar.edu.frba.ddsi.servicio_fuente_dinamica.controllers;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.SolicitudDeEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.SolicitudEliminacionOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.ISolicitudEliminacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dinamica/solicitudes")
public class SolicitudDeEliminacionController {
  private ISolicitudEliminacionService solicitudDeEliminacionService;

  public SolicitudDeEliminacionController(ISolicitudEliminacionService solicitudService) {
    this.solicitudDeEliminacionService = solicitudService;
  }

  @PostMapping
  public ResponseEntity<SolicitudEliminacionOutputDTO> crearSolicitudDeEliminacion(@RequestBody SolicitudDeEliminacionInputDTO inputDTO) {
    return ResponseEntity.ok(this.solicitudDeEliminacionService.crearSolicitudDeElminicacion(inputDTO));
  }


  @GetMapping
  public ResponseEntity<List<SolicitudEliminacionOutputDTO>> getSolicitudesDeEliminacion() {
    return ResponseEntity.ok(this.solicitudDeEliminacionService.buscarTodos());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Void> actualizarEstadoDeSolicitud(@PathVariable Long id, @RequestParam boolean aceptado) {
    this.solicitudDeEliminacionService.actualizarEstadoDeSolicitud(id, aceptado);
    return ResponseEntity.noContent().build();
  }


}
