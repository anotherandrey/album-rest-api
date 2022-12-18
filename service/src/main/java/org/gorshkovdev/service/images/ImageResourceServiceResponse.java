package org.gorshkovdev.service.images;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public record ImageResourceServiceResponse<T>(Image image, T body) {

  public String getFilename() {
    return image.getFilename();
  }

  public String getContentType() {
    return image.getContentType();
  }

  public Long getContentLength() {
    return image.getContentLength();
  }

  public String getContentLengthToString() {
    return image.getContentLength().toString();
  }
}
