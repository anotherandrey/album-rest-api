package org.album.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @since 1.0-SNAPSHOT
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedContentTypeException extends RuntimeException {

  private final String contentType;

  public NotAllowedContentTypeException(String contentType) {
    super();
    this.contentType = contentType;
  }

  @Override
  public String getMessage() {
    return contentType + " is not allowed";
  }
}
