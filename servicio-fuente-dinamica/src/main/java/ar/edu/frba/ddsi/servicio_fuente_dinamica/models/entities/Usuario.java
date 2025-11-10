package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.usuario.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nombre", nullable = false)
  private String nombre;
  @Column(name = "apellido", nullable = false)
  private String apellido;
  @Column(name = "fecha_nacimiento", nullable = false, unique = true)
  private LocalDate fechaNacimiento;
  /*@ManyToOne
  @JoinColumn(name="id_rol", nullable = true)
  private Rol rol;*/
}
