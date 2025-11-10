package ar.edu.frba.ddsi.servicio_fuente_proxy.service.impl;

import ar.edu.frba.ddsi.servicio_fuente_proxy.clients.APIMetamapaClient;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.ISolicitudDeEliminacionService;
import org.springframework.stereotype.Service;

@Service
public class SolicitudDeEliminacionService implements ISolicitudDeEliminacionService {
  APIMetamapaClient apiMetamapa;

  public SolicitudDeEliminacionService(APIMetamapaClient apiMetamapa) {
    this.apiMetamapa = apiMetamapa;
  }

  @Override
  public void crearSolicitudEliminacion(SolicitudEliminacionInputDTO solicitud){
    // TODO hacer la conversion aca en el service
    apiMetamapa.crearSolicitudDeEliminacion(solicitud);
  }
}
