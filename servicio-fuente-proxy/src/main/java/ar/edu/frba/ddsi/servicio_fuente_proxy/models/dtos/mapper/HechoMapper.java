package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.mapper;

import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.HechoOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.external.CatedraAPI.HechoAPICatedraResponseDTO;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.output.OrigenFuenteDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper
public interface HechoMapper {

  HechoMapper INSTANCE = Mappers.getMapper(HechoMapper.class);

  @Mapping(target = "id", source = "id")
  @Mapping(source = "titulo", target = "titulo")
  @Mapping(source = "descripcion", target = "descripcion")
  @Mapping(source = "categoria", target = "categoria")
  @Mapping(source = "latitud", target = "latitud")
  @Mapping(source = "longitud", target = "longitud")
  @Mapping(source = "fecha_hecho", target = "fecha_hecho")
  // Fuerza eliminado=false para Cátedra
  @Mapping(target = "eliminado", constant = "false")
  HechoOutputDTO hechoAPICatedraResponseDTOToHechoOutputDTO(HechoAPICatedraResponseDTO hecho);

  @AfterMapping
  public default void setOrigen(@MappingTarget HechoOutputDTO dto) {
    dto.setOrigen(new OrigenFuenteDTO("API_CATEDRA", "PROXY"));
  }
}
