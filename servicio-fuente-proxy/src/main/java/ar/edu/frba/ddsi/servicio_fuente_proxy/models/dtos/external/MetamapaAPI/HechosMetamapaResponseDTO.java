package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.MetamapaAPI;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import lombok.Data;

import java.util.List;

@Data
public class HechosMetamapaResponseDTO {
  private List<HechoOutputDTO> hechos;
}
