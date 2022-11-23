package org.album.openapi.v1.album;

import lombok.RequiredArgsConstructor;
import org.album.service.AlbumCommonService;
import org.album.service.AlbumCommonService.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class AlbumResourceRestController implements AlbumResourceRestControllerApi {

  private final AlbumCommonService commonService;

  @Override
  public ResponseEntity<Resource> getResource(Long id) {
    Response<Resource> response = commonService.getResource(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.image().getContentType())
        .header(HttpHeaders.CONTENT_LENGTH, response.image().getContentLength().toString())
        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", response.image().getFilename()))
        .body(response.body());
  }
}
