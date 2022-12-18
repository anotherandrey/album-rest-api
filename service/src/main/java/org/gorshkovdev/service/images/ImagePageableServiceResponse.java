package org.gorshkovdev.service.images;

import java.util.stream.Stream;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
public record ImagePageableServiceResponse(int totalPages, Stream<Image> images) {

}
