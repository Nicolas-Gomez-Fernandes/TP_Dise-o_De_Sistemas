package ar.edu.frba.ddsi.servicio_fuente_proxy.service;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IHechosService {
  public List<HechoOutputDTO> getHechos(FiltroHechoMetamapa filtroHechoMetamapa);

}
