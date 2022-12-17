package org.gorshkovdev.service.exception;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public class BadContentTypeException extends RuntimeException {

  private final String contentType;

  public BadContentTypeException(String contentType) {
    super();
    this.contentType = contentType;
  }

  @Override
  public String getMessage() {
    return "bad content type " + contentType;
  }
}
