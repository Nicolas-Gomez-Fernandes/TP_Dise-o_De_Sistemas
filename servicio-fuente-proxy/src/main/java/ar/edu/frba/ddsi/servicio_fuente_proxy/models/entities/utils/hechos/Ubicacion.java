package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos;


import lombok.Getter;

@Getter
public class Ubicacion {
    public Double latitud;
    public Double longitud;


  public Ubicacion(Double longitud, Double latitud) {
    this.longitud = longitud;
    this.latitud = latitud;
  }
}
