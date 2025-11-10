package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios.CriterioPertenencia;
import lombok.Data;

import java.util.List;

@Data
public class ColeccionOutputDto {
    private String nombre;
    private String descripcion;
    private List<HechoOutputDTO> hechos;
    private List<CriterioPertenencia> criterios;
    private String handle;
}
