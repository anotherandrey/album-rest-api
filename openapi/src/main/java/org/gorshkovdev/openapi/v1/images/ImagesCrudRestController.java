package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesCrudRestController implements ImagesCrudRestControllerApi {

  private final ImageResourceService resourceService;

  @Override
  public ResponseEntity<ImageDto> create(String filename, String contentType, Resource body) {
    Image image = resourceService.create(filename, contentType, body);

    ImageDto dto = new ImageDto()
        .id(image.getId())
        .filename(image.getFilename())
        .contentType(image.getContentType())
        .contentLength(image.getContentLength())
        .createdAt(image.getCreatedAt().toString());

    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    resourceService.delete(id);
    return ResponseEntity.ok(null);
  }
}
