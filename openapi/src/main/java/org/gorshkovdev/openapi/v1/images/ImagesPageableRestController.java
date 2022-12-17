package org.gorshkovdev.openapi.v1.images;

import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.gorshkovdev.service.images.ImagePageableService.PageableResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@RestController
public class ImagesPageableRestController implements ImagesPageableRestControllerApi {

  private final ImagePageableService pageableService;

  @Override
  public ResponseEntity<PageableImageDto> getImages(Integer page, Integer pageSize, String sortBy, String sortDirectionString) {
    PageableResponse response = pageableService.getImages(page, pageSize, sortBy, sortDirectionString);

    PageableImageDto dto = new PageableImageDto();
    dto.totalPages(response.totalPages());
    dto.images(response.images().map(this::mapDto).toList());

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
