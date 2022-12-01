package org.album.service;

import org.album.image.Image;

/**
 * @since 1.0-SNAPSHOT
 */
public interface AlbumCrudService {

  Image getImageById(long id);

  int countImagesByFilename(String filename);

  Image createImage(String filename, String contentType, long contentLength);

  Image deleteImageById(long id);
}
