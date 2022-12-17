package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.ImageContentService;
import org.gorshkovdev.service.images.ImageContentService.ContentResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesResourceRestController implements ImagesResourceRestControllerApi {

  private final ImageContentService contentService;

  @Override
  public ResponseEntity<Resource> getResource(Long id) {
    ContentResponse<Resource> response = contentService.getResource(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.image().getContentType())
        .header(HttpHeaders.CONTENT_LENGTH, response.image().getContentLength().toString())
        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", response.image().getFilename()))
        .body(response.content());
  }
}
