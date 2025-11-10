package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class HechoOutputDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private Double latitud;
  private Double longitud;
  private LocalDate fecha_hecho;
  private LocalDateTime fechaCreacion;
  private List<MultimediaOutputDTO> multimedia;
  private List<String> etiquetas;
  private OrigenFuenteDTO origen;
}
