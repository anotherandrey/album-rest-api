package org.gorshkovdev.service.images;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface ImageCrudService {

  Image getImageById(Long id);

  int countImagesByFilename(String filename);

  Image createImage(String filename, String contentType, Long contentLength);

  Image deleteImageById(Long id);
}
