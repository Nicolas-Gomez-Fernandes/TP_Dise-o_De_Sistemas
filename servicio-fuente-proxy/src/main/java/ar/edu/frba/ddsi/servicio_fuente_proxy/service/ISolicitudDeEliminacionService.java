package ar.edu.frba.ddsi.servicio_fuente_proxy.service;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;

public interface ISolicitudDeEliminacionService {
  void crearSolicitudEliminacion(SolicitudEliminacionInputDTO solicitud);
}
