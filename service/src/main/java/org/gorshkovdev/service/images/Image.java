package org.gorshkovdev.service.images;

import java.time.Instant;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface Image {

  Long getId();

  String getFilename();

  String getContentType();

  Long getContentLength();

  Instant getCreatedAt();

  String getCreatedAtToString();
}
