package org.gorshkovdev.db.images;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gorshkovdev.service.exception.ImageNotFoundException;
import org.gorshkovdev.service.images.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ImageEntityCrudService implements ImageCrudService {

  private final ImageEntityRepository repository;

  @Transactional(readOnly = true)
  @Override
  public int countImagesByFilename(String filename) {
    return repository.countByFilename(filename);
  }

  @Transactional(readOnly = true)
  @Override
  public Image getImageById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
  }

  @Transactional
  @Override
  public Image createImage(String filename, String contentType, Long contentLength) {
    ImageEntity entity = new ImageEntity();
    entity.setFilename(filename);
    entity.setContentType(contentType);
    entity.setContentLength(contentLength);

    log.info("create image entity {}", entity);
    return repository.save(entity);
  }

  @Transactional
  @Override
  public Image deleteImageById(Long id) {
    ImageEntity entity = repository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));

    log.info("delete image entity {}", entity);
    repository.delete(entity);

    return entity;
  }
}
