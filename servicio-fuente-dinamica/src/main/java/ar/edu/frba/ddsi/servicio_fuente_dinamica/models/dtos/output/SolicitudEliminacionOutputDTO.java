package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.solicitudEliminacion.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class SolicitudEliminacionOutputDTO {
  private Long id;
  private Long id_hecho;
  private String fundamento;
  private Estado estado;
  private LocalDateTime fechaCreacion;
}
