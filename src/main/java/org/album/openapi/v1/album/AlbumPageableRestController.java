package org.album.openapi.v1.album;

import lombok.RequiredArgsConstructor;
import org.album.image.Image;
import org.album.service.AlbumPageableService;
import org.album.service.AlbumPageableService.Response;
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
    Response response = pageableService.getItems(page, pageSize, sortBy, sortDirection);

    ImageDtoArray dto = new ImageDtoArray();
    dto.totalPages(response.totalPages());
    dto.items(response.items().map(this::mapDto).toList());

    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .body(dto);
  }

  private ImageDto mapDto(Image image) {
    ImageDto dto = new ImageDto()
        .id(image.getId())
        .filename(image.getFilename())
        .contentType(image.getContentType())
        .contentLength(image.getContentLength());

    String createdAt = image.getCreatedAt().toString();
    dto.createdAt(createdAt);

    return dto;
  }
}
