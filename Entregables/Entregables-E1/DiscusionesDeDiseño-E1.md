# Justificaciones de Diseño.

## Caso de Uso
Decidimos representar a los actores Administrador, Contribuyente y Visualizador.

El Administrador es capaz de importar hechos, evaluar solicitudes de eliminación de hechos y crear una colección de hechos.

El Contribuyente puede solicitar la eliminación de un hecho, lo que incluye ingresar al sistema identificándose. Al igual que el Visualizador, puede agregar hechos, teniendo como opcional la identificación del usuario, y navegar hechos, teniendo como opción hacerlo mediante el uso de filtros de búsqueda.

## Diagrama de clases

### FuenteDatos (interface)
Aplicamos el Patrón Strategy para la carga de fuentes de datos ya que hay distintas formas de carga de datos (Dataset: archivos csv, dinámicas, proxy) por lo que resolvemos el mismo problema de distintas maneras dependiendo de la fuente de datos.

Para importar los hechos de los Datasets, específicamente los archivos csv, se utiliza la clase/herramienta Importador CSV.

Modelamos una clase EstaticaCSV la cual implementa la interfaz FuenteDatos, y usa la clase ImportadorCSV.

### ImportadorCSV (interface)
Es una herramienta para importar los hechos desde una fuente estática. Tiene su metodo importarHechosDesdeCSV() que los importa, pasando la ruta absoluta del archivo por parámetro.

### Hecho
Tiene todos los atributos necesarios de un hecho.

Además, para el atributo contribuidor, diagramamos la clase Usuario, ya que este puede ser un visualizador o contribuyente.

Un Hecho puede tener contenido adicional opcional, representado como la interface Contenido.

Ubicación es representada con una clase que contiene la longitud y latitud.

La lista de etiquetas contiene todas las etiquetas del hecho.

### Usuario
Solamente es representado para ubicarlo como contribuidor en el hecho. De el heredan Contribuyente y Visualizador.

El contribuyente va a tener datos personales mientras que el Visualizador no.

### Filtro
Modelamos una Interface Filtro donde las diferentes clases de tipos de filtros que la implementan aplican una función aplicarFiltro la cual retorna un booleano si ese filtro aplicado cumple la condición propuesta, ej:
- Sobre la colección aplicar un filtro de tipo categoría = "Caída de Aeronave" y título ="un título".
- Demostrar que ningún hecho de la colección cumple con este filtro.

### Solicitud de eliminación
Modelamos una clase SolicitudDeEliminacion la cual tiene como atributos el hecho que se quiere eliminar, el fundamento por el cual se quiere eliminar y una lista con el historial de estados por lo que pasó la solicitud (ENUM con los estados ACEPTADA, PENDIENTE, RECHAZADA).

### Estado
Un enum que representa los posibles estados de la solicitud de eliminación.

### Colección
Para esto modelamos a la colección como una lista de Hecho, donde se van a guardar todos los hechos recolectados de cierta fuente elegida. Para esto agregamos el método asociarFuente(), el cual vincula la fuente a la colección, además de un criterio de pertenencia (opcional) que deben respetar los hechos de la colección.

Debido a que el criterio de pertenencia es configurable, agregamos a la colección el método agregarCiterioDePertenencia(), para que se modifique la lista de criterios de la colección, para luego poder utilizar aplicarCriterios() para poder emplearlos en la colección.

### Criterio Pertenencia
Aplicamos el Patrón Strategy para los criterios de pertenencia ya que hay varios (título, fecha. ubicación, categoría, descripción) y cada uno aplica el criterio de distinta forma.

Además utilizamos el mismo patrón de diseño para la implementación del filtro, ya que la lógica de búsqueda de cada campo es diferente.
