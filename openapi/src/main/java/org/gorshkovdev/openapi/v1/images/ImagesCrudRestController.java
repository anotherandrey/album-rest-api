package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesCrudRestController implements ImagesCrudRestControllerApi {

  private final ImageContentService contentService;

  @Override
  public ResponseEntity<ImageDto> create(String filename, String contentType, Resource body) {
    Image image = contentService.create(filename, contentType, body);

    ImageDto dto = new ImageDto()
        .id(image.getId())
        .filename(image.getFilename())
        .contentType(image.getContentType())
        .contentLength(image.getContentLength())
        .createdAt(image.getCreatedAt().toString());

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .body(dto);
  }

  @Override
  public ResponseEntity<Void> delete(Long id) {
    contentService.delete(id);

    return ResponseEntity
        .ok()
        .body(null);
  }
}
