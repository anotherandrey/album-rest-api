package org.album.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @since 1.0-SNAPSHOT
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSupportedContentTypeException extends RuntimeException {

  private final String contentType;

  public NotSupportedContentTypeException(String contentType) {
    super();
    this.contentType = contentType;
  }

  @Override
  public String getMessage() {
    return "content type " + contentType + ", not supported";
  }
}
