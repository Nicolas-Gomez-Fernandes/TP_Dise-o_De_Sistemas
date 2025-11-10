# Justificaciones

## Fuente Dinámica

El diseño implementa una clara separación de responsabilidades mediante el uso de interfaces de repositorio (`IHechosRepository` e `IColeccionesRepository`) que gestionan la persistencia de los datos. Estas interfaces proporcionan operaciones básicas como búsqueda, guardado y eliminación, tanto para hechos individuales como para colecciones.

Para manejar las diferentes formas de ingreso de datos, se ha implementado el patrón **Strategy** a través de la interfaz `FuenteDatos`.

El sistema está preparado para manejar contenido multimedia, permitiendo a los usuarios subir tanto información textual como archivos multimedia. Además, implementa un sistema de control temporal para las ediciones, permitiendo modificaciones solo durante la primera semana después de la contribución, y únicamente para usuarios registrados.

Llamamos **Usuario** a aquella entidad que contiene los datos de acceso para que una persona física u otro sistema pueda identificarse en nuestro sistema al usarlo.  
Una misma persona física o sistema podría cumplir varios roles dentro de nuestro sistema y ejecutar diferentes casos de uso.  
Distintos roles podrían tener la facultad de ejecutar el mismo caso de uso: cada rol podría ejecutar muchos casos de uso; para ejecutar un caso de uso se necesita tener uno o varios permisos; un mismo permiso podría ser adjudicado a varios roles distintos.

---

## Fuente Proxy

En el diseño se hizo un `service` por cada API para consumir, donde cada una tiene su `client` y los endpoints de consumo. A futuro, la idea es adaptar estos `service` para que sean extensibles si se quisiera agregar más APIs de las cuales consumir hechos.

Se generaron **DTO Response** para la entrada de datos sobre los hechos desde las diferentes APIs. Para evitar conflictos, se diseñó un **Mapper** donde se mapea cada atributo necesario en un DTO de salida.  
Para esto se importó **MapStruct**, que nos da la posibilidad de generar un Mapper con facilidad de modificación.

---

## Servicio Agregación

Se implementó el patrón **Adapter** para las distintas APIs de los servicios de las fuentes (Proxy, Dinámica y Estática).

Además de esto, se implementó el mismo uso de las **DTO Response** de Proxy, desarrollando también un **Mapper** que nos permitiera más flexibilidad y facilidad para modificar.
