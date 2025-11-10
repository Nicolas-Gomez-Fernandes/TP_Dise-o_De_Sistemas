package ar.edu.frba.ddsi.servicio_fuente_proxy.controllers;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.IColeccionesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/proxy/coleccion")
public class ColeccionController {
  IColeccionesService coleccionesService;

  public ColeccionController(IColeccionesService coleccionesService){
    this.coleccionesService = coleccionesService;
  }

  @GetMapping()
  public List<ColeccionOutputDto> getColecciones(){
    return this.coleccionesService.getColecciones();
  }

  @GetMapping("/{id}/hechos")
  public List<HechoOutputDTO> getHechosByIdColeccion(
      @PathVariable String id,
      @RequestParam(required = false) String categoria,
      @RequestParam(required = false) LocalDateTime fechaReporteDesde,
      @RequestParam(required = false) LocalDateTime fechaReporteHasta,
      @RequestParam(required = false) LocalDateTime fechaAcotencimientoDesde,
      @RequestParam(required = false) LocalDateTime fechaAcotencimientoHasta,
      @RequestParam(required = false) Ubicacion ubicacion){
    return this.coleccionesService.getHechosByIdColeccion(id, new FiltroHechoMetamapa(categoria, fechaReporteDesde, fechaReporteHasta, fechaAcotencimientoDesde, fechaAcotencimientoHasta, ubicacion));
  }


}
