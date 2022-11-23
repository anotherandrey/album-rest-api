package org.album.openapi.v1.album;

import lombok.RequiredArgsConstructor;
import org.album.image.Image;
import org.album.service.AlbumCommonService;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class AlbumCrudRestController implements AlbumCrudRestControllerApi {

  private final AlbumCommonService commonService;

  @Override
  public ResponseEntity<ImageDto> create(String filename, String contentType, Resource body) {
    Image image = commonService.create(filename, contentType, body);

    ImageDto dto = new ImageDto()
        .id(image.getId())
        .filename(filename)
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
    commonService.delete(id);

    return ResponseEntity
        .ok()
        .body(null);
  }
}
