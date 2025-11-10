package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.Hecho;

import java.util.List;

public class CriterioUbicacion implements CriterioPertenencia {
    private Double latitud;
    private Double longitud;

    public CriterioUbicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public List<Hecho> aplicarCriterio(List<Hecho> hechos) {
        return hechos.stream().filter(h->h.getUbicacion().latitud.equals(latitud)
                                        && h.getUbicacion().longitud.equals(longitud))
                                        .toList();
    }
}
