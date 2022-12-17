package org.gorshkovdev.service.exception;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public class ImageNotFoundException extends RuntimeException {

  private final Long id;

  public ImageNotFoundException(Long id) {
    super();
    this.id = id;
  }

  @Override
  public String getMessage() {
    return "image with id " + id + ", not found";
  }
}
