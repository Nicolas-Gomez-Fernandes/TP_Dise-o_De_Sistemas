package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FiltroHechoMetamapa {
  private String categoria;
  private LocalDateTime fecha_reporte_desde;
  private LocalDateTime fecha_reporte_hasta;
  private LocalDateTime fecha_acontecimiento_desde;
  private LocalDateTime fecha_acontecimiento_hasta;
  private Ubicacion ubicacion;


}
