package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input;

import lombok.Setter;

@Setter
public class UsuarioInputDTO {
  private String usuario;
  private String contrasenia;
  private String email;
}
