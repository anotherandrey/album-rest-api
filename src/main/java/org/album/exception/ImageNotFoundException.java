package org.album.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
