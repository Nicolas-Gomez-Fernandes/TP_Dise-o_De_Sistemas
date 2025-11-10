package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.Hecho;
import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.hechos.Categoria;

import java.util.List;

public class CriterioCategoria implements CriterioPertenencia{
    Categoria categoria;

    public CriterioCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public List<Hecho> aplicarCriterio(List<Hecho> hechos) {
        return hechos.stream()
                .filter(h -> h.getCategoria() == categoria).toList();
    }
}
