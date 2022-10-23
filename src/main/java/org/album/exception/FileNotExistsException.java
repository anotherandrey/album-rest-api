package org.album.exception;

import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @since 1.0-SNAPSHOT
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotExistsException extends RuntimeException {

  private final File file;

  public FileNotExistsException(File file) {
    super();
    this.file = file;
  }

  @Override
  public String getMessage() {
    String absolutePath = file.getAbsolutePath();
    return absolutePath + " is not exists";
  }
}
