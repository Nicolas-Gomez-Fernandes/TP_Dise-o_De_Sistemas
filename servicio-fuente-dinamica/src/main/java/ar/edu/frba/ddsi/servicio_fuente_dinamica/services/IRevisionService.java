package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.revision.EstadoRevision;

public interface IRevisionService {
  void revisarHecho(Long hechoId, EstadoRevision nuevoEstado, String comentarios);
  void solicitarRevision(Long hechoId, String motivoSolicitud) ;
}
