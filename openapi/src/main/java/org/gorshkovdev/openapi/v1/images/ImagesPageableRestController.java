package org.gorshkovdev.openapi.v1.images;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.springframework.http.ResponseEntity;
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
    ImagePageableServiceResponse response = pageableService.getImages(page, pageSize, sortBy, sortDirectionString);

    int totalPages = response.totalPages();

    List<ImageDto> images = response.images()
        .map(this::mapImageDto)
        .toList();

    PageableImageDto dto = new PageableImageDto().totalPages(totalPages).images(images);
    return ResponseEntity.ok(dto);
  }

  private ImageDto mapImageDto(Image image) {
    return new ImageDto()
        .id(image.getId())
        .filename(image.getFilename())
        .contentType(image.getContentType())
        .contentLength(image.getContentLength())
        .createdAt(image.getCreatedAtToString());
  }
}
