package ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Multimedia;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.hechos.Ubicacion;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.revision.EstadoRevision;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.models.entities.utils.revision.TipoContribucion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hechos")
public class Hecho {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column (name = "titulo", nullable = false)
  private String titulo;
  @Column (name = "descripcion", nullable = false)
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private Categoria categoria ;

  @Embedded
  private Ubicacion ubicacion;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "fecha_acontencimiento", nullable = false)
  private LocalDate fechaAcontencimiento;

  /*@Column(name = "fecha_ultima_edicion", nullable = true)
  private LocalDate fechaUltimaEdicion;*/

  @OneToMany(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "hecho_id")
  private List<Multimedia> archivosMultimedia;

  @ManyToOne
  @JoinColumn(name = "id_contribuyente")
  private Usuario contribuyente; // null si es anónimo

  @ElementCollection
  @CollectionTable(name = "etiquetas", joinColumns = @JoinColumn(name = "hecho_id", referencedColumnName = "id"))
  private List<String> etiquetas; //Por ahora lo pense asi, y cuando se aplica una etiqueta se la agrega a esa lista, para despues poder filtar por las etiquetas como si fuera #

  @Column(name = "eliminado", nullable = false)
  private Boolean eliminado;



  private TipoContribucion tipoContribucion; // TODO fletar
  private EstadoRevision estadoRevision;
  private String comentariosRevision;


  //TODO- diagramar contenido
  //private String contenido;



  public Hecho(String titulo,
               String descripcion,
               Categoria categoria,
               LocalDate fechaAcontencimiento,
               Usuario contribuyente) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.fechaCreacion = LocalDateTime.now();
    this.fechaAcontencimiento = fechaAcontencimiento;
    this.contribuyente = contribuyente;
    this.eliminado = false;
    this.archivosMultimedia = new ArrayList<>();
    this.etiquetas = new ArrayList<>();
  }

  public boolean puedeSerEditado() {
    if (tipoContribucion == TipoContribucion.ANONIMA) {
      return false;
    }
    return ChronoUnit.DAYS.between(fechaCreacion, LocalDateTime.now()) <= 7;
  }

  public void editar(String nuevoTitulo, String nuevaDescripcion) {
    if (!puedeSerEditado()) {
      throw new IllegalStateException("Este hecho no puede ser editado");
    }
    this.titulo = nuevoTitulo;
    this.descripcion = nuevaDescripcion;
    //this.fechaUltimaEdicion = LocalDate.now();
  //TODO: AGREGAR EL RESTO DE LOS ATRIBUTOS.
  }
}