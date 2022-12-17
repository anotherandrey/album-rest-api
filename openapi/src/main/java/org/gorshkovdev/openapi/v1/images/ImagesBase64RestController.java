package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.ImageContentService;
import org.gorshkovdev.service.images.ImageContentService.ContentResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesBase64RestController implements ImagesBase64RestControllerApi {

  private final ImageContentService contentService;

  @Override
  public ResponseEntity<String> getBase64(Long id) {
    ContentResponse<String> response = contentService.getBase64(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.image().getContentType())
        // .header(HttpHeaders.CONTENT_LENGTH, response.image().getContentLength().toString())
        .body(response.content());
  }
}
