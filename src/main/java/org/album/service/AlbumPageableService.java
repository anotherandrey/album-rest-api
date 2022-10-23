package org.album.service;

import jakarta.validation.constraints.*;
import java.util.stream.Stream;
import org.album.image.Image;
import org.album.service.validation.SortDirectionParameter;
import org.springframework.validation.annotation.Validated;

/**
 * @since 1.0-SNAPSHOT
 */
@Validated
public interface AlbumPageableService {

  Stream<Image> getImages(
      @Min(0) @Max(999) int page, @Min(0) @Max(999) int pageSize, String sortBy, @SortDirectionParameter String sortDirection
  );
}
