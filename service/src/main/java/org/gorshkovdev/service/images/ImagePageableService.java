package org.gorshkovdev.service.images;

import java.util.stream.Stream;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface ImagePageableService {

  record PageableResponse(Stream<Image> images, int totalPages) {

  }

  PageableResponse getImages(int page, int pageSize, String sortBy, String sortDirection);
}
