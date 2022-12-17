package org.gorshkovdev.service.images;

import jakarta.validation.constraints.PositiveOrZero;
import java.util.stream.Stream;
import org.gorshkovdev.service.validation.*;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface ImagePageableService {

  record PageableResponse(Stream<Image> images, int totalPages) {

  }

  PageableResponse getImages(@PositiveOrZero int page, @PositiveOrZero int pageSize,
                             @SortByParam(sortByProperty = "createdAt") String sortBy,
                             @SortDirectionParam String sortDirection);
}
