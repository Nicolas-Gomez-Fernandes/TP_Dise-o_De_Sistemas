package ar.edu.frba.ddsi.servicio_fuente_dinamica.exceptions.multimedia;

public class MultimediaException extends RuntimeException {
  public MultimediaException(String message) {
    super(message);
  }

  public MultimediaException(String message, Throwable cause) {
    super(message, cause);
  }
}
