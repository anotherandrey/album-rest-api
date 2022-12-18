package org.gorshkovdev.service.images;

import org.springframework.core.io.Resource;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public interface ImageResourceService {

  ImageResourceServiceResponse<Resource> getResource(long id);

  ImageResourceServiceResponse<String> getBase64(long id);

  Image create(String filename, String contentType, Resource body);

  void delete(long id);
}
