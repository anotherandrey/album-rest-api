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
public class ImagesResourceRestController implements ImagesResourceRestControllerApi {

  private final ImageResourceService resourceService;

  @Override
  public ResponseEntity<Resource> getResource(Long id) {
    ImageResourceServiceResponse<Resource> response = resourceService.getResource(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.getContentType())
        .header(HttpHeaders.CONTENT_LENGTH, response.getContentLengthToString())
        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", response.getFilename()))
        .body(response.body());
  }
}
