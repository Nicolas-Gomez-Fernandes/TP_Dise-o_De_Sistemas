package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Rol {
  private Long id;

  private String nombre;

  private List<Permiso> permisos;

  public boolean tenesPermiso (Permiso permiso) {
    return this.permisos.contains(permiso);
  }
}
