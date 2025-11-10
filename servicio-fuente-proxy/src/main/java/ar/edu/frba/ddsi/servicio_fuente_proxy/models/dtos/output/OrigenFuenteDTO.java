package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrigenFuenteDTO {
    private String fuente;
    private String tipoFuente;
}
