package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesBase64RestController implements ImagesBase64RestControllerApi {

  private final ImageResourceService resourceService;

  @Override
  public ResponseEntity<String> getBase64(Long id) {
    ImageResourceServiceResponse<String> response = resourceService.getBase64(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.getContentType())
        // .header(HttpHeaders.CONTENT_LENGTH, response.getContentLengthToString())
        .body(response.body());
  }
}
