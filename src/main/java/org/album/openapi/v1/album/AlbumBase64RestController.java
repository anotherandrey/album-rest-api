package org.album.openapi.v1.album;

import lombok.RequiredArgsConstructor;
import org.album.service.AlbumCommonService;
import org.album.service.AlbumCommonService.Response;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class AlbumBase64RestController implements AlbumBase64RestControllerApi {

  private final AlbumCommonService commonService;

  @Override
  public ResponseEntity<String> getBase64(Long id) {
    Response<String> response = commonService.getBase64(id);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, response.image().getContentType())
        .header(HttpHeaders.CONTENT_LENGTH, response.image().getContentLength().toString())
        .body(response.body());
  }
}
