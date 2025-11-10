package ar.edu.frba.ddsi.servicio_fuente_proxy.service.impl;

import ar.edu.frba.ddsi.servicio_fuente_proxy.clients.APICatedraClient;
import ar.edu.frba.ddsi.servicio_fuente_proxy.clients.APIMetamapaClient;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;
import ar.edu.frba.ddsi.servicio_fuente_proxy.service.IHechosService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class HechosService implements IHechosService {
  APIMetamapaClient apiMetamapa;
  APICatedraClient apiCatedra;

  public HechosService(APIMetamapaClient apiMetamapa, APICatedraClient apiCatedra) {
    this.apiMetamapa = apiMetamapa;
    this.apiCatedra = apiCatedra;
  }


  @Override
  public List<HechoOutputDTO> getHechos(FiltroHechoMetamapa filtroHechoMetamapa) {

    List<HechoOutputDTO> hechosDeApis = apiMetamapa.getHechos(filtroHechoMetamapa);
    hechosDeApis.addAll(apiCatedra.getHechos());
    return hechosDeApis;
  }
}
