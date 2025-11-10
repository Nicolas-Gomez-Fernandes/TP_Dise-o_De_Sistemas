package ar.edu.frba.ddsi.servicio_fuente_proxy.controllers;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.ISolicitudDeEliminacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/Solicitudes")
public class  SolicitudDeEliminacionController{
  private ISolicitudDeEliminacionService SolicitudDeEliminacionService;

  public SolicitudDeEliminacionController(ISolicitudDeEliminacionService SolicitudDeEliminacionService){
    this.SolicitudDeEliminacionService = SolicitudDeEliminacionService;
  }

  @PostMapping()
  public void crearSolicitudDeEliminacion(@RequestParam(required = false) SolicitudEliminacionInputDTO solicitud){
    this.SolicitudDeEliminacionService.crearSolicitudEliminacion(solicitud);
  }

}