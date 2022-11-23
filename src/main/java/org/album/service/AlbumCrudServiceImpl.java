package org.album.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.album.exception.ImageNotFoundException;
import org.album.image.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AlbumCrudServiceImpl implements AlbumCrudService {

  private final ImageRepository repository;

  @Transactional(readOnly = true)
  @Override
  public Image getImage(long id) {
    return repository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
  }

  @Transactional
  @Override
  public Image createImage(String filename, String contentType, long contentLength) {
    Image image = Image.builder()
        .filename(filename)
        .contentType(contentType)
        .contentLength(contentLength)
        .build();

    log.info("create image {}", image);
    return repository.save(image);
  }

  @Transactional
  @Override
  public void deleteImage(Image image) {
    log.info("delete image {}", image);
    repository.delete(image);
  }
}
