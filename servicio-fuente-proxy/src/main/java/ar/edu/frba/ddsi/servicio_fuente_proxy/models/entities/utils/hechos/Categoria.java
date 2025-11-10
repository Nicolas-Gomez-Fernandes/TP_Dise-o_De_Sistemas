package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Categoria {
    public String nombre;

    public Categoria(String dato) {
        this.nombre = dato;
    }
}
