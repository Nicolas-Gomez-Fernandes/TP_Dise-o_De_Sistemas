package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HechoAPICatedraResponseDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private Double latitud;
  private Double longitud;
  private LocalDateTime fecha_hecho;
}
