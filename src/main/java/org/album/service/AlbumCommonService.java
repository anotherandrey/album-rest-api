package org.album.service;

import org.album.image.Image;
import org.springframework.core.io.Resource;

/**
 * @since 1.0-SNAPSHOT
 */
public interface AlbumCommonService {

  record Response<T>(Image image, T body) {

  }

  Response<Resource> getResource(long id);

  Response<String> getBase64(long id);

  Image create(String filename, String contentType, Resource body);

  void delete(long id);
}
