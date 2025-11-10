package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.SolicitudDeEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.SolicitudEliminacionOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Hecho;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.SolicitudEliminacion;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.solicitudEliminacion.Estado;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.solicitudEliminacion.EstadoYHora;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IHechosRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.ISolicitudEliminacionRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.ISolicitudEliminacionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudEliminacionService implements ISolicitudEliminacionService {
  ISolicitudEliminacionRepository solicitudEliminacionRepository;
  IHechosRepository hechosRepository;

  public SolicitudEliminacionService(ISolicitudEliminacionRepository solicitudEliminacionRepository, IHechosRepository hechosRepository) {
    this.solicitudEliminacionRepository = solicitudEliminacionRepository;
    this.hechosRepository = hechosRepository;
  }

  @Override
  public SolicitudEliminacionOutputDTO crearSolicitudDeElminicacion(SolicitudDeEliminacionInputDTO solicitudInputDTO) {
    if(solicitudInputDTO.getFundamento().length()>500) {
      throw new IllegalArgumentException("El fundamento no puede tener más de 500 caracteres");
    }

    Hecho hecho = this.hechosRepository.findById(solicitudInputDTO.getId_hecho())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada con ID: " + solicitudInputDTO.getId_hecho()));

    SolicitudEliminacion nuevaSolicitud = new SolicitudEliminacion();

    nuevaSolicitud.setHistorialEstados(new ArrayList<>());
    nuevaSolicitud.setHechoAeliminar(hecho);
    nuevaSolicitud.setFundamento(solicitudInputDTO.getFundamento());
    nuevaSolicitud.setFechaCreacion(LocalDateTime.now());
    this.agregarHistorialEstadoPara(Estado.PENDIENTE, nuevaSolicitud);

    this.solicitudEliminacionRepository.save(nuevaSolicitud);

    return this.solicitudToDTO(nuevaSolicitud);
  }

  @Override
  public void aceptarSolicitudDeEliminacion(Long id) {
    var solicitudEliminacion = this.solicitudEliminacionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

    this.agregarHistorialEstadoPara(Estado.ACEPTADA, solicitudEliminacion);
    this.solicitudEliminacionRepository.save(solicitudEliminacion);
  }

  @Override
  public void rechazarSolicitudDeEliminacion(Long id) {
    var solicitudEliminacion = this.solicitudEliminacionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

    this.agregarHistorialEstadoPara(Estado.RECHAZADA, solicitudEliminacion);
    this.solicitudEliminacionRepository.save(solicitudEliminacion);
  }

  @Override
  public void actualizarEstadoDeSolicitud(Long id, boolean aceptado) {
    if(aceptado) {
      this.aceptarSolicitudDeEliminacion(id);
    } else {
      this.rechazarSolicitudDeEliminacion(id);
    }
  }

  private void agregarHistorialEstadoPara(Estado nuevoEstado, SolicitudEliminacion solicitudEliminacion) {
    solicitudEliminacion.setEstado(nuevoEstado);
    EstadoYHora estadoHoraActual = new EstadoYHora(nuevoEstado, LocalDateTime.now());
    solicitudEliminacion.getHistorialEstados().add(estadoHoraActual);
  }

  @Override
  public List<SolicitudEliminacionOutputDTO> buscarTodos() {
    List<SolicitudEliminacion> solicitudes = this.solicitudEliminacionRepository.findAll();
    return solicitudes.stream().map(this::solicitudToDTO).toList();
  }

  private SolicitudEliminacionOutputDTO solicitudToDTO(SolicitudEliminacion solicitudEliminacion) {

    return new SolicitudEliminacionOutputDTO(
        solicitudEliminacion.getId(),
        solicitudEliminacion.getHechoAeliminar().getId(),
        solicitudEliminacion.getFundamento(),
        solicitudEliminacion.getEstado(),
        solicitudEliminacion.getFechaCreacion()
    );
  }
}
