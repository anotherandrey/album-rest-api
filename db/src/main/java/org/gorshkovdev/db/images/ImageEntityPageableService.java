package org.gorshkovdev.db.images;

import static org.springframework.data.domain.PageRequest.of;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.gorshkovdev.service.images.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@RequiredArgsConstructor
@Service
public class ImageEntityPageableService implements ImagePageableService {

  private final ImageEntityRepository repository;

  @Override
  public PageableResponse getImages(int page, int pageSize, String sortBy, String sortDirectionString) {
    Page<ImageEntity> all = repository.findAll(of(page, pageSize, getSort(sortBy, sortDirectionString)));

    Stream<Image> images = all.stream().map(Image.class::cast);
    return new PageableResponse(images, all.getTotalPages());
  }

  private Sort getSort(String sortBy, String sortDirectionString) {
    Sort.Direction direction = Sort.Direction.fromString(sortDirectionString);
    return Sort.by(direction, sortBy);
  }
}
