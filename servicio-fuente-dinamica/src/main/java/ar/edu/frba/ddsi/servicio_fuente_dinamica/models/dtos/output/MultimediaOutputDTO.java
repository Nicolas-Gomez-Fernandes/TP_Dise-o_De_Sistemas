package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MultimediaOutputDTO {
  private Long id;
  private String rutaArchivo;
  private String tipoArchivo;

  public MultimediaOutputDTO(String rutaArchivo, String tipoArchivo) {
    this.rutaArchivo = rutaArchivo;
    this.tipoArchivo = tipoArchivo;
  }

}
