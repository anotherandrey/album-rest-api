package org.gorshkovdev.db.images;

import static org.springframework.data.domain.PageRequest.of;

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
  public ImagePageableServiceResponse getImages(int page, int pageSize, String sortBy, String sortDirectionString) {
    Page<ImageEntity> all = repository.findAll(of(page, pageSize, getSort(sortBy, sortDirectionString)));
    return new ImagePageableServiceResponse(all.getTotalPages(), all.stream().map(Image.class::cast));
  }

  private Sort getSort(String sortBy, String sortDirectionString) {
    Sort.Direction direction = Sort.Direction.fromString(sortDirectionString);
    return Sort.by(direction, sortBy);
  }
}
