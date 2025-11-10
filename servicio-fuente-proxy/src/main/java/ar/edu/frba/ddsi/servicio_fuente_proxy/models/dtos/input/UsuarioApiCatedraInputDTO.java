package ar.edu.frba.ddsi.servicio_fuente_proxy.models.dtos.input;

import lombok.Data;

@Data
public class UsuarioApiCatedraInputDTO {
  private String email;
  private String password;
}
