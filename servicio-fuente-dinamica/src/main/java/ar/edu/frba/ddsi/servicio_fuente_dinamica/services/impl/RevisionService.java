package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Hecho;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.revision.EstadoRevision;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.impl.HechosRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IRevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevisionService implements IRevisionService {
  private final HechosRepository hechosRepository;

  @Override
  public void revisarHecho(Long hechoId, EstadoRevision nuevoEstado, String comentarios) {
    Hecho hecho = hechosRepository.buscarPorId(hechoId);

    hecho.setEstadoRevision(nuevoEstado);
    hecho.setComentariosRevision(comentarios);

    hechosRepository.guardar(hecho);
  }

  @Override
  public void solicitarRevision(Long hechoId, String motivoSolicitud) {
    Hecho hecho = hechosRepository.buscarPorId(hechoId);

    hecho.setEstadoRevision(EstadoRevision.PENDIENTE);
    hecho.setComentariosRevision(motivoSolicitud);

    hechosRepository.guardar(hecho);
  }
}
