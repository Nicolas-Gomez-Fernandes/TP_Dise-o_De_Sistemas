package ar.edu.frba.ddsi.servicio_fuente_proxy.clients;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input.SolicitudEliminacionInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.ColeccionOutputDto;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.FiltroHechoMetamapa;

import java.util.List;

public interface IAdapterAPIMetamapa {
  List<HechoOutputDTO> getHechos(FiltroHechoMetamapa filtro);
  List<ColeccionOutputDto> getColecciones();
  List<HechoOutputDTO> getHechosDeColeccion(String id, FiltroHechoMetamapa filtro);
  void crearSolicitudDeEliminacion(SolicitudEliminacionInputDTO solicitud);
}
