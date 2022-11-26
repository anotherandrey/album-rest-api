package org.album.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @since 1.0-SNAPSHOT
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
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
