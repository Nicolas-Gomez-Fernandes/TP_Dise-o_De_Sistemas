package ar.edu.frba.ddsi.servicio_fuente_proxy.service;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;

import java.util.List;

public interface IColeccionesService {
  public List<ColeccionOutputDto> getColecciones();
  public List<HechoOutputDTO> getHechosByIdColeccion(String id, FiltroHechoMetamapa filtroHechoMetamapa);
}
