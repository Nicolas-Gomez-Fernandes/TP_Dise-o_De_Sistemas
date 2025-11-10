package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.MetamapaAPI;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class ColeccionResponseDTO{
    private List<ColeccionOutputDto> colecciones;
}
