package ar.edu.frba.ddsi.servicio_fuente_dinamica.controllers;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.HechoInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IHechosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dinamica/hechos")
public class HechoController {
  
  @Autowired
  private IHechosService hechosService;

  public HechoController(IHechosService hechosService) {
    this.hechosService = hechosService;
  }

  @PostMapping
  public ResponseEntity<HechoOutputDTO> createHecho(@RequestBody HechoInputDTO inputDTO) {
      HechoOutputDTO hechoCreado = this.hechosService.crear(inputDTO);
      URI ubicacion = URI.create("/dinamica/hechos/" + hechoCreado.getId());
      return ResponseEntity.created(ubicacion).body(hechoCreado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHecho(@PathVariable("id") Long id) {
    this.hechosService.eliminar(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<HechoOutputDTO> getHechoById(@PathVariable ("id") Long id){
    return ResponseEntity.ok(this.hechosService.buscarPorId(id));
  }

  @GetMapping
  public ResponseEntity<List<HechoOutputDTO>> getHechos(){
    return ResponseEntity.ok(this.hechosService.buscarTodos());
  }

}
