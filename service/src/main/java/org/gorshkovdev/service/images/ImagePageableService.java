package org.gorshkovdev.service.images;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface ImagePageableService {

  ImagePageableServiceResponse getImages(int page, int pageSize, String sortBy, String sortDirection);
}
