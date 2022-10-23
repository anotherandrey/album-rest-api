package org.album.service;

import org.album.image.Image;

/**
 * @since 1.0-SNAPSHOT
 */
public interface AlbumCrudService {

  Image getImage(long id);

  Image createImage(String filename, String contentType, long contentLength);

  void deleteImage(Image image);
}
