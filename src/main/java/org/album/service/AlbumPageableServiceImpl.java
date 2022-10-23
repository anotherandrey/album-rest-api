package org.album.service;

import jakarta.validation.constraints.*;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.album.service.validation.SortDirectionParameter;
import org.album.image.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @since 1.0-SNAPSHOT
 */
@AllArgsConstructor
@Service
@Validated
public class AlbumPageableServiceImpl implements AlbumPageableService {

  private final ImageRepository repository;

  @Override
  public Stream<Image> getImages(
      @Min(0) @Max(999) int page, @Min(0) @Max(999) int pageSize, String sortBy, @SortDirectionParameter String sortDirectionString
  ) {
    PageRequest request = PageRequest.of(
        page, pageSize, Sort.by(Sort.Direction.fromString(sortDirectionString), sortBy)
    );

    Page<Image> all = repository.findAll(request);
    return all.stream();
  }
}
