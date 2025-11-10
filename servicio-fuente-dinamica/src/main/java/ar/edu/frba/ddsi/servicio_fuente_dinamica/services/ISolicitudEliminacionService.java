package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;


import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.SolicitudDeEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.SolicitudEliminacionOutputDTO;

import java.util.List;

public interface ISolicitudEliminacionService {
  SolicitudEliminacionOutputDTO crearSolicitudDeElminicacion(SolicitudDeEliminacionInputDTO solicitudInputDTO);
  void aceptarSolicitudDeEliminacion(Long id);
  void rechazarSolicitudDeEliminacion(Long id);
  void actualizarEstadoDeSolicitud(Long id, boolean aceptado);
  List<SolicitudEliminacionOutputDTO> buscarTodos();

}
