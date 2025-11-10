package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.Hecho;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CriterioFecha implements CriterioPertenencia {
    private LocalDate desde;
    private LocalDate hasta;

    public CriterioFecha(LocalDate desde, LocalDate hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public List<Hecho> aplicarCriterio(List<Hecho> hechos) {
        return hechos.stream().filter(h->h.getFecha().isAfter(desde)
                                            && h.getFecha().isBefore(hasta))
                                            .collect(Collectors.toList());
    }
}
