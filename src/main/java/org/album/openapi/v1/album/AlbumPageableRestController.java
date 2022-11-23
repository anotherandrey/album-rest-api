package org.album.openapi.v1.album;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.album.service.AlbumPageableService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class AlbumPageableRestController implements AlbumPageableRestControllerApi {

  private final AlbumPageableService pageableService;

  @Override
  public ResponseEntity<ImageDtoArray> getImages(Integer page, Integer pageSize, String sortBy, String sortDirection) {
    List<ImageDto> items = pageableService.getImages(page, pageSize, sortBy, sortDirection)
        .map(image -> {
          return new ImageDto()
              .id(image.getId())
              .filename(image.getFilename())
              .contentType(image.getContentType())
              .contentLength(image.getContentLength())
              .createdAt(image.getCreatedAt().toString());
        })
        .toList();

    ImageDtoArray dto = new ImageDtoArray();
    dto.items(items);

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .body(dto);
  }
}
