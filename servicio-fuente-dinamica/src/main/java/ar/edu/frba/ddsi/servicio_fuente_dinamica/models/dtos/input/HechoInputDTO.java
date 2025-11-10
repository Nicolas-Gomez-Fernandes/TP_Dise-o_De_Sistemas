package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class HechoInputDTO {
  @NonNull
  private String titulo;
  @NonNull
  private String descripcion;
  @NonNull
  private Long id_categoria;
  @NonNull
  private Double latitud;
  @NonNull
  private Double longitud;
  private List<Long> archivos_multimedia;
  @NonNull
  private LocalDate fecha_acontecimiento;
  private Long id_contribuyente; // null si es anónimo
  private List<String> etiquetas;
}