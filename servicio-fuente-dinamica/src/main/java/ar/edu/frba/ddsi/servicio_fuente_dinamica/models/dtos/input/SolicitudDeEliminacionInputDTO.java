package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input;

import lombok.Data;

@Data
public class SolicitudDeEliminacionInputDTO {
  private Long id_hecho;
  private String fundamento;
}
