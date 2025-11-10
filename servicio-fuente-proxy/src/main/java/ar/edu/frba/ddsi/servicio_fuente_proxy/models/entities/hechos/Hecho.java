package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Categoria;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Ubicacion;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Hecho {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private Ubicacion ubicacion;
  private LocalDate fecha;



  public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion, LocalDate fecha) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
  }
}
