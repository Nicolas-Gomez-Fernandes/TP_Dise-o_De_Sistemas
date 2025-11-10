package ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.utils.criterios;


import ar.edu.frba.ddsi.servicio_fuente_proxy.models.entities.hechos.Hecho;

import java.util.List;
import java.util.stream.Collectors;

public class CriterioDescripcion implements CriterioPertenencia {
        private String palabraClave;

        public CriterioDescripcion(String palabraClave) {
            this.palabraClave = palabraClave.toLowerCase();
        }

        @Override
        public List<Hecho> aplicarCriterio(List<Hecho> hechos) {
            return hechos.stream()
                    .filter(h -> h.getDescripcion().toLowerCase().contains(palabraClave)).collect(Collectors.toList());
        }
}

