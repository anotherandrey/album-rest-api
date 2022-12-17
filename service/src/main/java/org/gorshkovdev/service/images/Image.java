package org.gorshkovdev.service.images;

import java.time.Instant;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface Image {

  String FILENAME_REGEXP = "^[A-Za-z]{42}\\.[A-Za-z]{42}$";

  Long getId();

  String getFilename();

  String getContentType();

  Long getContentLength();

  Instant getCreatedAt();
}
