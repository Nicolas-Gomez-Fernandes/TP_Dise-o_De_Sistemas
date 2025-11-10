package ar.edu.frba.ddsi.servicio_fuente_dinamica.services.impl;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.Usuario;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.repositories.IUsuarioRepository;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.services.IUsuarioService;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.output.UsuarioOutputDTO;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.dtos.input.UsuarioInputDTO;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
  private IUsuarioRepository usuarioRepository;

  UsuarioService(IUsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public boolean esAdmin(Long id) {
    Usuario usuario = this.usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    /*if (usuario != null) {
      return usuario.esAdmin(id);
    }*/
    return false;
  }

  @Override
  public boolean validarCredenciales(String usuario, String contrasenia) {
    // TODO -- return this.usuarioRepository.validarCredenciales(usuario, contrasenia);
    return false;
  }

  @Override
  public UsuarioOutputDTO buscarUsuarioPorUsername(String username) {
    // TODO return this.usuarioRepository.buscarUsuarioPorUsername(username);
    return null;
  }

  @Override
  public UsuarioOutputDTO buscarUsuarioPorEmail(String email) {
    // TODO return this.usuarioRepository.buscarUsuarioPorEmail(email);
    return null;
  }

  @Override
  public UsuarioOutputDTO buscarUsuarioPorId(Long id) {
    // TODO return this.usuarioRepository.buscarUsuarioPorId(id);
    return null;
  }

  @Override
  public  UsuarioOutputDTO buscarTodos() {
    // TODO: Implementar mapeo de List<Usuario> a UsuarioOutputDTO
    // List<Usuario> usuarios = this.usuarioRepository.findAll();
    return null;
  }

  @Override
  public UsuarioOutputDTO crearUsuario(UsuarioInputDTO usuarioInputDTO) {
    // TODO return this.usuarioRepository.crearUsuario(usuarioInputDTO);
    return null;
  }

  @Override
  public void eliminarUsuario(Long id) {

  }

}
