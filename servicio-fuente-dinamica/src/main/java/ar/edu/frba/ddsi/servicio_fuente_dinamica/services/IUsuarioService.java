package ar.edu.frba.ddsi.servicio_fuente_dinamica.services;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.UsuarioInputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.UsuarioOutputDTO;

public interface IUsuarioService {
  boolean validarCredenciales(String usuario, String contrasenia);
  UsuarioOutputDTO buscarUsuarioPorUsername(String username);
  UsuarioOutputDTO buscarUsuarioPorEmail(String email);
  UsuarioOutputDTO buscarUsuarioPorId(Long id);
  UsuarioOutputDTO buscarTodos();
  UsuarioOutputDTO crearUsuario(UsuarioInputDTO usuarioInputDTO);
  void eliminarUsuario(Long id);
  boolean esAdmin(Long id);

}
