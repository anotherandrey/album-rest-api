package org.gorshkovdev.openapi;

import static org.springframework.http.ProblemDetail.forStatusAndDetail;

import org.gorshkovdev.service.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RestControllerAdvice
public class OpenApiRestControllerAdvice {

  @ExceptionHandler(BadContentTypeException.class)
  public ProblemDetail handleBadContentTypeException(BadContentTypeException e) {
    return forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(ImageNotFoundException.class)
  public ProblemDetail handleImageNotFoundException(ImageNotFoundException e) {
    return forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(ResourceNotExistsException.class)
  public ProblemDetail handleResourceNotExistsException(ResourceNotExistsException e) {
    return forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  public ProblemDetail handleRuntimeException(RuntimeException e) {
    return forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
  }
}
