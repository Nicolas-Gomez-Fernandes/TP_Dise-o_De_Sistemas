package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI;

import lombok.Data;

import java.util.List;


@Data
public class PaginaResponseDTO {
  private Integer current_page;
  private Integer last_page;
  private String next_page_url;
  private List<HechoAPICatedraResponseDTO> data;
}
