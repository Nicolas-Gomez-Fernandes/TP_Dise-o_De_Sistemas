package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.Hecho;

import java.util.List;

public interface CriterioPertenencia {

    List<Hecho> aplicarCriterio(List<Hecho> hechos);
}
