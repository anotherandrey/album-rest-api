package org.gorshkovdev.service.exception;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public class ResourceNotExistsException extends RuntimeException {

  private final String filename;

  public ResourceNotExistsException(String filename) {
    super();
    this.filename = filename;
  }

  @Override
  public String getMessage() {
    return "resource with filename " + filename + ", not exists";
  }
}
