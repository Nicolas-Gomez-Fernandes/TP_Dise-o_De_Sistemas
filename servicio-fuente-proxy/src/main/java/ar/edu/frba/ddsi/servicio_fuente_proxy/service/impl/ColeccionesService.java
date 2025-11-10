package ar.edu.frba.ddsi.servicio_fuente_proxy.service.impl;

import ar.edu.frba.ddsi.servicio_fuente_proxy.clients.APIMetamapaClient;
import ar.edu.frba.ddsi.servicio_fuente_proxy.clients.IAdapterAPIMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.IColeccionesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColeccionesService implements IColeccionesService {
  IAdapterAPIMetamapa apiMetamapa;

  public ColeccionesService(APIMetamapaClient apiMetamapa) {
    this.apiMetamapa = apiMetamapa;
  }

  @Override
  public List<ColeccionOutputDto> getColecciones() {
    return apiMetamapa.getColecciones();
  }

  @Override
  public List<HechoOutputDTO> getHechosByIdColeccion(String id, FiltroHechoMetamapa filtroHechoMetamapa) {
    return apiMetamapa.getHechosDeColeccion(id, filtroHechoMetamapa);
  }
}
