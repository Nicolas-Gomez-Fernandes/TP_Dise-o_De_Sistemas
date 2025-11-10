package ar.edu.frba.ddsi.servicio_fuente_proxy.clients;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.MetamapaAPI.ColeccionResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.MetamapaAPI.HechosMetamapaResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.OrigenFuenteDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class APIMetamapaClient implements IAdapterAPIMetamapa {
  private WebClient webClient;

  public APIMetamapaClient(WebClient.Builder webClientBuilder, @Value("${app.base-url-Metamapa}") String url) {
    this.webClient = webClientBuilder.baseUrl(url).build();
  }

  //TODO HAY QUE VER COMO LLAMAR A LA UBICACION
  @Override
  public List<HechoOutputDTO> getHechos(FiltroHechoMetamapa filtro) {
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/hechos")
            .queryParam("categoria", filtro.getCategoria())
            .queryParam("fecha_reporte_desde", filtro.getFecha_reporte_desde())
            .queryParam("fecha_reporte_hasta", filtro.getFecha_acontecimiento_hasta())
            .queryParam("fecha_acontencimiento_desde", filtro.getFecha_acontecimiento_desde())
            .queryParam("fecha_acontecimiento_hasta", filtro.getFecha_acontecimiento_hasta())
            .queryParam("ubicacion", filtro.getUbicacion())
            .build())
        .retrieve()
        .bodyToMono(HechosMetamapaResponseDTO.class)
        .map(resp -> resp.getHechos().stream()
            .peek(h ->{
                h.setOrigen(new OrigenFuenteDTO("METAMAPA", "PROXY"));
                h.setEliminado(false); // fuerza false desde el prox
            })
            .collect(Collectors.toList()))
            /*
            peek() es una operación del Stream que te permite "espiar" cada elemento a medida que pasa por el flujo y realizar una acción sobre él.
            */
        .block();
  }

  public List<ColeccionOutputDto> getColecciones() {
    return webClient
        .get()
        .uri("/colecciones")
        .retrieve()
        .bodyToMono(ColeccionResponseDTO.class)
        .map(ColeccionResponseDTO::getColecciones)
        .block();
  }

  @Override
  public List<HechoOutputDTO> getHechosDeColeccion(String id, FiltroHechoMetamapa filtro) {
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/colecciones/" + id + "/hechos")
            .queryParam("categoria", filtro.getCategoria())
            .queryParam("fecha_reporte_desde", filtro.getFecha_reporte_desde())
            .queryParam("fecha_reporte_hasta", filtro.getFecha_acontecimiento_hasta())
            .queryParam("fecha_acontencimiento_desde", filtro.getFecha_acontecimiento_desde())
            .queryParam("fecha_acontecimiento_hasta", filtro.getFecha_acontecimiento_hasta())
            .queryParam("ubicacion", filtro.getUbicacion())
            .build())
        .retrieve()
        .bodyToMono(HechosMetamapaResponseDTO.class)
        .map(resp -> resp.getHechos().stream()
            .peek(h ->{
                h.setOrigen(new OrigenFuenteDTO("METAMAPA", "PROXY"));
                h.setEliminado(false); // fuerza false desde el prox)
                })
            .collect(Collectors.toList()))/*
            peek() es una operación del Stream que te permite "espiar" cada elemento a medida que pasa por el flujo y realizar una acción sobre él.
            */
        .block();
  }

  //TODO ANALIZAR QUE SE DEVUELVE DESPUES -> EL ESTADO DEL HECHO?
  @Override
  public void crearSolicitudDeEliminacion(SolicitudEliminacionInputDTO solicitud) {
    webClient.post()
        .uri("/solicitudes")
        .bodyValue(solicitud)
        .retrieve()
        .toBodilessEntity()
        .block();
  }
}