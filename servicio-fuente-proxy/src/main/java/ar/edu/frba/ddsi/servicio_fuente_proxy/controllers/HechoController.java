package ar.edu.frba.ddsi.servicio_fuente_proxy.controllers;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.IHechosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/proxy/hechos")
public class HechoController {
  private IHechosService hechosService;

  public HechoController(IHechosService hechosService){
    this.hechosService = hechosService;
  }

  @GetMapping()
  public List<HechoOutputDTO> getHechos(
      @RequestParam(required = false) String categoria,
      @RequestParam(required = false) LocalDateTime fechaReporteDesde,
      @RequestParam(required = false) LocalDateTime fechaReporteHasta,
      @RequestParam(required = false) LocalDateTime fechaAcotencimientoDesde,
      @RequestParam(required = false) LocalDateTime fechaAcotencimientoHasta,
      @RequestParam(required = false) Ubicacion ubicacion
  ){
    return this.hechosService.getHechos(new FiltroHechoMetamapa(categoria, fechaReporteDesde, fechaReporteHasta, fechaAcotencimientoDesde, fechaAcotencimientoHasta, ubicacion));
  }

  

  //TODO HACER MODULARIZACION DE LLAMADAS A 1 SOLA API
}
