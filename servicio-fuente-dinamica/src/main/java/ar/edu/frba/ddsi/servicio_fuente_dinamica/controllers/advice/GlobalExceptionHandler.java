package ar.edu.frba.ddsi.servicio_fuente_dinamica.controllers.advice;

import ar.edu.frba.ddsi.servicio_fuente_dinamica.exceptions.multimedia.MultimediaNotFoundException;
import ar.edu.frba.ddsi.servicio_fuente_dinamica.exceptions.multimedia.MultimediaOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MultimediaNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleMultimediaNotFound(MultimediaNotFoundException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "No encontrado");
    body.put("mensaje", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(MultimediaOperationException.class)
  public ResponseEntity<Map<String, Object>> handleMultimediaOperation(MultimediaOperationException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "Error en la operacion");
    body.put("mensaje", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
