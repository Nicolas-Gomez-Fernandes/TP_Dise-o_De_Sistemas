package ar.edu.frba.ddsi.servicio_fuente_proxy.clients;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI.HechoAPICatedraResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI.LoginResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI.PaginaResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.UsuarioApiCatedraInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.mapper.HechoMapper;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class APICatedraClient {

  private WebClient webClient;

  public APICatedraClient(WebClient.Builder webClientBuilder, @Value("${app.base-url-ApiCatedra}") String url, @Value("${app.token-ApiCatedra}") String token) {
    this.webClient = webClientBuilder.baseUrl(url).defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
  }

  public LoginResponseDTO login(UsuarioApiCatedraInputDTO usuario) {
    return this.webClient.post()
        .uri("/api/login")
        .bodyValue(usuario)
        .retrieve()
        .bodyToMono(LoginResponseDTO.class)
        .block();
  }

  public HechoOutputDTO getDesastreById(Integer id){
    return webClient
        .get()
        .uri("/api/desastres/{id}",id)
        .retrieve()
        .bodyToMono(HechoAPICatedraResponseDTO.class)
        .map(HechoMapper.INSTANCE::hechoAPICatedraResponseDTOToHechoOutputDTO)
        .block();
  }

  public List<HechoOutputDTO> getHechosByPage(Integer currentPage){
    return webClient
        .get()
        .uri("/api/desastres?page={page}",currentPage)
        .retrieve()
        .bodyToMono(PaginaResponseDTO.class)
        .map(pag -> pag.getData().stream()
            .map(HechoMapper.INSTANCE::hechoAPICatedraResponseDTOToHechoOutputDTO)
            .collect(Collectors.toList()))
        .block();
  }

  public List<HechoOutputDTO> getHechos(){

    List<HechoAPICatedraResponseDTO> hechos = new ArrayList<>();
    PaginaResponseDTO pagina = webClient.get().uri("/api/desastres?page=1").retrieve().bodyToMono(PaginaResponseDTO.class).block();
    String url_next_page = pagina.getNext_page_url();

    // Mientras haya pagina siguiente
    while(url_next_page != null){

      pagina = webClient.get().uri(url_next_page).retrieve().bodyToMono(PaginaResponseDTO.class).block();
      assert pagina != null;
      url_next_page = pagina.getNext_page_url();

      hechos.addAll(pagina.getData());

    }

    return hechos.stream().map(HechoMapper.INSTANCE::hechoAPICatedraResponseDTOToHechoOutputDTO).collect(Collectors.toList());
  }
}
